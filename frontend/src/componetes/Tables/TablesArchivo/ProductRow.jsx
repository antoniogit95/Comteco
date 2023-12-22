import React, { useState } from "react"
import botonmas from "../../../imagenes/botonmas.png"
import botonmenos from "../../../imagenes/botonmenos.png"
import ExcelTable from "./DatosProducto"

export const ProdcutRow = ({product}) => {
    if(!product){
        return "Cargando ...."
    }

    const [imagenClicada, setImagenClicada] = useState(false);
    const [showAdditionalTable, setShowAdditionalTable] = useState(false);

    const toggleAdditionalTable = () => {
        setShowAdditionalTable(!showAdditionalTable);
        setImagenClicada(!imagenClicada);
        console.log(product);
    };

    return (<>
        <tr className="stylesTr" key={product.id}>
            <td>
                <img
                    src={imagenClicada ? botonmenos : botonmas}
                    alt='Mostrar Detalles'
                    onClick={() => toggleAdditionalTable()}
                    style={{ cursor: 'pointer', width: '40px', height: '40px' }}
                />
            </td>
            <td className='stylesTh-Td'>{product.producto}</td>
            <td className='stylesTh-Td'>{product.estadoOrden}</td>
            <td className='stylesTh-Td'>{product.planComercial}</td>
            <td className='stylesTh-Td'>{product.tipoTramite}</td>
            <td className='stylesTh-Td'>{product.tipoTrabajo}</td>
            <td className='stylesTh-Td'>{product.tipoCliente}</td>
        </tr>
        {showAdditionalTable && (
            <tr>
            <td colSpan="7">
                <div className='strylesContentOterTable'>
                    <ExcelTable producto={product.producto} />
                </div>
            </td>
        </tr>
        )}
    </>);
}