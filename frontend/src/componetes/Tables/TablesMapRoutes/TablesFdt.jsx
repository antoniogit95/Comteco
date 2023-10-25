import './TablesMapRoutes.css'
import { useEffect, useState } from "react";
import { URL_API_private } from "../../../providerContext/EndPoint";
import axios from "axios";
import { TablesNap } from './TablesNap';

export const TablesFdt = ({id_odf}) => {
    const [fdts, setFdts] = useState([]);
    const endPointFdt = URL_API_private+"/fdt/byodf/"+id_odf;
    const [expandedRow, setExpandedRow] = useState([]);
    const token  = JSON.parse(localStorage.getItem('user_data')).token;

    useEffect(() => {
        getAllFdts();
    }, []);

    const config = {
        headers:{
            Authorization: `Bearer ${token}`,
        }
    }

    const getAllFdts = async () => {
        try {
            const response = await axios.get(endPointFdt, config);
            setFdts(response.data);
        } catch (error) {
            console.error("Error del servidor: "+error.response);
        }
    }

    const handleRowToggle = (fdt) => {
        if (expandedRow === fdt.id_fdt) {
            setExpandedRow(null);
        } else {
            setExpandedRow(fdt.id_fdt); 
        }
    }
    
    return (<>
        <div className='styleContentTableChildren'>
            <table className="styleTable">
                <thead className='stylesHead'>
                    <tr className='stylesHead'>     
                        <th className='stylesTh-Td'>CODIGO</th>
                        <th className='stylesTh-Td'>TECNICO</th>
                    </tr>
                </thead>
                <tbody className='stylesBody'>
                   {fdts.map((fdt) => (<>
                        <tr className = 'stylesTr' key={fdt.id_fdt} onClick={() => handleRowToggle(fdt)}>
                            <td className='stylesTh-Td'>{fdt.cod}</td>
                            <td className='stylesTh-Td'>{fdt.tec}</td>
                        </tr>
                        {expandedRow === fdt.id_fdt && (
                            <tr>
                                <td colSpan="2">
                                    <div className='strylesContentOterTable'>
                                        <TablesNap id_fdt= {fdt.id_fdt}/>
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