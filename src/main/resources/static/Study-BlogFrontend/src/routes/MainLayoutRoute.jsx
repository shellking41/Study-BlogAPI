import React from 'react'
import {Route} from "react-router-dom";
import Home from "../pages/Home.jsx";
import MainLayout from "../layoutes/MainLayout.jsx";
import About from "../pages/About.jsx";

const MainLayoutRoute= (
        <Route path={"/"} element={<MainLayout/>}>
            <Route index element={<Home/>}/>
            <Route path={"about"} element={<About/>}/>
        </Route>
    )


export default MainLayoutRoute
