import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './TableArchive.css'
import { URL_API_private } from '../../../providerContext/EndPoint';
import TablaAdicional from './DatosProducto';
import { ProdcutRow } from './ProductRow';
import { format } from 'date-fns';

export const  TableArchive = () => {
    const [datos, setDatos] = useState([]);
    const [datosPlanes, setDatosPlanes] = useState([]);
    const [rescatarDatos, setRescatarDatos] = useState([])
    const [select, setSelectd] = useState("");
    const [buscar, setBuscar] = useState("");
    const endPoint = URL_API_private+"/orden_dia/resumido"
    const endPointPlanes = URL_API_private+"/plancomercial"
    const token  = JSON.parse(localStorage.getItem('user_data')).token
    const [datosTranspuestos, setDatosTranspuestos] = useState([]);
    const [datosPlanesFiltrados, setDatosPlanesFiltrados] = useState([]);
    const [datosEditados, setDatosEditados] = useState({});
    const [editando, setEditando] = useState(null);       
    const [showAdditionalTable, setShowAdditionalTable] = useState(false);
    const [productoSeleccionado, setProductoSeleccionado] = useState('');
    const [selectedRowPosition, setSelectedRowPosition] = useState(null);
    const endPointByDate = URL_API_private+"/orden_dia/date"

    useEffect( () => {
        //getAllDatos();
        getAllDataTecnicoByDate();
    }, [])

    const config = {
        headers:{
            Authorization: `Bearer ${token}`,
        }
    }

    const getAllDataTecnicoByDate = async () => {
        const fechaActual = format(new Date(), 'yyyy-MM-dd');
        console.log("Imprimeido las fechas: "+fechaActual + "  "+fechaActual );
        if(fechaActual){
            console.log("Buscando por fecha: "+fechaActual+" "+fechaActual);
            try {
                console.log(endPointByDate);
                const response = await axios.post(endPointByDate, {
                    fechaInicio: fechaActual,
                    fechaFinal: fechaActual
                    },config);
                setRescatarDatos(response.data);
                setDatos(response.data);
                console.log("Orden Dia Por Fechas obtenidos satisfactormente..")
            } catch (error) {
                console.error(error)
            }
        }
    }

    const getAllDatos = async () => {
        try {

            console.log(endPoint)
            const response = await axios.get(endPoint, config);
            setDatos(response.data);
            setRescatarDatos(response.data);
            console.log("datos rescatados exitosamente")
        } catch (error) {
            console.log('Error al obtener datos:', error.response);
            console.log('Error completo:', error);
        }
        
    }
    
    const filtrar = (terminoBusqueda, busquedaPor) => {
        console.log(terminoBusqueda +" - "+ busquedaPor + "mostrando algo")
        console.log("Filtrando...");
        if(terminoBusqueda !== ''){
            const resultadoBusqueda = rescatarDatos.filter((elemento) => {
                if (select === 'cliente') {
                    return elemento.cliente.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'fecha_CREATION') {
                    const fechaEnFormato = elemento.fecha_CREACION.split('T')[0];
                    return fechaEnFormato.includes(terminoBusqueda);
                } else if (select === 'producto') {
                    return elemento.producto.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                }
                return true;
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

        setDatosTranspuestos(Object.entries(transpuestos));
      
        const datosPlanesFiltrados = datosPlanes.filter((datop) => {
            const velocidadEnDatop = datop.codLab;
            
            return resultadoBusqueda.some((dato) => {
                const primerosCuatro = dato.clase_SERVICIO.slice(0, 4);
                //console.log('primeros cuatro',primerosCuatro);
                const velocidadEnDatos = `${dato.cod_PLAN_COMERCIAL}-${primerosCuatro}`;
                //console.log('velocidad en datos',velocidadEnDatop);
                return velocidadEnDatos === velocidadEnDatop;
            });
        });
    
        setDatosPlanesFiltrados(datosPlanesFiltrados);


        resultadoBusqueda.forEach((dato) => {
            const velocidadEnDatos = dato.PLANES_VELOCIDAD.toString().toLowerCase();
        });
        
        datosPlanes.forEach((datoPlan) => {
            const velocidadEnDatop = datoPlan.cod_lab.toString().toLowerCase();
        });
        }

    }

    return (

      <div className='stylesTableArchiveJose'>
        <div>
          <select 
              value={select}
              onChange={(e) => setSelectd(e.target.value)}
              >
              <option value = 'fecha_CREACION' >Fecha</option>
              <option value = 'producto'>Producto</option>
          </select>
          {select === 'producto' ? (
          <input 
              type='text'
              id='buscar'
              name='buscar'
              placeholder='Elemento de búsqueda'
              value={buscar}
              onChange={ (e) => setBuscar(e.target.value) }
          ></input>
          ) : (
          <>
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
            
            </>
          )}
          <button  className='stylesButoon2' onClick={ () => filtrar(buscar, select)}>Buscar</button>
          <br></br>
        </div>
        <div className='styleContentTable' >
          <table className='styleTable'>
                <thead className='stylesHead'>
                    <tr className='stylesHead'>
                      <th className='stylesTh-Td'></th>
                      <th className='stylesTh-Td'>Prodcuto</th>
                      <th className='stylesTh-Td'>Estado Orden</th>
                      <th className='stylesTh-Td'>Plan Comercial</th>
                      <th className='stylesTh-Td'>Tipo de Tramite</th>
                      <th className='stylesTh-Td'>Tipo de Trabajo</th>
                      <th className='stylesTh-Td'>Tipo de Cliente</th>
                    </tr>
                </thead>
                <tbody className='stylesBody'>
                {datos.map((data) => (
                  <ProdcutRow
                    product={data}
                  />
                ))}
                </tbody>
            </table>
        </div>
      </div>
    );
}