import React from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { Reports } from "../../componetes/Reports/Reports"
export const Report = () => {
    return(<>
        <NavBar />
        <h2>Reportes</h2>
        <Reports/>
    </>);
}