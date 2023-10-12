import React, { useState } from "react";
import './PersonCard.css'

export const PersonCard = ({person}) => {
    const [acepted, setAcepted] = useState(person.status);
    
    return <>
        <div className={acepted? 'stylesCardPersonAcepted':'stylesCardPersonFailed'}>
            <div>
                <label>{person.firstname}</label>
            </div>
            <div>
                <label>{person.lastname}</label>
            </div>
            <div>
                <label>{person.item}</label>
            </div>
            <div>
                <label>{person.ocupation}</label>
            </div>
            <div>
                <label>{person.dateBrich}</label>
            </div>
            <div>
                <label>estado: desconectado</label>
            </div>
        </div>
    </>
}