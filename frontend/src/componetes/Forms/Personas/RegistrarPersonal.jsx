import './RegistrarPersonal.css';
import React from "react";
import { useState } from 'react';
import { Formik, Field } from 'formik';
import axios from 'axios';
import { URL_API_public } from '../../../providerContext/EndPoint';
import { useNavigate } from 'react-router-dom';
import {ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export const RegistrarPersonal = () => {

    const endPoint = URL_API_public+"/register";
    const endPointP = URL_API_public;
    const navigate = useNavigate();

    function handleClick (){
        navigate("/");
    }

    const ciExistente = async (dato) =>{
        try {
            const response = await axios.post(endPointP+"/checkci", 
                {
                    username : dato,
                    password : ""
                })
            return response.data;
        } catch (e) {
            console.error(e)
            return true;
        }
    }

    const numInterExistente = async (dato) => {
        try {
            const response = await axios.post(endPointP+"/checkitem", 
                {
                    username : dato,
                    password : ""
                })
            return response.data;
        } catch (e) {
            console.error(e)
            return true;
        }
    }

    const emailExistente = async (dato) => {
        try {
            const response = await axios.post(endPointP+"/checkusername", 
                {
                    username : dato,
                    password : ""
                })
            return response.data;
        } catch (e) {
            console.error(e)
            return true;
        }
    }

    const sugesRole = [
        {id: 1, role: "ADMIN"},
        {id: 2, role: "SOPORTE"},
        {id: 3, role: "EQUIPOS"},
        {id: 4, role: "SUPERVISOR"}
    ];

    return(
        <div className='stylesRegistroPersonal'>
            <Formik
                initialValues={{
                    ci: '',
                    item:'',
                    nombre: '',
                    apellidos: '',
                    telefono: '',
                    nombre_cargo:'SOPORTE',
                    fecha_nacimiento: '',
                    email: '',
                    password: '',
                    confirmar_password: '',  
                }}

                validate={async (valores) => {
                    let errores = {};

                    //validacion Celula de Identidad
                    if(!valores.ci){
                        errores.ci = 'el campo Celula de Intentidad es requerido obligatoriamente';
                    }else if(!/^[0-9\s]{1,9}$/.test(valores.ci)){
                        errores.ci = 'no es un numero';
                    }else if( await ciExistente(valores.ci)){
                        errores.ci = 'el numero de carnet ya fue registrado';
                    }

                    //validacion de item
                    if(!valores.item){
                        errores.item = 'el campo numero de item es requerido obligatoriamente';
                    }else if(!/^[0-9\s]{1,9}$/.test(valores.item)){
                        errores.item = 'no es un numero';
                    }else if( await numInterExistente(valores.item)){
                        errores.item = 'el numero de interno ya fue registrado';
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
                    }else if( await emailExistente(valores.email)){
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
                    const store = async (e) => {
                        console.log(valores)
                        console.log("end point: "+endPoint)
                        e.preventDefault()
                        try {
                            await axios.post(endPoint, {
                                username: valores.email,
                                password: valores.password,
                                nombre: valores.nombre.toUpperCase(),
                                apellidos: valores.apellidos.toUpperCase(),
                                celula_identidad: valores.ci,
                                item: valores.item,
                                fecha_nacimiento: valores.fecha_nacimiento,
                                email: valores.email,
                                telefono: valores.telefono,
                                role: valores.nombre_cargo
                            });
                            toast.success('Usuario registrado con éxito', {
                                position: 'top-right',
                                autoClose: 3000,      
                              });
                            navigate('/home');    
                        } catch (error) {
                            console.log(error)
                            console.log("mensaje")
                            toast.error(error.code, {
                                position: 'top-right', 
                                autoClose: 3000,  
                            });
                        }
                        
                    }
                    store(event);

                }}

            >
                {({values, errors, touched, handleSubmit, handleChange, handleBlur, resetForm}) => (
                    <form onSubmit={handleSubmit}>
                    <div>
                        <h3>Registro nuevo personal</h3>
                    </div>
                    <div>
                        <label htmlFor='item'>Numero de Item</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='item'
                            name='item'
                            placeholder='Escribe tu numero de Item'
                            value={values.item}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.item && errors.item && <div className='styleErrores'>{errors.item}</div>}
                    </div>
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
                            placeholder='Escribe tu nombre'
                            value={values.nombre}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.nombre && errors.nombre && <div className='styleErrores'>{errors.nombre}</div>}
                    </div>
                    <div>
                        <label htmlFor='apellidos'>Apellidos</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='apellidos'    
                            name='apellidos'
                            placeholder='Escribe tu apellido paterno y materno'
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
                            placeholder='Escribe tu numero de Telefono Corporativo'
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
                        <label htmlFor='nombre_cargo'>Cargo</label>
                        <Field
                            as='select'
                            className='stylesInput'
                            id='nombre_cargo'
                            name='nombre_cargo'
                         >
                            <option value='' disabled>Selecciona un cargo</option>
                                {sugesRole.map((cargo) => (
                                    <option key={cargo.id} value={cargo.role}>
                                        {cargo.role}
                                    </option>
                                ))}
                        </Field>
                        {touched.nombre_cargo && errors.nombre_cargo && <div className='styleErrores'>{errors.nombre_cargo}</div>}
                    </div>
                    <div>
                        <label htmlFor='email'>Correo Electronico</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='email'
                            name='email'
                            placeholder='Escribe tu Correo Electronio'
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
                            placeholder='Escribe tu Contraseña'
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
                            placeholder='Vuelva a escribir su Contraseña'
                            value={values.confirmar_password}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.confirmar_password && errors.confirmar_password && <div className='styleErrores'>{errors.confirmar_password}</div>}
                    </div>
                    <br></br>
                    <div className="stylesContenedorButton">
                        <button  className='stylesButoon' type="submit">
                            Guardar
                        </button>
                        
                    </div>
                    <br />
                    <div className='stylesContenedorButton'>
                        <button className='stylesButoon' type="button" onClick={handleClick}>
                            Cancelar
                        </button>
                    </div>
                </form>
                )}
            </Formik>
            <ToastContainer />
        </div>
    );
}