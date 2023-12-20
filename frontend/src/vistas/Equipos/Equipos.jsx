import { NavBar } from "../../componetes/NavBar/NavBar"
import { SaveNaps } from "../../componetes/saveFiles/saveNaps/SaveNaps"
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
                <SavePlanesVelocidad />
                <SaveNaps />
            </div>
        </div>
    </>)
}