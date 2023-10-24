import React, { useEffect, useRef } from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_private } from '../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';
import Chart from 'chart.js/auto';
import './Reports.css'

export const Reports = () => {
   
   
    const [mostrarColumnas, setMostrarColumnas] = useState(true);
    const endPoint = URL_API_private+"/datatecnico";
    const [dataTecnico, setDataTecnico] = useState([]);
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [filtro, setFiltro] = useState('nombre'); 
    const [termino, setTermino] = useState('');
    //const chartRef = useRef(null);
    //const [chartData, setChartData] = useState

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

    
    const buscarDatos = () => {
        const resultados = dataTecnico.filter(data => {
            if (filtro === 'nombre') {
                return data.person.nombre.toLowerCase().includes(termino.toLowerCase());
            } else if (filtro === 'producto') {
                return data.num_producto.toString().includes(termino);
            } else if (filtro === 'EstadoDt' ) {
                return data.estadp_odt.toLowerCase().includes(termino.toLowerCase());
            } else if (filtro === 'CajaNap') {
                return data.caja_nap.toLowerCase().includes(termino.toLowerCase());
            } else if (filtro === 'fecha') {
                const fechaEnFormato = data.update_at.split('T')[0];
                return fechaEnFormato.includes(termino);
            }

            return false;
        });
    
        
        setDataTecnico(resultados);
    };

    // Obtener el número de registros por mes
    //const registrosPorMes = {};
    //dataTecnico.forEach(data => {
    //    const mes = new Date(data.created_at).getMonth() + 1;
    //    registrosPorMes[mes] = (registrosPorMes[mes] || 0) + 1;
    //});

    // Obtener las etiquetas y datos para el gráfico
    //const etiquetas = Object.keys(registrosPorMes).map(mes => `Mes ${mes}`);
    //const datos = Object.values(registrosPorMes);

    //useEffect(() => {

         // Destruir el gráfico existente
         //if (chartRef.current !== null) {
         //   chartRef.current.destroy();
       // }

        // Crear el gráfico cuando se actualicen los datos
        //const ctx = document.getElementById('grafico').getContext('2d');
        //new Chart(ctx, {
        //    type: 'bar',
        //    data: {
        //        labels: etiquetas,
        //        datasets: [{
        //            label: 'Número de registros',
        //           data: datos,
        //            backgroundColor: 'rgba(75, 192, 192, 0.2)',
        //            borderColor: 'rgba(75, 192, 192, 1)',
        //            borderWidth: 1
        //        }]
        //    },
        //    options: {
        //        scales: {
        //            y: {
        //                beginAtZero: true
        //            }
        //        }
        //    }

            

        //});

        //chartRef.current = newChart;

        // Limpiar el gráfico al desmontar el componente
        //return () => {
        //    if (chartRef.current) {
        //        chartRef.current.destroy();
        //    }
        //};

    //}, [chartData]); // Renderizar el gráfico cuando los datos se actualicen


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
        <div className="table-container">

            <div>
                <select value={filtro} onChange={(e) => setFiltro(e.target.value)}>
                    <option value="nombre">Nombre Analista Soporte</option>
                    <option value="producto">Producto</option>
                    <option value="EstadoDt">Estado Dt</option>
                    <option value="CajaNap">Caja Nap -Dato Tecnico Actual-</option>
                    <option value="fecha">Fecha - formato aaaa-mm-dd -</option>
                </select>
                <input 
                    type="text"
                    value={termino}
                    onChange={(e) => setTermino(e.target.value)}
                    placeholder="Ingrese el término de búsqueda"
                />
                <button onClick={() => buscarDatos()}>Buscar</button>
            </div>

            <button onClick={toggleColumnas}>Mostrar/Ocultar Columnas</button>
            {mostrarColumnas && (
                <table className="excel-table">
                    <thead className="table-header">
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
                    <tbody className="table-body">
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
