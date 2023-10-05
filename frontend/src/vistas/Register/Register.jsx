import React from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { RegistroTecnico } from "../../componetes/RegistroTecnico/RegistroTecnico"
export const Register = () => {
    return(<>
        <NavBar />
        <h2>Pagina de Registrar los datos tecnicos</h2>
        <RegistroTecnico/>
    </>);
}