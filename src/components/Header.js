import React from 'react';
import { useNavigate } from 'react-router-dom';
import { isTokenValid } from '../utils/tokenUtils';

const Header = () => {
  const navigate = useNavigate();
  const isLoggedIn = isTokenValid();

  const handleLogout = () => {
    localStorage.removeItem('authToken'); // Remove the token
    navigate('/login'); // Redirect to the login page
  };

  return (
    <header className="bg-gray-800 text-white py-4 px-8 flex justify-between items-center shadow-md">
      <div>
        <h1 className="text-xl font-bold cursor-pointer" onClick={() => isLoggedIn && navigate('/dashboard')}>
          My App
        </h1>
      </div>
      <nav className="flex items-center space-x-4">
        <button
          onClick={() => navigate('/dashboard')}
          className="bg-gray-700 hover:bg-gray-600 px-4 py-2 rounded"
        >
          Dashboard
        </button>
        <button
          onClick={() => navigate('/profile')} // Add this if you have a profile page
          className="bg-gray-700 hover:bg-gray-600 px-4 py-2 rounded"
        >
          Profile
        </button>
        <button
          onClick={handleLogout}
          className="bg-red-600 hover:bg-red-500 px-4 py-2 rounded"
        >
          Logout
        </button>
      </nav>
    </header>
  );
};

export default Header;
