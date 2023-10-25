import './NavBarReports.css'

export const NavBarReports = ({showReport}) => {
    return (<div className='stylesNavBarReport'>

        <button className='stylesButoonNavBar' onClick={() => showReport("jose")}> Report Ing Jose</button>    
        <button className='stylesButoonNavBar' onClick={() => showReport("david")}> Report Ing David</button>
        <button className='stylesButoonNavBar' onClick={() => showReport("map")}> Map de Odf</button>
    </div>);
}