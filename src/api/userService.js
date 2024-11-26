import axios from 'axios';

import apiClient from './apiClient';
// Signup a new user
export const signup = async (signUpData) => {
  try {
    const response = await apiClient.post('/signup', signUpData);
    return response.data; // Return the created user object
  } catch (error) {
    console.error('Error during signup:', error.response?.data || error.message);
    throw error; // Throw error for frontend error handling
  }
};

// Sign in an existing user
export const signin = async (signInData) => {
  try {
    const response = await apiClient.post('/signin', signInData);
    return response.data; // Return the JWT token
    
  } catch (error) {
    console.error('Error during signin:', error.response?.data || error.message);
    throw error;
  }
};

// Fetch all users
export const fetchUsers = async () => {
  try {
    const response = await apiClient.get('/');
    return response.data; // Return the list of users
  } catch (error) {
    console.error('Error fetching users:', error.response?.data || error.message);
    throw error;
  }
};

// Fetch a user by ID
export const fetchUserById = async (id) => {
  try {
    const response = await apiClient.get(`/${id}`);
    return response.data; // Return the user object
  } catch (error) {
    console.error('Error fetching user by ID:', error.response?.data || error.message);
    throw error;
  }
};

// Update a user by ID
export const updateUser = async (id, userData) => {
  try {
    const response = await apiClient.put(`/${id}`, userData);
    return response.data; // Return the updated user object
  } catch (error) {
    console.error('Error updating user:', error.response?.data || error.message);
    throw error;
  }
};

// Delete a user by ID
export const deleteUser = async (id) => {
  try {
    const response = await apiClient.delete(`/${id}`);
    return response.data; // Return success message
  } catch (error) {
    console.error('Error deleting user:', error.response?.data || error.message);
    throw error;
  }
};
