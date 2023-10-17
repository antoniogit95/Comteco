import React, { useState, useEffect } from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { PersonCard } from "../../componetes/Person/PersonCard";
import { URL_API_private } from "../../providerContext/EndPoint";
import { ModalPerson } from "../../componetes/Modals/ModalsPerson/ModalsPerson";
import axios from "axios";

export const Personal = () => {

    const endPoint = URL_API_private + "/person";
    const endPointUser = URL_API_private + "/user/active"
    const [data, setData] = useState([]);
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [selectedPerson, setSelectedPerson] = useState(null);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        getRoutePrivate();
    }, [])

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const handleShowModal = (person) => {
        setSelectedPerson(person);
        setShowModal(true);
    }

    const handleOnChange = async () => {
        setShowModal(false);
        console.log(selectedPerson.id_person)
        try {
            const response = await axios.put(endPointUser+"/"+selectedPerson.id_person, {
                id_person: selectedPerson.id_person
            }, {
                headers: config.headers,
            });
                console.log('se activo con exito: '+ response.data)
        }catch (error) {
            console.log('Error Inesperado: '+error)
        }
    }

    const getRoutePrivate = async () => {
        try {
            const response = await axios.get(endPoint, config);
            setData(response.data)
        } catch (error) {
            console.log("Error : "+error)   
        }
    }

    return(<>
        <NavBar/>
        <div>
          {data.map((data) => (
            <PersonCard key={data.id_person} person={data} onCardClick={() => handleShowModal(data)}/>
          ))}
        </div>

        <ModalPerson person={selectedPerson} show={showModal} onHide={() => setShowModal(false)} onChange={() => handleOnChange}/>
    </>);
}