const app = Vue.createApp({
    data() {
        return {
            inputValue: "",
            num1: 0,
            num2: 0,
            result: "",
            operator: NaN,
        };
    },
    methods: {
        clearInput() {
            this.inputValue = "";
        },

        clearAll() {
            this.inputValue = "";
            this.num1 = "";
            this.num2 = "";
            this.result = "";
        },

        deleteLastDigit() {
            if (/[^0-9.]/.test(this.inputValue.toString())) {
                this.inputValue = "";
            } else {
                this.inputValue = this.inputValue.toString().slice(0, -1);
            }

        },

        calculate() {
            const requestBody = {
                leftOperand: this.num1,
                rightOperand: this.inputValue,
                operator: this.operator,
            };
            console.log('inputValue:', this.inputValue);
            fetch('/api/calculator/' + this.operator, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(requestBody)
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    this.result = data;
                    this.inputValue = this.result.toString();
                    this.addToLog();
                })
                .catch(error => {
                    console.error('Error:', error);
                    window.alert('An error occurred while performing the calculation. Please try again later.');
                });
        },

        method3(number){
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

        addToLog(){
            if (/[^0-9.]/.test(this.inputValue.toString())) {
                this.inputValue = this.inputValue.toString().slice(0, -1);
            }else{
                const li = document.createElement("li");
                li.textContent = this.num1 + " " + this.operator + " " + this.num2 + " = " + this.result;
                document.getElementById("logList").appendChild(li);
            }

        }
    }
});