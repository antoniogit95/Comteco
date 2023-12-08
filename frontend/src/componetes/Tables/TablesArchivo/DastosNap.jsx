import React from 'react';
import './TableArchive.css'
const ExcelTablenap = () => {
  const data = [
    {
      Actividades: 'Actividad1',
      Componentes: 'Componente1',
      'Estado Componentes': 'Activo',
      'Clase servicio': 'Clase1',
      'Numero servicio': '123456',
      'Estado numero servicio': 'Activo',
      'Serial (MAC ADDRESS)': 'ABC123',
    },
    // Puedes agregar más filas según sea necesario
  ];

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