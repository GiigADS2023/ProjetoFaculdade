<template>
  <div>
    <div class="title">
      <h4>Reservas</h4>
      <h6>Condomínio >> Reservas >> Alterar</h6>
    </div>

    <div class="container">
      <div class="header">
        <i class="bx bx-bell icon"><span>Reservas</span></i>
        <button @click="openModal(null)" id="new">Criar Reservas</button>
      </div>

      <div class="table">
        <table>
          <thead>
            <tr>
              <th>Ambiente</th>
              <th>Data</th>
              <th>Descrição</th>
              <th class="action">Editar</th>
              <th class="action">Excluir</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(reserve, index) in reserves" :key="index">
              <td>{{ reserve.espaco }}</td>
              <td>{{ reserve.data }}</td>
              <td>{{ reserve.comentario }}</td>
              <td class="action"><button @click="openModal(reserve)"><i class='bx bx-edit'></i></button></td>
              <td class="action"><button @click="deleteItem(reserve.id)"><i class='bx bx-trash'></i></button></td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="modal-container">
        <div class="modal">
          <form @submit.prevent="saveItem">
            <label for="m-espaco">Ambiente</label>
            <select id="m-espaco" v-model="espaco" required class="styled-select">
              <option value="" disabled>Selecione o ambiente</option>
              <option value="Salão A">Salão A</option>
              <option value="Salão B">Salão B</option>
            </select>
            <label for="m-date">Data</label>
            <input id="m-date" v-model="data" type="date" required/>
            <label for="m-description">Descrição</label>
            <input id="m-description" v-model="comentario" type="text" required/>
            <button type="submit">{{ isEditing ? 'Atualizar' : 'Salvar' }}</button>
          </form>
        </div>
      </div>
    </div>

    <!-- Alerta -->
    <div v-if="showAlert" class="alert">
      <p>{{ alertMessage }}</p>
      <button @click="closeAlert">Fechar</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      reserves: [],
      espaco: '',
      data: '',
      comentario: '',
      isEditing: false,
      editId: null,
      today: new Date().toISOString().split('T')[0],
      showAlert: false,
      alertMessage: ''
    };
  },
  methods: {
    getUserId() {
      return localStorage.getItem('userId');
    },
    openModal(reserve) {
      if (reserve) {
        this.isEditing = true;
        this.espaco = reserve.espaco;
        this.comentario = reserve.comentario;
        this.data = reserve.data.split('T')[0]; // Ajuste para remover a hora
        this.editId = reserve.id;
      } else {
        this.isEditing = false;
        this.editId = null;
        this.resetForm();
      }
      const modal = document.querySelector('.modal-container');
      modal.classList.add('active');
      modal.addEventListener('click', this.closeModalOutside);
    },
    closeModalOutside(event) {
      const modal = document.querySelector('.modal-container');
      if (!event.target.closest('.modal')) {
        modal.classList.remove('active');
        modal.removeEventListener('click', this.closeModalOutside);
      }
    },
    async saveItem() {
      if (!this.espaco || !this.comentario || !this.data) {
        alert('Por favor, preencha todos os campos.');
        return;
      }

      // Verificar se a data é anterior ao dia atual
      const today = new Date().toISOString().split('T')[0];
      if (this.data < today) {
        alert('A data da reserva não pode ser anterior ao dia de hoje.');
        return;
      }

      const reserveData = {
        espaco: this.espaco,
        comentario: this.comentario,
        data: this.data,
      };

      if (this.isEditing) {
        this.updateItem(reserveData);
      } else {
        await this.checkAvailabilityAndCreate(reserveData);
      }
    },
    async checkAvailabilityAndCreate(reserveData) {
      try {
        const response = await axios.get('http://localhost:8080/reserva');
        const existingReservations = response.data;

        const isReserved = existingReservations.some(reserve => 
          reserve.data === reserveData.data && reserve.espaco === reserveData.espaco
        );

        if (isReserved) {
          const otherSpace = this.findAvailableSpace(existingReservations, reserveData.data);
          if (otherSpace) {
            this.alertMessage = `O espaço selecionado já está reservado. Você pode reservar o ${otherSpace}.`;
          } else {
            this.alertMessage = 'Todos os espaços estão reservados para esta data. Por favor, escolha outro dia.';
          }
          this.showAlert = true;
        } else {
          this.createItem(reserveData);
        }
      } catch (error) {
        console.error('Erro ao verificar disponibilidade:', error);
      }
    },
    findAvailableSpace(existingReservations, date) {
      const allSpaces = ['Salão A', 'Salão B']; // Lista de todos os espaços
      for (let space of allSpaces) {
        const isSpaceAvailable = !existingReservations.some(reserve => 
          reserve.data === date && reserve.espaco === space
        );
        if (isSpaceAvailable) {
          return space;
        }
      }
      return null; // Todos os espaços estão reservados
    },
    createItem(reserveData) {
      const userId = this.getUserId();
      if (!userId) {
        console.error('Usuário não está autenticado.');
        return;
      }

      axios.post(`http://localhost:8080/newreserva/${userId}`, reserveData)
        .then(response => {
          this.reserves.push(response.data);
          this.resetForm();
          this.closeModal();
        })
        .catch(error => {
          console.error('Erro ao enviar dados:', error);
        });
    },
    updateItem(reserveData) {
      axios.put(`http://localhost:8080/putreserva/${this.editId}`, reserveData)
        .then(response => {
          const index = this.reserves.findIndex(item => item.id === this.editId);
          if (index !== -1) {
            this.reserves.splice(index, 1, response.data);
          }
          this.resetForm();
          this.closeModal();
        })
        .catch(error => {
          console.error('Erro ao atualizar:', error);
        });
    },
    deleteItem(id) {
      const confirmDelete = confirm('Tem certeza que deseja excluir?');
      if (confirmDelete) {
        axios.delete(`http://localhost:8080/deletereserva/${id}`)
          .then(response => {
            this.reserves = this.reserves.filter(reserve => reserve.id !== id);
          })
          .catch(error => {
            console.error('Erro ao excluir:', error);
          });
      }
    },
    resetForm() {
      this.espaco = '';
      this.comentario = '';
      this.data = '';
    },
    closeModal() {
      const modal = document.querySelector('.modal-container');
      modal.classList.remove('active');
      modal.removeEventListener('click', this.closeModalOutside);
    },
    closeAlert() {
      this.showAlert = false;
    }
  },
  mounted() {
    const userId = this.getUserId();
    if (!userId) {
      console.error('Usuário não está autenticado.');
      return;
    }

    axios.get(`http://localhost:8080/reservausuario/${userId}`)
      .then(response => {
        this.reserves = response.data;
      })
      .catch(error => {
        console.error('Erro ao carregar:', error);
      });
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;700&family=Roboto:wght@100;300;400;500;700;900&family=Source+Sans+Pro:wght@200;300;400;600;700;900&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

/* Adicione aqui o estilo para o modal e alerta */
.modal-container.active {
  display: block;
}

.alert {
  position: fixed;
  top: 20px;
  left: 50%;
  transform: translateX(-50%);
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
  padding: 10px;
  border-radius: 5px;
  z-index: 1000;
}

.alert p {
  margin: 0;
}

.alert button {
  background: none;
  border: none;
  color: #721c24;
  cursor: pointer;
}

.alert button:hover {
  text-decoration: underline;
}

.container {
  width: 80%;
  height: 75%;
  margin: 115px auto 0 180px; 
  border: none;
  background: white;
  position: fixed;
}

.title {
  text-align: left;
  margin-top: 13px;
  margin-left: 120px;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%; 
}

.title h4 {
  font-size: 24px;
  font-weight: bold;
}

.title h6 {
  font-size: 18px;
  color: gray;
}

button {
  cursor: pointer;
}

.header {
  min-height: 70px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: auto 12px;
}

.header span {
  font-weight: 900;
  font-size: 20px;
  word-break: break-all;
}

#new {
  font-size: 16px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #C4C4C4; 
  color: #4070EC;
  background-color: white;
}

