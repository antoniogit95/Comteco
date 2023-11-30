import React from "react";
import './Login.css'
import { useState } from "react";
import { Formik } from "formik";
import { NavLink, Navigate, useNavigate } from "react-router-dom";
import { useAuth } from "../../providerContext/AuthProvider";
import { URL_API_public } from "../../providerContext/EndPoint";


import axios from "axios";

export const Login = () => {
    const [login, setLogin] = useState();
    const navigate = useNavigate();
    const endPoint = URL_API_public+"/login";
    const [error, setError] = useState("");
    const [exisError, setExisError] = useState(false);
    const auth = useAuth();

    if(auth.isAuthenticated){
        return <Navigate to="/home" />
    }

    function handleClick (){
        console.log("presionaste boton cancelar");
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

    return (<div>
        <div className='stylesLoginContainer'>
            <Formik
                initialValues={{
                    user: '',
                    pass: ''       
                }}

                validate={(valores) => {
                    let errores = {};

                    //validacion Celula de Identidad
                    if(!valores.user){
                        errores.user = 'el campo Usuario es requerido obligatoriamente';
                    }


                    //validacion para contraseña
                    if(!valores.pass){
                        errores.pass = 'el campo Contraseña es requerido obligatoriamente';
                    }
                    return errores;
                }}

                onSubmit={ (valores) => {
                    const store = async (e) => {
                        e.preventDefault()  
                        try{
                            const response = await axios.post(endPoint, {
                                username: valores.user,
                                password: valores.pass
                            });
                            console.log(response.data.message);
                            auth.saveToken(
                                response.data.token, 
                                response.data.person, 
                                response.data.role,
                                response.data.time
                            );
                            navigate("/home")
                        } catch (error){
                            console.log(error.code)
                            if(error.code === "ERR_NETWORK"){
                                adminErrros("No hay conexion con el servidor");
                            }else{
                                adminErrros(error.response.data.message);
                            }
                                
                        }
                        
                    }

                    store(event);
                }}

            >
                {({values, errors, touched, handleSubmit, handleChange, handleBlur, resetForm}) => (
                    <form onSubmit={handleSubmit}>
                    <h2 className="stylesH2Login"> Iniciar Sesion</h2>
                    <div className={exisError? 'stylesErrosGeneral': ''}>
                        <label >{error}</label>
                    </div>
                    <div>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='user'
                            name='user'
                            placeholder='escribe tu nombre de usuario'
                            value={values.user}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.user && errors.user && <div className='styleErrores'>{errors.user}</div>}
                    </div>
                    <div>    
                        <input 
                            className='stylesInput'
                            type='password'
                            id='pass'
                            name='pass'
                            placeholder='escribe tu Contraseña'
                            value={values.pass}
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {touched.pass && errors.pass && <div className='styleErrores'>{errors.pass}</div>}
                    </div>                   
                    <div className="stylesContenedorButton">
                        <button  className='stylesButoonLogin' type="submit">
                            Iniciar Secion
                        </button>
                    </div>
                    <div className="styleLinks">
                        <NavLink to="/singauth" className="styleLinksA">Crear cuenta</NavLink>
                        <NavLink to= '/forgenpassword' className="styleLinksA">Olvide mi Contraseña</NavLink>
                    </div>
                </form>
                )}
            </Formik>
            
        </div>
        
        </div>
    );
}