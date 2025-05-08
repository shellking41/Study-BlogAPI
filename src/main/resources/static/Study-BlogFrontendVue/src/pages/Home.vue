<script setup>

import Form from '../service/Form.vue';
import { useUserDataStore } from '../store/UserDataStore.js';
import { computed, nextTick } from 'vue';
import { storeToRefs } from 'pinia';
import { useApiCall } from '../hooks/useApiCall.js';

const userDataStore = useUserDataStore();
//state management
const { userData } = storeToRefs(userDataStore);
const { setUserData } = userDataStore;

const handleSubmit = async (formData) => {
  console.log(formData);

  setUserData(formData);


  await nextTick();
  await useApiCall.POST(`${import.meta.env.VITE_API_URL}/auth/register`, formData, 'include', 'application/json');
};

</script>

<template>
  <div>
    home
  </div>
  <Form :header="{text:'Register',tag:'h1'}"
        :inputs="
        [{name:'firstname',type: 'text',minLength:3},
        {name:'lastname',type:'text',minLength: 3},
        {name:'email',type: 'email',minLength: 3},
        {name:'password',type:'password',minLength: 8}]"
        :onSubmit="(formData)=>handleSubmit(formData)"

  />
  {{ userData }}
</template>

<style scoped>

</style>