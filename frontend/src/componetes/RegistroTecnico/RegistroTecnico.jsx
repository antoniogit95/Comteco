import React from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_public } from '../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';

export const RegistroTecnico = () => {

    const endPoint = URL_API_public+"/register";
    const navigate = useNavigate();

    function handleClick (){
        console.log("presionaste boton cancelar");
    }

    return(
        <div className='stylesRegistroPersonal'>
            <Formik
                initialValues={{
                    date: '',
                    nomber_product: '',
                    BoxNap: '',
                    DtStatus: '',
                    Suport:'',
                    Comments:'',
                }}

                validate={(valores) => {
                    let errores = {};

                    //validacion Celula de Identidad
                    if(!valores.date){
                        errores.date = 'el campo Date es requerido obligatoriamente';
                    }

                    //validacion nobre del producto
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

                    //validacion para analista
                    if(!valores.Suport){
                        errores.Suport = 'el campo Analista es requerido obligatoriamente';
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
                    <div>
                        <label htmlFor='date'>date</label>
                        <input 
                            className='stylesInput'
                            type='date'
                            id='date'
                            name='date'
                            value={values.date}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.date && errors.date && <div className='styleErrores'>{errors.date}</div>}
                    </div>
                    <div>
                        <label htmlFor='nomber_product'>Numero de prodicto</label>
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
                    <div>
                        <label htmlFor='DtStatus'>Estado DT</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='DtStatus'
                            name='DtStatus'
                            placeholder='escribe el estado DT'
                            value={values.DtStatus}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.DtStatus && errors.DtStatus && <div className='styleErrores'>{errors.DtStatus}</div>}
                    </div>
                    <div>
                        <label htmlFor='Suport'>Analista</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='Suport'
                            name='Suport'
                            placeholder='escribe el analista'
                            value={values.Suport}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.Suport && errors.Suport && <div className='styleErrores'>{errors.Suport}</div>}
                    </div>
                    <div>
                        <label htmlFor='Comments'>Observaciones</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='Comments'
                            name='Comments'
                            placeholder='escribe alguna observacion'
                            value={values.Comments}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.Comments && errors.Comments && <div className='styleErrores'>{errors.Comments}</div>}
                    </div>
                    <div className="stylesContenedorButton">
                        <button  className='stylesButoon' type="submit">
                            Guardar
                        </button>
                        
                    </div>
                    <br />
                    <div className='stylesContenedorButton'>
                        <button className='stylesButoon' onClick={handleClick}>
                            Cancelar
                        </button>
                    </div>
                </form>
                )}
            </Formik>
            
        </div>
    );
}