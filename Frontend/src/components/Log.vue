<template>
    <div id="logDiv">
      <ul id="logList">
        <li v-for="(calculation, index) in calculations" :key="index">
          {{ calculation }}
        </li>
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
      }
    }
  };
  </script>
  <style scoped>
  #logDiv {
    grid-column: 2/3;
    grid-row: 4/5;
    font-size: 50px;
    width: auto;
    border-style: solid;
    border-color: black;
    border-width: 5px;
    padding: 20px;
    margin: 20px;
  }

  #logList {
    text-align: left;
    margin: auto;
    padding-left: 30%;
  }
  </style>