import React, { useState, useEffect } from 'react';
import { URL_API_private } from "../../../providerContext/EndPoint"
import './TablesPerson.css'
import axios from "axios";
import { FcOk, FcCancel } from "react-icons/fc";

export const PersonRow = ({person, onShowConnections, onShowInformation}) =>{
    if(!person){
        return null;
    }
    const [isOnline, setIsOnline] = useState(false);
    const [acepted, setAcepted] = useState(false);
    const endPoint = URL_API_private+"/user/isValidate/"+person.id_person;
    const endPointActive = URL_API_private+"/user/active/"+person.id_person;
    const endPointDesactive = URL_API_private+"/user/desactive/"+person.id_person;
    const endPointCesion = URL_API_private+"/cesion/actives/"+person.id_person;
    const endPointUltimaConexion = URL_API_private+"/cesion/ultima/"+person.id_person;
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [ultimaConexion, setUltimaConexion] = useState("")
    
    useEffect(()=>{
        onIsOnline();
        isAcepeted();
        getSesionByIdUser();
    },[])

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const getSesionByIdUser = async () => {
        try {
            const response = await axios.get(endPointUltimaConexion, config)
            const dataObjest = new Date(response.data.finalyAt);
            const year = dataObjest.getFullYear();
            const mounth =  dataObjest.getMonth() +1;
            const day = dataObjest.getDate();
            const hour = dataObjest.getHours()+":"+dataObjest.getMinutes().toString().padStart(2, '0');;
            setUltimaConexion(year+ "/"+ mounth + "/" + day +"  "+hour);
        } catch (error) {
            console.log("error: " +error)
        }  
    }
    const onIsOnline = async () => {
        try {
            const response = await axios.get(endPointCesion, config)
            setIsOnline(response.data);
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

    const onValidate = async () => {
        try {
            let response = "";
            if(acepted){
                response = await axios.put(endPointDesactive, {
                    id_person: person.id_person,
                }, config);
                setAcepted(false)
            }else{
                response = await axios.put(endPointActive, {
                    id_person: person.id_person,
                }, config);
                setAcepted(true)
            }
            console.log(response.data);
        } catch (error) {
            console.log("error: " +error)
        }   
    }

    return(<>
        <tr className="stylesTr" key={person.id_person}>
            <td className='stylesTh-Td'>{person.item}</td>
            <td className='stylesTh-Td'>{person.nombre}</td>
            <td className='stylesTh-Td'>{person.apellidos}</td>
            <td className='stylesTh-Td'>
                <button className='stylesButoon' onClick={() => onShowInformation(person)}> Ver</button>
            </td>
            <td className='stylesTh-Td'>
                {acepted ? (
                <button className='stylesButoon' onClick={() => onValidate()}>Bloquear</button>
                ) : (
                <button className='stylesButoon' onClick={() => onValidate()}>Validar</button>
                )}
            </td>
            <td className='stylesTh-Td'>{isOnline ? (<>
                    <FcOk/> 
                    <label>Activado</label>
                </>) : (<>
                        <FcCancel/> 
                        <label>Desactivado</label>
                </> )}</td>
            <td className='stylesTh-Td'>{isOnline? "": ultimaConexion }</td>
            <td className='stylesTh-Td'>
                <button className='stylesButoon' onClick={() => onShowConnections(person)}>Ver</button>
            </td>
        </tr>
    </>)
}