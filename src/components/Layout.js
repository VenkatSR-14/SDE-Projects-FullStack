import React from 'react';
import { useLocation } from 'react-router-dom';
import Header from './Header';

const Layout = ({ children }) => {
  const location = useLocation();
  const hideHeaderPaths = ['/login', '/register'];

  return (
    <div className="min-h-screen flex flex-col">
      {/* Conditionally render Header */}
      {!hideHeaderPaths.includes(location.pathname) && <Header />}
      {children}
    </div>
  );
};

export default Layout;
