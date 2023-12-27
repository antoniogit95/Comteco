import React, {useContext, useState, createContext, useEffect} from "react";

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

    function getAccessToken(){
        return accessToken;
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

    return <AuthContext.Provider value={{ isAuthenticated, getAccessToken, saveToken, deletToken }}>
        {children}
    </AuthContext.Provider> 
}

export const useAuth = () => useContext(AuthContext);