package org.example.backendwakandaresiduos.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PapeleraDTO {
    private Long id;
    private String ubicacion;
    private int nivelLlenado;
    private String composicionResiduos; // Ej: "Orgánico: 40%, Resto: 30%, Envases: 30%"

    public PapeleraDTO(Long id, String ubicacion, int nivelLlenado, String composicionResiduos) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.nivelLlenado = nivelLlenado;
        this.composicionResiduos = composicionResiduos;
    }

    public PapeleraDTO(Long id, String ubicacion, int nivelLlenado, LocalDateTime ultimaActualizacion) {
    }
}
