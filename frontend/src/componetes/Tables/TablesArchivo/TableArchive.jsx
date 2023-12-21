import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './TableArchive.css'
import { URL_API_private } from '../../../providerContext/EndPoint';
import TablaAdicional from './DatosProducto';
import botonmas from "../../../imagenes/botonmas.png"
import botonmenos from "../../../imagenes/botonmenos.png"

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
    const [imagenClicada, setImagenClicada] = useState(false);
    const [productoSeleccionado, setProductoSeleccionado] = useState('');
    const [selectedRowPosition, setSelectedRowPosition] = useState(null);

    useEffect( () => {
        getAllDatos();
        //getAllDatosPlanes();

        //console.log(datosPlanes);
    }, [])

    const config = {
        headers:{
            Authorization: `Bearer ${token}`,
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

    const getAllDatosPlanes = async () => {
        try {
            const response = await axios.get(endPointPlanes, config);
            setDatosPlanes(response.data);
            setRescatarDatos(response.data);
            console.log("Datos de planes rescatados exitosamente");
        } catch (error) {
            console.log('Error al obtener datos de planes:', error.response);
        }
    }

    const toggleAdditionalTable = (producto) => {
        setShowAdditionalTable(!showAdditionalTable);
        setImagenClicada(!imagenClicada);
        setProductoSeleccionado(producto);
        setSelectedRowPosition(position);
        console.log(producto);
      };

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
 
    const productosUnicos = new Set(datos.map((dato) => dato.producto));

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
                      
                      <th className='stylesTh-Td'>Prodcuto</th>
                      <th className='stylesTh-Td'>Estado Orden</th>
                      <th className='stylesTh-Td'>Plan Comercial</th>
                      <th className='stylesTh-Td'>Tipo de Tramite</th>
                      <th className='stylesTh-Td'>Tipo de Trabajo</th>
                      <th className='stylesTh-Td'>Tipo de Cliente</th>
                    </tr>
                </thead>
                <tbody className='stylesBody'>
        {[...productosUnicos].map((productoUnico, index) => {
          const primerDatoProducto = datos.find((dato) => dato.producto === productoUnico);
          return (
            <tr className='stylesTr' key={primerDatoProducto.id}>
              <td>
                {primerDatoProducto.producto}
                <img
                  src={imagenClicada ? botonmenos : botonmas}
                  alt='Mostrar Detalles'
                  onClick={() => toggleAdditionalTable(primerDatoProducto.producto, index)}
                  style={{ cursor: 'pointer', width: '40px', height: '40px' }}
                />
              </td>
              <td className='stylesTh-Td'>{primerDatoProducto.estadoOrden}</td>
              <td className='stylesTh-Td'>{primerDatoProducto.planComercial}</td>
              <td className='stylesTh-Td'>{primerDatoProducto.tipoTramite}</td>
              <td className='stylesTh-Td'>{primerDatoProducto.tipoTrabajo}</td>
              <td className='stylesTh-Td'>{primerDatoProducto.tipoCliente}</td>
            </tr>
          );
        })}
      </tbody>
          </table>
          {showAdditionalTable && (
  <div
    className='additional-table-container'
    style={{
      top: selectedRowPosition * 50 + 375 + 'px',
      position: 'absolute',
      zIndex: 1000, // Ajusta el valor según sea necesario
    }}
  >
    <TablaAdicional producto={productoSeleccionado} />
  </div>
)}

        </div>
      </div>
    );
}