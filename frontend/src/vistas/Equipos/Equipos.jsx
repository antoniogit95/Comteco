import { NavBar } from "../../componetes/NavBar/NavBar"
import { SaveArchive } from "../../componetes/saveFiles/saveDataGeneral/SaveArchive"
import { SaveDataOrdenDia } from "../../componetes/saveFiles/saveDataOrdenDia/SaveDataOrdenDia"
import { SavePlanesVelocidad } from "../../componetes/saveFiles/savePlanesVelocidad/SavePlanesVelocidad"
import './Equipos.css'

export const Equipos = () => {
    
    return(<>
        <NavBar />
        <div className="stylesContentEquipos">
            <h1>Equipos</h1>
            <br></br>
            <div className='cargaArchivos'>
                <SaveDataOrdenDia />                
                <SaveArchive />
                <SavePlanesVelocidad />
            </div>
        </div>
    </>)
}