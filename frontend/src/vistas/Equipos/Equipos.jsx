import { NavBar } from "../../componetes/NavBar/NavBar"
import { SaveArchive } from "../../componetes/saveFiles/saveDataGeneral/SaveArchive"
import { SavePlanesVelocidad } from "../../componetes/saveFiles/savePlanesVelocidad/SavePlanesVelocidad"
import './Equipos.css'

export const Equipos = () => {
    return(<>
        <NavBar />
        <div className="stylesContentEquipos">
        <h1>Equipos</h1>  
        <section class="layout">
        <div>    
        <SaveArchive />
        </div>
        <div>
        <SavePlanesVelocidad />
        </div>
        </section>  
        </div>
        
    </>)
}