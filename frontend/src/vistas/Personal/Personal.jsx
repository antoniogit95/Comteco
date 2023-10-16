import React, { useState, useEffect } from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { PersonCard } from "../../componetes/Person/PersonCard";
import { URL_API_private } from "../../providerContext/EndPoint";
import axios from "axios";

export const Personal = () => {

    const endPoint = URL_API_private + "/person";
    const [data, setData] = useState([]);
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    useEffect(() => {
        getRoutePrivate();
    }, [])

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

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
        {console.log(data)}
        <div>
          {data.map((data) => (
            <PersonCard key={data.id_person} person={data} />
          ))}
        </div>
    </>);
}