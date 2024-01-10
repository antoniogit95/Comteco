package comteco.backend.ordenDia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import comteco.backend.nap.posicion.Posicion;
import comteco.backend.nap.posicion.PosicionService;
import comteco.backend.ordenDia.cliente.Cliente;
import comteco.backend.ordenDia.cliente.ClienteRepository;
import comteco.backend.ordenDia.planComercial.PlanComercial;
import comteco.backend.ordenDia.planComercial.PlanComercialRepository;
import comteco.backend.ordenDia.servicio.Servicio;
import comteco.backend.ordenDia.servicio.ServicioRepository;
import comteco.backend.ordenDia.solicitud.Solicitud;
import comteco.backend.ordenDia.solicitud.SolicitudRepository;
import comteco.backend.ordenDia.trabajo.Trabajo;
import comteco.backend.ordenDia.trabajo.TrabajoRepository;
import comteco.backend.ordenDia.vendedor.Vendedor;
import comteco.backend.ordenDia.vendedor.VendedorRepository;
import lombok.AllArgsConstructor;

/**
 * Servicio de la clase OrdenDia
 */
@Service
@AllArgsConstructor
public class OrdenDiaService {

    private OrdenDiaRepository ordenDiaRepository;
    private PlanComercialRepository planComercialRepository;
    private SolicitudRepository solicitudRepository;
    private TrabajoRepository trabajoRepository;
    private ServicioRepository servicioRepository;
    private ClienteRepository clienteRepository;
    private VendedorRepository vendedorRepository;
    private PosicionService posicionService;

    /**
     * @return debulve todas las ordenes del dia.
     */
    public List<OrdenDia> getAllOrdenDias() {
        return ordenDiaRepository.findAll();
    }

    /**
     * 
     * @param id de la orden dia a buscar
     * @return un objeto de tipo Otional que puede o no tener una orden dia.
     */
    public Optional<OrdenDia> getOrdenDiaById(Long id) {
        return ordenDiaRepository.findById(id);
    }

    /**
     * 
     * @param ordenDia a ser gurdada en la base de datos
     * @return la misma orden dia una vez guardada
     */
    public OrdenDia saveOrdenDia(OrdenDia ordenDia) {
        return ordenDiaRepository.save(ordenDia);
    }

    /**
     * 
     * @param id de la orden dia a ser borrada.
     */
    public void deleteOrdenDia(Long id) {
        ordenDiaRepository.deleteById(id);
    }

    /**
     * Guardar una orden dia en formato de String
     * @param ordenDiaString en formato de String que contiene todos los campos de una orden dia,
     * @return un String con los datos mensionados
     */
    public String saveOrdenDia(String ordenDiaString){
        String partes[] = ordenDiaString.split(";"); //dividimos la orden dia en 29 partes por el ;
        if(partes.length == 29){ //preguntamos si tiene un longitud = 29

            Long productId = Long.parseLong(partes[12] != null ? partes[12]: "0");
            System.err.println("IMPRIMIR EL ID DEL PRODUCOT");
            Optional<OrdenDia> oDOptional = getOprdenDiaByProducto(productId);

            /*
             * Preguntamos si el producto existe en la base de datos, en caso que exista para no guardar doble,
             * solo guardar su Servicio que cada orden dia tiene servicios distintos.
             */
            if(oDOptional.isPresent()){
                System.out.println("POSICIION YA EXISTE");
                OrdenDia ordenDia = oDOptional.get();
                Servicio servicio = Servicio.builder()
                    .componente(partes[7])
                    .claseServicio(partes[8])
                    .areaServicio(partes[9])
                    .numeroServicio(partes[16])
                    .estado(partes[17])
                    .ordenDia(ordenDia)
                    .build();
                Servicio savedServicio = servicioRepository.save(servicio);
                ordenDia.getServicio().add(savedServicio);
                ordenDiaRepository.save(ordenDia);
            }else{
                
                System.err.println("No Existe el producto, creadando...");
                //Creacion del Plan_comercial
                PlanComercial planComercial = PlanComercial.builder()
                    .codLab(partes[5])
                    .planCorto(partes[6])
                    .build();
                planComercialRepository.save(planComercial);
                System.out.println("plan comercial creado");
                //Creacion de la Solicitud
                Solicitud solicitud = Solicitud.builder()
                    .codTipoSol(Long.parseLong(partes[1]))
                    .tipoSolicitud(partes[2])
                    .planComercial(planComercial)
                    .build();
                solicitudRepository.save(solicitud);
                System.out.println("Solicitud Creada");

                //Creacion del Trabajo
                Trabajo trabajo = Trabajo.builder()
                    .codTipo(Long.parseLong(partes[3]))
                    .tipoTrabajo(partes[4])
                    .build();
                trabajoRepository.save(trabajo);

                System.out.println("trabajo creado creado");
                //Creacion de la ruta nap, en caso de ya existir reasignar
                Posicion pos = posicionService.saveNapAndPos(partes[18]+"-"+partes[19], partes[20]);
                System.out.println("posicion creado");

                //Creacion del Clinete
                Cliente cliente = Cliente.builder()
                    .cliente(partes[24])
                    .direccion(partes[25])
                    .tipoCliente(partes[26])
                    .build();
                clienteRepository.save(cliente);
                System.out.println("cliente creado");

                //Creacion Vendedor
                Vendedor vendedor = Vendedor.builder()
                    .puntoVenta(partes[27])
                    .vendedor(partes[28])
                    .build();
                vendedorRepository.save(vendedor);
                System.out.println("vendedor creado");

                //Creacion del Orden_Dia
                OrdenDia ordenDia = OrdenDia.builder()
                    .fecha(getFecha(partes[0]))
                    .solicitud(solicitud)
                    .trabajo(trabajo)
                    .ubicacion(partes[10])
                    .contrato(partes[11])
                    .producto(Long.parseLong(partes[12]))
                    .orden(partes[13])
                    .posicion(pos)
                    .cliente(cliente)
                    .vendedor(vendedor)
                    .estadoOt(Integer.parseInt(partes[14]) == 1)
                    .actividad(partes[21])
                    .servicio(new ArrayList<>())
                    .build();
                ordenDiaRepository.save(ordenDia);
                System.out.println("orden dia creado creado");

                Servicio servicio = Servicio.builder()
                    .componente(partes[7])
                    .claseServicio(partes[8])
                    .areaServicio(partes[9])
                    .numeroServicio(partes[16])
                    .ordenDia(ordenDia)
                    .build();
                servicioRepository.save(servicio);
                ordenDia.getServicio().add(servicio);
                System.out.println("servicio creado");
            }
         }     
        return "OrdenDiaRegistrado.";
    }

