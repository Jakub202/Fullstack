

<template>
    <form @submit.prevent="submitComment">
      <div class="form-group">
        <label for="name">Name:</label>
        <input
          type="text"
          id="name"
          v-model="name"
          @input="validateForm"
        />
        <div v-if="errors.name" class="error">{{ errors.name }}</div>
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input
          type="email"
          id="email"
          v-model="email"
          @input="validateForm"
        />
        <div v-if="errors.email" class="error">{{ errors.email }}</div>
      </div>
      <div class="form-group">
        <label for="message">Message:</label>
        <textarea 
            type="message"
            id="message"
            v-model="message"
            @input="validateForm">
            
        </textarea>
        <div v-if="errors.message" class="error">{{ errors.message }}</div>
      </div>
      <button id="submitButton" :disabled="!isValid">Submit</button>
    </form>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
        name: "",
        email: "",
        message: "",
        isValid: false,
        errors: {
          name: "",
          email: "",
          message: ""
        }
      };
    },
    methods: {
      validateForm() {
  const emailPattern = /\S+@\S+\.\S+/;

  this.errors = {
    name: this.name ? "" : "Name is required.",
    email: emailPattern.test(this.email) ? "" : "Email is not valid.",
    message: this.message ? "" : "Message is required."
  };

  this.isValid = Object.values(this.errors).every(error => !error);
},

      async submitComment() {
      if (!this.isValid) return;

      // create comment object from form input
      const comment = {
        id: 2,
        name: this.name,
        email: this.email,
        message: this.message
      };

      try {
        // post comment object to mock server
        const response = await axios.post(
          'https://my-json-server.typicode.com/Jakub202/dbjson/comments',
          comment
        );

        // show success message
        alert('Comment submitted successfully!');

        // clear input fields
        this.name = '';
        this.email = '';
        this.message = '';
        this.errors = {
          name: '',
          email: '',
          message: ''
        };
        this.isValid = false;
        this.$store.dispatch('setName', {
            name: comment.name,
        });
        this.$store.dispatch('setEmail', {
            name: comment.email,
        });

        
        console.log(this.$store.getters.getName);
        console.log(this.$store.getters.getEmail);
      } catch (error) {
        console.error(error);
      }
    }
    }
  };
  </script>
  
  <style scoped>
  .contact-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    padding: 2rem;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  form {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    max-width: 500px;
  }
  
  label {
    font-weight: bold;
    margin-bottom: 0.5rem;
  }
  
  input {
    width: 100%;
    padding: 0.5rem;
    margin-bottom: 1rem;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  textarea{
    height: 200px;
    width: 100%;
    padding: 0.5rem;
    margin-bottom: 1rem;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  button {
    width: 100%;
    max-width: 300px;
    padding: 0.5rem;
    background-color: #0077cc;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:disabled {
    background-color: #bbb;
    cursor: not-allowed;
  }
  </style>
  