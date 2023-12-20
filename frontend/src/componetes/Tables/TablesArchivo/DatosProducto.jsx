import React, { useState, useEffect } from 'react';
import TablaAdicional from './DastosNap';
import axios from 'axios';
import './TableArchive.css'
import botonmas from "../../../imagenes/botonmas.png"
import botonmenos from "../../../imagenes/botonmenos.png"
import { URL_API_private } from '../../../providerContext/EndPoint';

const ExcelTable = ({ producto }) => {
  const [showAdditionalTable, setShowAdditionalTable] = useState(false);
  const [imagenClicada, setImagenClicada] = useState(false);
  const [texto, setTexto] = useState('');
  const [napsCod, setNapsCod] = useState([]);
  const [sugesNapsCod, setSugesNapsCod] = useState([]);
  const token  = JSON.parse(localStorage.getItem('user_data')).token;
  const endPoint = URL_API_private+ "/naps"

  useEffect(() => {
    getAlLCods();
  }, []);

  const config = {
    headers:{
        Authorization: `Bearer ${token}`,
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

  const guardarDato = () => {
    
    console.log('Guardando dato:', texto);
    
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

  return (
    <div>
    <table className='excel-table'>
      <thead className='table-header'>
        <tr>
          <th className='white-color'>NAP</th>
          <th className='white-color'>Posicion</th>
          <th className='white-color'>NAP Utilizado</th>
          <th className='white-color'>Datos Tecnicos</th>
          <th className='white-color'>Zona</th>
          <th className='white-color'>Ubicacion</th>
          <th className='white-color'>Direccion</th>
        </tr>
      </thead>
      <tbody className='table-body'>
          <tr>
            <td>SMA-07-04
            <img
                src={imagenClicada ? botonmenos : botonmas}
                alt="Mostrar Detalles"
                onClick={toggleAdditionalTable}
                style={{ cursor: 'pointer', width: '40px', height: '40px' }}
            />
            </td>
            <td>3</td>
            <td>
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
                <button className='stylesButoon' onClick={guardarDato}>Guardar</button>
            </td>
            <td>SMA-07-04-03</td>
            <td>Aeropuerto</td>
            <td>°1888432465-ds6d5a4wead</td>
            <td>Avenida Ingavi esquina Cap. M. Waya</td>
          </tr>
      </tbody>
    </table>
    {showAdditionalTable && <TablaAdicional producto="SMA-07-04" />}
    </div>
  );
};

export default ExcelTable;