import React, { useState } from 'react';
import Header from '../components/Header.jsx';
import style from './styles/FormStyle.module.css';

function Form({ inputs, headers, onSubmit }) {

  const [formData, setFormData] = useState(
    inputs.reduce((acc, input) => ({ ...acc, [input.name]: '' }), {}),
  );

  const handleChange = (e) => {
    const { name, value } = e.target;
    console.log(formData);
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  return (
    <form onSubmit={(e) => onSubmit(e, formData)} className={style.form}>
      <div className={style.headers}>
        {headers && headers.length > 0 && headers.map((header) => {
          return (
            <span key={header.id}>
              <Header tag={header.tag} text={header.text} className={style.header} />
            </span>
          );
        })}
      </div>
      <div className={style.inputs}>
        {inputs && inputs.length > 0 && inputs.map((input) => {
          return (
            <input key={input.id} placeholder={input.placeholder} type={input.type} className={style.input}
                   name={input.name}
                   onChange={handleChange} />
          );
        })}
      </div>
    </form>
  );
}

export default Form;
