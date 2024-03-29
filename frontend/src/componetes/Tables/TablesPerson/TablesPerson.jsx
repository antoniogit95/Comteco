import { URL_API_private } from "../../../providerContext/EndPoint"
import './TablesPerson.css'
import 'react-icons/fa';
import { FaSpinner } from 'react-icons/fa';
import { useState,useEffect } from "react";
import axios from "axios";
import { PersonRow } from "./PersonRow";
import { ModalsCesion } from "../../Modals/ModalsCesion/ModalsCesion";
import { ModalPerson } from "../../Modals/ModalsPerson/ModalsPerson";

export const TablesPerson = () => {

    
    const endPoint = URL_API_private + "/person";
    const endPointValidate = URL_API_private + "/person/activate/"
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(false);
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [showModalCesion, setShowModalCesion] = useState(false);
    const [showModalInformation, setShowModalInformation] = useState(false);
    const [selectedPersonCesion, setSelectedPersonCesion] = useState(null);
    const [selectedPersonInform, setSelectedPersonInform] = useState(null);

    useEffect(() => {
        getALLPerson();
    }, [])

    useEffect(() => {
        if (selectedPersonCesion) {
            handleShowConnections(selectedPersonCesion);
        }
        if(selectedPersonInform) {
            handleShowInformation(selectedPersonInform)
        }
    }, [selectedPersonCesion, selectedPersonInform]);

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const handleShowConnections = (person) => {
        setSelectedPersonCesion(person);
        setShowModalCesion(true);
    };

    const handleShowInformation = (person) => {
        setSelectedPersonInform(person);
        setShowModalInformation(true);
    };

    const handleValidateUser = async () => {
        try {
            const response = await axios.put(endPointValidate+selectedPersonInform.id, {
                id_person: selectedPersonInform.id,
            }, config);
            console.log(response.data);
        } catch (error) {
            console.error("error: " +error.response)
        }
    }

    const closeModalCesion = () => {
        setShowModalCesion(false)
        setSelectedPersonCesion(null)
    }

    const closeModalInform = () => {
        setShowModalInformation(false)
        setSelectedPersonInform(null)
    }

    const getALLPerson = async () => {
        setLoading(true);
        try {
            const response = await axios.get(endPoint, config);
            setData(response.data)
            setLoading(false);
        } catch (error) {
            console.log("Error : " +error)
            setLoading(false);
        }
    }

    return(<>
        {loading && (
            <div className="loading-spinner">
                <FaSpinner className="spinner-icon" />
            </div>
        )}
        <div className="styleContentTable">
            <table className="styleTable">
                <thead className="stylesHead">
                    <tr>
                        <th className='stylesTh-Td'>ITEM</th>
                        <th className='stylesTh-Td'>NOMBRE</th>
                        <th className='stylesTh-Td'>APELLIDOS</th>
                        <th className='stylesTh-Td'>VER INFORMACION</th>
                        <th className='stylesTh-Td'>VALIDAR/BLOQUEAR</th>
                        <th className='stylesTh-Td'>ESTADO</th>
                        <th className='stylesTh-Td'>ULTIMA CONEXION</th>
                        <th className='stylesTh-Td'>HISTORIAL</th>
                    </tr>
                </thead>
                <tbody className='stylesBody'>
                    {data.map((data) => (
                        <PersonRow
                            key={data.id_person}
                            person={data}
                            onShowConnections={() => handleShowConnections(data)}
                            onShowInformation={() => handleShowInformation(data)}
                        />
                    ))}
                </tbody>
            </table>
        </div>
        <div>
            <ModalsCesion person={selectedPersonCesion} show={showModalCesion} onHide={() => closeModalCesion()}/>
        </div>
        <div>
            <ModalPerson 
                person={selectedPersonInform} 
                show={showModalInformation} 
                onHide={() => closeModalInform()}
                onChange={() => handleValidateUser()}
            />
        </div>
        
    </>)
}