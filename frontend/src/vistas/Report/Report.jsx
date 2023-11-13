import './Report.css'
import React, { useState } from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { Reports } from "../../componetes/Reports/Reports"
import { SaveDataTecnico } from "../../componetes/saveFiles/saveDataTecnico/SaveDataTecnico"
import { NavBarReports } from "../../componetes/NavBar/NavBarReports/NavBarReports";
import { TablesMapRoutes } from "../../componetes/Tables/TablesMapRoutes/TablesMapRoutes";
import { TableArchive } from '../../componetes/Tables/TablesArchivo/TableArchive';

export const Report = () => {

    const [activeReport, setActiveReport] = useState("")

    const showReport = (reportType) => {
        setActiveReport(reportType);
    };

    return(<>
        <NavBar />
        <div className='styleContentGlobalReport'>
            <NavBarReports showReport={showReport}/>
            <div className="stylesContetnReport">
                {activeReport === "david" && <Reports/>}
                {activeReport === "jose" && <TableArchive/>}
                {activeReport === "map" && <TablesMapRoutes/>}
            </div>
        </div>
        {/**<SaveDataTecnico />*/}
    </>);
}