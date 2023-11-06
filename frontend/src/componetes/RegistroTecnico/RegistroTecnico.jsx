import React from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_private } from '../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';
import './RegistroTecnico.css'

export const RegistroTecnico = () => {
    
    const navigate = useNavigate();
    const personData = localStorage.getItem('user_data');
    const id_person = JSON.parse(personData).person.id_person;
    const token = JSON.parse(personData).token;
    const endPoint = URL_API_private+"/datatecnico"


    console.log(id_person)
    function handleClick (){
        console.log("presionaste boton cancelar");
    }
    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };

    //function numberValue(e) {
        // Obtén el valor del campo de entrada
        //let inputValue = e.target.value;
    
        // Reemplaza cualquier carácter que no sea número con una cadena vacía
        //inputValue = inputValue.replace(/[^0-9]/g, '');
        //console.log(inputValue);
        // Actualiza el estado con el nuevo valor numérico
        //setValues({
        //    ...values,
        //   nomber_product: inputValue
        //});
    //}

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
                        console.log('Error Inesperado: '+error)
                    }
                    
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
                            className='stylesTextarea'
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
                        <option value='vacio' >vacio</option>
                        <option value='Mismo DT'>Mismo DT</option>
                        <option value='Cambia DT'>Cambia DT</option>
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