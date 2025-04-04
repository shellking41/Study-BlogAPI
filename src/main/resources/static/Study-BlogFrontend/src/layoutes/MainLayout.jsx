import React from 'react';
import { Outlet } from 'react-router-dom';
import style from './styles/MainLayoutStyle.module.css';

function MainLayout() {
  return (
    <>
      <header>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
      </header>
      <main className={style.content}>
        <Outlet />
      </main>
      
    </>
  );
}

export default MainLayout;
