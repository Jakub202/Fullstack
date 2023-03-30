import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createStore } from 'vuex';
import { createPinia } from "pinia";

const store = createStore({
  state: {
    name: "",
    email: ""
  },
  mutations: {
    setName(state, name) {
      state.name = name;
    },
    setEmail(state, email) {
      state.email = email;
    }
  },
  actions: {
    setName({ commit }, name) {
      commit('setName', name);
    },
    setEmail({ commit }, email) {
      commit('setEmail', email);
    }
  },
  getters: {
    getName(state) {
      return state.name;
    },
    getEmail(state) {
      return state.email;
    }
  }
});


const pinia = createPinia();
const app = createApp(App);

app.use(router);
app.use(pinia);

app.mount('#app');


/* import { createApp } from 'vue'
import { createStore } from 'vuex'
import App from "./App.vue"
import router from "./router";


// Create a new store instance.
const store = createStore({
  state () {
    return {
      count: 0
    }
  },
  mutations: {
    increment (state) {
      state.count++
    }
  }
})

const app = createApp(App)

// Install the store instance as a plugin
app.use(store)

app.mount("#app") */