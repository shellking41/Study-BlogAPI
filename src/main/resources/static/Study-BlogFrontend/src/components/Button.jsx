import React from 'react'
import PropTypes from 'prop-types';
import {useNavigate} from "react-router-dom";

function navButton({className="",type="button",disabled=false,children,path }) {

    const navigate=useNavigate();

    const handleNavigation=(path)=>{
        navigate(path)
    }

    return (
         <button onClick={handleNavigation} className={className} type={type} disabled={disabled}>
             {children }
         </button>
    )
}
navButton.propTypes = {
    path:PropTypes.string,
    className: PropTypes.string,
    type: PropTypes.string,
    disabled: PropTypes.bool,
    text: PropTypes.string,
};

navButton.defaultProps = {
    path:"/",
    className: '',
    type: 'button',
    disabled: false,
    text: 'button',
};

export default Button
