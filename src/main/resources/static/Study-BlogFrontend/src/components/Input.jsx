import React from 'react'
import PropTypes from "prop-types";
import Button from "./Button.jsx";

function Input({type,placeholder,className,onChange,disabled,value}) {
    return (
        <input type={type} placeholder={placeholder} className={className} onChange={onChange} disabled={disabled} defaultValue={value}/>
    )



}
Input.prototype = {
    type: PropTypes.string,
    placeholder: PropTypes.string,
    className: PropTypes.string,
    onChange: PropTypes.func,
    disabled: PropTypes.bool,
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]), // Helyes típusellenőrzés
};

Input.defaultProps = {
    type: "text",
    placeholder:"placeholder",
    className:"",
    onChange:()=>{},
    disabled:false,
    value:null
}
export default Input
