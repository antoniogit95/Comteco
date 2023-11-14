import React, { useEffect, useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import './ModalsCesion.css'
import { URL_API_private } from "../../../providerContext/EndPoint"
import axios from 'axios';

export const ModalsCesion = ({ person, show, onHide }) => {
    
    const [cesion, setSecion] = useState([]);
    const endPoint = URL_API_private+"/cesion/allactivesperson/";
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    useEffect(() => {
        if(person){
            getAllCesionPerson();
        }
    },[person])

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const getAllCesionPerson = async () => {
        try {
            console.log(endPoint)
            const response = await axios.get(endPoint+person.id_person, config);
            setSecion(response.data);
        } catch (error) {
            console.error(error)
        }
    }

    const getDataPlusHour = (timestamp) => {
        const dataObjest = new Date(timestamp);
        const year = dataObjest.getFullYear();
        const mounth =  dataObjest.getMonth() +1;
        const day = dataObjest.getDate();
        const hour = dataObjest.getHours()+":"+dataObjest.getMinutes().toString().padStart(2, '0');;
        return year+ "/"+ mounth + "/" + day +"  "+hour
    }

    return (
        <Modal show={show} onHide={onHide} centered>
            <Modal.Header closeButton>
                <Modal.Title> Historial de Conexion del Usuario: {person && person.nombre}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {cesion.map((cesion) => (<>
                    <div className='stylesModalCesionList' key={cesion.id_person}>
                        <p>{getDataPlusHour(cesion.createdAt) +" - "+ getDataPlusHour(cesion.finalyAt)}</p>
                    </div>
                </>))}
            </Modal.Body>   
        </Modal>
    );
};