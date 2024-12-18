package org.example.backendwakandaresiduos.services;

import org.example.backendwakandaresiduos.domain.PuntoLimpio;
import org.example.backendwakandaresiduos.model.PuntoLimpioDTO;
import org.example.backendwakandaresiduos.repos.PuntoLimpioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuntoLimpioService {

    private final PuntoLimpioRepository puntoLimpioRepository;

    public PuntoLimpioService(PuntoLimpioRepository puntoLimpioRepository) {
        this.puntoLimpioRepository = puntoLimpioRepository;
    }

    /**
     * Listar todos los puntos limpios.
     */
    public List<PuntoLimpioDTO> listarPuntosLimpios() {
        return puntoLimpioRepository.findAll()
                .stream()
                .map(p -> new PuntoLimpioDTO(p.getId(), p.getUbicacion(), p.getTiposAceptados()))
                .collect(Collectors.toList());
    }

    /**
     * Obtener el punto limpio más cercano (simulado).
     */
    public PuntoLimpioDTO obtenerPuntoLimpioMasCercano(String ubicacion) {
        return puntoLimpioRepository.findAll()
                .stream()
                .findAny() // Simula encontrar el más cercano.
                .map(p -> new PuntoLimpioDTO(p.getId(), p.getUbicacion(), p.getTiposAceptados()))
                .orElseThrow(() -> new RuntimeException("No se encontraron puntos limpios cercanos."));
    }

    /**
     * Crear un nuevo punto limpio.
     */
    public PuntoLimpioDTO crearPuntoLimpio(PuntoLimpioDTO puntoLimpioDTO) {
        PuntoLimpio punto = new PuntoLimpio();
        punto.setUbicacion(puntoLimpioDTO.getUbicacion());
        punto.setTiposAceptados(puntoLimpioDTO.getTiposAceptados());

        PuntoLimpio guardado = puntoLimpioRepository.save(punto);

        return new PuntoLimpioDTO(guardado.getId(), guardado.getUbicacion(), guardado.getTiposAceptados());
    }

    /**
     * Actualizar un punto limpio existente.
     */
    public void actualizarPuntoLimpio(Long id, PuntoLimpioDTO puntoLimpioDTO) {
        PuntoLimpio puntoExistente = puntoLimpioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Punto limpio no encontrado"));

        puntoExistente.setUbicacion(puntoLimpioDTO.getUbicacion());
        puntoExistente.setTiposAceptados(puntoLimpioDTO.getTiposAceptados());

        puntoLimpioRepository.save(puntoExistente);
    }

    /**
     * Eliminar un punto limpio.
     */
    public void eliminarPuntoLimpio(Long id) {
        if (!puntoLimpioRepository.existsById(id)) {
            throw new RuntimeException("Punto limpio no encontrado");
        }
        puntoLimpioRepository.deleteById(id);
    }
}
