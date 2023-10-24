import React, { useState } from "react";
import { NavBar } from "../../componetes/NavBar/NavBar";
import { Reports } from "../../componetes/Reports/Reports"
import { SaveDataTecnico } from "../../componetes/saveFiles/saveDataTecnico/SaveDataTecnico"
import { NavBarReports } from "../../componetes/NavBar/NavBarReports/NavBarReports";
import { TablesMapRoutes } from "../../componetes/Tables/TablesMapRoutes/TablesMapRoutes";

export const Report = () => {

    const [activeReport, setActiveReport] = useState("")

    const showReport = (reportType) => {
        setActiveReport(reportType);
    };

    return(<>
        <NavBar />
        <NavBarReports showReport={showReport}/>
        <div>
            {activeReport === "jose" && <Reports/>}
            {activeReport === "david" && <h3>Reportes Ing David</h3>}
            {activeReport === "map" && <TablesMapRoutes/>}
        </div>
        {/**<SaveDataTecnico />*/}
    </>);
}