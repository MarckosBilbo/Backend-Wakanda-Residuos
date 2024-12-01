package org.example.backendwakandaresiduos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "papeleras")
@Getter
@Setter
public class Papelera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ubicacion; // Calle o coordenadas de la papelera

    @Column(nullable = false)
    private int nivelLlenado; // Nivel de llenado, de 0 a 250

    @Column(nullable = false)
    private LocalDateTime ultimaActualizacion;

    // Constructor vacío (necesario para JPA)
    public Papelera() {}

    // Constructor con parámetros
    public Papelera(String ubicacion, int nivelLlenado, LocalDateTime ultimaActualizacion) {
        this.ubicacion = ubicacion;
        this.nivelLlenado = nivelLlenado;
        this.ultimaActualizacion = ultimaActualizacion;
    }
}

