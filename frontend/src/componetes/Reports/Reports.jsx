import React, { useEffect } from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_private } from '../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';
import './Reports.css'

export const Reports = () => {
   
   
    const [mostrarColumnas, setMostrarColumnas] = useState(true);
    const endPoint = URL_API_private+"/datatecnico";
    const [dataTecnico, setDataTecnico] = useState([]);
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    
    
    console.log(endPoint);

    useEffect(() => {
        getAllDataTecnico();
    }, [])

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const getAllDataTecnico = async() =>{
        try {
            const response = await axios.get(endPoint, config)
            setDataTecnico(response.data);
        } catch (error) {
            console.error(error)
        }
    }

    function getMes(data){
        const dataObjest = new Date(data);
        return dataObjest.getMonth() +1;
    }

    function getDia(data){
        const dataObjest = new Date(data);
        return dataObjest.getDate();
    }

    function getHora(data){
        const dataObjest = new Date(data);
        return dataObjest.getHours()+":"+dataObjest.getMinutes();
    }

    const toggleColumnas = () => {
        setMostrarColumnas(!mostrarColumnas); 
    };
    return (
        <div className="excel-table-container">
            <button onClick={toggleColumnas}>Mostrar/Ocultar Columnas</button>
            {mostrarColumnas && (
                <table className="excel-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>MES</th>
                            <th>FECHA</th>
                            <th>TIPO DE ORDEN</th>
                            <th>ZONA</th>
                            <th>PRODUCTO</th>
                            <th>DATO TECNICO ACTUAL</th>
                            <th>DATO TECNICO ANTERIOR</th>
                            <th>HORA</th>
                            <th>ESTADO DATO TECNICO</th>
                            <th>OBSERVACION</th>
                            <th>TECNICO</th>
                            <th>ANALISTA SOPORTE</th>
                        </tr>
                    </thead>
                    <tbody>
                       {dataTecnico.map(data => (
                        <tr key={data.id_reg_data_tec}>
                            <td>{data.id_reg_data_tec}</td>
                            <td>{getMes(data.created_at)}</td>
                            <td>{getDia(data.created_at)}</td>
                            <td>{"----"}</td>
                            <td>{"----"}</td>
                            <td>{data.num_producto}</td>
                            <td>{data.caja_nap}</td>
                            <td>{"----"}</td>
                            <td>{getHora(data.created_at)}</td>
                            <td>{data.estadp_odt}</td>
                            <td>{data.obasrvaciones}</td>
                            <td>{"----"}</td>
                            <td>{data.person.nombre + " "+ data.person.apellidos}</td>
                        </tr>
                       ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};
