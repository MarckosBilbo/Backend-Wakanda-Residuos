package org.example.backendwakandaresiduos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PapeleraDTO {
    private Long id;
    private String ubicacion;
    private int nivelLlenado;
    private String ultimaActualizacion;

    public PapeleraDTO(Long id, String ubicacion, int nivelLlenado, LocalDateTime ultimaActualizacion) {
    }
}
