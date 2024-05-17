<script setup>
import { ref, computed } from 'vue';

const currentMonth = ref(new Date().getMonth());
const currentYear = ref(new Date().getFullYear());

const events = ref({
  6: ["9am Weekly Meeting"],
  13: ["9am Weekly Meeting"],
  20: ["9am Weekly Meeting"],
  27: ["9am Weekly Meeting"],
  3: ["First Weekend"],
  31: ["End of Month"]
});

const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

const daysInMonth = computed(() => {
  const date = new Date(currentYear.value, currentMonth.value + 1, 0);
  return Array.from({ length: date.getDate() }, (_, i) => i + 1);
});

const blankDays = computed(() => {
  const firstDayOfMonth = new Date(currentYear.value, currentMonth.value, 1).getDay();
  return Array.from({ length: firstDayOfMonth });
});

const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

const prevMonth = () => {
  if (currentMonth.value === 0) {
    currentMonth.value = 11;
    currentYear.value--;
  } else {
    currentMonth.value--;
  }
};

const nextMonth = () => {
  if (currentMonth.value === 11) {
    currentMonth.value = 0;
    currentYear.value++;
  } else {
    currentMonth.value++;
  }
};
</script>

<template>
  <div class="juncao">
    <div class="informacoes">
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
      <div class="Calendario">
        <div class="calendar">
          <div class="header">
            <button @click="prevMonth">‹</button>
            <span>{{ monthNames[currentMonth] }} {{ currentYear }}</span>
            <button @click="nextMonth">›</button>
          </div>
          <div class="weekdays">
            <div v-for="day in daysOfWeek" :key="day">{{ day }}</div>
          </div>
          <div class="days">
            <div v-for="day in blankDays" :key="day" class="day blank"></div>
            <div v-for="day in daysInMonth" :key="day" class="day">
              <span>{{ day }}</span>
              <div v-if="events[day]" class="event" v-for="event in events[day]" :key="event">{{ event }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.title {
  margin-top: 25px;
  margin-bottom: 20px;
  margin-left: 120px;
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
  margin: 0;
  position: absolute;
  top: 0;
  left: 0;
}

.date {
  margin-bottom: 20px;
}

.Suasreservas {
  margin-bottom: 20px;
  margin-left: 100px;
}

.OutraReserva {
  margin-bottom: 20px;
  margin-left: 100px;
}

.AzulReservado {
  display: flex;
  margin-left: 100px;
}

.dotVerde {
  height: 25px;
  width: 25px;
  background-color: #0fbd26;
  border-radius: 50%;
  display: inline-block;
  margin-right: 20px;
}

.VerdeReservado {
  display: flex;
  margin-left: 100px;
}

.dotAzul {
  height: 25px;
  width: 25px;
  background-color: rgb(15, 119, 189);
  border-radius: 50%;
  display: inline-block;
  margin-right: 20px;
}

.calendar {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.header button {
  background: none;
  border: none;
  font-size: 1.5em;
}

.weekdays {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  margin-bottom: 10px;
  font-weight: bold;
  text-align: center;
}

.days {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 10px;
}

.day {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100px;
  background: #f9f9f9;
  border-radius: 4px;
  position: relative;
}

.day.blank {
  background: transparent;
}

.event {
  background: #8ab4f8;
  color: white;
  border-radius: 4px;
  padding: 2px 5px;
  font-size: 0.8em;
  margin-top: 5px;
  text-align: center;
}
</style>