    /**
     * Busca un Objeto de Orden Dia en la base de datos
     * @param producto valor por el cual buscar la ordenDia
     * @return un Objeto de tipo Optional que contiene o no una OrdenDia.
     */
    public Optional<OrdenDia> getOprdenDiaByProducto(Long producto) {
        return ordenDiaRepository.findByProducto(producto);
    }

    /**
     * 
     * @param file una lista de ordenes de dia a ser guardados en la base de datos
     * @return una cadena por cada orden dia registrado en caso de haber algun fallo especificar en la cadena
     */
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String linea;
            boolean firstLine = true;
            while ((linea = br.readLine()) != null) {
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                saveOrdenDia(linea);
            }
            return new ResponseEntity<>("ARCHIVO REGISTARDA CON EXITO", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("ERROR EN LA CARGA DE ARCHIVOS", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * 
     * @param date formato de la fecha en String 
     * @return un objetod e tipo Timestamp que obtiene la fecha
     */
    private Timestamp getFecha(String date){
        try {
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formatFecha.parse(date);
            return new Timestamp(fecha.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @return todas las Ordenes dia en formato resumido 
     */
    public List<OrdenDiaResponse> getOrdenDiaResumido() {
        try {
            List<OrdenDia> ordenDias = ordenDiaRepository.findAll();
            List<OrdenDiaResponse> responses = new ArrayList<>();
            for (OrdenDia ordenDia : ordenDias) {
                OrdenDiaResponse res = OrdenDiaResponse.builder()
                    .producto(ordenDia.getProducto()+"")
                    .planComercial(ordenDia.getSolicitud().getPlanComercial().getPlanCorto())
                    .fecha(ordenDia.getFecha())
                    .tipoTramite(ordenDia.getSolicitud().getTipoSolicitud())
                    .tipoTrabajo(ordenDia.getTrabajo().getTipoTrabajo())
                    .tipoCliente(ordenDia.getCliente().getTipoCliente())
                    .build();
                responses.add(res);
            }
            return responses;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @param producto a ser buscado en la base de datos
     * @return todos los ordenes del dia dela base de datos relaciados al producto
     */
    public List<OrdenDiaResponseByProducto> getOrdenDiaByProducto(Long producto) {
        List<OrdenDia> ordenDias = ordenDiaRepository.findAllByProducto(producto);
        List<OrdenDiaResponseByProducto> response = new ArrayList<>();
        for (OrdenDia ordenDia : ordenDias) {
            OrdenDiaResponseByProducto oByProducto = OrdenDiaResponseByProducto.builder()
                .nap(ordenDia.getPosicion().getNap().getCod())
                .posicion(ordenDia.getPosicion().getCod())
                .datoTecnico(ordenDia.getPosicion().getNap().getCod()+"-"+ordenDia.getPosicion().getCod())
                .zona(ordenDia.getUbicacion())
                .ubicacion("sin ubicacion geografica")
                //.direccion(ordenDia.getDireccion())
                .build();
            response.add(oByProducto);
        }
        return response;
    }
}
