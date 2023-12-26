import './NavBarReports.css'

export const NavBarReports = ({showReport}) => {
    return (<div className='stylesNavBarReport'>

        <button className='stylesButoonNavBar' onClick={() => showReport("jose")}>Reportes</button>    
        <button className='stylesButoonNavBar' onClick={() => showReport("david")}>Analista</button>
        <button className='stylesButoonNavBar' onClick={() => showReport("map")}> Map de Odf</button>
    </div>);
}