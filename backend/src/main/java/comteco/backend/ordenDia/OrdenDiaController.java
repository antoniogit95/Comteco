package comteco.backend.ordenDia;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import comteco.backend.dataTecnico.DataTecnicoRequesBusqueda;
import lombok.AllArgsConstructor;

/**
 * Controlador de la clase OrdenDia
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orden_dia")
public class OrdenDiaController {
    
    private  OrdenDiaService ordenDiaService;

    /**
     * 
     * @return Todas las Ordenes del dia en formato resumido solo los datos solicitados.
     */
    @GetMapping("/resumido")
    public ResponseEntity<List<OrdenDiaResponse>> getOrdenDiaResponse(){
        List<OrdenDiaResponse> ordenDiaResponses= ordenDiaService.getOrdenDiaResumido();
        if(ordenDiaResponses != null){
            return new ResponseEntity<>(ordenDiaResponses, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 
     * @param producto Producto a ser buscado den la base de datos
     * @return si existe el producto lo retornara dentro un lista de productos.
     */
    @GetMapping("/producto/{producto}")
    public ResponseEntity<List<OrdenDiaResponseByProducto>> getOrdenDiaByProducto(@PathVariable Long producto){
        System.out.println("Producto recibido: " + producto);
        List<OrdenDiaResponseByProducto> ordenDias= ordenDiaService.getOrdenDiaByProducto(producto);
        if(ordenDias != null){
            return new ResponseEntity<>(ordenDias, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 
     * @param file Archivos a ser revisados que obtinen todos los valores de las ordenes de cada dia
     * @return un String donde estan los estados de cada linea de la orden dia dia si se guardo correcto 
     * o hubo alguna anomalia al momento de registrar.
     */
    @PostMapping("/save_file")
    public ResponseEntity<String> saveOrdenDiaFile(@RequestParam("file") MultipartFile file) {
        if(file != null){
            System.err.println("recibiendo el archivo");
            return ordenDiaService.saveFile(file);
        }else{
            return new ResponseEntity<>("el archivo esta vacio", HttpStatus.ACCEPTED);
        }
    }
    
    /**
     * 
     * @param ordenDia Los Datos de una ordenDia que estan en formato texto
     * @return El estado de la orden dia en formato texto.
     */
    @PostMapping("/txt")
    public ResponseEntity<String> saveOrdenDia(@RequestBody String ordenDia) {
        String savedOrdenDia = ordenDiaService.saveOrdenDia(ordenDia);
        return new ResponseEntity<>(savedOrdenDia, HttpStatus.CREATED);
    }

    /**
     * Metodo creados de forma generica
     * @return Todas Ordenes de dia, no recomendable porque rescata demaciados datos.
     */
    @GetMapping
    public ResponseEntity<List<OrdenDia>> getAllOrdenDias() {
        List<OrdenDia> ordenDias = ordenDiaService.getAllOrdenDias();
        return new ResponseEntity<>(ordenDias, HttpStatus.OK);
    }


    /**
     * Metodo creados de forma generica
     * @param id de la Orden del dia a buscar
     * @return Obtiene un Orden Dia en Su Formato Completo si existem, en caso de no existir
     * retorna not_found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDia> getOrdenDiaById(@PathVariable Long id) {
        return ordenDiaService.getOrdenDiaById(id)
                .map(ordenDia -> new ResponseEntity<>(ordenDia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * Metodo creados de forma generica
     * @param ordenDia a ser Guardada en la base de datos.
     * @return la orden dia a ser guardada.
     */
    @PostMapping
    public ResponseEntity<OrdenDia> saveOrdenDia(@RequestBody OrdenDia ordenDia) {
        OrdenDia savedOrdenDia = ordenDiaService.saveOrdenDia(ordenDia);
        return new ResponseEntity<>(savedOrdenDia, HttpStatus.CREATED);
    }


    /**
     * Metodo creados de forma generica
     * @param id de la orden de dia a ser borrado.
     * @return, no retorna nada.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdenDia(@PathVariable Long id) {
        ordenDiaService.deleteOrdenDia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * Metodo creados de forma generica
     * @param id de la orden dia a ser editado
     * @param ordenDia a ser cambiada o con los datos actualizados
     * @return la orden dia editada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrdenDia> editOrdenDia(@PathVariable String id, @RequestBody OrdenDia ordenDia) { 
        return new ResponseEntity<>(ordenDia, HttpStatus.OK);
    }

    /**
     * 
     * @param request recibimos el rango de fechas a buscar los datos tecnicos.
     * @return todas las ordenes del dia en la fecha seleccionada.
     */
    @PostMapping("/date")
    public ResponseEntity<List<OrdenDiaResponse>> getOrdenDiaByDate(@RequestBody DataTecnicoRequesBusqueda request) {
        List<OrdenDiaResponse> responses = ordenDiaService.getAllDatoTecnicoByIntervaloDate(request);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
