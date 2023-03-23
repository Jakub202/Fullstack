<template>
  <div class="log">
    <ul class="log-list">
      <li v-for="(calculation, index) in calculations" :key="index">{{ calculation }}</li>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['getCalculations']),
    calculations() {
      return this.getCalculations;
    },
  },
};
</script>

<style scoped>
.log {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 2rem;
}

.log-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  max-width: 500px;
  padding: 1rem;
  background-color: #ffffff;
  border: 1px solid #dcdcdc;
  border-radius: 5px;
  font-size: 1.5rem;
}

.log-list li {
  margin-bottom: 1rem;
  color: #666666;
}
</style>
