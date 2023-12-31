import React, {useState} from "react";
import { URL_API_public } from "../../../providerContext/EndPoint";
import { useNavigate } from "react-router-dom";
import { Formik, Field } from "formik";
import {ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './FormsForgenPassword.css'

export const FromsForgenPassword = () => {
    
    const [personas, setPersonas] = useState( [] );
    const endPoint = URL_API_public+"/forgenPassword";
    const navigate = useNavigate();

    function handleClick (){
        navigate("/");
    }

    return(
        <div className='stylesRegistroPersonal'>
            <Formik
                initialValues={{
                    ci: '',
                    item:'',
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
                    }

                    //validacion de item
                    if(!valores.item){
                        errores.item = 'el campo numero de item es requerido obligatoriamente';
                    }else if(!/^[0-9\s]{1,9}$/.test(valores.item)){
                        errores.item = 'no es un numero';
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
                    const store = async (e) => {
                        console.log(valores)
                        e.preventDefault()
                        try {
                            await axios.put(endPoint, {
                                username: valores.email,
                                password: valores.password,
                                celula_identidad: valores.ci,
                                item: valores.item,
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
                        <label htmlFor='ci'>Celula de Identidad</label>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='ci'
                            name='ci'
                            placeholder='Escribe tu numero de carnet'
                            value={values.ci}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.ci && errors.ci && <div className='styleErrores'>{errors.ci}</div>}
                    </div>
                    <div>
                        <label htmlFor='item'>Numero de Item</label>
                        <Field 
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
                            Enviar
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