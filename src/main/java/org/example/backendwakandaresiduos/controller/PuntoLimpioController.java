package org.example.backendwakandaresiduos.controller;


import org.example.backendwakandaresiduos.model.PuntoLimpioDTO;
import org.example.backendwakandaresiduos.services.PuntoLimpioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puntos-limpios")
public class PuntoLimpioController {

    private final PuntoLimpioService puntoLimpioService;

    public PuntoLimpioController(final PuntoLimpioService puntoLimpioService) {
        this.puntoLimpioService = puntoLimpioService;
    }

    /**
     * Endpoint para obtener todos los puntos limpios disponibles.
     */
    @GetMapping
    public ResponseEntity<List<PuntoLimpioDTO>> getAllPuntosLimpios() {
        List<PuntoLimpioDTO> puntosLimpios = puntoLimpioService.listarPuntosLimpios();
        return ResponseEntity.ok(puntosLimpios);
    }

    /**
     * Endpoint para obtener el punto limpio más cercano a una ubicación específica.
     *
     * @param ubicacion La ubicación actual del usuario (ejemplo: "Calle 1").
     */
    @GetMapping("/cercano")
    public ResponseEntity<PuntoLimpioDTO> getPuntoLimpioCercano(@RequestParam String ubicacion) {
        PuntoLimpioDTO puntoLimpioCercano = puntoLimpioService.obtenerPuntoLimpioMasCercano(ubicacion);
        return ResponseEntity.ok(puntoLimpioCercano);
    }

    /**
     * Endpoint para crear un nuevo punto limpio.
     *
     * @param puntoLimpioDTO Información del nuevo punto limpio.
     */
    @PostMapping
    public ResponseEntity<PuntoLimpioDTO> createPuntoLimpio(@RequestBody PuntoLimpioDTO puntoLimpioDTO) {
        PuntoLimpioDTO nuevoPuntoLimpio = puntoLimpioService.crearPuntoLimpio(puntoLimpioDTO);
        return ResponseEntity.ok(nuevoPuntoLimpio);
    }

    /**
     * Endpoint para actualizar un punto limpio existente.
     *
     * @param id            ID del punto limpio que se actualizará.
     * @param puntoLimpioDTO Nueva información del punto limpio.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePuntoLimpio(@PathVariable Long id, @RequestBody PuntoLimpioDTO puntoLimpioDTO) {
        puntoLimpioService.actualizarPuntoLimpio(id, puntoLimpioDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para eliminar un punto limpio.
     *
     * @param id ID del punto limpio que se eliminará.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuntoLimpio(@PathVariable Long id) {
        puntoLimpioService.eliminarPuntoLimpio(id);
        return ResponseEntity.noContent().build();
    }
}
