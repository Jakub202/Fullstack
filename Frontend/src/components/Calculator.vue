<template>
  <div class="root">
    <div class="input">
      <input type="text" id="inputBox" placeholder="enter number" v-model="inputValue" size="30">
    </div>

    <div class="buttons">
      <button class="operator" id="buttonAC" @click="clearAll()">AC</button>
      <button class="operator" id="buttonAns" @click="getAnswer()">Ans</button>
      <button class="operator" id="buttonDel" @click="deleteLastDigit()">Del</button>
      <button class="operator" id="buttonDiv" @click="setOperator('divide')">/</button>
      <button class="operator" id="buttonMulti" @click="setOperator('multiply')">*</button>
      <button class="operator" id="buttonAdd" @click="setOperator('add')">+</button>
      <button class="operator" id="buttonDiff" @click="setOperator('subtract')">-</button>
      <button class="number" id="button1" @click="addNumber(1)">1</button>
      <button class="number" id="button2" @click="addNumber(2)">2</button>
      <button class="number" id="button3" @click="addNumber(3)">3</button>
      <button class="number" id="button4" @click="addNumber(4)">4</button>
      <button class="number" id="button5" @click="addNumber(5)">5</button>
      <button class="number" id="button6" @click="addNumber(6)">6</button>
      <button class="number" id="button7" @click="addNumber(7)">7</button>
      <button class="number" id="button8" @click="addNumber(8)">8</button>
      <button class="number" id="button9" @click="addNumber(9)">9</button>
      <button class="number" id="button0" @click="addNumber(0)">0</button>
      <button class="number" id="buttonComma" @click="addNumber('.')">.</button>
      <!-- change to calculate() for Vitest-->
      <button class="operator" id="buttonEquals" @click="calculateAPI()">=</button>
    </div>

    <div class="historyBox">
      <h2>History</h2>
      <div class="historyList">
        <ul>
          <li v-for="calculation in history">{{ calculation }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import axios from 'axios';
    export default{

    data() {
      return {
        inputValue: "",
        num1: 0,
        num2: 0,
        result: "",
        operator: NaN,
        history: [],
        //localhost or local ip adress
        apiUrl: "http://localhost:8080/api/calculator"
      };
    },
    methods: {
        clearInput(){
          this.inputValue ="";
        },

        ...mapActions(['logCalculation']),

        clearAll(){
          this.inputValue ="";
          this.num1 = "";
          this.num2 = "";
          this.result = "";
        },

        deleteLastDigit(){
          if (/[^0-9.]/.test(this.inputValue.toString())) {
            this.inputValue = "";
          }else{
            this.inputValue = this.inputValue.toString().slice(0, -1);
          }

        },

        calculateAPI(){
          this.num2 = this.parseToNumber(this.inputValue);
          if(this.operator === "divide"){
            if(this.num2 === 0){
              window.alert("Cannot divide by 0!");
              this.clearInput();
            }
          }
          const url = `${this.apiUrl}/solve`;
          axios.post(url, {
            leftOperand: this.num1,
            rightOperand: this.num2,
            operator: this.operator
          })
          .then(response => {
            this.result = response.data;
            this.inputValue = this.result;
            this.history.push(this.num1 + " " + this.getOperatorSymbol(this.operator) + " " + this.num2 + " = " + this.result);
          })
          .catch(error => {
            console.error(error);
          });

        },

        calculate(){
          this.num2 = this.parseToNumber(this.inputValue);
          if(this.operator === "divide"){
            if(this.num2 === 0){
              window.alert("Cannot divide by 0!");
              this.clearInput();
            }else{
              this.inputValue = this.num1 / this.num2;
              this.result = this.num1 / this.num2;
            }

          }else if(this.operator === "multiply"){
            this.inputValue = this.num1 * this.num2;
            this.result = this.num1 * this.num2;
          }else if(this.operator === "add"){
            this.result = this.num1 + this.num2;
            this.inputValue = this.result;
          }else if(this.operator === "subtract"){
            this.result = this.num1 - this.num2;
            this.inputValue = this.result;
          }
          this.history(this.num1 + " " + this.operator + " " + this.num2 + " = " + this.result);
        },

        addNumber(number){
          this.inputValue += number.toString();
        },

        setOperator(str){
          if(this.inputValue !== ""){
            this.operator = str;
            this.num1 = this.parseToNumber(this.inputValue);
            this.clearInput();
          }else{
            window.alert("Empty input!")
          }
        },

        parseToNumber(str){
          if (/[^0-9.]/.test(str)) {
            window.alert("Please only use digits and a period")
          }else{
            return parseFloat(str);
          }
        },

        getAnswer(){
          if(this.result !== ""){
            this.inputValue = this.result.toString();
          }
        },
      getOperatorSymbol(operator) {
        switch (operator) {
          case "add":
            return "+";
          case "subtract":
            return "-";
          case "multiply":
            return "*";
          case "divide":
            return "/";
          default:
            return "";
        }
      }


    },
      mounted() {
        const userId = 1; // replace with the actual user ID
        axios.get(`${this.apiUrl}/history?userId=${userId}`)
            .then(response => {
              this.history = response.data.map(equation => {
                return `${equation.leftOperand} ${this.getOperatorSymbol(equation.operator)} ${equation.rightOperand} = ${equation.result}`;
              });
            })
            .catch(error => {
              console.log(error)
            })
      }

    }
</script>

<style scoped>
.root {
  display: grid;
  grid-template-columns: 20% 60% 20%;
  grid-template-rows: 10% 20% auto 10% auto;
  height: 100vh;
  background-color: #f7f7f7;
}

.input{
  grid-column: 2/3;
  grid-row: 2/3;
  padding-bottom: 15px;
}

#inputBox{
  width: 100%;
  height: 100%;
  font-size: 30px;
  text-align: center;
  border: 2px solid #f2f2f2;
  border-radius: 10px;
}

.buttons{
  grid-column: 2/3;
  grid-row: 3/4;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(5, 1fr);
  grid-row-gap: 20px;
  grid-column-gap: 20px;
  font-size: 50px;
}

.number{
  font-size: 50px;
}

.operator {
  font-size: 50px;
  background-color: #f2f2f2;
  color: #333;
}

#buttonAC {
  grid-column: 1/2;
  background-color: #f2f2f2;
  color: #333;
}

#buttonMulti {
  grid-column: 4/5;
  grid-row: 2/3;
  background-color: #f2f2f2;
  color: #333;
}

#buttonAdd {
  grid-column: 4/5;
  grid-row: 3/4;
  background-color: #f2f2f2;
  color: #333;
}

#buttonDiv {
  grid-column: 4/5;
  grid-row: 4/5;
  background-color: #f2f2f2;
  color: #333;
}

#button1{
  grid-column: 1/2;
}

#button4{
  grid-column: 1/2;
}

#button7{
  grid-column: 1/2;
}

#button0{
  grid-column: 2/3;
  background-color: #f2f2f2;
  color: #333;
}

#buttonEquals {
  grid-column: 4/5;
  grid-row: 5/6;
  background-color: #f2f2f2;
  color: #333;
}

button:hover {
  background-color: #e6e6e6;
}

.historyBox {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
  grid-column: 2/3;
  grid-row: 5/6;
}

.historyBox ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.historyBox li {
  font-size: 16px;
  margin-bottom: 5px;
}

</style>