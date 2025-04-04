
import './App.css'
import {BrowserRouter, Routes} from "react-router-dom";
import MainLayoutRoute from "./routes/MainLayoutRoute.jsx";


function App() {


  return (
    <>
      <BrowserRouter>
          <Routes>
              {MainLayoutRoute}
          </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
