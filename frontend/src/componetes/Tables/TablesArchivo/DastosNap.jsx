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

    <div>
    <div className='styleContentTable' >
    <table className='styleTable'>
      <thead className='stylesHead'>
        <tr className='stylesHead'>
          <th className='stylesTh-Td'>Actividades</th>
          <th className='stylesTh-Td'>Componentes</th>
          <th className='stylesTh-Td'>Estado Componentes</th>
          <th className='stylesTh-Td'>Clase servicio</th>
          <th className='stylesTh-Td'>Numero servicio</th>
          <th className='stylesTh-Td'>Estado numero servicio</th>
          <th className='stylesTh-Td'>Serial MAC ADDRESS</th>
        </tr>
      </thead>
      <tbody className='table-body-desp2'>
        {dataProduct.map((data, index) => (
          <tr key={index}>
            <td className='stylesTh-Td'>{data.actividades}</td>
            <td className='stylesTh-Td'>{data.componente}</td>
            <td className='stylesTh-Td'>{data.estado}</td>
            <td className='stylesTh-Td'>{data.claseServicio}</td>
            <td className='stylesTh-Td'>{data.numeroServicio}</td>
            <td className='stylesTh-Td'>{data.estado}</td>
            <td className='stylesTh-Td'>{data.macAddress}</td>
          </tr>
        ))}
      </tbody>
    </table>
    </div>      
    </div>
  );
};

export default ExcelTablenap;