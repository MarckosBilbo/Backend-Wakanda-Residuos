package org.example.backendwakandaresiduos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "puntos_limpios")
@Getter
@Setter
public class PuntoLimpio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ubicacion; // Calle o coordenadas del punto limpio

    @Column(nullable = false)
    private String tiposAceptados; // Tipos de residuos aceptados

    // Constructor vacío (necesario para JPA)
    public PuntoLimpio() {}

    // Constructor con parámetros
    public PuntoLimpio(String ubicacion, String tiposAceptados) {
        this.ubicacion = ubicacion;
        this.tiposAceptados = tiposAceptados;
    }

}
