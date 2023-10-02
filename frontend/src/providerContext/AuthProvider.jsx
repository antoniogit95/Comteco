import React, {useContext, useState, createContext, useEffect} from "react";

const AuthContext  = createContext({
    isAuthenticated: false,
})

export const AuthProvider = ({ children }) =>{
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    return <AuthContext.Provider value={{ isAuthenticated }}>
        {children}
    </AuthContext.Provider>
}

export const useAuth = () => useContext(AuthContext);