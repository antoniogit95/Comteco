import React, { useState, useEffect } from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { PersonCard } from "../../componetes/Person/PersonCard";
import { URL_API_private } from "../../providerContext/EndPoint";
import axios from "axios";

export const Personal = () => {

    const endPoint = URL_API_private + "/person";
    const [data, setData] = useState();
    const token = localStorage.getItem('token');

    useEffect(() => {
        getRoutePrivate();
    })

    const headers = {
        Authorization: `Bearer ${token}`
    }

    const getRoutePrivate = async () => {
        try {
            const response = await axios.get(endPoint);
            setData(response.data)+ "logro entrar aqui";    
        } catch (error) {
            console.log("Error : "+error)   
        }
    }

    const person1 = {
        firstname: 'JUAN',
        lastname: 'PEREZ',
        item: '12345',
        ocupation: 'SOPORTE',
        dateBrich: '01/01/2000',
        status: false
    }
    
    const person2 = {
        firstname: 'JUAN',
        lastname: 'PEREZ',
        item: '12345',
        ocupation: 'SOPORTE',
        dateBrich: '01/01/2000',
        status: true
    }

    return(<>
        <NavBar/>
        <h1>{endPoint}</h1>
        <h1>{headers.Authorization}</h1>
        <h2>{data+ ": datos del server"}</h2>
        <PersonCard person = {person1}/>
        <PersonCard person = {person2}/>
        <PersonCard person = {person2}/>
        
    </>);
}