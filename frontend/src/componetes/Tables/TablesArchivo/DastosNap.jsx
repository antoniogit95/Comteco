import React, { useState, useEffect } from 'react';
import { URL_API_private } from '../../../providerContext/EndPoint';
import './TableArchive.css'
import axios from 'axios';

const ExcelTablenap = ({product, nap}) => {
  const [dataProduct, setDatos] = useState([]);
  const [rescatarDatos, setRescatarDatos] = useState([])
  const endPointNap = URL_API_private+"/servicio/producto/"+product;
  const token  = JSON.parse(localStorage.getItem('user_data')).token

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

        console.log("Entpoint:  "+endPointNap);
        console.log("token:  "+token);
        
        const response = await axios.get(endPointNap, config);
        setDatos(response.data);
        setRescatarDatos(response.data);
        console.log("datos rescatados exitosamente")
        console.log(response.data)
    } catch (error) {
        console.log('Error al obtener datos:', error.response);
        console.log('Error completo:', error);
    }
    
}



  console.log("este es la nap recibida :", nap);
  console.log("este es ek end point: ", endPointNap);
  console.log("este es el dato: ", dataProduct);


  return (
    <table className='excel-table'>
      <thead className='table-header'>
        <tr>
          <th className='white-color'>Actividades</th>
          <th className='white-color'>Componentes</th>
          <th className='white-color'>Estado Componentes</th>
          <th className='white-color'>Clase servicio</th>
          <th className='white-color'>Numero servicio</th>
          <th className='white-color'>Estado numero servicio</th>
          <th className='white-color'>Serial MAC ADDRESS</th>
        </tr>
      </thead>
      <tbody className='table-body'>
          <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
      </tbody>
    </table>
  );
};

export default ExcelTablenap;