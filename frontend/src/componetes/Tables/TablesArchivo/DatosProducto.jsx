import React, { useState, useEffect } from 'react';
import TablaAdicional from './DastosNap';
import axios from 'axios';
import './TableArchive.css'
import botonmas from "../../../imagenes/botonmas.png"
import botonmenos from "../../../imagenes/botonmenos.png"
import { URL_API_private } from '../../../providerContext/EndPoint';
import {ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const ExcelTable = ({ producto }) => {
  const [loading, setLoading] = useState(false);
  const [showAdditionalTable, setShowAdditionalTable] = useState(false);
  const [imagenClicada, setImagenClicada] = useState(false);
  const [texto, setTexto] = useState('');
  const [napsCod, setNapsCod] = useState([]);
  const [sugesNapsCod, setSugesNapsCod] = useState([]);
  const token  = JSON.parse(localStorage.getItem('user_data')).token;
  const user_loguin  = JSON.parse(localStorage.getItem('user_data')).username;
  const endPoint = URL_API_private+ "/naps"
  const endPointDtaProduct = URL_API_private+"/orden_dia/producto/"+producto
  const endPointDataTecnico = URL_API_private +"/data_tecnico"
  const [dataProduct, setDataProdcut] = useState([]);
  const [isLLenado, setIsLlenado] = useState(false);

  useEffect(() => {
    getAlLCods();
    getProductos();
  }, []);

  const config = {
    headers:{
        Authorization: `Bearer ${token}`,
    }
  }

  const getProductos = async () => {
    try {
      console.log(endPointDtaProduct+" "+config.headers)
      const response = await axios.get(endPointDtaProduct, config);
      setDataProdcut(response.data);
      setIsLlenado(response.data[0].status)
      console.log(dataProduct);
      console.log("Products Recuperados exitosamente")
  } catch (error) {
      console.error("Error del servidor: "+error.response);
  }
  }
  const getAlLCods = async () => {
    try {
        const response = await axios.get(endPoint, config);
        setNapsCod(response.data);
        console.log("NAPs Recuperados exitosamente")
    } catch (error) {
        console.error("Error del servidor: "+error.response);
    }
  }
  
  const toggleAdditionalTable = () => {
    setShowAdditionalTable(!showAdditionalTable);
    setImagenClicada(!imagenClicada);
    
  };

  const guardarDato = async (e) => {
    setLoading(true);
    e.preventDefault()  
    try {
      const response = await axios.post(endPointDataTecnico, {
        nuevoNap: texto,
        antogupNap: dataProduct[0].datoTecnico,
        producto: producto,
        username: user_loguin,
        observaciones: "Sin Observaciones"
      }, {
          headers: config.headers,
      });
      console.log("Respuesta del Servidor: ", response.data);
      console.log("Registro Tecnico Registrado exitosamente.");
      toast.success("Registro Tecnico Registrado exitosamente");
      setLoading(false);
      setIsLlenado(true);
    }catch (error) {
      if(error.response.data){
        console.error("Error del Servidor: "+ error.response.data.message);
        toast.error(`Error del Servidor: ${error.response.data.message}`);
      }
      setLoading(false);
    }
  };

  const handleSearch = async (searchValue) => {
    if (searchValue) {
      const filterSuges = napsCod.filter((nap) =>
        nap.cod.toLowerCase().includes(searchValue.toLowerCase())
      );
      setSugesNapsCod(filterSuges);
    } else {
      setSugesNapsCod([]);
    }
  }

  const handleSuggestionClick = (suggestion) => {
    setTexto(suggestion);
    setSugesNapsCod([]); // Oculta la lista de sugerencias después de hacer clic
  };
  //const napSet = new Set(datosTablaAdicional.map((dato) => dato.nap));

  return (<>
    <div className='styleContentTable'>
    <table className='styleTable'>
      <thead className='stylesHead'>
        <tr>
          <tr></tr>
          <th className='stylesTh-Td'>NAP</th>
          <th className='stylesTh-Td'>Posicion</th>
          <th className='stylesTh-Td'>NAP Utilizado</th>
          <th className='stylesTh-Td'>Datos Tecnicos</th>
          <th className='stylesTh-Td'>Zona</th>
          <th className='stylesTh-Td'>Ubicacion</th>
          <th className='stylesTh-Td'>Direccion</th>
        </tr>
      </thead>
      <tbody className='table-body-desp1'>
        {dataProduct.length > 0 && (
          <tr className='stylesTr'>
            <td>
            <img
                src={imagenClicada ? botonmenos : botonmas}
                alt="Mostrar Detalles"
                onClick={toggleAdditionalTable}
                style={{ cursor: 'pointer', width: '40px', height: '40px' }}
            />
            </td>
            <td className='stylesTh-Td'>{dataProduct[0].nap}</td>
            <td className='stylesTh-Td' >{dataProduct[0].posicion}</td>
            <td className='stylesTh-Td' >
              {isLLenado? "Ya fue llenado": (<>
                <input
              type="text"
              value={texto}
              onChange={(e) => {
                const searchValue = e.target.value;
                handleSearch(searchValue);
                setTexto(searchValue);
              }}
              placeholder="Ingrese dato"
              list="suggestionsList"
            />
                <datalist id="suggestionsList">
                  {sugesNapsCod.map((nap) => (
                    <option key={nap.id} value={nap.cod} onClick={() => handleSuggestionClick(nap.cod)} />
                  ))}
                </datalist>
                <button className='stylesButoon' onClick={() => guardarDato(event)}>
                  {loading ? <FaSpinner className="loadingIcon"/> : "Guardar"}
                </button>
            </td>
            <td className='stylesTh-Td' >{dataProduct[0].datoTecnico}</td>
            <td className='stylesTh-Td' >{dataProduct[0].zona}</td>
            <td className='stylesTh-Td' >{dataProduct[0].ubicacion}</td>
            <td className='stylesTh-Td' >{dataProduct[0].direccion}</td>
          </tr>
          )}
      </tbody>
    </table>
    {showAdditionalTable && <TablaAdicional nap={dataProduct[0].nap} product={producto}/>}
    <ToastContainer/>
    </div>
  </>);
};

export default ExcelTable;