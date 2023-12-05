import React, {useContext, useState, createContext, useEffect} from "react";

const AuthContext  = createContext({
    isAuthenticated: false,
    getAccessToken: () => {},
    saveToken: (token, person, role, time) => {},
    deletToken: () =>{},
})

export const AuthProvider = ({ children }) =>{
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [accessToken, setAccessToekn] = useState("");
    const [timeOut, setTimeOut] = useState("");

    function getAccessToken(){
        return accessToken;
    }

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
            role: decodedToken.role
        }));
        setIsAuthenticated(true);
        setTimeOut(decodedToken)
    }

    return <AuthContext.Provider value={{ isAuthenticated, getAccessToken, saveToken, deletToken }}>
        {children}
    </AuthContext.Provider> 
}

export const useAuth = () => useContext(AuthContext);