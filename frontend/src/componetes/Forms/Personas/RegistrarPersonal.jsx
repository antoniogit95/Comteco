import './RegistrarPersonal.css';
import React from "react";
import { useState } from 'react';
import { Formik } from 'formik';
import axios from 'axios';
import { URL_API_public } from '../../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';

export const RegistrarPersonal = () => {

    const [personas, setPersonas] = useState( [] );
    const endPoint = URL_API_public+"/register";
    const navigate = useNavigate();

    function handleClick (){
        console.log("presionaste boton cancelar");
    }

    function ciExistente(dato){
        return false;
    }

    function numInterExistente(dato){
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
                    numero_interno: '',
                    nombre: '',
                    apellidos: '',
                    telefono: '',
                    nombre_cargo:'SOPORTE',
                    fecha_nacimiento: '',
                    email: '',
                    password: '',
                    confirmar_password: '',  
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

                    //validacion de numero_interno
                    if(!valores.numero_interno){
                        errores.numero_interno = 'el campo numero de interno es requerido obligatoriamente';
                    }else if(!/^[0-9\s]{1,9}$/.test(valores.numero_interno)){
                        errores.numero_interno = 'no es un numero';
                    }else if(numInterExistente(valores.numero_interno)){
                        errores.numero_interno = 'el numero de interno ya fue registrado';
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
                    }else if(!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(valores.apellidos)){
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

                    //validacion de fecha de nacimiento
                    if(!valores.fecha_nacimiento){
                        errores.fecha_nacimiento = 'el campo Fecha de nacimiento es obligatoriamente';
                    }

                    //validacion correo electronio
                    if(!valores.email){
                        errores.email = 'el campo Correo Electronico es requerido obligatoriamente';
                    }else if(!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(valores.email)){
                        errores.email = 'el campo solo puede tener letras, numeros, gion y guion bajo';
                    }else if(emailExistente(valores.email)){
                        errores.email = 'el correo electronico ya fue registrado';
                    }

                    //validacion para contraseña
                    if(!valores.password){
                        errores.password = 'el campo Contraseña es requerido obligatoriamente';
                    }else if(valores.password.length < 6){
                        errores.password = 'el campo Contraseña debe tener una longitud mayor a 6 digitos';
                    }

                    //validacion para confirmar contraseña
                    if(!valores.confirmar_password){
                        errores.confirmar_password = 'el campo confirmar Contraseña es requerido obligatoriamente';
                    }else if(valores.password !== valores.confirmar_password){
                        errores.confirmar_password = 'las contraseñas no coiciden ';
                    }

                    return errores;
                }}

                onSubmit={ (valores) => {   
                    console.log(valores);
                    const store = async (e) => {
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
                        <label htmlFor='numero_interno'>Numero de Interno</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='numero_interno'
                            name='numero_interno'
                            placeholder='escribe tu numero de Interno'
                            value={values.numero_interno}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.numero_interno && errors.numero_interno && <div className='styleErrores'>{errors.numero_interno}</div>}
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
                            placeholder='escribe tu numero de Telefono Corporativo'
                            value={values.telefono}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.telefono && errors.telefono && <div className='styleErrores'>{errors.telefono}</div>}
                    </div>
                    <div>
                        <label htmlFor='fecha_nacimiento'>Fecha nacimiento</label>
                        <input 
                            className='stylesInput'
                            type='date'
                            id='fecha_nacimiento'
                            name='fecha_nacimiento'
                            placeholder='dd/mm/aaaa'
                            value={values.fecha_nacimiento}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.fecha_nacimiento && errors.fecha_nacimiento && <div className='styleErrores'>{errors.fecha_nacimiento}</div>}
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
                    <div>
                        <label htmlFor='password'>Contraseña</label>
                        <input 
                            className='stylesInput'
                            type='password'
                            id='password'
                            name='password'
                            placeholder='escribe tu Contraseña'
                            value={values.password}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.password && errors.password && <div className='styleErrores'>{errors.password}</div>}
                    </div>
                    <div>
                        <label htmlFor='confirmar_password'>Confirmar contraseña</label>
                        <input 
                            className='stylesInput'
                            type='password'
                            id='confirmar_password'
                            name='confirmar_password'
                            placeholder='escribe tu Contraseña'
                            value={values.confirmar_password}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.confirmar_password && errors.confirmar_password && <div className='styleErrores'>{errors.confirmar_password}</div>}
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