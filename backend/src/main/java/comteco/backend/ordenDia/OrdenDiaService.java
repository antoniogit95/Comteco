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

import comteco.backend.nap.Nap;
import comteco.backend.nap.NapRepository;
import comteco.backend.nap.posicion.Posicion;
import comteco.backend.nap.posicion.PosicionRepository;
import comteco.backend.ordenDia.planComercial.PlanComercial;
import comteco.backend.ordenDia.planComercial.PlanComercialRepository;
import comteco.backend.ordenDia.servicio.Servicio;
import comteco.backend.ordenDia.servicio.ServicioRepository;
import comteco.backend.ordenDia.solicitud.Solicitud;
import comteco.backend.ordenDia.solicitud.SolicitudRepository;
import comteco.backend.ordenDia.trabajo.Trabajo;
import comteco.backend.ordenDia.trabajo.TrabajoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrdenDiaService {

    private OrdenDiaRepository ordenDiaRepository;
    private PlanComercialRepository planComercialRepository;
    private SolicitudRepository solicitudRepository;
    private TrabajoRepository trabajoRepository;
    private ServicioRepository servicioRepository;
    private PosicionRepository posicionRepository;
    private NapRepository napRepository;

    public List<OrdenDia> getAllOrdenDias() {
        return ordenDiaRepository.findAll();
    }

    public Optional<OrdenDia> getOrdenDiaById(Long id) {
        return ordenDiaRepository.findById(id);
    }

    public OrdenDia saveOrdenDia(OrdenDia ordenDia) {
        return ordenDiaRepository.save(ordenDia);
    }

    public void deleteOrdenDia(Long id) {
        ordenDiaRepository.deleteById(id);
    }

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
                String partes[] = linea.split(";");
                if(partes.length == 29){

                    PlanComercial planComercial = PlanComercial.builder()
                        .codLab(partes[5]+"-")
                        .planCorto(partes[6])
                        .build();
                    planComercialRepository.save(planComercial);
                    
                    Solicitud solicitud = Solicitud.builder()
                        .codTipoSol(Long.parseLong(partes[1]))
                        .tipoSolicitud(partes[2])
                        .planComercial(planComercial)
                        .build();
                    solicitudRepository.save(solicitud);

                    Trabajo trabajo = Trabajo.builder()
                        .codTipo(Long.parseLong(partes[3]))
                        .tipoTrabajo(partes[4])
                        .build();
                    trabajoRepository.save(trabajo);
                    
                    Servicio servicio = Servicio.builder()
                        .componente(partes[7])
                        .claseServicio(partes[8])
                        .areaServicio(partes[9])
                        .numeroServicio(partes[16])
                        .build();
                    servicioRepository.save(servicio);


                    Nap nap = Nap.builder().cod(partes[18]).build();
                    napRepository.save(nap);
                    Posicion pos = Posicion.builder().cod(partes[19]).nap(nap).build();
                    posicionRepository.save(pos);

                    OrdenDia ordenDia = OrdenDia.builder()
                        .fecha(getFecha(partes[0]))
                        .solicitud(solicitud)
                        .trabajo(trabajo)
                        .servicio(servicio)
                        .ubicacion(partes[10])
                        .contrato(partes[11])
                        .producto(Long.parseLong(partes[12]))
                        .orden(partes[13])
                        .posicion(pos)
                        .estado(Integer.parseInt(partes[14]) == 1)
                        .estadoOt(partes[17])
                        .descripcion(partes[20])
                        .actividad(partes[21])
                        .codUnidad(partes[22])
                        .unidadOperativa(partes[23])
                        .cliente(partes[24])
                        .direccion(partes[25])
                        .tipoCliente(partes[26])
                        .puntoVenta(partes[27])
                        .vendedor(partes[28])
                        .build();
                    ordenDiaRepository.save(ordenDia);
                }
            }
            return new ResponseEntity<>("ARCHIVO REGISTARDA CON EXITO", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("ERROR EN LA CARGA DE ARCHIVOS", HttpStatus.NOT_FOUND);
        }

    }

    private Timestamp getFecha(String date){
        try {
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formatFecha.parse(date);
            return new Timestamp(fecha.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public List<OrdenDiaResponse> getOrdenDiaResumido() {
        try {
            List<OrdenDia> ordenDias = ordenDiaRepository.findAll();
            List<OrdenDiaResponse> responses = new ArrayList<>();
            for (OrdenDia ordenDia : ordenDias) {
                OrdenDiaResponse res = OrdenDiaResponse.builder()
                    .producto(ordenDia.getProducto()+"")
                    .estadoOrden(ordenDia.getEstadoOt())
                    .planComercial(ordenDia.getSolicitud().getPlanComercial().getPlanCorto())
                    .fecha(ordenDia.getFecha())
                    .tipoTramite(ordenDia.getSolicitud().getTipoSolicitud())
                    .tipoTrabajo(ordenDia.getTrabajo().getTipoTrabajo())
                    .tipoCliente(ordenDia.getTipoCliente())
                    .build();
                responses.add(res);
            }
            return responses;
        } catch (Exception e) {
            return null;
        }
    }
}
