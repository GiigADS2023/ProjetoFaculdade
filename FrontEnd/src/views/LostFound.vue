<template>
  <div class="title">
    <h4>Achados e Perdidos</h4>
    <h6>Condomínio >> Achados e Perdidos >> Alterar</h6>
  </div>

  <div class="container">
    <div class="header">
      <i class="bx bx-like icon"><span>Achados e Perdidos</span></i>    
      <button @click="openModal" id="new">Criar Achados e Perdidos</button>
    </div>

    <div class="table">
      <table> 
        <thead>
          <tr>
            <th>Título</th>
            <th>Descrição</th>
            <th class="action">Editar</th>
            <th class="action">Excluir</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in items" :key="index">
            <td>{{ item.titulo }}</td>
            <td>{{ item.descricao }}</td>
            <td class="action"><button @click="saveItem"><i class='bx bx-edit'></i></button></td>
            <td class="action"><button @click="deleteItem(item)"><i class='bx bx-trash'></i></button></td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="modal-container" ref="modalContainer">
      <div class="modal">
        <form>
          <label for="m-title">Título</label>
          <input id="m-title" type="text" v-model="titulo" required/>
          <label for="m-description">Descrição</label>
          <input id="m-description" type="text" v-model="descricao" required/>
          <button type="button" @click="saveItem">Salvar</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, reactive, onMounted } from 'vue';

export default {
  data() {
    return {
      items: [] // Adicione um array para armazenar os itens
    };
  },
  methods: {
    openModal() {
      this.$refs.modalContainer.classList.add('active');
    },
    saveItem() {
      if (!this.titulo || !this.descricao) {
        alert('Por favor, preencha todos os campos.');
        return;
      }

      const itemData = {
        titulo: this.titulo,
        descricao: this.descricao
      };

      const endpoint = this.itemID ? `http://localhost:8080/putachadoperdido/1/${this.itemID}` : 'http://localhost:8080/newachadoperdido/1';
      const requestMethod = this.itemID ? 'put' : 'post';

      axios[requestMethod](endpoint, itemData)
        .then(response => {
          console.log(response.data);
          // Atualiza os itens
          this.fetchItems();
          // Fecha o modal
          this.$refs.modalContainer.classList.remove('active');
        })
        .catch(error => {
          console.error('Erro ao salvar item:', error);
        });
    },
    deleteItem(item) {
      const confirmaExclusao = confirm("Tem certeza de que deseja excluir este item?");
      
      if (!confirmaExclusao) {
        return; // Cancela a exclusão caso seja clicado em "Cancelar"
      }
      
      axios.delete(`http://localhost:8080/deleteachadoperdido/1/${item.id}`)
        .then(response => {
          // Remover item da lista de itens que estão sendo exibidos
          this.items = this.items.filter(i => i !== item);
        })
        .catch(error => {
          console.error('Erro ao excluir item: ', error);
        });
    },
    insertItem(item) {
      this.items.push(item);  // Adicione o novo item ao array de itens
    },
    fetchItems() {
      axios.get('http://localhost:8080/newassembleia/1')
        .then(response => {
          this.items = response.data; // Atualize o array de itens com os dados obtidos
        })
        .catch(error => {
          console.error('Erro ao buscar dados:', error);
        });
    }
  },
  setup() {
    const titulo = ref('');
    const descricao = ref('');
    
    //Usado para reatividade em items
    const state = reactive({
      items: []
    });

    onMounted(() => {
      this.fetchItems(); // Ao montar o componente, buscar os itens existentes
    });

    return {
      titulo,
      descricao,
      items: state.items // Retornar items do estado reativo
    };
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
  margin-bottom: 20px;
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
  align-items: center;
  justify-content: center;
}

.modal {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px;
  background-color: white;
  border-radius: 10px;
  width: 50%;
}

.modal label {
  font-size: 14px;
  width: 100%;
}

.modal input {
  width: 100%;
  outline: none;
  padding: 5px 10px; 
  width: 100%;
  margin-bottom: 20px;
  border-top: none;
  border-left: none;
  border-right: none;
}

.modal button {
  width: 100%;
  margin: 10px auto;
  outline: none;
  border-radius: 20px; 
  padding: 5px 10px; 
  width: 100%;
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