import './TablesMapRoutes.css'
import { useEffect, useState } from "react";
import { URL_API_private } from "../../../providerContext/EndPoint";
import axios from "axios";
import { TablesFdt } from './TablesFdt';

export const TablesMapRoutes = () => {
    const [odfs, setOdfs] = useState([]);
    const endPointOdf = URL_API_private+"/odf";
    const [expandedRow, setExpandedRow] = useState([]);
    const token  = JSON.parse(localStorage.getItem('user_data')).token;

    useEffect(() => {
        getAllOdfs();
    }, []);

    const config = {
        headers:{
            Authorization: `Bearer ${token}`,
        }
    }

    const getAllOdfs = async () => {
        try {
            const response = await axios.get(endPointOdf, config);
            setOdfs(response.data);
        } catch (error) {
            console.error("Error del servidor: "+error.response);
        }
    }
    
    const handleRowToggle = (odf) => {
        if (expandedRow === odf.id_odf) {
            setExpandedRow(null);
        } else {
            setExpandedRow(odf.id_odf); 
        }
    }

    return (<>
        <div className='styleContentTable'>
            <table className="styleTable">
                <thead className='stylesHead'>
                    <tr className='stylesHead'>     
                        <th className='stylesTh-Td'>ID</th>
                        <th className='stylesTh-Td'>CODIGO</th>
                        <th className='stylesTh-Td'>DESCRIPCION</th>
                        <th className='stylesTh-Td'>NOMBRE</th>
                    </tr>
                </thead>
                <tbody className='stylesBody'>
                   {odfs.map((odf) => (
                    <>
                        <tr className = 'stylesTr' key={odf.id_odf} onClick={() => handleRowToggle(odf)}>
                            <td className='stylesTh-Td'>{odf.id_odf}</td>
                            <td className='stylesTh-Td'>{odf.codigo}</td>
                            <td className='stylesTh-Td'>{odf.descripcion}</td>
                            <td className='stylesTh-Td'>{odf.nombre}</td>
                        </tr>
                        {expandedRow === odf.id_odf && (
                            <tr>
                                <td colSpan="4">
                                    <div className='strylesContentOterTable'>
                                        <TablesFdt id_odf= {odf.id_odf}/>
                                    </div>
                                </td>
                            </tr>
                        )}
                    </>
                    
                   ))}
                </tbody>
            </table>
        </div>
    </>);
}