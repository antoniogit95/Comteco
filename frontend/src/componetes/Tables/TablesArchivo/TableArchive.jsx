import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './TableArchive.css'
import { URL_API_private } from '../../../providerContext/EndPoint';

export const  TableArchive = () => {
    const [datos, setDatos] = useState([]);
    const [rescatarDatos, setRescatarDatos] = useState([])
    const [select, setSelectd] = useState("");
    const [buscar, setBuscar] = useState("");
    const endPoint = URL_API_private+"/files/get"
    const token  = JSON.parse(localStorage.getItem('user_data')).token
    const [datosTranspuestos, setDatosTranspuestos] = useState([]);


    useEffect( () => {
        getAllDatos();
    }, [])

    const config = {
        headers:{
            Authorization: `Bearer ${token}`,
        }
    }

    const getAllDatos = async () => {
        try {

            console.log(endPoint)
            console.log(config.headers)
            const response = await axios.get(endPoint, config);
            setDatos(response.data);
            setRescatarDatos(response.data);
            console.log("datos rescatados exitosamente")
        } catch (error) {
            console.log('Error al obtener datos:', error.response);
        }
        
    }

    const filtrar = (terminoBusqueda, busquedaPor) => {
        console.log(terminoBusqueda +" - "+ busquedaPor + "mostrando algo")
        if(terminoBusqueda !== ''){
            var resultadoBusqueda = rescatarDatos.filter((elemento) => {
                if (select === 'cliente') {
                    return elemento.cliente.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'fecha_CREATION') {
                    const fechaEnFormato = elemento.fecha_CREACION.split('T')[0];
                    return fechaEnFormato.includes(termino);
                } else if (select === 'area_servicio') {
                    return elemento.area_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_tipo_sol') {
                    return elemento.cod_TIPO_SOL.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_TIPO_SOL') {
                    return elemento.tipo_SOLICITUD.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_TIPO') {
                    return elemento.cod_TIPO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'tipo_TRABAJO') {
                    return elemento.tipo_TRABAJO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_PLAN_COMERCIAL') {
                    return elemento.cod_PLAN_COMERCIAL.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'plan_COMERCIAL') {
                    return elemento.plan_COMERCIAL.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'componente') {
                   return elemento.componente.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'clase_SERVICIO') {
                    return elemento.clase_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'area_servicio') {
                    return elemento.area_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'ubicacion') {
                    return elemento.ubicacion.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'producto') {
                    return elemento.producto.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'orden') {
                    return elemento.orden.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'desc_servicio') {
                    return elemento.desc_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                }else if (select === 'numero_servicio') {
                    return elemento.numero_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'estado_componente') {
                    return elemento.estado_COMPONENTE.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'nap') {
                    return elemento.nap.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'posicion') {
                    return elemento.posicion.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'descripcion') {
                    return elemento.descripcion.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cliente') {
                    return elemento.cliente.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'direccion') {
                    return elemento.direccion.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_estado_ot') {
                    return elemento.cod_ESTADO_OT.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'unidad_operativa') {
                    return elemento.unidad_OPERATIVA.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                }
            });
            setDatos(resultadoBusqueda);

            // Transforma los datos para mostrarlos en una tabla transpuesta
            const transpuestos = resultadoBusqueda.reduce((acc, dato) => {
                Object.keys(dato).forEach(key => {
                    if (!acc[key]) {
                        acc[key] = [];
                    }
                    // Evita valores duplicados en la misma clave
                    if (!acc[key].includes(dato[key])) {
                        acc[key].push(dato[key]);
                    }
                });
                return acc;
        }, {});

        const transpuestosArray = {};
        Object.keys(transpuestos).forEach(key => {
            transpuestosArray[key] = Array.from(transpuestos[key]);
        });

        // Asigna los datos transformados al estado
        setDatosTranspuestos(Object.entries(transpuestos));

        }
    }
    
    return (

        <div>
        <div>
            
            <input 
                type='text'
                id='buscar'
                name='buscar'
                placeholder='escribe tu elemento de busqueda'
                value={buscar}
                onChange={ (e) => setBuscar(e.target.value) }
            ></input>
            <select 
                value={select}
                onChange={(e) => setSelectd(e.target.value)}
                >
                <option value = '' >seleccione un elemnto</option>
                <option value = 'fecha_CREACION' >FECHA_CREACION</option>
                <option value = 'cod_tipo_sol'>COD_TIPO_SOL</option>
                <option value = 'cod_TIPO_SOL'>TIPO_SOLICITUD</option>
                <option value = 'cod_TIPO'>COD_TIPO</option>
                <option value = 'tipo_TRABAJO'>TIPO_TRABAJO</option>
                <option value = 'cod_PLAN_COMERCIAL'>COD_PLAN_COMERCIAL</option>
                <option value = 'plan_COMERCIAL'>PLAN_COMERCIAL</option>
                <option value = 'componente'>COMPONENTE</option>
                <option value = 'clase_SERVICIO'>CLASE_SERVICIO</option>
                <option value = 'area_servicio'>AREA_SERVICIO</option>
                <option value = 'ubicacion'>UBICACION</option>
                <option value = 'producto'>PRODUCTO</option>
                <option value = 'orden'>ORDEN</option>
                <option value = 'desc_servicio'>DESC_SERVICIO</option>
                <option value = 'numero_servicio'>NUMERO_SERVICIO</option>
                <option value = 'estado_componente'>ESTADO_COMPONENTE</option>
                <option value = 'nap'>NAP</option>
                <option value = 'posicion'>POSICION</option>
                <option value = 'descripcion'>DESCRIPCION</option>
                <option value = 'cliente'>CLIENTE</option>
                <option value = 'direccion'>DIRECCION</option>
                <option value = 'cod_estado_ot'>COD_ESTADO_OT</option>
                <option value = 'unidad_operativa'>UNIDAD_OPERATIVA</option>
            </select>
            <button onClick={ () => filtrar(buscar, select)}>Buscar</button>
            <div className='table-container'>
            <table className='excel-table'>
                <thead className='table-header'>
                    <tr>
                        <th className='white-color'>ID</th>
                        <th className='white-color'>FECHA_CREACION</th>
                        <th className='white-color'>COD_TIPO_SOL</th>
                        <th className='white-color'>TIPO_SOLICITUD</th>
                        <th className='white-color'>COD_TIPO</th>
                        <th className='white-color'>TIPO_TRABAJO</th>
                        <th className='white-color'>COD_PLAN_COMERCIAL</th>
                        <th className='white-color'>PLAN_COMERCIAL</th>
                        <th className='white-color'>COMPONENTE</th>
                        <th className='white-color'>CLASE_SERVICIO</th>
                        <th className='white-color'>AREA_SERVICIO</th>
                        <th className='white-color'>UBICACION</th>
                        <th className='white-color'>PRODUCTO</th>
                        <th className='white-color'>ORDEN</th>
                        <th className='white-color'>DESC_SERVICIO</th>
                        <th className='white-color'>NUMERO_SERVICIO</th>
                        <th className='white-color'>ESTADO_COMPONENTE</th>
                        <th className='white-color'>NAP</th>
                        <th className='white-color'>POSICION</th>
                        <th className='white-color'>DESCRIPCION</th>
                        <th className='white-color'>CLIENTE</th>
                        <th className='white-color'>DIRECCION</th>
                        <th className='white-color'>COD_ESTADO_OT</th>
                        <th className='white-color'>UNIDAD_OPERATIVA</th>
                    </tr>
                </thead>
                <tbody className='table-body'>
                    {datos.map((dato) => (
                        <tr key={dato.id}>
                            <td>{dato.id}</td>
                            <td>{dato.fecha_CREACION}</td>
                            <td>{dato.cod_TIPO_SOL}</td>
                            <td>{dato.tipo_SOLICITUD}</td>
                            <td>{dato.cod_TIPO}</td>
                            <td>{dato.tipo_TRABAJO}</td>
                            <td>{dato.cod_PLAN_COMERCIAL}</td>
                            <td>{dato.plan_COMERCIAL}</td>
                            <td>{dato.componente}</td>
                            <td>{dato.clase_SERVICIO}</td>
                            <td>{dato.area_SERVICIO}</td>
                            <td>{dato.ubicacion}</td>
                            <td>{dato.producto}</td>
                            <td>{dato.orden}</td>
                            <td>{dato.desc_SERVICIO}</td>
                            <td>{dato.numero_SERVICIO}</td>
                            <td>{dato.estado_COMPONENTE}</td>
                            <td>{dato.nap}</td>
                            <td>{dato.posicion}</td>
                            <td>{dato.descripcion}</td>
                            <td>{dato.cliente}</td>
                            <td>{dato.direccion}</td>
                            <td>{dato.cod_ESTADO_OT}</td>
                            <td>{dato.unidad_OPERATIVA}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            </div>
        </div>
        <h2>Datos Campo / valor</h2>
        <div className='table-container'>
       <table className='excel-table'>
        <thead className='table-header'>
            <tr>
                <th className='white-color'>Campo</th>
                <th className='white-color'>Valor</th>
            </tr>
        </thead>
        <tbody className='table-body'>
            {datosTranspuestos.map((dato, index) => (
                <tr key={index}>
                    <td>{dato[0]}</td>
                    <td>{dato[1].join(', ')}</td> {/* Unir valores con coma */}
                </tr>
            ))}
        </tbody>
    </table>
</div>


        </div>
    );
}