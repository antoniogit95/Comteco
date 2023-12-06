import React, {useState} from 'react';
import { URL_API_private } from '../../../providerContext/EndPoint';
import axios from 'axios';
import {ToastContainer, toast } from 'react-toastify';
import '../saveDataGeneral/SaveArchive.css'

export const SaveDataOrdenDia = () => {
    const [file, setFile] = useState([]);

    const endPoint = URL_API_private+"/orden_dia"
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    const subirArchivo = async () => {
        const formData = new FormData();
        formData.append('file', file);
        console.log(endPoint)
        try {
            const response = await axios.post(endPoint, formData,{
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Bearer ${token}`,
                }
            });
            toast.success('Usuario registrado con éxito', {
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
                onChange={(e) => setFile(e.target.files)} />
            <button className='stylesButoon' onClick={subirArchivo}>guardar</button>
        </div>
        <ToastContainer />
    </>)
}