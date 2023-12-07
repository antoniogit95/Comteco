import React, { useState, useRef } from 'react';
import { URL_API_private } from '../../../providerContext/EndPoint';
import axios from 'axios';
import './SaveArchive.css'

export const SaveArchive = () => {
    const [archivo, setArchivo] = useState(null);
    const [dragging, setDragging] = useState(false);
    const [archivoNombre, setArchivoNombre] = useState('');
    const endPoint = URL_API_private + "/files/save";
    const token = JSON.parse(localStorage.getItem('user_data')).token;

    const fileInputRef = useRef(null);

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file && file.name.endsWith('.csv')) {
            setArchivo(file);
            setArchivoNombre(file.name);
        } else {
            alert('Por favor, selecciona un archivo CSV.');
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
            alert('Por favor, selecciona un archivo CSV.');
        }
    };

    const subirArchivo = async () => {
        if (archivo) {
            const formData = new FormData();
            formData.append('archive', archivo);
            try {
                const response = await axios.post(endPoint, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        Authorization: `Bearer ${token}`,
                    },
                });
                console.log(response.data);
                alert('Archivo cargado con éxito.');
                setArchivo(null);
                setArchivoNombre('');
            } catch (error) {
                console.log('Error al cargar el archivo:', error.response);
            }
        } else {
            alert('Por favor, selecciona un archivo antes de subirlo.');
        }
    };

    const handleSelectFileClick = () => {
        // Simular clic en el input de archivo
        if (fileInputRef.current) {
            fileInputRef.current.click();
        }
    };

    return (
        <div
            style={{
                border: `1px solid ${dragging ? 'blue' : '#ccc'}`,
                borderRadius: '10px',
                padding: '15px',
                width: '380px',
                //maxWidth: '300px',*/
            }}
            onDragOver={handleDragOver}
            onDragLeave={handleDragLeave}
            onDrop={handleDrop}
        >
            <h1>Carga de Archivos</h1>
            <input
                type="file"
                onChange={handleFileChange}
                style={{ display: 'none' }}
                ref={fileInputRef}
            />
            <p>Selecciona el archivo excel o arrastralo aqui</p>
            <button className="stylesButoon" onClick={handleSelectFileClick}>
                Seleccionar Archivo
            </button>   
            <br></br>
                <button className="stylesButoon" onClick={subirArchivo}>
                    Subir Archivo
                </button>
                <br></br>
            
            {archivoNombre && (
                <p>
                    Archivo seleccionado: <strong>{archivoNombre}</strong>
                </p>
            )}
        </div>
    );
};
