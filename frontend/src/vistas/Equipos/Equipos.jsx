import { NavBar } from "../../componetes/NavBar/NavBar"
import { SaveArchive } from "../../componetes/saveFiles/saveDataGeneral/SaveArchive"
import './Equipos.css'

export const Equipos = () => {
    return(<>
        <NavBar />
        <div className="stylesContentEquipos">
        <h1>Equipos</h1>    
        <SaveArchive />
        </div>
    </>)
}