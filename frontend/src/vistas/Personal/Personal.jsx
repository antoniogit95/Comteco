import React from "react";
import { RegistrarPersonal } from "../../componetes/Forms/Personas/RegistrarPersonal";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { PersonCard } from "../../componetes/Person/PersonCard";

export const Personal = () => {
    const person1 = {
        firstname: 'JUAN',
        lastname: 'PEREZ',
        item: '12345',
        ocupation: 'SOPORTE',
        dateBrich: '01/01/2000',
        status: false
    }
    
    const person2 = {
        firstname: 'JUAN',
        lastname: 'PEREZ',
        item: '12345',
        ocupation: 'SOPORTE',
        dateBrich: '01/01/2000',
        status: true
    }

    return(<>
        <NavBar/>
        <PersonCard person = {person1}/>
        <PersonCard person = {person2}/>
    </>);
}