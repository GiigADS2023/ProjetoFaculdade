<script setup>

</script>

<template>
<div>
  <div class="title">
    <h4>Reserva</h4>
    <h6>Condomínio >> Reservas >> Consulta</h6>
  </div>

  <div class="date">
    <h3>Abril 2024</h3>
  </div>
  <div class="Suasreservas">
    <h3>Suas Reservas:</h3>

  </div>
  <div class="VerdeReservado">
  <div class="BotaoVerdde">
    <span class="dotVerde"></span>
  </div>
    <h3>Reservado</h3>
  </div>

  <div class="OutraReserva">
    <h3>Outras Reservas:</h3>
  </div>
  <div class="AzulReservado">
    
  <div class="BotaoAzul">
    <span class="dotAzul"></span>
  </div>
    <h3>Reserva</h3>
  </div>
</div>
</template>


<style scoped>
.title {
  margin-top: 25px;
  margin-bottom: 20px;
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

.date {
  margin-bottom: 20px;
}

.Suasreservas{
  margin-bottom: 20px;
}

.OutraReserva{
  margin-bottom: 20px;
}
.AzulReservado{
 display: flex;
}

.dotVerde{
  height: 25px;
  width: 25px;
  background-color: #0fbd26;
  border-radius: 50%;
  display: inline-block;
  margin-right: 20px;
}
.VerdeReservado{
  display: flex;
  margin-bottom: 20px;
}

.dotAzul{
  height: 25px;
  width: 25px;
  background-color: rgb(15, 119, 189);
  border-radius: 50%;
  display: inline-block;
  margin-right: 20px;
}

</style>