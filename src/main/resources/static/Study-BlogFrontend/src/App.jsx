import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Button from "./components/Button.jsx";
import Input from "./components/Input.jsx";

function App() {


  return (
    <>
      <Button  type={"button"} disabled={"fasz"}><h1>Click me</h1></Button>
      <Input placeholder={"xd"} value={"jo"}/>
    </>
  )
}

export default App
