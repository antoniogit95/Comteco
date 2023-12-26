import React, { useEffect, useRef } from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_private } from '../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';
import Chart from 'chart.js/auto';
import './Reports.css'
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { Line } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    Filler,
} from 'chart.js';


export const Reports = () => {
   
   
    const [mostrarColumnas, setMostrarColumnas] = useState(true);
    const endPoint = URL_API_private+"/datatecnico";
    const endPointFdt = URL_API_private+"/fdt/getzone";
    const [dataTecnico, setDataTecnico] = useState([]);
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [filtro, setFiltro] = useState('nombre'); 
    const [termino, setTermino] = useState('');
    const [dataPorMes, setDataPorMes] = useState({});
    const [datosEditados, setDatosEditados] = useState({});
    const [editando, setEditando] = useState(null);  

    ChartJS.register(
        CategoryScale,
        LinearScale,
        PointElement,
        LineElement,
        Title,
        Tooltip,
        Legend,
        Filler
    );
    
    
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

    useEffect(() => {
        // Calcula la cantidad de datos por mes
        const dataPorMesCalculada = dataTecnico.reduce((acc, data) => {
            const mes = new Date(data.created_at).getMonth(); // Obtiene el mes (0-11)
            acc[mes] = (acc[mes] || 0) + 1; // Incrementa la cantidad de datos para el mes
            return acc;
        }, []);

        // Convierte el objeto a un array para Chart.js
        setDataPorMes(Object.values(dataPorMesCalculada));
    }, [dataTecnico]);

    const manejarEdicion = (id) => {
        setEditando(id);
      };
    
      const manejarGuardar = (id) => {
        // Guardar los datos editados y salir del modo de edición
        setEditando(null);
        // Aquí deberías enviar los datos editados al servidor o realizar la lógica necesaria para guardarlos
      };

      const manejarCancelar = () => {
        setEditando(null);
        setDatosEditados({});
      };

    const meses = [
        "Octubre","Noviembre","Diciembre"
    ];

    const midata = {
        labels: meses,
        datasets: [
            {
                label: 'Cantidad de Datos',
                data: dataPorMes,
                fill: true,
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            }
        ]
    }; 

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

    function getMes(data){
        const dataObjest = new Date(data);
        const mounth =  dataObjest.getMonth() +1;
        switch (mounth) {
            case 1:
                return 'Enero';
                break;
            case 2:
                return 'Febrero';
                break;
            case 3:
                return 'Marzo';
                break;
            case 4:
                return 'Abril';
                break;
            case 5:
                return 'Mayo';
                break;
            case 6:
                return 'Junio';
                break;
            case 7:
                return 'Julio';
                break;
            case 8:
                return 'Agosto';
                break;
            case 9:
                return 'Septiembre';
                break;
            case 10:
                return 'Octubre';
                break;
            case 11:
                return 'Noviembre';
                break;
            case 12:
                return 'Diciembre';
                break;
            default:
                return '-------'
                break;
        }
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

    const obtenerZona = (odf) => {
        console.log("obtenienso zona");
        const array = odf.split('-');
        if(array.length === 4){
            const codFDT = array[0] + '-'+ array[1];
            try {
                const response = axios.get(endPointFdt+'/'+codFDT, config);
                return response.data;
            } catch (error) {
                return response.error;
            }
        }
        return "Sin Zona";
    }

    function obtenerDataTecnicoAnterior(odf){
        return 'Sin Dato Tecnico';
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
        <div className="table-container">
                <table className="excel-table">
                    <thead className="table-header">
                        <tr>
                        <th className="white-color">Dato tecnico anterior</th>
                        <th className="white-color">Dato tecnico nuevo</th>
                        <th className="white-color">Fecha de cambio</th>
                        <th className="white-color">Hora</th>
                        <th className="white-color">Analista</th>
                        <th className="white-color">Observaciones</th>
                        </tr>
                    </thead>
                    <tbody className="table-body">
                       
                    </tbody>
                </table>
            
        </div>
        <br></br>
      </div>  
    );
};
