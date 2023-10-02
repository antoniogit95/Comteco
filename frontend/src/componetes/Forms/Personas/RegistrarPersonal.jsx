import './RegistrarPersonal.css';
import React from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_public } from '../../../providerContext/EndPoint';

export const RegistrarPersonal = () => {

    const [personas, setPersonas] = useState( [] );
    const endPoint = URL_API_public+"/register"

    function handleClick (){
        console.log("presionaste boton cancelar");
    }

    function ciExistente(dato){
        return false;
    }

    function telefonoExistente(dato){
        return false
    }

    function emailExistente(dato){
        return false;
    }

    return(
        <div className='stylesRegistroPersonal'>
            <Formik
                initialValues={{
                    ci: '',
                    nombre: '',
                    apellidos: '',
                    telefono: '',
                    nombre_cargo:'soporte tecnico',
                    email: '',        
                }}

                validate={(valores) => {
                    let errores = {};

                    //validacion Celula de Identidad
                    if(!valores.ci){
                        errores.ci = 'el campo Celula de Intentidad es requerido obligatoriamente';
                    }else if(!/^[0-9\s]{1,9}$/.test(valores.ci)){
                        errores.ci = 'no es un numero';
                    }else if(ciExistente(valores.ci)){
                        errores.ci = 'el numero de carnet ya fue registrado';
                    }


                    //validacion para el nombre
                    if(!valores.nombre){
                        errores.nombre = 'el campo nombre es requerido obligatoriamente';
                    }else if(!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(valores.nombre)){
                        errores.nombre = 'el campo no pude tener numeros';
                    }

                    //validacion apellidos
                    if(!valores.apellidos){
                        errores.apellidos = 'el campo Apellido Paterno es requerido obligatoriamente';
                    }else if(!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(valores.apellids)){
                        errores.apellidos = 'el campo no pude tener numeros';
                    }

                    //validacion Telefono
                    if(!valores.telefono){
                        errores.telefono = 'el campo Telefono es requerido obligatoriamente';
                    }else if(!/^[0-9\s]{1,10}$/.test(valores.telefono)){
                        errores.telefono = 'el campo no pude tener letras o caracteres especiales';
                    }else if(telefonoExistente(valores.telefono)){
                        errores.telefono = 'el numero de telefono ya fue registrado';
                    }

                    //validacion correo electronio
                    if(!valores.email){
                        errores.email = 'el campo Correo Electronico es requerido obligatoriamente';
                    }else if(!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(valores.email)){
                        errores.email = 'el campo solo puede tener letras, numeros, gion y guion bajo';
                    }else if(emailExistente(valores.email)){
                        errores.email = 'el correo electronico ya fue registrado';
                    }

                    return errores;
                }}

                onSubmit={ (valores) => {
                    console.log(valores);
                    console.log(endPoint);
                    console.log("http://localhost:8080/auth/register");
                    const store = async (e) => {
                        e.preventDefault()
                        await axios.post(endPoint, {
                            username: valores.email,
                            password: valores.ci,
                            firstname: valores.nombre,
                            lastname: valores.apellidos,
                            country: "Bolivia",
                        });
                        navigate('/home');
                    }
                    store(event);

                }}

            >
                {({values, errors, touched, handleSubmit, handleChange, handleBlur, resetForm}) => (
                    <form onSubmit={handleSubmit}>
                    <div>
                        <label htmlFor='ci'>Celula de Identidad</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='ci'
                            name='ci'
                            placeholder='escribe tu numero de carnet'
                            value={values.ci}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.ci && errors.ci && <div className='styleErrores'>{errors.ci}</div>}
                    </div>
                    <div>
                        <label htmlFor='nombre'>nombres</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='nombre'
                            name='nombre'
                            placeholder='escribe tu nombre'
                            value={values.nombre}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.nombre && errors.nombre && <div className='styleErrores'>{errors.nombre}</div>}
                    </div>
                    <div>
                        <label htmlFor='apellidos'>Apellido Paterno</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='apellidos'    
                            name='apellidos'
                            placeholder='escribe tus apellidos'
                            value={values.apellidos}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.apellidos && errors.apellidos && <div className='styleErrores'>{errors.apellidos}</div>}
                    </div>
                    <div>
                        <label htmlFor='telefono'>Telefono</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='telefono'
                            name='telefono'
                            placeholder='escribe tu numero de Telefono'
                            value={values.telefono}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.telefono && errors.telefono && <div className='styleErrores'>{errors.telefono}</div>}
                    </div>
                    <div>
                        <label htmlFor='nombre_cargo'>Empleado</label>
                        <select 
                            className='stylesInput'
                            type='text' 
                            id='nombre_cargo'
                            name="nombre_cargo"
                            value={values.nombre_cargo}
                            onChange={handleChange}
                        >
                            <option value='Soporte Tecnico'> Soporte Tecnico </option>
                            <option value='Supervisor'> Supervisor </option>
                        </select>
                    </div>
                    <div>
                        <label htmlFor='email'>Correo Electronico</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='email'
                            name='email'
                            placeholder='escribe tu Correo Electronio'
                            value={values.email}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.email && errors.email && <div className='styleErrores'>{errors.email}</div>}
                    </div>
                    
                    <div className="stylesContenedorButton">
                        <button  className='stylesButoon' type="submit">
                            Guardar
                        </button>
                        
                    </div>
                    <br />
                    <div className='stylesContenedorButton'>
                        <button className='stylesButoon' onClick={ handleClick }>
                            Cancelar
                        </button>
                    </div>
                </form>
                )}
            </Formik>
            
        </div>
    );
}