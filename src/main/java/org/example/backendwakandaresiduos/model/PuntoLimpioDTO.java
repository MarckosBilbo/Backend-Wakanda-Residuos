package org.example.backendwakandaresiduos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PuntoLimpioDTO {
    private Long id;
    private String ubicacion;
    private String tiposAceptados;
}
