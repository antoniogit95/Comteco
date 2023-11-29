import React from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import './Personal.css'
import { TablesPerson } from "../../componetes/Tables/TablesPerson/TablesPerson";
import { Footer } from "../../componetes/Footer/Footer"

export const Personal = () => {

    return(<>
        <NavBar/>
        <div className="stylesContentPersonal">
            <TablesPerson/>
        </div>
        <Footer/>
    </>);
}