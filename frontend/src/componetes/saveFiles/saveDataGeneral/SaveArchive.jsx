import React, { useState } from 'react';
import { URL_API_private } from '../../../providerContext/EndPoint';
import axios from 'axios';
import './SaveArchive.css'

export const SaveArchive = () => {

    const [archivo, setArchivo] = useState(null);
    const endPoint = URL_API_private+"/files/save"
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        setArchivo(file);
    };

    const subirArchivo = async () => {
        const formData = new FormData();
        formData.append('archive', archivo);
        console.log(endPoint)
        try {
            const response = await axios.post(endPoint, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Bearer ${token}`,
                }
            });
            console.log(response.data);
            alert('Archivo cargado con éxito.');
        } catch (error) {
            console.log('Error al cargar el archivo:', error.response);
        }
    };

    return (

        
        <div className='stylesCardSaveFolder'>
            <h1>Carga de Archivos</h1>
            <input 
                type="file"
                onChange={handleFileChange}
                placeholder='ingrese su archivo'
                />
            <button className='stylesButoon' onClick={subirArchivo}>Subir Archivo</button>
        </div>
        
    );
};

