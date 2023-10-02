import React from "react";
import './Home.css'
export const Home = () => {
    return(<>ESCOJA AL EQUIPO DE TRABAJO AL QUE PERTENECE
        <section class="layout">
            <div>
                <section class="Celda">
                    <div class="header">Equipo de trabajo 1</div>
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
        </section>
        
    </>)
}