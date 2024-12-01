package org.example.backendwakandaresiduos.controller;

import org.example.backendwakandaresiduos.model.PapeleraDTO;
import org.example.backendwakandaresiduos.services.PapeleraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/papeleras")
public class PapeleraController {

    private final PapeleraService papeleraService;

    public PapeleraController(final PapeleraService papeleraService) {
        this.papeleraService = papeleraService;
    }

    /**
     * Endpoint para obtener todas las papeleras.
     */
    @GetMapping
    public ResponseEntity<List<PapeleraDTO>> getAllPapeleras() {
        List<PapeleraDTO> papeleras = papeleraService.listarPapeleras();
        return ResponseEntity.ok(papeleras);
    }

    /**
     * Endpoint para obtener información de una papelera específica.
     *
     * @param id ID de la papelera.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PapeleraDTO> getPapeleraById(@PathVariable Long id) {
        PapeleraDTO papelera = papeleraService.obtenerPapeleraPorId(id);
        return ResponseEntity.ok(papelera);
    }

    /**
     * Endpoint para actualizar el nivel de llenado de una papelera.
     *
     * @param id          ID de la papelera.
     * @param nivelLlenado Nuevo nivel de llenado de la papelera.
     */
    @PutMapping("/{id}/llenado")
    public ResponseEntity<Void> actualizarNivelLlenado(@PathVariable Long id, @RequestParam int nivelLlenado) {
        papeleraService.actualizarNivelLlenado(id, nivelLlenado);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para crear una nueva papelera.
     *
     * @param papeleraDTO Datos de la nueva papelera.
     */
    @PostMapping
    public ResponseEntity<PapeleraDTO> createPapelera(@RequestBody PapeleraDTO papeleraDTO) {
        PapeleraDTO nuevaPapelera = papeleraService.crearPapelera(papeleraDTO);
        return ResponseEntity.ok(nuevaPapelera);
    }

    /**
     * Endpoint para eliminar una papelera.
     *
     * @param id ID de la papelera que se desea eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePapelera(@PathVariable Long id) {
        papeleraService.eliminarPapelera(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para consultar la composición de residuos en una papelera.
     *
     * @param id ID de la papelera.
     */
    @GetMapping("/{id}/composicion")
    public ResponseEntity<String> consultarComposicionResiduos(@PathVariable Long id) {
        String composicion = papeleraService.consultarComposicionResiduos(id);
        return ResponseEntity.ok(composicion);
    }
}
