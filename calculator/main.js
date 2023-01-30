const app = Vue.createApp({
    data() {
      return {
        message: "Hello from Vue!!!"
      };
    },
    methods: {
        method1(){
            this.message = "Hello 2!"
        }
    }
  });