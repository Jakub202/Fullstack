<template>
  <div class="home">
    <h1>Welcome to My App</h1>
    <div class="buttons">
      <button @click="showLogin = true">Login</button>
      <button @click="showRegistration = true">Register</button>
    </div>
    <div v-if="showLogin">
      <Login @close="showLogin = false"/>
    </div>
    <div v-if="showRegistration">
      <Registration @close="showRegistration = false"/>
    </div>
  </div>
</template>

<script>
import Login from '@/components/LoginForm.vue';
import Registration from '@/components/RegistrationForm.vue';

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Home',
  components: {
    Login,
    Registration
  },
  data() {
    return {
      showLogin: false,
      showRegistration: false
    }
  }
}
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

h1 {
  font-size: 3rem;
  margin-bottom: 2rem;
}

.buttons {
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
}

button {
  padding: 1rem 2rem;
  font-size: 1.2rem;
  border: none;
  border-radius: 5px;
  background-color: #3498db;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #2980b9;
}

/* Styles for Login and Registration components go here */
</style>
