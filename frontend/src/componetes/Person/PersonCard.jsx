import React, { useEffect, useState } from "react";
import './PersonCard.css'
import { URL_API_private } from "../../providerContext/EndPoint";
import axios from "axios";

export const PersonCard = ({person, onCardClick}) => {
    const [acepted, setAcepted] = useState(false);
    const endPoint = URL_API_private+"/user/isValidate/"+person.id_person;
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    useEffect( () => {
        isAcepeted();
    });
    
    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const isAcepeted = async () => {
        try {
            console.log(endPoint+"\n"+config.headers);
            const response = await axios.get(endPoint, config)
            console.log("\n---------------------------------------------------------------\n"
            +"entrando al servidor"+"\n---------------------------------------------------------------\n")
            setAcepted(response.data);
        } catch (error) {
            console.log("error: " +error)
        }   
    }

    return <>
        <div onClick={() => onCardClick(person)} className={acepted? 'stylesCardPersonAcepted':'stylesCardPersonFailed'}>
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