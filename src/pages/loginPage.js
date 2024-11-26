import React, { useState } from 'react';
import { FcGoogle } from 'react-icons/fc';
import { FaGithub } from 'react-icons/fa';
import APP_BASE_URL from '../utils/constants';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // Import useNavigate

const LoginPage = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });
  
  const navigate = useNavigate();
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`${APP_BASE_URL}/signin`, formData);
      const token  = response.data; // Ensure the backend response includes this
      localStorage.setItem('authToken', token);

      console.log("Login successful, token: ", token);
      navigate("/dashboard"); // Redirect after login
    } catch (error) {
      console.error("Login failed", error.response?.data || error.message);
      alert("Invalid email or password. Please try again.");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <div className="flex justify-center mb-6">
          <div className="h-24 w-24 rounded-full overflow-hidden border-4">
            <img
              src="codequest.png"
              alt="CodeQuest Logo"
              className="object-cover h-full w-full"
            />
          </div>
        </div>
        <h1 className="text-2xl font-bold text-center mb-6">CodeQuest</h1>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Email
            </label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="Enter your email"
              className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-200"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Password
            </label>
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="Enter your password"
              className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-200"
              required
            />
          </div>
          <div className="flex justify-between items-center mb-4">
            <a
              href="/forgot-password"
              className="text-sm text-blue-500 hover:underline"
            >
              Forgot Password?
            </a>
            <a
              href="/register"
              className="text-sm text-blue-500 hover:underline"
            >
              Sign Up
            </a>
          </div>
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600"
          >
            Sign In
          </button>
        </form>
        <div className="text-center mt-4 text-gray-500">
          <p>or you can sign in with</p>
          <div className="flex justify-center mt-2 space-x-4">
            <button className="bg-gray-200 rounded-full p-2 hover:bg-gray-300">
              <FcGoogle className="h-6 w-6" />
            </button>
            <button className="bg-gray-200 rounded-full p-2 hover:bg-gray-300">
              <FaGithub className="h-6 w-6 text-black" />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
