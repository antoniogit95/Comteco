import React from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { Reports } from "../../componetes/Reports/Reports"
import { SaveDataTecnico } from "../../componetes/saveFiles/saveDataTecnico/SaveDataTecnico"

export const Report = () => {
    return(<>
        <NavBar />
        <SaveDataTecnico />
        <h2>Reportes</h2>
        <Reports/>
    </>);
}