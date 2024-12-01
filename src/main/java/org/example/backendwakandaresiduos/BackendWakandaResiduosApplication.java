package org.example.backendwakandaresiduos;

import org.example.backendwakandaresiduos.domain.Papelera;
import org.example.backendwakandaresiduos.domain.PuntoLimpio;
import org.example.backendwakandaresiduos.repos.PapeleraRepository;
import org.example.backendwakandaresiduos.repos.PuntoLimpioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BackendWakandaResiduosApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendWakandaResiduosApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PapeleraRepository papeleraRepository, PuntoLimpioRepository puntoLimpioRepository) {
        return args -> {
            // Inicializar papeleras con datos ficticios
            papeleraRepository.save(new Papelera("Calle 1", 30, LocalDateTime.now()));
            papeleraRepository.save(new Papelera("Calle 2", 80, LocalDateTime.now()));
            papeleraRepository.save(new Papelera("Calle 3", 150, LocalDateTime.now()));
            papeleraRepository.save(new Papelera("Avenida Principal", 200, LocalDateTime.now()));

            // Inicializar puntos limpios con datos ficticios
            puntoLimpioRepository.save(new PuntoLimpio("Plaza Central", "Electrónicos, Plásticos, Orgánicos"));
            puntoLimpioRepository.save(new PuntoLimpio("Zona Norte", "Electrónicos, Vidrio, Papel"));
            puntoLimpioRepository.save(new PuntoLimpio("Zona Sur", "Orgánicos, Metales"));
            puntoLimpioRepository.save(new PuntoLimpio("Parque Urbano", "Electrónicos, Vidrio, Plásticos"));
        };
    }
}
