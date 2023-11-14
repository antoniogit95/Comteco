
import { URL_API_private } from "../../../providerContext/EndPoint"
import './TablesPerson.css'
import { useState,useEffect } from "react";
import axios from "axios";
import { PersonRow } from "./PersonRow";
import { ModalsCesion } from "../../Modals/ModalsCesion/ModalsCesion";
import { ModalPerson } from "../../Modals/ModalsPerson/ModalsPerson";

export const TablesPerson = () => {

    const endPoint = URL_API_private + "/person";
    const [data, setData] = useState([]);
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

    const handleValidateUser = () => {
        console.log(selectedPersonInform.id_person)
        console.log("validar usuario")
    }

    const getALLPerson = async () => {
        try {
            const response = await axios.get(endPoint, config);
            setData(response.data)
        } catch (error) {
            console.log("Error : " +error)   
        }
    }

    return(<>
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
            <ModalsCesion person={selectedPersonCesion} show={showModalCesion} onHide={() => setShowModalCesion(false)}/>
        </div>
        <div>
            <ModalPerson 
                person={selectedPersonInform} 
                show={showModalInformation} 
                onHide={() => setShowModalInformation(false)}
                onChange={() => handleValidateUser()}
            />
        </div>
        
    </>)
}