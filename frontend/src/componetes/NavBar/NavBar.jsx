import React, { useState } from "react";
import "./NavBar.css"
import { NavLink } from "react-router-dom";
import { Link } from "react-router-dom";

export const NavBar = () => {

    const userRole = JSON.parse(localStorage.getItem('user_data')).role;
    const isAdmin = userRole === 'ADMIN';
    const isSoporte = userRole === 'SOPORTE';

    return(
    <nav className="stylesNavBar">
        <div>
            <h2 className="stylesH2">Laboratorio Comteco</h2>
        </div>
        <ul>
            {(isAdmin || isSoporte) && (
                <NavLink className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/home">Home</NavLink>
            )}
            
            {isAdmin && (
                <NavLink className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/personal">Personas</NavLink>
            )}
            
            {(isAdmin || isSoporte) && (
                <NavLink className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                to="/registrar">Registrar</NavLink>
            )}

            {isAdmin && (
                    <NavLink className={({ isActive }) => (isActive ? 'stylesActive' : 'stylesA')}
                    to="/reportes">Reportes</NavLink>
                )}
        </ul>
        <div>
            <button>Cerrar Sesión</button>
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