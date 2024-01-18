import './TablesMapRoutes.css'
import { useEffect, useState } from "react";
import { URL_API_private } from "../../../providerContext/EndPoint";
import 'react-icons/fa';
import { FaSpinner } from 'react-icons/fa'; 
import axios from "axios";
import { TablesPos } from './TablesPos';

export const TablesMapRoutes = () => {
    const [naps, setNaps] = useState([]);
    const endPoint = URL_API_private+"/naps";
    const [loading, setLoading] = useState(false);
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
        setLoading(true);
            try {
            const response = await axios.get(endPoint, config);
            setNaps(response.data);
            console.log("Naps obtenidos exitosamente")
            setLoading(false);
        } catch (error) {
            console.error("Error del servidor: "+error.response);
            setLoading(false);
        }
    }
    
    const handleRowToggle = (nap) => {
        if (expandedRow === nap.id) {
            setExpandedRow(null);
        } else {
            setExpandedRow(nap.id); 
        }
    }

    return (<>
        {loading && (
            <div className="loading-spinner">
                <FaSpinner className="spinner-icon" />
            </div>
        )}
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
                   {naps.map((nap) => (
                    <>
                        <tr className = 'stylesTr' key={nap.id} onClick={() => handleRowToggle(nap)}>
                            <td className='stylesTh-Td'>{nap.id}</td>
                            <td className='stylesTh-Td'>{nap.cod}</td>
                            <td className='stylesTh-Td'>{nap.descripcion}</td>
                            <td className='stylesTh-Td'>{nap.ubicacion}</td>
                        </tr>
                        {expandedRow === nap.id && (
                            <tr>
                                <td colSpan="4">
                                    <div className='strylesContentOterTable'>
                                        <TablesPos id_nap= {nap.id}/>
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