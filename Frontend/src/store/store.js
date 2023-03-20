import { createStore } from 'vuex';

export default createStore({
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