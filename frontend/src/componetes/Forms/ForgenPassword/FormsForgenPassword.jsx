import React, {useState} from "react";
import { URL_API_public } from "../../../providerContext/EndPoint";
import { useNavigate } from "react-router-dom";
import { Formik, Field } from "formik";
import {ToastContainer, toast } from 'react-toastify';
import axios from 'axios';  
import 'react-toastify/dist/ReactToastify.css';
import './FormsForgenPassword.css'

export const FromsForgenPassword = () => {
    
    const endPoint = URL_API_public+"/forgen_password";
    const endPointP = URL_API_public;
    const [isValidate, setIsValidate] = useState(true);
    const navigate = useNavigate();
    const [error, setError] = useState("");
    const [exisError, setExisError] = useState(false);

    function handleClick (){
        navigate("/");
    }

    const validar = async (data) =>{
        if(data.item !== '' && data.email !== ''){
            try {
                const response = await axios.post(endPointP+"/checkData", 
                    {
                        ci : '',
                        item : data.item,
                        email: data.email,
                    }
                );
                if(response.data.message === "SIN ERROR"){
                    setIsValidate(false);
                }else{
                    adminErrros(response.data.message);
                }

            } catch (e) {
                console.error(e)
                adminErrros(error.response.data.message);
            }
        }else{
            adminErrros("debe llenar todos los campos");
        }
        
    }

    function adminErrros(nameErrors){
        setExisError(true);
        switch (nameErrors) {
            case "ERR_BAD_REQUEST":
                setError("usuario Incoreccto");
                break;
            case "ERR_NETWORK":
                setError("no hay conexion con el servidor");
                break;
            case "":
                    setError("no hay conexion con el servidor");
                    break;
            default:
                setError(nameErrors);
                break;
        }

        setTimeout(() => {
            setExisError(false);
            setError("");
          }, 3000);
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
                    /** 
                    if(!valores.ci){
                        errores.ci = 'el campo Celula de Intentidad es requerido obligatoriamente';
                    }else if(!/^[0-9\s]{1,9}$/.test(valores.ci)){
                        errores.ci = 'no es un numero';
                    }
                    */

                    //validacion de item
                    if(!valores.item){
                        errores.item = 'el campo numero de item es requerido obligatoriamente';
                    }else if(!/^[0-9\s]{1,9}$/.test(valores.item)){
                        errores.item = 'no es un numero';
                    }

                    //validacion correo electronio
                    if(!valores.email){
                        errores.email = 'el campo Correo Electronico es requerido obligatoriamente';
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
                        e.preventDefault()
                        try {
                            const response = await axios.put(endPoint, {
                                ci: '',
                                item: valores.item,
                                email: valores.email,
                                password: valores.password
                            });
                            console.log(response.data);
                            if(response.data.message === "SIN ERROR"){
                            }else{
                                adminErrros(response.data.message);
                            }
                            navigate('/home');    
                        } catch (error) {
                            console.log(error)
                            adminErrros(error.response.data.message);

                        }
                        
                    }
                    store(event);

                }}

            >
                {({values, errors, touched, handleSubmit, handleChange, handleBlur, resetForm}) => (
                    <form onSubmit={handleSubmit}>
                        <div className={exisError? 'stylesErrosGeneral': ''}>
                        <label >{error}</label>
                        </div>
                        {/**
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
                     */}
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