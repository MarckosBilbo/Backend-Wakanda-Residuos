package org.example.backendwakandaresiduos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PuntoLimpioDTO {
    private Long id;
    private String ubicacion;
    private String tiposAceptados;
    private String descripcionCompleta;

    public PuntoLimpioDTO(Long id, String ubicacion, String tiposAceptados) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.tiposAceptados = tiposAceptados;
        this.descripcionCompleta = String.format("Ubicación: %s | Tipos de residuos aceptados: %s",
                ubicacion, tiposAceptados);
    }
}
