import './TablesMapRoutes.css'
import { useEffect, useState } from "react";
import { URL_API_private } from "../../../providerContext/EndPoint";
import axios from "axios";
import { TablesPos } from './TablesPos';

export const TablesNap = ({id_fdt}) => {
    const [naps, setNaps] = useState([]);
    const endPointNap = URL_API_private+"/nap/byfdt/"+id_fdt;
    const [expandedRow, setExpandedRow] = useState([]);
    const token  = JSON.parse(localStorage.getItem('user_data')).token;

    useEffect(() => {
        getAllNaps();
    }, []);

    const config = {
        headers:{
            Authorization: `Bearer ${token}`,
        }
    }

    const getAllNaps = async () => {
        try {
            const response = await axios.get(endPointNap, config);
            setNaps(response.data);
        } catch (error) {
            console.error("Error del servidor: "+error.response);
        }
    }
    
    const handleRowToggle = (nap) => {
        if (expandedRow === nap.id_nap) {
            setExpandedRow(null);
        } else {
            setExpandedRow(nap.id_nap); 
        }
    }

    return (<>
        <div className='styleContentTableChildren'>
            <table className="styleTable">
                <thead className='stylesHead'>
                    <tr className='stylesHead'>     
                        <th className='stylesTh-Td'>CODIGO</th>
                        <th className='stylesTh-Td'>ESTADO</th>
                    </tr>
                </thead>
                <tbody className='stylesBody'>
                   {naps.map((nap) => (
                    <>
                        <tr className = 'stylesTr' key={nap.id_nap} onClick={() => handleRowToggle(nap)}>
                            <td className='stylesTh-Td'>{"Pos Nap: "+nap.cod}</td>
                            <td className='stylesTh-Td'>{nap.estado? "Activo": "Desactivado"}</td>
                        </tr>
                        {expandedRow === nap.id_nap && (
                            <tr>
                                <td colSpan="2">
                                    <div className='strylesContentOterTable'>
                                        <TablesPos id_nap= {nap.id_nap}/>
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