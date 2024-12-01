package org.example.backendwakandaresiduos.services;

import org.example.backendwakandaresiduos.model.PapeleraDTO;
import org.example.backendwakandaresiduos.repos.PapeleraRepository;
import org.example.backendwakandaresiduos.domain.Papelera;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PapeleraService {

    private final PapeleraRepository papeleraRepository;

    public PapeleraService(final PapeleraRepository papeleraRepository) {
        this.papeleraRepository = papeleraRepository;
    }

    public List<PapeleraDTO> listarPapeleras() {
        return papeleraRepository.findAll().stream()
                .map(papelera -> new PapeleraDTO(
                        papelera.getId(),
                        papelera.getUbicacion(),
                        papelera.getNivelLlenado(),
                        papelera.getUltimaActualizacion()
                ))
                .collect(Collectors.toList());
    }

    public PapeleraDTO obtenerPapeleraPorId(Long id) {
        Papelera papelera = papeleraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Papelera no encontrada"));
        return new PapeleraDTO(
                papelera.getId(),
                papelera.getUbicacion(),
                papelera.getNivelLlenado(),
                papelera.getUltimaActualizacion()
        );
    }

    public void actualizarNivelLlenado(Long id, int nivelLlenado) {
        Papelera papelera = papeleraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Papelera no encontrada"));
        papelera.setNivelLlenado(nivelLlenado);
        papelera.setUltimaActualizacion(LocalDateTime.now());
        papeleraRepository.save(papelera);
    }

    public PapeleraDTO crearPapelera(PapeleraDTO papeleraDTO) {
        Papelera papelera = new Papelera();
        papelera.setUbicacion(papeleraDTO.getUbicacion());
        papelera.setNivelLlenado(papeleraDTO.getNivelLlenado());
        papelera.setUltimaActualizacion(LocalDateTime.now());
        papelera = papeleraRepository.save(papelera);
        return new PapeleraDTO(
                papelera.getId(),
                papelera.getUbicacion(),
                papelera.getNivelLlenado(),
                papelera.getUltimaActualizacion()
        );
    }

    public void eliminarPapelera(Long id) {
        papeleraRepository.deleteById(id);
    }

    public String consultarComposicionResiduos(Long id) {
        Papelera papelera = papeleraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Papelera no encontrada"));

        // Generar una composición aleatoria
        return String.format(
                "Orgánicos: %d%%, Plásticos: %d%%, Vidrio: %d%%, Metales: %d%%",
                (int) (Math.random() * 40),
                (int) (Math.random() * 30),
                (int) (Math.random() * 20),
                (int) (Math.random() * 10)
        );
    }
}
