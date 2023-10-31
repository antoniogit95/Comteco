import React, { useEffect, useState } from "react";
import './PersonCard.css'
import { URL_API_private } from "../../providerContext/EndPoint";
import axios from "axios";
import { FcOk, FcCancel } from "react-icons/fc";

export const PersonCard = ({person, onCardClick, id_user}) => {
    const [acepted, setAcepted] = useState(false);
    const endPoint = URL_API_private+"/user/isValidate/"+person.id_person;
    const endPointCesion = URL_API_private+"/cesion/actives/"+person.id_person;
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [online, setOnline] = useState(false);
    useEffect( () => {
        isAcepeted();
        isOnline();
    });
    
    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const isOnline = async () => {
        try {
            const response = await axios.get(endPointCesion, config)
            setOnline(response.data);
        } catch (error) {
            console.log("error: " +error)
        }   
    }

    const isAcepeted = async () => {
        try {
            const response = await axios.get(endPoint, config)
            setAcepted(response.data);
        } catch (error) {
            console.log("error: " +error)
        }   
    }

    return <>
        <div onClick={() => onCardClick(person)} className={acepted? 'stylesCardPersonAcepted':'stylesCardPersonFailed'}>
            <div className="stylesHeaderCard">
                <h4>Datos personales</h4>
            </div>
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
            <div className="stylesFooterCard">
                {online? (<>
                    <FcOk/> 
                    <label>Activado</label>
                </>) : (<>
                        <FcCancel/> 
                        <label>Desactivado</label>
                </> )}
            </div>
        </div>
    </>
}