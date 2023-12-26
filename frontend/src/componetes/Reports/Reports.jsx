import React, { useEffect, useRef } from "react";
import { useState } from 'react';
import axios from 'axios';
import { URL_API_private } from '../../providerContext/EndPoint';
import './Reports.css'

export const Reports = () => {
   
   
    const [mostrarColumnas, setMostrarColumnas] = useState(true);
    const endPoint = URL_API_private+"/data_tecnico";
    const endPointFdt = URL_API_private+"/fdt/getzone";
    const [dataTecnico, setDataTecnico] = useState([]);
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [filtro, setFiltro] = useState('nombre'); 
    const [termino, setTermino] = useState('');
    const [dataPorMes, setDataPorMes] = useState({}); 

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
            console.log("Datos Tecnicos obtenidos satisfactorimente..")
        } catch (error) {
            console.error(error)
        }
    }
    
    function getMes(data){
        const dataObjest = new Date(data);
        const mounth =  dataObjest.getMonth() +1;
        const day = dataObjest.getDate();
        const year = dataObjest.getFullYear();
        return day+"/"+mounth+"/"+year;
    }

    function getDia(data){
        const dataObjest = new Date(data);
        return dataObjest.getDate();
    }

    function getHora(data){
        const dataObjest = new Date(data);
        let minute = dataObjest.getMinutes();
        minute = minute < 10 ? "0"+minute : minute;
        return dataObjest.getHours()+":"+minute;
    }

    
    return (
        <div>
            <div classname='styleBusquedas'>
                <input 
                    type="text"
                    value={termino}
                    onChange={(e) => setTermino(e.target.value)}
                    placeholder="Ingrese el término de búsqueda"
                />
                <input 
                    type = 'date'
                    id = 'fecha_Inicio'
                    name = 'fecha_Inicio'
                    //value = {fecha_Inicio}
                    ></input>
                    
                <input 
                    type = 'date'
                    id = 'fecha_Final'
                    name = 'fecha_Final'
                    //value = {fecha_Final}
                ></input>

                <button className="stylessButoon" onClick={() => buscarDatos()}>Buscar</button>
            </div>
            <br></br>
            <div>
                <select value={filtro} onChange={(e) => setFiltro(e.target.value)}>
                    <option value="nombre">Dato tecnico</option>
                    <option value="producto">Posicion</option>
                    <option value="EstadoDt">NAP</option>
                    <option value="CajaNap">FDT</option>
                    <option value="fecha">ODF</option>D
                </select>
            </div> 
        <br></br>
        <div className="styleContentTable">
                <table className="styleTable">
                    <thead className="stylesHead">
                        <tr className="stylesHead">
                        <th className="stylesTh-Td">Dato tecnico anterior</th>
                        <th className="stylesTh-Td">Dato tecnico nuevo</th>
                        <th className="stylesTh-Td">Fecha de cambio</th>
                        <th className="stylesTh-Td">Hora</th>
                        <th className="stylesTh-Td">Analista</th>
                        <th className="stylesTh-Td">Observaciones</th>
                        </tr>
                    </thead>
                    <tbody className="stylesBody">
                    {dataTecnico.map((data) => (
                        <tr className="stylesTr" key={data.id}>
                            <td className="stylesTh-Td">{data.antiguaPosicion}</td>
                            <td className="stylesTh-Td">{data.nuevaPosicion}</td>
                            <td className="stylesTh-Td">{getMes(data.createdAt)}</td>
                            <td className="stylesTh-Td">{getHora(data.createdAt)}</td>
                            <td className="stylesTh-Td">{data.nombreCompleto}</td>
                            <td className="stylesTh-Td">{"Sin Observaciones"}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            
        </div>
        <br></br>
      </div>  
    );
};
