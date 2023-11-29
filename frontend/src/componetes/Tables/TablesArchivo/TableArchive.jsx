import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './TableArchive.css'
import { URL_API_private } from '../../../providerContext/EndPoint';

export const  TableArchive = () => {
    const [datos, setDatos] = useState([]);
    const [datosPlanes, setDatosPlanes] = useState([]);
    const [rescatarDatos, setRescatarDatos] = useState([])
    const [select, setSelectd] = useState("");
    const [buscar, setBuscar] = useState("");
    const endPoint = URL_API_private+"/files/get"
    const endPointPlanes = URL_API_private+"/plancomercial"
    const token  = JSON.parse(localStorage.getItem('user_data')).token
    const [datosTranspuestos, setDatosTranspuestos] = useState([]);
    const [datosPlanesFiltrados, setDatosPlanesFiltrados] = useState([]);
    const [datosEditados, setDatosEditados] = useState({});
    const [editando, setEditando] = useState(null);       


    useEffect( () => {
        getAllDatos();
        getAllDatosPlanes();

        console.log(datosPlanes);
    }, [])

    const config = {
        headers:{
            Authorization: `Bearer ${token}`,
        }
    }

    const getAllDatos = async () => {
        try {

            console.log(endPoint)
            console.log(config.headers)
            const response = await axios.get(endPoint, config);
            setDatos(response.data);
            setRescatarDatos(response.data);
            console.log("datos rescatados exitosamente")
        } catch (error) {
            console.log('Error al obtener datos:', error.response);
        }
        
    }

    const getAllDatosPlanes = async () => {
        try {
            const response = await axios.get(endPointPlanes, config);
            setDatosPlanes(response.data);
            setRescatarDatos(response.data);
            console.log("Datos de planes rescatados exitosamente");
        } catch (error) {
            console.log('Error al obtener datos de planes:', error.response);
        }
    }

    const manejarEdicion = (id) => {
        setEditando(id);
      };
    
      const manejarGuardar = (id) => {
        // Guardar los datos editados y salir del modo de edición
        setEditando(null);
        // Aquí deberías enviar los datos editados al servidor o realizar la lógica necesaria para guardarlos
      };

      const manejarCancelar = () => {
        setEditando(null);
        setDatosEditados({});
      };

    const filtrar = (terminoBusqueda, busquedaPor) => {
        console.log(terminoBusqueda +" - "+ busquedaPor + "mostrando algo")
        console.log("Filtrando...");
        if(terminoBusqueda !== ''){
            const resultadoBusqueda = rescatarDatos.filter((elemento) => {
                if (select === 'cliente') {
                    return elemento.cliente.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'fecha_CREATION') {
                    const fechaEnFormato = elemento.fecha_CREACION.split('T')[0];
                    return fechaEnFormato.includes(terminoBusqueda);
                } else if (select === 'area_servicio') {
                    return elemento.area_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_tipo_sol') {
                    return elemento.cod_TIPO_SOL.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_TIPO_SOL') {
                    return elemento.tipo_SOLICITUD.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_TIPO') {
                    return elemento.cod_TIPO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'tipo_TRABAJO') {
                    return elemento.tipo_TRABAJO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_PLAN_COMERCIAL') {
                    return elemento.cod_PLAN_COMERCIAL.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'plan_COMERCIAL') {
                    return elemento.plan_COMERCIAL.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'componente') {
                   return elemento.componente.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'clase_SERVICIO') {
                    return elemento.clase_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'area_servicio') {
                    return elemento.area_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'ubicacion') {
                    return elemento.ubicacion.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'producto') {
                    return elemento.producto.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'orden') {
                    return elemento.orden.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'desc_servicio') {
                    return elemento.desc_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                }else if (select === 'numero_servicio') {
                    return elemento.numero_SERVICIO.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'estado_componente') {
                    return elemento.estado_COMPONENTE.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'nap') {
                    return elemento.nap.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'posicion') {
                    return elemento.posicion.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'descripcion') {
                    return elemento.descripcion.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cliente') {
                    return elemento.cliente.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'direccion') {
                    return elemento.direccion.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'cod_estado_ot') {
                    return elemento.cod_ESTADO_OT.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                } else if (select === 'unidad_operativa') {
                    return elemento.unidad_OPERATIVA.toString().toLowerCase().includes(terminoBusqueda.toLowerCase());
                }
                return true;
            });
            setDatos(resultadoBusqueda);
           
            

            // Transforma los datos para mostrarlos en una tabla transpuesta
            const transpuestos = resultadoBusqueda.reduce((acc, dato) => {
                Object.keys(dato).forEach(key => {
                    if (!acc[key]) {
                        acc[key] = [];
                    }
                    // Evita valores duplicados en la misma clave
                    if (!acc[key].includes(dato[key])) {
                        acc[key].push(dato[key]);
                    }
                });
                return acc;
        }, {});

        const transpuestosArray = {};
        Object.keys(transpuestos).forEach(key => {
            transpuestosArray[key] = Array.from(transpuestos[key]);
        });

        setDatosTranspuestos(Object.entries(transpuestos));
      
        const datosPlanesFiltrados = datosPlanes.filter((datop) => {
            const velocidadEnDatop = datop.codLab;
            
            return resultadoBusqueda.some((dato) => {
                const primerosCuatro = dato.clase_SERVICIO.slice(0, 4);
                //console.log('primeros cuatro',primerosCuatro);
                const velocidadEnDatos = `${dato.cod_PLAN_COMERCIAL}-${primerosCuatro}`;
                //console.log('velocidad en datos',velocidadEnDatop);
                return velocidadEnDatos === velocidadEnDatop;
            });
        });
    
        setDatosPlanesFiltrados(datosPlanesFiltrados);


        resultadoBusqueda.forEach((dato) => {
            const velocidadEnDatos = dato.PLANES_VELOCIDAD.toString().toLowerCase();
        });
        
        datosPlanes.forEach((datoPlan) => {
            const velocidadEnDatop = datoPlan.cod_lab.toString().toLowerCase();
        });


        }
    }
    
    return (

        <div>
        <div>
            
            <input 
                type='text'
                id='buscar'
                name='buscar'
                placeholder='escribe tu elemento de busqueda'
                value={buscar}
                onChange={ (e) => setBuscar(e.target.value) }
            ></input>
            <select 
                value={select}
                onChange={(e) => setSelectd(e.target.value)}
                >
                <option value = '' >seleccione un elemnto</option>
                <option value = 'fecha_CREACION' >FECHA_CREACION</option>
                <option value = 'cod_tipo_sol'>COD_TIPO_SOL</option>
                <option value = 'cod_TIPO_SOL'>TIPO_SOLICITUD</option>
                <option value = 'cod_TIPO'>COD_TIPO</option>
                <option value = 'tipo_TRABAJO'>TIPO_TRABAJO</option>
                <option value = 'cod_PLAN_COMERCIAL'>COD_PLAN_COMERCIAL</option>
                <option value = 'plan_COMERCIAL'>PLAN_COMERCIAL</option>
                <option value = 'componente'>COMPONENTE</option>
                <option value = 'clase_SERVICIO'>CLASE_SERVICIO</option>
                <option value = 'area_servicio'>AREA_SERVICIO</option>
                <option value = 'ubicacion'>UBICACION</option>
                <option value = 'producto'>PRODUCTO</option>
                <option value = 'orden'>ORDEN</option>
                <option value = 'desc_servicio'>DESC_SERVICIO</option>
                <option value = 'numero_servicio'>NUMERO_SERVICIO</option>
                <option value = 'estado_componente'>ESTADO_COMPONENTE</option>
                <option value = 'nap'>NAP</option>
                <option value = 'posicion'>POSICION</option>
                <option value = 'descripcion'>DESCRIPCION</option>
                <option value = 'cliente'>CLIENTE</option>
                <option value = 'direccion'>DIRECCION</option>
                <option value = 'cod_estado_ot'>COD_ESTADO_OT</option>
                <option value = 'unidad_operativa'>UNIDAD_OPERATIVA</option>
            </select>
            <button  className='stylesButoon' onClick={ () => filtrar(buscar, select)}>Buscar</button>
            
        </div>
        <div className='tables-container'>
        <div className='table-container'>
            <table className='excel-table'>
                <thead className='table-header'>
                    <tr>
                        <th className='white-color'>Campo</th>
                        <th className='white-color'>Valor</th>
                    </tr>
                </thead>
                <tbody className='table-body'>
                    {datosTranspuestos.map((dato, index) => (
                    <tr key={index}>
                        <td>{dato[0]}</td>
                        <td>{dato[1].join(', ')}</td>
                    </tr>
                    ))}
                </tbody>
            </table>
        </div>
        
        <div className='table-container'>
            <table className='excel-table'>
                <thead className='table-header'>
                    <tr>
                        <th className='white-color'>ID</th>
                        <th className='white-color'>cod_lab</th>
                        <th className='white-color'>nueva_velocidad</th>
                        <th className='white-color'>nuevo_nombre</th>
                        <th className='white-color'>plan_comercial</th>
                        <th className='white-color'>tipo_equipo</th>
                        <th className='white-color'>tipo_plan</th>
                    </tr>
                </thead>
                <tbody className='table-body'>
                    {datosPlanesFiltrados.map((datoPlan) =>  (
                        <tr key={datoPlan.id}>
                            <td>{datoPlan.id}</td>
                            <td>{datoPlan.codLab}</td>
                            <td>{datoPlan.nuevaVelocidad}</td>
                            <td>{datoPlan.nuevoNombre}</td>
                            <td>{datoPlan.planComercial}</td>
                            <td>{datoPlan.tipoEquipo}</td>  
                            <td>{datoPlan.tipoPlan}</td>  
                        </tr>
                        )
                         )}
                </tbody>
            </table>
        </div>
        <div className="table-container">
      <table className="excel-table">
        <thead className="table-header">
          <tr>
            <th className="white-color">Componente</th>
            <th className="white-color">Clase de Servicio</th>
            <th className="white-color">Número de Servicio</th>
            <th className="white-color">Estado Componente</th>
            <th className="white-color">Acciones</th>
          </tr>
        </thead>
        <tbody className="table-body">
          {datos.map((dato) => (
            <tr key={dato.id} style={{ background: editando === dato.id ? 'yellow' : 'transparent' }}>
              <td>
                {editando === dato.id ? (
                  <input
                    type="text"
                    value={datosEditados[dato.id]?.componente || dato.componente}
                    onChange={(e) =>
                      setDatosEditados({
                        ...datosEditados,
                        [dato.id]: { ...datosEditados[dato.id], componente: e.target.value },
                      })
                    }
                  />
                ) : (
                  dato.componente
                )}
              </td>
              <td>
                {editando === dato.id ? (
                  <input
                    type="text"
                    value={datosEditados[dato.id]?.clase_SERVICIO || dato.clase_SERVICIO}
                    onChange={(e) =>
                      setDatosEditados({
                        ...datosEditados,
                        [dato.id]: { ...datosEditados[dato.id], clase_SERVICIO: e.target.value },
                      })
                    }
                  />
                ) : (
                  dato.clase_SERVICIO
                )}
              </td>
              <td>
                {editando === dato.id ? (
                  <input
                    type="text"
                    value={datosEditados[dato.id]?.numero_SERVICIO || dato.numero_SERVICIO}
                    onChange={(e) =>
                      setDatosEditados({
                        ...datosEditados,
                        [dato.id]: { ...datosEditados[dato.id], numero_SERVICIO: e.target.value },
                      })
                    }
                  />
                ) : (
                  dato.numero_SERVICIO
                )}
              </td>
              <td>
                {editando === dato.id ? (
                  <input
                    type="text"
                    value={datosEditados[dato.id]?.estado_COMPONENTE || dato.estado_COMPONENTE}
                    onChange={(e) =>
                      setDatosEditados({
                        ...datosEditados,
                        [dato.id]: { ...datosEditados[dato.id], estado_COMPONENTE: e.target.value },
                      })
                    }
                  />
                ) : (
                  dato.estado_COMPONENTE
                )}
              </td>
              <td>
                {editando === dato.id ? (
                  <div>
                    <button className='stylesButoon'  onClick={() => manejarGuardar(dato.id)}>Guardar</button>
                    <button className='stylesButoon'  onClick={manejarCancelar}>Cancelar</button>
                  </div>  
                ) : (
                  <button className='stylesButoon'  onClick={() => manejarEdicion(dato.id)}>Editar</button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>

        </div>


        
            

            <div className='table-container'>
            <table className='excel-table'>
                <thead className='table-header'>
                    <tr>
                        <th className='white-color'>ID</th>
                        <th className='white-color'>corresponde_internet</th>
                        <th className='white-color'>cod_lab</th>
                        <th className='white-color'>codigo_alternativa</th>
                        <th className='white-color'>codigo_help_desk</th>
                        <th className='white-color'>codigo_plan_comercial</th>
                        <th className='white-color'>fecha_creacion</th>
                        <th className='white-color'>incremento_mbps</th>
                        <th className='white-color'>notas</th>
                        <th className='white-color'>nueva_tarifa</th>
                        <th className='white-color'>nueva_velocidad</th>
                        <th className='white-color'>nuevo_nombre</th>
                        <th className='white-color'>plan_comercial</th>
                        <th className='white-color'>plan_corto</th>
                        <th className='white-color'>tarifa1</th>
                        <th className='white-color'>tipo_equipo</th>
                        <th className='white-color'>tipo_plan</th>
                    </tr>
                </thead>
                <tbody className='table-body'>
                    {datosPlanesFiltrados.map((datoPlan) =>  (
                        <tr key={datoPlan.id}>
                            <td>{datoPlan.id}</td>
                            <td>{datoPlan.correspondeInternet}</td>
                            <td>{datoPlan.codLab}</td>
                            <td>{datoPlan.codigoAlternativa}</td>
                            <td>{datoPlan.codigoHelpDesk}</td>
                            <td>{datoPlan.codigoPlanComercial}</td>
                            <td>{datoPlan.fechaCreacion}</td>
                            <td>{datoPlan.incrementoMbps}</td>
                            <td>{datoPlan.notas}</td>
                            <td>{datoPlan.nuevaTarifa}</td>
                            <td>{datoPlan.nuevaVelocidad}</td>
                            <td>{datoPlan.nuevoNombre}</td>
                            <td>{datoPlan.planComercial}</td>
                            <td>{datoPlan.planCorto}</td>   
                            <td>{datoPlan.tarifa1}</td>  
                            <td>{datoPlan.tipoEquipo}</td>  
                            <td>{datoPlan.tipoPlan}</td>  
                        </tr>
                    ))}
                </tbody>
            </table>
            </div>


        </div>
    );
}