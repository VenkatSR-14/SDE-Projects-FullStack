import axios from 'axios';
import { isTokenValid } from '../utils/tokenUtils';
import { getNavigateFunction } from '../utils/navigationUtil';

const BASE_URL = 'http://localhost:8081/api/users';

const apiClient = axios.create({
  headers: {
    'Content-Type': 'application/json',
  },
  baseURL: BASE_URL,
});

apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('authToken');

    // Check if the token is valid
    if (!isTokenValid()) {
      alert('The session has expired. Please log in again.');
      localStorage.removeItem('authToken');

      // Redirect to login using the global navigate function
      const navigate = getNavigateFunction();
      if (navigate) {
        navigate('/login');
      }
    }

    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('API error', error.response?.data || error.message);
    return Promise.reject(error);
  }
);

export default apiClient;
