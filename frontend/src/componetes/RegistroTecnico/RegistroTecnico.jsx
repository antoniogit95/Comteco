import React from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_public } from '../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';
import './RegistroTecnico.css'

export const RegistroTecnico = () => {

    const endPoint = URL_API_public+"/register";
    const navigate = useNavigate();

    function handleClick (){
        console.log("presionaste boton cancelar");
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
                   

                    //validacion para la observacion
                    //if(!valores.Comments){
                      //  errores.Comments = 'el campo Observacion es requerido obligatoriamente';
                    //}


                    return errores;
                }}

                onSubmit={ (valores) => {   
                    console.log(valores);
                    /**const store = async (e) => {
                        e.preventDefault()
                        await axios.post(endPoint, {
                            username: valores.email,
                            password: valores.password,
                            firstname: valores.nombre,
                            lastname: valores.apellidos,
                            country: "Bolivia",
                        });
                        navigate('/home');
                    }
                    store(event);
                    */

                }}

            >
                {({values, errors, touched, handleSubmit, handleChange, handleBlur, resetForm}) => (
                    <form onSubmit={handleSubmit}>
                    <section className="layout">
                    <div>
                        <label htmlFor='nomber_product'>Numero de producto</label>
                        <input 
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
                        <label htmlFor='Comments'>Observaciones</label>
                        <textarea
                            className='stylesInput'
                            type='textarea'
                            rows='3'
                            cols='50'
                            id='Comments'
                            name='Comments'
                            placeholder='escribe alguna observacion'
                            value={values.Comments}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.Comments && errors.Comments && <div className='styleErrores'>{errors.Comments}</div>}
                    </div>

                    <div>
                        <label htmlFor='BoxNap'>Caja Nap</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='BoxNap'
                            name='BoxNap'
                            placeholder='escribe la caja nap'
                            value={values.BoxNap}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.BoxNap && errors.BoxNap && <div className='styleErrores'>{errors.BoxNap}</div>}
                    </div>
                    <div></div>
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
                        <option value='' disabled>Selecciona un estado</option>
                        <option value='estado1'>Mismo DT</option>
                        <option value='estado2'>Cambia DT</option>
                        <option value='estado3'>Vacio</option>
                        {/* Agrega más opciones según tus necesidades */}
                        </select>
                        {touched.DtStatus && errors.DtStatus && <div className='styleErrores'>{errors.DtStatus}</div>}
                    </div>
                    <div></div>
                    <div></div>
                    <section className="layoutButton">
                    <div className="stylesContenedorButton">
                        <button  className='stylesButoon' type="submit">
                            Guardar
                        </button>
                        
                    </div>
                
                    <div className='stylesContenedorButton'>
                        <button className='stylesButoon' onClick={handleClick}>
                            Cancelar
                        </button>
                    </div>
                    </section>
                    </section>
                </form>
                )}
            </Formik>
            
        </div>
    );
}