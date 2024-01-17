import React, { useState } from "react";
import './Home.css'
import { NavBar } from "../../componetes/NavBar/NavBar";
import { Button } from "../../componetes/Button/Button"


export const Home = () => {
    
    const role = JSON.parse(localStorage.getItem('user_data')).role;
    const buttons = showButtons();

    function showButtons(){
        switch (role) {
            case "ADMIN":
                return buttonsAdmin();
            case "SOPORTE":
                return buttonsSoporte();
            case "EQUIPOS":
                return buttonsEquipos();
            default:
                return sinTrabajo();
                break;
        }
    }

    function sinTrabajo(){
        return (<>
            No tienes trabajo asignado...
        </>)
    }

    function buttonsEquipos(){
        return (<div className="stylesContentButtonsBig">
            <Button name="Equipos" link="/equipos"/>
        </div>)
    }
    
    function buttonsAdmin(){
        return (<div className="stylesContentButtonsBig">
            <Button name="Personal" link="/personal"/>
            <Button name="Equipos" link="/equipos"/>
            <Button name="Reportes" link="/reportes"/>
        </div>)
    }
    
    function buttonsSoporte(){
        return (<div className="stylesContentButtonsBig">
            <Button name="Reportes" link="/reportes"/>
        </div>)
    }
    

    return(<>
        <NavBar />
        <div className="stylesContentHome">
            { buttons }
        </div>  
            
    </>)
}