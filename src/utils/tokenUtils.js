import { jwtDecode } from "jwt-decode";


export const isTokenValid = () => {
    const token = localStorage.getItem("authToken");
    if (!token) return false
    try{
        const decoded = jwtDecode(token);
        const now = Date.now() / 1000;
        return decoded.exp > now;
    } catch (error){
        console.error("Invalid token", error);
        return false;
    }
}