import React, {useContext, useState, createContext, useEffect, useRef} from "react";
import axios from "axios";
import { URL_API_private } from "./EndPoint";

const AuthContext  = createContext({
    isAuthenticated: false,
    getAccessToken: () => {},
    saveToken: (token, person, role, time) => {},
    deletToken: () =>{},
})

export const AuthProvider = ({ children }) =>{
    const [isAuthenticated, setIsAuthenticated] = useState(() => {
        const storedData = localStorage.getItem("user_data");

        return storedData ? true : false;
    });
    const [accessToken, setAccessToekn] = useState("");
    const [timeOut, setTimeOut] = useState("");
    const endPoint = URL_API_private+"/refresh_token"
    const lastRefreshTime = useRef({ current: Date.now() });
    const INACTIVITY_TIMEOUT = 31 * 60 * 1000; // 31 minutos en milisegundos
    const REFRESH_INTERVAL = 10*60*1000 //20 en milisegundos

    function getAccessToken(){
        return accessToken;
    }


    const handleLogout = () => {
        setAccessToekn("");
        localStorage.removeItem('user_data');
        setIsAuthenticated(false);
    };
    
    function deletToken(){
        setAccessToekn("");
        localStorage.removeItem('user_data');
        setIsAuthenticated(false);
    }

    function parseJwt(token) {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    
        return JSON.parse(jsonPayload);
    }

    function saveToken(token){
        setAccessToekn(token);
        const decodedToken = parseJwt(token);
        localStorage.setItem("user_data", JSON.stringify({
            token: token,
            username: decodedToken.sub,
            role: decodedToken.role,
        }));
        setIsAuthenticated(true);
        setTimeOut(new Date(decodedToken.exp * 1000));
    }


    useEffect(() => {
        const checkTokenExpiration = () => {
            if (timeOut && new Date() > timeOut) {
                handleLogout();
            }
        };
    
        checkTokenExpiration();
        const intervalId = setInterval(checkTokenExpiration, 5000); // Verificar cada segundo
    
        return () => {
            clearInterval(intervalId);
        };

    }, [timeOut]);

    useEffect(() => {
        const handleUserActivity = async () => {
            const now = Date.now();
            if(getAccessToken() && now - lastRefreshTime.current >= REFRESH_INTERVAL){
                try {
                    const username  = JSON.parse(localStorage.getItem('user_data')).username;
                    const response = await axios.post(endPoint, {username}, {headers: {
                        'Authorization': `Bearer ${getAccessToken()}`,
                    }},);
                    saveToken(response.data)
                } catch (error) {
                    console.error('Error al obtener un nuevo token:', error);   
                }
                lastRefreshTime.current = now;
            }
        };
    
        const logoutOnInactivity = setTimeout(() => {
            handleLogout();
        }, INACTIVITY_TIMEOUT);
    
        document.addEventListener('mousemove', handleUserActivity);
        document.addEventListener('keypress', handleUserActivity);
    
        return () => {
            clearTimeout(logoutOnInactivity);
            document.removeEventListener('mousemove', handleUserActivity);
            document.removeEventListener('keypress', handleUserActivity);
        };
    }, [getAccessToken]);

    return <AuthContext.Provider value={{ isAuthenticated, getAccessToken, saveToken, deletToken }}>
        {children}
    </AuthContext.Provider> 
}

export const useAuth = () => useContext(AuthContext);