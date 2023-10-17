import React, {useContext, useState, createContext, useEffect} from "react";

const AuthContext  = createContext({
    isAuthenticated: false,
    getAccessToken: () => {},
    saveToken: (token) => {}, 
})

export const AuthProvider = ({ children }) =>{
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [accessToken, setAccessToekn] = useState("");

    function getAccessToken(){
        return accessToken;
    }

    function saveToken(token, person, role){
        setAccessToekn(token);
        localStorage.setItem("user_data", JSON.stringify({
            token: token,
            person: {
                id_person: person.id_person,
                firstname: person.nombre,
                lastname: person.apellidos,
            },
            role: role,
        }));
        setIsAuthenticated(true);
    }

    return <AuthContext.Provider value={{ isAuthenticated, getAccessToken, saveToken }}>
        {children}
    </AuthContext.Provider>
}

export const useAuth = () => useContext(AuthContext);