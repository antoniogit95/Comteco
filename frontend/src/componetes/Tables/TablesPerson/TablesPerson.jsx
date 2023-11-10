
import { URL_API_private } from "../../../providerContext/EndPoint"
import './TablesPerson.css'
import { useState,useEffect } from "react";
import axios from "axios";
import { PersonRow } from "./PersonRow";
import { ModalsCesion } from "../../Modals/ModalsCesion/ModalsCesion";

export const TablesPerson = () => {

    const endPoint = URL_API_private + "/person";
    const [data, setData] = useState([]);
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [showModal, setShowModal] = useState(false);
    const [selectedPerson, setSelectedPerson] = useState(null);

    useEffect(() => {
        getALLPerson();
    }, [])

    useEffect(() => {
        if (selectedPerson) {
            handleShowConnections(selectedPerson);
        }
    }, [selectedPerson]);

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const handleShowConnections = (person) => {
        setSelectedPerson(person);
        setShowModal(true);
    };

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
                        />
                    ))}
                </tbody>
            </table>
        </div>
        <ModalsCesion person={selectedPerson} show={showModal} onHide={() => setShowModal(false)}/>
    </>)
}