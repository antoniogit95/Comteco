import { URL_API_private } from "../../../providerContext/EndPoint"
import React, { useState, useEffect } from 'react';
import TablaAdicional from './DastosNap';
import axios from 'axios';
import './TableArchive.css'
import botonmas from "../../../imagenes/botonmas.png"
import botonmenos from "../../../imagenes/botonmenos.png"

const ExcelTable = () => {
  const [showAdditionalTable, setShowAdditionalTable] = useState(false);
  const [imagenClicada, setImagenClicada] = useState(false);
  const [texto, setTexto] = useState('');
  const [napCods, setNapCods] = useState([]);
  const [suggestions, setSuggestions] = useState([]);
  const token = JSON.parse(localStorage.getItem('user_data')).token;

  useEffect(() => {

    // Llama a la función para obtener los codigos de la tabla "nap"
    getNapCods();

  }, []); // Se ejecuta solo una vez al montar el componente

  const config = {
    headers: {
        Authorization: `Bearer ${token}`,
    },
  };

  const getNapCods = async () => {
    const napEndpoint = URL_API_private + "/naps";

    //const napEndPointValidate = URL_API_private + "/nap/"

    try { 
      console.log("Endpoint: "+napEndpoint, "\n Token: "+config.headers)
      const response = await axios.get(napEndpoint, config); //aqui sale el error de restriccion
      // Extrae los IDs de la respuesta y almacénalos en el estado
      const cods = response.data.map(nap => nap.cods);
      setNapCods(cods);
    } catch (error) {
      console.log('Error obteniendo los codigos de la tabla "nap": ' + error);
    }
    };

  const handleInputChange = (e) => {
    const inputText = e.target.value;
    setTexto(inputText);
    // Filtra las sugerencias basadas en el texto de entrada
    const filteredSuggestions = napCods.filter((cod) =>
      cod.toLowerCase().includes(inputText.toLowerCase())
    );
    setSuggestions(filteredSuggestions);
  };


  const data = [
    {
      NAP: 'NAP1',
      Posicion: 'Posicion1',
      'NAP utilizado': 'NAP Utilizado1',
      'Datos Tecnicos': 'Datos Tecnicos1',
      Zona: 'Zona1',
      Ubicacion: 'Ubicacion1',
      Direccion: 'Direccion1',
    },
    // Puedes agregar más filas según sea necesario
  ];
  
  const toggleAdditionalTable = () => {
    setShowAdditionalTable(!showAdditionalTable);
    setImagenClicada(!imagenClicada);
    
  };

  const guardarDato = () => {
    
    console.log('Guardando dato:', texto);
    
  };

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
              onChange={handleInputChange}
              list="suggetionsList"
              placeholder="Ingrese texto"
            />
            <datalist id="suggestionsList">
              {suggestions.map((cod) => (
            < option key={cod} value={cod} />
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