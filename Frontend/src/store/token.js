import { defineStore } from "pinia";
import { getJwtToken } from "../utils/httputils.js";

export const useTokenStore = defineStore("token", {
  state: () => ({
    jwtToken: null,
    loggedInUser: null,
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
    async getTokenAndSaveInStore(username, password) {
      try {
        let response = await getJwtToken(username, password);
        let data = response.data;
        if (data != null && data != "" && data != undefined) {
          this.jwtToken = data;
          this.loggedInUser = username;
        }
      } catch (err) {
        console.log(err);
      }
    },
  },
});
