import React from 'react';
import PropTypes from 'prop-types';

function Header({ tag: Tg = 'h1', text, className }) {
  return (
    <Tg className={className}>{text}</Tg>
  );
}

Header.propTypes = {
  tag: PropTypes.string,
  text: PropTypes.string,
  className: PropTypes.string,
};

Header.defaultProps = {
  text: 'text',
  className: '',
};

export default Header;
