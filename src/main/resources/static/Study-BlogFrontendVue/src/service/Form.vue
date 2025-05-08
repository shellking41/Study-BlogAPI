<script setup>
import { reactive, ref } from 'vue';

const props = defineProps({
  inputs: {
    type: Array,
    default: () => [],
  },
  header: {
    type: Object,
    default: () => ({ text: 'Header', tag: 'h1' }),
  },
  onSubmit: {
    type: Function,
    default: () => {
    },
  },
});

const formData = reactive(
  Object.fromEntries(props.inputs.map((input) => {
    return ([input.name, '']);
  })));
const submitted = ref(false);

const inputError = reactive(Array(props.inputs.length).fill(false));
const touchedInputs = reactive(Array(props.inputs.length).fill(false));

const handleChange = (minLength, index, e, name) => {
  const value = formData[name];
  submitted.value = false;
  touchedInputs[index] = true;
  const isEmailInvalid =
    e.target.type === 'email' &&
    !/^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,})$/.test(value);

  const isTooShort = value.length < minLength;
  const isEmpty = value === '' || value == null;
  inputError[index] = isEmailInvalid || isTooShort || isEmpty;


};

const handleSubmit = () => {
  submitted.value = true;
  if (inputError.includes(true) || touchedInputs.includes(false)) return;
  props.onSubmit(formData);
};


</script>

<template>
  <teleport to="body">
    <div class="modal">

      <button title="exit" class="exit-button"></button>

      <div class="form-wrapper">
        <form @submit.prevent="handleSubmit">
          <div class="input-section">
            <div class="header-wrapper">
              <component :is="props.header.tag" class="header">{{ props.header.text }}</component>
            </div>

            <label class="input-wrapper"
                   :class="inputError[index] || submitted && !touchedInputs[index]?'input-error':''" :for="input.name"
                   v-for="(input,index) in props.inputs" :key="input.name">
              <label class="input-label" :for="input.name">{{ input.name }}</label>
              <input class="input" :type="input.type" :id="input.name" placeholder="" v-model="formData[input.name]"
                     @change="(e)=>handleChange(input.minLength,index,e,input.name)" autocomplete="off" maxlength="50">

            </label>
          </div>
          <div class="submit-button-wrapper">
            <button class="submit-button" type="submit">submit</button>
          </div>
        </form>
      </div>
    </div>
  </teleport>
</template>

<style scoped>
.modal {

  min-width: 550px;
  position: fixed;
  display: flex;
  flex-direction: column;
  height: 70dvh;
  
  min-height: 500px;
  top: 50%;
  right: 50%;
  transform: translate(50%, -50%);
  background-color: var(--dark-color);
  border-radius: var(--base-radius);
}

.exit-button {
  width: 30px;
  border: none;
  background-color: var(--dark-color);
  aspect-ratio: 1/1;
  border-radius: 100%;
  position: relative;
}

.exit-button:hover,
.exit-button:focus {
  background-color: rgba(183, 183, 183, 0.34);
}

.exit-button:before {
  content: "";
  position: absolute;
  width: 50%;
  height: 2px;
  background-color: white;
  transform: rotateZ(45deg);
  top: 50%;
  right: 50%;
  translate: 50% -50%;
}

.exit-button:after {
  content: "";
  position: absolute;
  width: 50%;
  height: 2px;
  background-color: white;
  transform: rotateZ(-45deg);
  top: 50%;
  right: 50%;
  translate: 50% -50%;
}

.form-wrapper {
  width: 80%;
  margin: auto;
  height: calc(100% - 30px);
  padding: 3%;
}

.form-wrapper form {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  flex-direction: column;

}

.input-section {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.header-wrapper {
  width: 100%;
  display: flex;
  justify-content: flex-start;

}

.header {
  color: white;
}

.input-wrapper {
  background-color: var(--dark-color);
  width: 100%;
  height: 60px;
  border: 1px solid var(--secondary-color);
  border-radius: calc(var(--base-radius) - 10px);
  display: flex;
  flex-direction: column;
  position: relative;
  padding: 2%;
}


.input-label {
  width: 100%;
  height: 50%;
  color: gray;
  font-size: 20px;
  position: relative;
  top: 25%;
  transition: top 200ms ease-in-out, font-size 200ms ease-in-out;
}

.input {
  height: 50%;
  width: 100%;
  background-color: var(--dark-color);
  border: none;
  color: white;
  outline: none;
}

.input-error {
  border: 1px solid var(--error-color);
}

input:-webkit-autofill {
  -webkit-box-shadow: 0 0 0px 1000px #000000 inset !important;
  -webkit-text-fill-color: #ffffff !important;
  transition: background-color 5000s ease-in-out 0s; /* Néha szükséges trükk */
}

.input-wrapper.input-error .input-label {
  color: var(--error-color);
}

.input-wrapper:has(.input:focus) .input-label {
  color: #1DA1F2;
  top: 0;
  font-size: 15px;
  transition: top 200ms ease-in-out, font-size 200ms ease-in-out;
}

.input-wrapper:has(.input:not(:placeholder-shown)) .input-label {
  top: 0;
  font-size: 15px;
  transition: top 200ms ease-in-out, font-size 200ms ease-in-out;
}

.input-wrapper:has(.input:-webkit-autofill) .input-label {
  top: 0;
  font-size: 15px;
  transition: top 200ms ease-in-out, font-size 200ms ease-in-out;
}

.input-wrapper:has(.input:focus) {
  border: 1px solid var(--main-color);
}

.submit-button-wrapper {
  width: 100%;
  display: flex;
  justify-content: center;
  padding: 10%;
}

.submit-button {
  width: 60%;
  aspect-ratio: 6/1;
  background-color: white;
  border: none;
  outline-color: var(--main-color);

  border-radius: var(--base-radius);
}

@media (max-width: 768px) {
  .modal {

    width: 100%;
    height: 100dvh;
    border-radius: 0;
    min-width: 0;


  }


}
</style>