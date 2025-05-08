import { defineStore } from 'pinia';
import { reactive } from 'vue';
//getters can be added
export const useUserDataStore = defineStore('userData', () => {
  const userData = reactive({
    firstname: '',
    lastname: '',
    email: '',
    userIcon: '',
  });

  const setUserData = (data) => {
    Object.assign(userData, data);
  };

  return { userData, setUserData };
});