#new:hover {
  color: white;
  background-color: #4070EC;
}

.table {
  padding: 10px;
  width: auto;
  height: inherit;
  overflow: auto;
}

.table::-webkit-scrollbar {
  width: 12px;
  background-color: whitesmoke; 
}

.table::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: darkgray; 
}

table {
  width: 100%;
  border-spacing: 10px;
  word-break: break-all;
  border-collapse: collapse;
}

thead {
  background-color: whitesmoke;
}

tr {
  border-bottom: 1px solid rgb(238, 235, 235)!important;
}

tbody tr td {
  vertical-align: text-top;
  padding: 6px;
  max-width: 50px;
}

thead tr th {
  padding: 5px;
  text-align: start;
  margin-bottom: 50px;
}

tbody tr {
  margin-bottom: 50px;
}

thead tr th.action {
  width: 100px!important;
  text-align: center;
}

tbody tr td.action {
  text-align: center;
}

@media (max-width: 700px) {
  .container {
    font-size: 10px;
  }
  
  .header span {
    font-size: 15px;
  }

  #new {
    padding: 5px;
    font-size: 10px;
  }

  thead tr th.action {
    width: auto!important;
  }
  
  td button i {
    font-size: 20px!important;
  }

  td button i:first-child {
    margin-right: 0;
  }

  .modal {
    width: 90%!important;
  }

  .modal label {
    font-size: 12px!important;
  }
}

.modal-container {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: none;
  z-index: 999;
}

.modal {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 40px;
  background-color: white;
  border-radius: 10px;
  width: 50%;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.modal label {
  font-size: 14px;
  width: 100%;
}

.modal input,
.modal select {
  width: 100%;
  outline: none;
  padding: 5px 10px; 
  margin-bottom: 20px;
  border-top: none;
  border-left: none;
  border-right: none;
  border-bottom: 1px solid #ccc;
  font-size: 14px;
}

.styled-select {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  background-color: white;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  width: 100%;
  font-size: 14px;
}

.styled-select:focus {
  border-color: #4070EC;
}

.modal button {
  width: 100%;
  margin: 10px auto;
  outline: none;
  border-radius: 20px; 
  padding: 5px 10px; 
  border: none;
  background-color: rgb(57, 57, 226);
  color: white;
}

.active {
  display: flex;
}

.active .modal {
  animation: modal .4s;
}

@keyframes modal {
  from {
    opacity: 0;
    transform: translate3d(0, -60px, 0);
  }
  to {
    opacity: 1;
    transform: translate3d(0, 0, 0);
  }
}

td button {
  border: none;
  outline: none;
  background: transparent;
}

td button i {
  font-size: 25px;
}

td button i:first-child {
  margin-right: 10px;
}
</style>
