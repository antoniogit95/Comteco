import React, {useState, useRef} from 'react';
import { URL_API_private } from '../../../providerContext/EndPoint';
import axios from 'axios';
import {ToastContainer, toast } from 'react-toastify';
import '../saveNaps/SaveNaps.css'

export const SaveDataOrdenDia = () => {
    const [file, setFile] = useState([]);
    const [archivo, setArchivo] = useState(null);
    const endPoint = URL_API_private+"/orden_dia/save_file"
    const token = JSON.parse(localStorage.getItem('user_data')).token;
    const [dragging, setDragging] = useState(false);
    const [archivoNombre, setArchivoNombre] = useState('');

    const fileInputRef = useRef(null);

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file && file.name.endsWith('.csv')) {
            setArchivo(file);
            setArchivoNombre(file.name);
        } else {
            toast.error('Por favor, selecciona un archivo CSV.');
        }
    };

    const handleDragOver = (e) => {
        e.preventDefault();
        e.stopPropagation();
        setDragging(true);
    };

    const handleDragLeave = (e) => {
        e.preventDefault();
        e.stopPropagation();
        setDragging(false);
    };

    const handleDrop = (e) => {
        e.preventDefault();
        e.stopPropagation();
        setDragging(false);
        const file = e.dataTransfer.files[0];
        if (file && file.name.endsWith('.csv')) {
            setArchivo(file);
            setArchivoNombre(file.name);
        } else {
            toast.error('Por favor, selecciona un archivo CSV.');
        }
    };

    const subirArchivo = async () => {
        if (!archivo) {
            // Muestra un mensaje de error si no se ha seleccionado ningún archivo
            toast.error('Por favor, selecciona un archivo antes de guardar.', {
                position: 'top-right',
                autoClose: 3000,
            });
            return;
        }
        const formData = new FormData();
        formData.append('file', archivo);
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

    const handleSelectFileClick = () => {
        // Simular clic en el input de archivo
        if (fileInputRef.current) {
            fileInputRef.current.click();
        }
    };

    return (<>
        <div className='stylesCardSaveFolder'
        style={{
            border: `1px solid ${dragging ? 'blue' : '#ccc'}`,
            borderRadius: '10px',
            padding: '15px',
            width: '420px',
            backgroundColor: '#3A3F49',
            //maxWidth: '300px',*/
        }}
        onDragOver={handleDragOver}
        onDragLeave={handleDragLeave}
        onDrop={handleDrop}>
            <h1>Subir la orden del dia</h1>
            <p>Seleccione el archivo csv a subir o arrastrelo aqui</p>
            <input
                className='stylesInputGuardarFile'
                type="file"
                name='file'
                onChange={handleFileChange} 
                style={{ display: 'none' }}
                ref={fileInputRef}
            />
            <button className="stylesButoon" onClick={handleSelectFileClick}>
                Seleccionar Archivo
            </button>   
            <br></br>
            <button className='stylesButoon' onClick={subirArchivo}>Guardar</button>
            <br></br>
            {archivoNombre && (
                <p>
                    Archivo seleccionado: <strong>{archivoNombre}</strong>
                </p>
            )}
        </div>
        <ToastContainer />
    </>)
}