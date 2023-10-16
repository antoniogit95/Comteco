import React, { useState } from "react";
import './PersonCard.css'

export const PersonCard = ({person}) => {
    const [acepted, setAcepted] = useState(person.status);
    console.log(person.id_person +" -----------------------")
    return <>
        <div className={acepted? 'stylesCardPersonAcepted':'stylesCardPersonFailed'}>
            <div>
                <label>{person.nombre}</label>
            </div>
            <div>
                <label>{person.apellidos}</label>
            </div>
            <div>
                <label>{person.item}</label>
            </div>
            <div>
                <label>{person.telefono}</label>
            </div>
            <div>
                <label>{person.fecha_nacimiento}</label>
            </div>
            <div>
                <label>estado: desconectado</label>
            </div>
        </div>
    </>
}