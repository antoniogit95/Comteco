import React, { useState } from "react";
import './Home.css'
import { NavBar } from "../../componetes/NavBar/NavBar";
import { Button } from "../../componetes/Button/Button"

export const Home = () => {
    
    const role = JSON.parse(localStorage.getItem('user_data')).role;
    const buttons = showButtons();

    function showButtons(){
        switch (role) {
            case "ADMIN":
                return buttonsAdmin();
            case "SOPORTE":
                return buttonsSoporte();
            default:
                setError(nameErrors);
                break;
        }
    }

    function buttonsAdmin(){
        return (<div className="stylesContentButtonsBig">
            <Button name="Personal" link="/personal"/>
            <Button name="Reportes" link="/reportes"/>
            <Button name="Registrar" link="/registrar"/>
        </div>)
    }
    
    function buttonsSoporte(){
        return (<div className="stylesContentButtonsBig">
            <Button name="Registrar" link="/registrar"/>
        </div>)
    }
    

    return(<>
        <NavBar />
        {buttons}       
    </>)
}

/** <section class="layout">
            <div>
                <section class="Celda">
                    <div class="header"></div>
                    <div class="main">Reportes</div>
                    <div class="footer">
                        <button  className='stylesButoon' type="submit">
                            Ingresar                
                        </button>
                    </div>  
                </section>
            </div>
            <div>
                <section class="Celda">
                    <div class="header">Equipo de trabajo 2</div>
                    <div class="main"></div>
                    <div class="footer">
                        <button  className='stylesButoon' type="submit">
                            Ingresar                 
                        </button>
                    </div>
                </section>
            </div>
            <div> 
                <section class="Celda">
                    <div class="header">Equipo de trabajo 3</div>
                    <div class="main"></div>
                    <div class="footer">
                        <button  className='stylesButoon' type="submit">
                            Ingresar                 
                        </button>
                    </div>
                </section>
            </div>
            <div>
                <section class="Celda">
                    <div class="header">Equipo de trabajo 4</div>
                    <div class="main"></div>
                    <div class="footer">
                        <button  className='stylesButoon' type="submit">
                            Ingresar                                       
                        </button>
                    </div>
                </section>
            </div>
        </section>*/