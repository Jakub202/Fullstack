<template>
  <div class="login">
    <h2>{{ commentStore.name }}</h2>
    <form>
      <label for="username">Username</label>
      <input type="text" id="username" v-model="username" />

      <label for="password">Password</label>
      <input type="password" id="password" v-model="password" />

      <button type="submit" @click.prevent="handleLoginClick">Login</button>
      <button type="button" @click="close">Close</button>
    </form>
  </div>
</template>

<script>
import { useTokenStore } from "@/store/token";
import router from "@/router";
import { useCommentStore } from "@/store/commentStore";

export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  setup() {
    const tokenStore = useTokenStore();
    const commentStore = useCommentStore();
    return { tokenStore, commentStore };
  },
  methods: {
    close() {
      this.$emit("close");
    },
    async handleLoginClick() {
      await this.tokenStore.getTokenAndSaveInStore(
        this.username,
        this.password
      );
      if (this.tokenStore.jwtToken) {
        await router.push("/home");
      } else {
        this.loginStatus = "Login failed!";
      }
    },
  },
};
</script>

<style scoped>
.login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

h2 {
  font-size: 2rem;
  margin-bottom: 2rem;
}

form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  width: 300px;
}

label {
  font-size: 1.2rem;
}

input[type="text"],
input[type="password"] {
  padding: 0.5rem;
  font-size: 1.2rem;
  border: 2px solid #ccc; /* Add a 2px solid gray border */
  border-radius: 5px;
}

input[type="text"]:focus,
input[type="password"]:focus {
  border-color: #3498db; /* Change the border color to blue on focus */
  outline: none; /* Remove the default blue outline on focus */
}

button[type="submit"],
button[type="button"] {
  padding: 1rem 2rem;
  font-size: 1.2rem;
  border: none;
  border-radius: 5px;
  background-color: #3498db;
  color: white;
  cursor: pointer;
}

button[type="submit"]:hover,
button[type="button"]:hover {
  background-color: #2980b9;
}
</style>
