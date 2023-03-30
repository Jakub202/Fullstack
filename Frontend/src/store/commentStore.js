import { defineStore } from 'pinia'

export const useCommentStore = defineStore({
  id: 'comment',
  state: () => ({
    name: '',
    email: '',
  }),
  persist: {
    enabled: true,
    strategies: [
      {
        // Use sessionStorage for storing the state
        storage: window.sessionStorage,
        // The key to use when storing the state
        key: 'commentStore',
      },
    ],
  },
  actions: {
    setName(name) {
      this.name = name
    },
    setEmail(email) {
      this.email = email
    },
  },
})
