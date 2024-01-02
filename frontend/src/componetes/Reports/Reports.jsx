import React, { useEffect, useRef } from "react";
import { useState } from 'react';
import axios, { toFormData } from 'axios';
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

    const cambiosPosicion = () => {
        console.log("Obteniendo los cambios de la posicion..")
        try {
            const response = [];
            for (let i = 0; i < dataTecnico.length; i++) {
                const cadena1 = dataTecnico[i].antiguaPosicion;
                const cadena2 = dataTecnico[i].nuevaPosicion;
                const antiguaPosicion = cadena1.split("-");
                const nuevaPosicion = cadena2.split("-")
                if(antiguaPosicion.length === 4 && nuevaPosicion.length === 4){
                    const antigNap = antiguaPosicion[0]+"-"+antiguaPosicion[1]+"-"+antiguaPosicion[2];
                    const nuevoNap = nuevaPosicion[0]+"-"+nuevaPosicion[1]+"-"+nuevaPosicion[2];
                    if(antigNap === nuevoNap){
                        if(antiguaPosicion[3] !== nuevaPosicion[3]){
                            console.log("posiciones diferentes")
                            response.push(dataTecnico[i]);
                        }
                    }else{
                        console.log("naps desiguales");
                    }
                }
            }
            setDatos(response);
        } catch (error) {
            console.error(error)
        }
    }

    const cambiosNap = async () => {
        console.log("Obteniendo los cambios de la posicion..")
        try {
            const response = [];
            for (let i = 0; i < dataTecnico.length; i++) {
                const cadena1 = dataTecnico[i].antiguaPosicion;
                const cadena2 = dataTecnico[i].nuevaPosicion;
                const antiguaPosicion = cadena1.split("-");
                const nuevaPosicion = cadena2.split("-")
                if(antiguaPosicion.length === 4 && nuevaPosicion.length === 4){
                    const antigFdt = antiguaPosicion[0]+"-"+antiguaPosicion[1];
                    const nuevoFdt = nuevaPosicion[0]+"-"+nuevaPosicion[1];
                    if(antigFdt === nuevoFdt){
                        if(antiguaPosicion[2] !== nuevaPosicion[2]){
                            console.log("posiciones diferentes")
                            response.push(dataTecnico[i]);
                        }
                    }else{
                        console.log("naps desiguales");
                    }
                }
            }
            setDatos(response);
        } catch (error) {
            console.error(error)
        }
    }

    const cambiosFdt = async () => {
        console.log("Obteniendo los cambios de la posicion..")
        try {
            const response = [];
            for (let i = 0; i < dataTecnico.length; i++) {
                const cadena1 = dataTecnico[i].antiguaPosicion;
                const cadena2 = dataTecnico[i].nuevaPosicion;
                const antiguaPosicion = cadena1.split("-");
                const nuevaPosicion = cadena2.split("-")
                if(antiguaPosicion.length === 4 && nuevaPosicion.length === 4){
                    const antigOdf = antiguaPosicion[0];
                    const nuevoOdf = nuevaPosicion[0];
                    if(antigOdf === nuevoOdf){
                        if(antiguaPosicion[1] !== nuevaPosicion[1]){
                            console.log("posiciones diferentes")
                            response.push(dataTecnico[i]);
                        }
                    }else{
                        console.log("naps desiguales");
                    }
                }
            }
            setDatos(response);
        } catch (error) {
            console.error(error)
        }
    }

    const cambiosOdf = async () => {
        console.log("Obteniendo los cambios de la posicion..")
        try {
            const response = [];
            for (let i = 0; i < dataTecnico.length; i++) {
                const cadena1 = dataTecnico[i].antiguaPosicion;
                const cadena2 = dataTecnico[i].nuevaPosicion;
                const antiguaPosicion = cadena1.split("-");
                const nuevaPosicion = cadena2.split("-")
                if(antiguaPosicion.length === 4 || antiguaPosicion.length === 2){
                    if(antiguaPosicion[0] !== nuevaPosicion[0]){
                        console.log("posiciones diferentes")
                        response.push(dataTecnico[i]);
                    }else{
                        console.log("naps desiguales");
                    }
                }
            }
            setDatos(response);
        } catch (error) {
            console.error(error)
        }
    }
    
    const filtrar = (searchValue) => {
        switch (searchValue) {
            case 'PROD':
                getAllDataTecnicoByProduct();
                break;
            case 'DATE':
                getAllDataTecnicoByDate();
                break
            default:
                console.log("No seleccionaste ningun metodo de busqueda")
                break;
        }
    }

    const filtrarProduc = async () => {
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
    
    const getAllDataTecnicoByProduct = async() =>{
        try {
            const response = await axios.get(endPoint+"/producto/"+buscar, config)
            setDataTecnico(response.data);
            setDatos(response.data);
            console.log("Datos Tecnicos  Por producto obtenidos satisfactormente..")
        } catch (error) {
            console.error(error)
        }
    }

    const getAllDataTecnicoByDate = async () => {

        console.log("Buscando por fecha: "+fechaInicio+" "+fechaFinal);
        try {
            console.log(endPoint+"/date")
            const response = await axios.post(endPoint + "/date", {
                fechaInicio: fechaInicio,
                fechaFinal: fechaFinal
                },config);
            setDataTecnico(response.data);
            setDatos(response.data);
            console.log("Datos Tecnicos  Por Fechas obtenidos satisfactormente..")
        } catch (error) {
            console.error(error)
        }
    }

    const filtrarDatosOrigneCom = async () => {
        console.log("Filtrando datos desde el origen COM-00-00-0000")
        try {
            const response = [];
            for (let i = 0; i < dataTecnico.length; i++) {
                const cadena1 = dataTecnico[i].antiguaPosicion;
                const cadena2 = dataTecnico[i].nuevaPosicion;
                const antiguaPosicion = cadena1.split("-");
                const nuevaPosicion = cadena2.split("-")
                if(antiguaPosicion.length === 4){
                    const antigFdt = antiguaPosicion[0]+"-"+antiguaPosicion[1]; 
                    if(antigFdt === "COM-00"){
                        if(antiguaPosicion[0] !== nuevaPosicion[0]){
                            console.log("posiciones diferentes")
                            response.push(dataTecnico[i]);
                        }
                    }else{
                        console.log("naps desiguales");
                    }
                }
            }
            setDatos(response);
        } catch (error) {
            console.error(error)
        }
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
                <button className='stylesButoon' onClick={filtrarDatosOrigneCom}>filtrar virtual</button>
                <button className='stylesButoon' onClick={() => setDatos(dataTecnico)}>mostrar todo</button>
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
