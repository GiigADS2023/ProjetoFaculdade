<template>
  <div class="title">
    <h4>Ocorrências</h4>
    <h6>Condomínio >> Ocorrências >>  Consultar</h6>
  </div>

  <div class="container">
    <div class="header">
      <i class="bx bx-like icon" ><span>Ocorrências</span></i>    
      <button @click="openModal()" id="new">Criar Ocorrência</button>
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
              <td>{{ item.title }}</td>
              <td>{{ item.description }}</td>
              <td class="action">
                <button @click="editItem(index)" id="edit">Editar</button>
              </td>
              <td class="action">
                <button @click="deleteItem(index)" id="delete">Excluir</button>
              </td>
            </tr>
          </tbody>
      </table>
    </div>

    <div class="modal-container" v-show="modalVisible">
      <div class="modal">
        <form @submit.prevent="saveObject">
          <label for="m-title">Título</label>
          <input id="m-title" type="text" v-model="title" required/>

          <label for="m-description">Descrição</label>
          <input id="m-description" type="text" v-model="description" required/>

          <button type="submit" id="btnSave">Salvar</button>
        </form>
        <button @click="closeModal">Fechar</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Occurrence',
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;700&family=Roboto:wght@100;300;400;500;700;900&family=Source+Sans+Pro:wght@200;300;400;600;700;900&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

template{
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;;
  background-color: var(--body-color);
}

.title {
  text-align: left;
  margin-top: 25px;
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

#edit {
  font-size: 16px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #C4C4C4;
  color: white;
  background-color: #4070EC;
}

#delete {
  font-size: 16px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid red;
  color: white;
  background-color: red;
}

.container {
  width: 80%;
  height: 75%;
  margin-top: 115px;
  margin-left: 50px;
  border: none;
  background: white;
    position: fixed;
}

.header {
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
