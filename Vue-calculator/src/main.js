import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createStore } from 'vuex';

const store = createStore({
  state: {
    calculations: []
  },
  mutations: {
    addCalculation(state, calculation) {
      state.calculations.push(calculation);
    }
  },
  actions: {
    logCalculation({ commit }, calculation) {
      commit('addCalculation', calculation);
    }
  },
  getters: {
    getCalculations(state) {
      return state.calculations;
    }
  }
});

const app = createApp(App);

app.use(router);
app.use(store);

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