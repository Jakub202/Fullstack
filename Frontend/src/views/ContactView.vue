import ContactForm from '../components/ContactForm.vue';

<template>
  <main>
    <ContactForm />
  </main>
</template>

<script setup>
import ContactForm from "../components/ContactForm.vue";
</script>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
