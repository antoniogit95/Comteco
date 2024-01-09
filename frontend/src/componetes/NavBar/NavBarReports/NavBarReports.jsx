import './NavBarReports.css'

export const NavBarReports = ({showReport}) => {
    return (<div className='stylesNavBarReport'>

        <button className='stylesButoonNavBar' onClick={() => showReport("jose")}>Producto</button>    
        <button className='stylesButoonNavBar' onClick={() => showReport("david")}>Analista</button>
        <button className='stylesButoonNavBar' onClick={() => showReport("map")}> Rutas Naps</button>
    </div>);
}