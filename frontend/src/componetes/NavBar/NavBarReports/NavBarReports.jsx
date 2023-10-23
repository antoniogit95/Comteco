import './NavBarReports.css'

export const NavBarReports = ({showReport}) => {
    return (<div className='stylesNavBarReport'>

        <button onClick={() => showReport("jose")}> Report Ing Jose</button>    
        <button onClick={() => showReport("david")}> Report Ing David</button>
        <button onClick={() => showReport("map")}> Map de Odf</button>
    </div>);
}