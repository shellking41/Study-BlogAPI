import React from 'react';
import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom';

function NavButton({ className, type, disabled, children, path }) {

  const navigate = useNavigate();

  const handleNavigation = (path) => {
    navigate(path);
  };

  return (
    <button onClick={() => handleNavigation(path)} className={className} type={type} disabled={disabled}>
      {children ?? 'button'}
    </button>
  );
}

NavButton.propTypes = {
  path: PropTypes.string,
  className: PropTypes.string,
  type: PropTypes.string,
  disabled: PropTypes.bool,
  children: PropTypes.node,
};

NavButton.defaultProps = {
  path: '/',
  className: '',
  type: 'button',
  disabled: false,

};

export default NavButton;
