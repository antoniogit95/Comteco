import './TablesMapRoutes.css'
import { useEffect, useState } from "react";
import { URL_API_private } from "../../../providerContext/EndPoint";
import axios from "axios";

export const TablesPos = ({id_nap}) => {
    const [poss, setPoss] = useState([]);
    const endPointPos = URL_API_private+"/naps/pos/byNap/"+id_nap;
    const token  = JSON.parse(localStorage.getItem('user_data')).token;

    useEffect(() => {
        getAllPoss();
    }, []);

    const config = {
        headers:{
            Authorization: `Bearer ${token}`,
        }
    }

    const getAllPoss = async () => {
        try {
            const response = await axios.get(endPointPos, config);
            setPoss(response.data);
        } catch (error) {
            console.error("Error del servidor: "+error.response);
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
                   {poss.map((pos) => (<>
                        <tr className = 'stylesTr' key={pos.id_pos}>
                            <td className='stylesTh-Td'>{"Posicion: "+pos.cod}</td>
                            <td className='stylesTh-Td'>{pos.estado? "Activo": "desactivado"}</td>
                        </tr>
                   </>
                    
                   ))}
                </tbody>
            </table>
        </div>
    </>);
}