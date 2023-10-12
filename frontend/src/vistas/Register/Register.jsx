import React from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { RegistroTecnico } from "../../componetes/RegistroTecnico/RegistroTecnico"
export const Register = () => {
    return(<>
        <NavBar />
        <h2>Registro Soporte</h2>
        <RegistroTecnico/>
    </>);
}