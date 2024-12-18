package org.example.backendwakandaresiduos;

import org.example.backendwakandaresiduos.services.*;
import org.example.backendwakandaresiduos.domain.Papelera;
import org.example.backendwakandaresiduos.domain.PuntoLimpio;
import org.example.backendwakandaresiduos.repos.PapeleraRepository;
import org.example.backendwakandaresiduos.repos.PuntoLimpioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@EnableDiscoveryClient
@SpringBootApplication
public class BackendWakandaResiduosApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendWakandaResiduosApplication.class, args);
    }

    @Bean
    CommandLineRunner run(PapeleraRepository papeleraRepository,
                          PuntoLimpioRepository puntoLimpioRepository,
                          PapeleraService papeleraService) {
        return args -> {
            Random random = new Random();

            // Inicializar papeleras
            papeleraRepository.save(new Papelera("Calle A", random.nextInt(100), LocalDateTime.now()));
            papeleraRepository.save(new Papelera("Calle B", random.nextInt(100), LocalDateTime.now()));

            // Inicializar puntos limpios
            puntoLimpioRepository.save(new PuntoLimpio("Parque Central", "Orgánicos, Envases"));
            puntoLimpioRepository.save(new PuntoLimpio("Avenida Norte", "Resto, Plásticos"));

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                System.out.println("\n[INFO] Estado actual de las papeleras:");
                papeleraRepository.findAll().forEach(p -> {
                    String composicion = papeleraService.consultarComposicionResiduos(p.getId());
                    System.out.println("Papelera en " + p.getUbicacion() +
                            " | Nivel llenado: " + p.getNivelLlenado() + "% | " + composicion);
                });

                System.out.println("\n[INFO] Puntos limpios disponibles:");
                puntoLimpioRepository.findAll().forEach(p -> {
                    System.out.println("Punto limpio: " + p.getUbicacion() +
                            " | Acepta: " + p.getTiposAceptados());
                });
            }, 0, 10, TimeUnit.SECONDS);
        };
    }
}
