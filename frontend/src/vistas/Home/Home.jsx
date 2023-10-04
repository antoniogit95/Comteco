import React, { useState } from "react";
import './Home.css'
import { NavBar } from "../../componetes/NavBar/NavBar";
import { Button } from "../../componetes/Button/Button"

export const Home = () => {
    
    const [permisos, setPermisos] = useState("ADMIN");

    function isAdmin(){
        if(permisos === "ADMIN"){
            return true;
        }else{
            return false;
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
            <Button name="Registrar" link="/"/>
        </div>)
    }
    

    return(<>
        <NavBar />
        {isAdmin? buttonsAdmin(): buttonsSoporte()}       
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