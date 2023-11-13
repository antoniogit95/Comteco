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
            default:
                setError(nameErrors);
                break;
        }
    }

    function buttonsAdmin(){
        return (<div className="stylesContentButtonsBig">
            <Button name="Personal" link="/personal"/>
            <Button name="Reportes" link="/reportes"/>
            <Button name="Registrar" link="/registrar"/>
        </div>)
    }
    
    function buttonsSoporte(){
        return (<div className="stylesContentButtonsBig">
            <Button name="Registrar" link="/registrar"/>
        </div>)
    }
    

    return(<>
        <NavBar />
        <div className="stylesContentHome">
            { buttons }
        </div>       
    </>)
}