package org.example.backendwakandaresiduos.services.usuario;



import org.example.backendwakandaresiduos.domain.usuario.Credenciales;
import org.example.backendwakandaresiduos.model.usuario.CredencialesDTO;
import org.example.backendwakandaresiduos.repos.usuario.CredencialesRepository;
import org.springframework.stereotype.Service;

@Service
public class CredencialesService {

    private final CredencialesRepository credencialesRepository;

    public CredencialesService(final CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    public CredencialesDTO get(final Long id) {
        return credencialesRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Credenciales no encontradas con ID: " + id));
    }

    public Long create(final CredencialesDTO credencialesDTO) {
        final Credenciales credenciales = mapToEntity(credencialesDTO, new Credenciales());
        return credencialesRepository.save(credenciales).getId();
    }

    private CredencialesDTO mapToDTO(final Credenciales credenciales) {
        CredencialesDTO dto = new CredencialesDTO();
        dto.setId(credenciales.getId());
        dto.setCorreo(credenciales.getCorreo());
        dto.setPassword(credenciales.getPassword());
        dto.setUsuarioId(credenciales.getUsuario() != null ? credenciales.getUsuario().getId() : null);
        return dto;
    }

    private Credenciales mapToEntity(final CredencialesDTO dto, final Credenciales credenciales) {
        credenciales.setCorreo(dto.getCorreo());
        credenciales.setPassword(dto.getPassword());
        return credenciales;
    }
}

