import React, {useState} from 'react';
import { URL_API_private } from '../../../providerContext/EndPoint';
import axios from 'axios';


export const SaveDataTecnico = () => {
    const [file, setFile] = useState([]);

    const endPoint = URL_API_private+"/datatecnico/savefile"
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    const subirArchivo = async () => {
        const formData = new FormData();
        formData.append('file', file);
        try {
            const response = await axios.post(endPoint, formData,{
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Bearer ${token}`,
                }
            });
            console.log(response.code);
            alert('Archivo cargado con éxito.');
        } catch (error) {
            console.error('Error al cargar el archivo:', error.message);
            alert('Error al cargar el archivo.');
        }
    }

    return (
        <div>
            <h1>cargar datos del archivo</h1>
            <input type="file" name='file' onChange={(e) => setFile(e.target.files)} />
            <button onClick={subirArchivo}>guardar</button>
        </div>
    )
}