import React, {useContext, useState, createContext, useEffect} from "react";

const AuthContext  = createContext({
    isAuthenticated: false,
    getAccessToken: () => {},
    saveToken: (token) => {}, 
})

export const AuthProvider = ({ children }) =>{
    const [isAuthenticated, setIsAuthenticated] = useState(true);
    const [accessToken, setAccessToekn] = useState("");

    function getAccessToken(){
        return accessToken;
    }

    function saveToken(token){
        setAccessToekn(token);
        localStorage.setItem("token", token);
        setIsAuthenticated(true);
    }

    return <AuthContext.Provider value={{ isAuthenticated, getAccessToken, saveToken }}>
        {children}
    </AuthContext.Provider>
}

export const useAuth = () => useContext(AuthContext);