import React, {useState} from 'react';
import { URL_API_private } from '../../../providerContext/EndPoint';
import axios from 'axios';
import {ToastContainer, toast } from 'react-toastify';
import '../saveDataGeneral/SaveArchive.css'

export const SaveDataOrdenDia = () => {
    const [file, setFile] = useState([]);
    const endPoint = URL_API_private+"/orden_dia/save_file"
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    const subirArchivo = async () => {
        if (file.length === 0) {
            // Muestra un mensaje de error si no se ha seleccionado ningún archivo
            toast.error('Por favor, selecciona un archivo antes de guardar.', {
                position: 'top-right',
                autoClose: 3000,
            });
            return;
        }
        const formData = new FormData();
        formData.append('file', file);
        console.log(formData)
        try {
            const response = await axios.post(endPoint, formData,{
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Bearer ${token}`,
                }
            });
            toast.success('Orden del dia  registrado con éxito', {
                position: 'top-right',
                autoClose: 3000,      
              });
            console.log(response.code);
        } catch (error) {
            console.error('Error al cargar el archivo:', error.message);
            toast.error(error.code, {
                position: 'top-right', 
                autoClose: 3000,  
            });
        }
    }

    return (<>
        <div className='stylesCardSaveFolder'>
            <h1>subir la orden del dia</h1>
            <input
                className='stylesInputGuardarFile'
                type="file"
                name='file'
                onChange={(e) => e.target.files && setFile(e.target.files[0])} />
            <button className='stylesButoon' onClick={subirArchivo}>guardar</button>
        </div>
        <ToastContainer />
    </>)
}