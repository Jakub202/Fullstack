import { defineStore } from 'pinia';

export const useCommentStore = defineStore({
  id: 'comment',
  state: () => ({
    name: '',
    email: '',
  }),
  actions: {
    setName(name) {
      this.name = name;
    },
    setEmail(email) {
      this.email = email;
    },
  },
});

