import React from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_public } from '../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';
import './Reports.css'

export const Reports = () => {
   

    const [mostrarColumnas, setMostrarColumnas] = useState(true); 

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
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            )}
        </div>
    );
};
