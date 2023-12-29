import React, { useEffect, useRef } from "react";
import { useState } from 'react';
import axios from 'axios';
import { URL_API_private } from '../../providerContext/EndPoint';
import './Reports.css'

export const Reports = () => {
   
    const endPoint = URL_API_private+"/data_tecnico";
    const [dataTecnico, setDataTecnico] = useState([]);
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [filtro, setFiltro] = useState('nombre'); 
    const [datos, setDatos] = useState([]);
    const [select, setSelectd] = useState("DATE");
    const [buscar, setBuscar] = useState("");
    const [fechaInicio, setFechaInicio] = useState("");
    const [fechaFinal, setFechaFinal] = useState("");


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
            setDatos(response.data);
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

    function getHora(data){
        const dataObjest = new Date(data);
        let minute = dataObjest.getMinutes();
        minute = minute < 10 ? "0"+minute : minute;
        return dataObjest.getHours()+":"+minute;
    }

    const filtrarDatos = () => {
        switch (filtro) {
            case 'posicicion':
                cambiosPosicion();
                break;
            case 'nap':
                cambiosNap();
                break;
            case 'fdt':
                cambiosFdt();
                break;
            case 'odf':
                cambiosOdf();
                break;
            default:
                break;
        }
    }

    const cambiosPosicion = async () => {
        console.log("Obteniendo los cambios de la posicion..")
        try {
            const response = await axios.get(endPoint+"/cambios_pos", config)
            setDatos(response.data);
            console.log("Cambios en la posicion obtenidos satisfactorimente..")
        } catch (error) {
            console.error(error)
        }
    }

    const cambiosNap = async () => {
        console.log("Obteniendo los cambios de la posicion..")
        try {
            const response = await axios.get(endPoint+"/cambios_nap", config)
            setDatos(response.data);
            console.log("Cambios en la posicion obtenidos satisfactorimente..")
        } catch (error) {
            console.error(error)
        }
    }

    const cambiosFdt = async () => {
        console.log("Obteniendo los cambios de la posicion..")
        try {
            const response = await axios.get(endPoint+"/cambios_fdt", config)
            setDatos(response.data);
            console.log("Cambios en la posicion obtenidos satisfactorimente..")
        } catch (error) {
            console.error(error)
        }
    }

    const cambiosOdf = async () => {
        console.log("Obteniendo los cambios de la posicion..")
        try {
            const response = await axios.get(endPoint+"/cambios_odf", config)
            setDatos(response.data);
            console.log("Cambios en la posicion obtenidos satisfactorimente..")
        } catch (error) {
            console.error(error)
        }
    }
    
    const filtrar = (searchValue) => {
        switch (searchValue) {
            case 'PROD':
                filtrarProduc();
                break;
            case 'DATE':
                filtrarDate();
                break
            default:
                console.log("No seleccionaste ningun metodo de busqueda")
                break;
        }
    }

    const filtrarProduc =  () => {
        if (buscar) {
            console.log("Buscando por producto: "+buscar)
            const resultadoBusqueda = dataTecnico.filter((elemento) => {
                return elemento.producto.toString().toLowerCase().includes(buscar.toLowerCase());
            });
            setDatos(resultadoBusqueda);
          } else {
            setDatos([]);
          }
    }
    
    const filtrarDate =  () => {

        console.log("Buscando por fecha: "+fechaInicio+" "+fechaFinal);
        /**
        if (searchValue) {
            console.log("Imprimiedo el termino a buscar: "+searchValue)
            const resultadoBusqueda = dataTecnico.filter((elemento) => {
                return elemento.producto.toString().toLowerCase().includes(searchValue.toLowerCase());
            });
            setDatos(resultadoBusqueda);
          } else {
            setDatos([]);
          }
           */
    }

    /**
    const filtrar = async (terminoBusqueda) => {

        if(terminoBusqueda){
            
            const resultadoBusqueda = dataTecnico.filter((elemento) => {
                console.log(elemento.producto);
                elemento.producto.includes(terminoBusqueda);
            });
            console.log("filtrado con exito");
            setDatos(resultadoBusqueda);
            
            // Transforma los datos para mostrarlos en una tabla transpuesta
            console.log("poniendo los datos filtrados a la tabla:", datos);//hasta aqui funciona
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
                console.log("exito");
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
        }

    }
     */


    return (
        <div >
            <div className="stylesEncabezadoAnalista">
                <select
                    className="stylesInput"
                    value={select}
                    onChange={(e) => setSelectd(e.target.value)}
                    >
                    <option value = 'DATE' >Fecha</option>
                    <option value = 'PROD'>Producto</option>
                </select>
                {select === 'PROD' ? (
                <input 
                    className="stylesInput"
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
                    className="stylesInput"
                    type = 'date'
                    id = 'fechaInicio'
                    name = 'fechaInicio'
                    value = {fechaInicio}
                    onChange={ (e) => setFechaInicio(e.target.value) }
                    />
                    
                <input 
                    className="stylesInput"
                    type = 'date'
                    id = 'fechaFinal'
                    name = 'fechaFinal'
                    value = {fechaFinal}
                    onChange={ (e) => setFechaFinal(e.target.value) }
                    ></input>
                    
                    </>
                )}
                <button  className='stylesButoon' onClick={ () => filtrar(select)}>Buscar</button>
                
                <button className='stylesButoon' onClick={cambiosPosicion}>filtrar pos</button>
                <button className='stylesButoon' onClick={cambiosOdf}>filtrar odf</button>
                <button className='stylesButoon' onClick={cambiosFdt}>filtrar fdt</button>
                <button className='stylesButoon' onClick={cambiosNap}>filtrar nap</button>
                <button className='stylesButoon'>filtrar virtual</button>
                <button className='stylesButoon'>mostrar todo</button>
            </div>
            <div>
                <select value={filtro} onChange={(e) => setFiltro(e.target.value)}>
                    <option value="nombre">Dato tecnico</option>
                    <option value="posicicion">Posicion</option>
                    <option value="nap">NAP</option>
                    <option value="fdt">FDT</option>
                    <option value="odf">ODF</option>D
                </select>
                <button className="stylesButoon2" onClick={() => filtrarDatos()}>Buscar</button>
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
                    {/**datos.map((data) => (
                    <ProdcutRow
                        product={data}
                    />))*/}
                    {datos.map((data) => (
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
