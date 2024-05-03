<script setup>
import axios from 'axios'
import { ref, onMounted } from 'vue';

const achadosPerdidos = ref([]);
const atualizarItem = async (itemAtualizado) => {
    try {
        const response = await axios.put(`http://localhost:8080/achados-perdidos/${itemAtualizado.id}`, itemAtualizado);
        const index = achadosPerdidos.value.findIndex(item => item.id === itemAtualizado.id);
        if (index !== -1) {
            achadosPerdidos.value.splice(index, 1, response.data); 
        }
        // Emitir evento para notificar a atualização do item
        emit('itemAtualizado', response.data);
    } catch (error) {
        console.error('Erro ao atualizar o item:', error);
    }
};

const excluirItem = async (id) => {
    try {
        await axios.delete(`http://localhost:8080/achados-perdidos/${id}`);
        achadosPerdidos.value = achadosPerdidos.value.filter(item => item.id !== id); 
        // Emitir evento para notificar a exclusão do item
        emit('itemExcluido', id);
    } catch (error) {
        console.error('Erro ao excluir o item:', error);
    }
}

onMounted(() => {
  axios.get('http://localhost:8080/achados-perdidos')
    .then(response => {
        achadosPerdidos.value = response.data
    })
    .catch(error => {
        console.error('Erro ao obter achados e perdidos:', error)
    })
})
</script>
