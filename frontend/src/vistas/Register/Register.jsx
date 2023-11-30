import React from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { RegistroTecnico } from "../../componetes/Forms/RegistroTecnico/RegistroTecnico"
import './Register.css'

export const Register = () => {
    return(<>
        <NavBar />
        <div className="styleContentRegister">
            <h2>Registro Soporte</h2>
            <RegistroTecnico/>
        </div>
        
    </>);
}