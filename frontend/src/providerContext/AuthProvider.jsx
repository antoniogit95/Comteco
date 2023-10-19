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

    function getAccessToken(){
        return accessToken;
    }

    function deletToken(){
        setAccessToekn("");
        localStorage.removeItem('user_data');
        setIsAuthenticated(false);
    }

    function saveToken(token, person, role, expirationTime){
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
        setTimeout(deletToken, expirationTime);
    }

    return <AuthContext.Provider value={{ isAuthenticated, getAccessToken, saveToken, deletToken }}>
        {children}
    </AuthContext.Provider> 
}

export const useAuth = () => useContext(AuthContext);