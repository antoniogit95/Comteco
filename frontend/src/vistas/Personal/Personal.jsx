import React from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import './Personal.css'
import { TablesPerson } from "../../componetes/Tables/TablesPerson/TablesPerson";


export const Personal = () => {

    return(<>
        <NavBar/>
        <div className="stylesContentPersonal">
            <TablesPerson/>
        </div>
       
    </>);
}