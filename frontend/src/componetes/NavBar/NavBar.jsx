import React, { useState } from "react";
import "./NavBar.css"
import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../../providerContext/AuthProvider";

export const NavBar = () => {

    const userRole = JSON.parse(localStorage.getItem('user_data')).role;
    const isAdmin = userRole === 'ADMIN';
    const isSoporte = userRole === 'SOPORTE';
    const { deletToken } = useAuth();
    const navigate = useNavigate();
    const auth = useAuth();

    console.log(auth.isAuthenticated);
    if(!auth.isAuthenticated){
        console.log(auth.isAuthenticated);
        navigate('/login');
    }

    const handleClick = () => {
        console.log("eliminando Token")
        deletToken();
        navigate('/login');
    }

    return(
    <nav className="stylesNavBar">
        <div className="stylesLogoContainer">
            <h2 className="stylesH2">Laboratorio Comteco</h2>
        </div>
        <ul className="stylesUl">
            {(isAdmin || isSoporte) && ( <li className="stylesLi">
                <NavLink className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/home">Home</NavLink></li>
            )}
            
            {isAdmin && ( <li className="stylesLi">
                <NavLink className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/personal">Personas</NavLink></li>
            )}
            
            {(isAdmin || isSoporte) && ( <li className="stylesLi">
                <NavLink className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/registrar">Registrar</NavLink></li>
            )}

            {isAdmin && ( <li className="stylesLi">
                    <NavLink className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                    to="/reportes">Reportes</NavLink></li>
                )}
        </ul>
        <div>
            <button className="stylesButoonNavBar" onClick={handleClick}>Cerrar Sesión</button>
        </div>
    </nav>
    );
}

/**<NavLink 
                className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')} 
                to="/">Home</NavLink>
            <NavLink 
                className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/reportes">Reportes</NavLink>
            <NavLink 
                className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/registrar">Registrar</NavLink>
            <NavLink 
                className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/personal">Personal</NavLink>
 */