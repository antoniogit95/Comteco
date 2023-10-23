import React, { useEffect } from "react";
import { useState } from 'react';
import { Formik, Form, Field} from 'formik';
import axios from 'axios';
import { URL_API_private } from '../../../providerContext/EndPoint';
import './RegistroTecnico.css'

export const RegistroTecnico = () => {
    
    const personData = localStorage.getItem('user_data');
    const id_person = JSON.parse(personData).person.id_person;
    const token = JSON.parse(personData).token;
    const endPoint = URL_API_private+"/datatecnico";
    const endPointFtp = URL_API_private+"/ftp"
    const [ftpData, setFtpData] = useState([]);
    const [suggestions, setSuggestions] = useState([]);

    useEffect(() => {
        getAllDataFtp();
    }, []);

    const getAllDataFtp = async () => {
        try {
            const response = await axios.get(endPointFtp, config);
            setFtpData(response.data);
        } catch (error) {
            console.error("error inesperado: " +error);
            console.error("mensaje de error del servidor: " +error.response);
        }
    }

    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    const fechtSuggestion = async (searchValue) =>{
        if(searchValue){
            const filteredSuggestions = ftpData.filter((item) =>
                item.cod.toLowerCase().includes(searchValue.toLowerCase())
            );
            setSuggestions(filteredSuggestions);
        }else{
            setSuggestions([])
        }
        
    }

    return(
        <div className='stylesRegistroTecnico'>
            <Formik
                initialValues={{
                    nomber_product: '',
                    BoxNap: '',
                    DtStatus: '',
                    Comments:'',
                }}

                validate={(valores) => {
                    let errores = {};

                    //validacion numero del producto
                    if(!valores.nomber_product){
                        errores.nomber_product = 'el campo numero de producto es requerido obligatoriamente';
                    }

                    //validacion para la caja nap
                    if(!valores.BoxNap){
                        errores.BoxNap = 'el campo CAJA NAP es requerido obligatoriamente';
                    }

                    //validacion para estado dt
                    if(!valores.DtStatus){
                        errores.DtStatus = 'el campo ESTADO DT es requerido obligatoriamente';
                    }
                   
                    return errores;
                }}
                onSubmit={ async (valores) => {
                    try {
                        const response = await axios.post(endPoint, {
                            id_person: id_person,
                            num_producto: valores.nomber_product,
                            caja_nap: valores.BoxNap,
                            estado_odt: valores.DtStatus,
                            observaciones: valores.Comments
                        }, {
                            headers: config.headers,
                        });
                            console.log('Respuesta del Servidor: '+ response.data)
                    }catch (error) {
                        console.error("Error del Servidor: "+ error.response)
                        console.erro('Error Inesperado: '+error)
                    }
                    
                }}

            >
                {({values, errors, touched, handleSubmit, handleChange, handleBlur, setFieldValue, resetForm}) => (
                    <Form onSubmit={handleSubmit} className="stylesForm">
                    <section className="stylesContentForm">
                        <div>
                            <label htmlFor='nomber_product'>Numero de producto</label>
                            <Field 
                                className='stylesInput'
                                type='text'
                                id='nomber_product'
                                name='nomber_product'
                                placeholder='escribe tu numero de producto'
                                value={values.nomber_product}
                                onChange={handleChange}
                                onBlur={handleBlur}
                            />
                            {touched.nomber_product && errors.nomber_product && <div className='styleErrores'>{errors.nomber_product}</div>}
                        </div>
                    
                        <div>
                            <label htmlFor='BoxNap'>Caja Nap</label>
                            <Field 
                                className='stylesInput'
                                type='text'
                                id='BoxNap'
                                name='BoxNap'
                                autocomplete='off'
                                placeholder='escribe la caja nap'
                                onBlur={handleBlur}
                                onChange={(e) => {
                                    const searchValue = e.target.value;
                                    fechtSuggestion(searchValue);  // Realiza la búsqueda de sugerencias
                                    setFieldValue('BoxNap', searchValue);  // Actualiza el valor del campo BoxNap
                                }}
                            />
                            <div className="stylesSuggestion">
                                {suggestions.length > 0 && (
                                    suggestions.map((suggestions) =>(
                                        <div className="stylesSuggestion-item" key={suggestions.id_fdt} onClick={() => {
                                            setFieldValue('BoxNap', suggestions.cod);
                                            setSuggestions([])
                                        }}>
                                            {suggestions.cod + " zona: "+ suggestions.odf.nombre}
                                        </div>
                                    ))
                                )}
                            </div>
                            {touched.BoxNap && errors.BoxNap && <div className='styleErrores'>{errors.BoxNap}</div>}
                        </div>
                        <div>
                            <label htmlFor='DtStatus'>Estado DT</label>
                            <select
                                className='stylesInput'
                                id='DtStatus'
                                name='DtStatus'
                                value={values.DtStatus}
                                onChange={handleChange}
                                onBlur={handleBlur}
                            >
                            <option value='vacio' >vacio</option>
                            <option value='mismo'>Mismo DT</option>
                            <option value='cambio'>Cambia DT</option>
                            {/* Agrega más opciones según tus necesidades */}
                            </select>
                            {touched.DtStatus && errors.DtStatus && <div className='styleErrores'>{errors.DtStatus}</div>}
                        </div>
                        <div>
                            <label htmlFor='Comments'>Observaciones</label>
                            <Field
                                className='stylesInput'
                                type='textarea'
                                id='Comments'
                                name='Comments'
                                placeholder='escribe alguna observacion'
                                value={values.Comments}
                                onChange={handleChange}
                                onBlur={handleBlur}
                            />
                            {touched.Comments && errors.Comments && <div className='styleErrores'>{errors.Comments}</div>}
                        </div>
                    </section>

                    <section className="layoutButton">
                    <div className="stylesContenedorButton">
                        <button  className='stylesButoon' type="submit">
                            Guardar
                        </button>
                        
                    </div>
                
                    <div className='stylesContenedorButton'>
                        <button className='stylesButoon' onClick={resetForm}>
                            Cancelar
                        </button>
                    </div>
                    </section>
                </Form>
                )}
            </Formik>
            
        </div>
    );
}