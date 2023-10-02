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

    const auth = useAuth();

    if(auth.isAuthenticated){
        return <Navigate to="/home" />
    }

    function handleClick (){
        console.log("presionaste boton cancelar");
    }

    return (
        <div className='stylesLoginContainer'>
            <Formik
                initialValues={{
                    user: '',
                    pass: ''       
                }}

                onSubmit={ (valores) => {
                    console.log(endPoint);
                    const store = async (e) => {
                        e.preventDefault()
                        await axios.post(endPoint, {
                            username: valores.user,
                            password: valores.pass
                        });
                        navigate('/home');
                    }
                    store(event);
                }}

            >
                {({values, handleSubmit, handleChange, handleBlur, resetForm}) => (
                    <form onSubmit={handleSubmit}>
                    <h2 className="stylesH2Login"> Iniciar Sesion</h2>
                    <div>
                        <input 
                            className='stylesInput'
                            type='text'
                            id='user'
                            name='user'
                            placeholder='escribe tu nomnre de usuario'
                            value={values.user}
                            onChange={handleChange}
                        />
                        <input 
                            className='stylesInput'
                            type='password'
                            id='pass'
                            name='pass'
                            placeholder='escribe tu Contraseña'
                            value={values.pass}
                            onChange={handleChange}
                        />
                    </div>                   
                    <div className="stylesContenedorButton">
                        <button  className='stylesButoon' type="submit">
                            Iniciar Secion
                        </button>
                    </div>
                    <div className="styleLinks">
                        <NavLink to="/personal" className="styleLinksA">Crear cuenta</NavLink>
                        <a className="styleLinksA">Olvide mi Contraseña</a>
                    </div>
                </form>
                )}
            </Formik>
            
        </div>
    );
}