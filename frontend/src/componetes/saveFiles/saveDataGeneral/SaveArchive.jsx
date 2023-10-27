import React, { useState } from 'react';
import { URL_API_private } from '../../../providerContext/EndPoint';
import axios from 'axios';

export const SaveArchive = () => {

    const [archivo, setArchivo] = useState(null);
    const endPoint = URL_API_private+"/files/Save"
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        setArchivo(file);
    };

    const subirArchivo = async () => {
        const formData = new FormData();
        formData.append('archive', archivo);
        try {
            const response = await axios.post(endPoint, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Bearer ${token}`,
                }
            });
            alert('Archivo cargado con éxito.');
        } catch (error) {
            console.error('Error al cargar el archivo:', error.response);
        }
    };

    return (
        <div>
            <h1>Carga de Archivos</h1>
            <input type="file" onChange={handleFileChange} />
            <button onClick={subirArchivo}>Subir Archivo</button>
        </div>
    );
};

