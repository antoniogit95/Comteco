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
  const endPoint = URL_API_private+"/orden_dia/producto/"+producto;
  const [datosTablaAdicional, setDatos] = useState([]);
  const token  = JSON.parse(localStorage.getItem('user_data')).token

  useEffect(() => {
    getAllDatos();
}, []);

const config = {
    headers:{
        Authorization: `Bearer ${token}`,
    }
}

const getAllDatos = async () => {
  try {

    
      const response = await axios.get(endPoint, config);
      setDatos(response.data);
      console.log("datos rescatados exitosamente")
  } catch (error) {
      console.log('Error al obtener datos:', error.response);
  }
  
}


  console.log("reciviendo:",producto);
  console.log("este es el endpoint:", endPoint);
  const toggleAdditionalTable = () => {
    setShowAdditionalTable(!showAdditionalTable);
    setImagenClicada(!imagenClicada);
    
  };

  const guardarDato = () => {
    
    console.log('Guardando dato:', texto);
    
  };

  const napSet = new Set(datosTablaAdicional.map((dato) => dato.nap));

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
      <tbody className="table-body">
          {[...napSet].map((nap, index) => {
            const filteredData = datosTablaAdicional.filter((dato) => dato.nap === nap);
            const firstData = filteredData[0]; // Tomar solo la primera fila para evitar repeticiones
            return (
              <tr key={index}>
                <td>
                  {firstData.nap}
                  <img
                    src={imagenClicada ? botonmenos : botonmas}
                    alt="Mostrar Detalles"
                    onClick={toggleAdditionalTable}
                    style={{ cursor: 'pointer', width: '40px', height: '40px' }}
                  />
                </td>
                <td>{firstData.posicion}</td>
                <td>
                  <input
                    type="text"
                    value={texto}
                    onChange={(e) => setTexto(e.target.value)}
                    placeholder="Ingrese dato"
                  />
                  <button className="stylesButoon" onClick={guardarDato}>
                    Guardar
                  </button>
                </td>
                <td>{firstData.datoTecnico}</td>
                <td>{firstData.ubicacion}</td>
                <td>°1888432465-ds6d5a4wead</td>
                <td>{firstData.direccion}</td>
              </tr>
            );
          })}
        </tbody>
    </table>
    {showAdditionalTable && <TablaAdicional producto="SMA-07-04" />}
    </div>
  );
};

export default ExcelTable;