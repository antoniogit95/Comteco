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
        <div className="table-container">
                <table className="excel-table">
                    <thead className="table-header">
                        <tr>
                            <th className="white-color">ID</th>
                            <th className="white-color">MES</th>
                            <th className="white-color">FECHA</th>
                            <th className="white-color">TIPO DE ORDEN</th>
                            <th className="white-color">ZONA</th>
                            <th className="white-color">PRODUCTO</th>
                            <th className="white-color">DATO TECNICO ACTUAL</th>
                            <th className="white-color">DATO TECNICO ANTERIOR</th>
                            <th className="white-color">HORA</th>
                            <th className="white-color">ESTADO DATO TECNICO</th>
                            <th className="white-color">OBSERVACION</th>
                            <th className="white-color">TECNICO</th>
                            <th className="white-color">ANALISTA SOPORTE</th>
                            <th className="white-color">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody className="table-body">
                       {dataTecnico.map( data => (
                        <tr key={data.id_reg_data_tec}>
                            <td>{data.id_reg_data_tec}</td>
                            <td>{getMes(data.created_at)}</td>
                            <td>{getDia(data.created_at)}</td>
                            <td>{"----"}</td>
                            <td>{obtenerZona(data.caja_nap)}</td>
                            <td>{data.num_producto}</td>
                            <td>
                            {editando === data.id ? (
                            <input
                            type="text"
                            value={datosEditados[data.id]?.caja_nap || data.caja_nap}
                            onChange={(e) =>
                            setDatosEditados({
                            ...datosEditados,
                            [data.id]: { ...datosEditados[data.id], caja_nap: e.target.value },
                            })
                            }
                            />
                            ) : (
                            data.caja_nap
                            )}
                            </td>
                            <td>{obtenerDataTecnicoAnterior(data.num_producto)}</td>
                            <td>{getHora(data.created_at)}</td>
                            <td>{data.estadp_odt}</td>
                            <td>{data.obasrvaciones}</td>
                            <td>{"----"}</td>
                            <td>{data.person.nombre + " "+ data.person.apellidos}</td>
                            <td>
                                {editando === data.id ? (
                                <div>
                                    <button className='stylesButoon'  onClick={() => manejarGuardar(data.id)}>Guardar</button>
                                    <button className='stylesButoon'  onClick={manejarCancelar}>Cancelar</button>
                                </div>  
                                ) : (
                                    <button className='stylesButoon'  onClick={() => manejarEdicion(data.id)}>Editar</button>
                                )}
                            </td>
                            
                        </tr>
                       ))}
                    </tbody>
                </table>
            
        </div>
        
        <div>
            
            
            <div>
                <p className="m-2"><b>Gráfico de Líneas: Cantidad de Datos por Mes</b></p>
                <div style={{ width: "450px", height: "230px" }}>
                    <Line data={midata} />
                </div>
            </div>
        </div>
      </div>  
    );
};
