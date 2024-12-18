package org.example.backendwakandaresiduos;

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
    CommandLineRunner run(PapeleraRepository papeleraRepository, PuntoLimpioRepository puntoLimpioRepository) {
        return args -> {
            Random random = new Random();

            // Limpiar y agregar datos iniciales
            papeleraRepository.deleteAll();
            puntoLimpioRepository.deleteAll();

            // Crear papeleras
            List<Papelera> papeleras = List.of(
                    new Papelera("Calle 1", 30, LocalDateTime.now()),
                    new Papelera("Calle 2", 80, LocalDateTime.now()),
                    new Papelera("Calle 3", 150, LocalDateTime.now()),
                    new Papelera("Avenida Principal", 200, LocalDateTime.now())
            );

            papeleraRepository.saveAll(papeleras);

            // Crear puntos limpios
            List<PuntoLimpio> puntosLimpios = List.of(
                    new PuntoLimpio("Plaza Central", "Electrónicos, Plásticos"),
                    new PuntoLimpio("Zona Norte", "Vidrio, Papel"),
                    new PuntoLimpio("Zona Sur", "Orgánicos, Metales")
            );

            puntoLimpioRepository.saveAll(puntosLimpios);

            // Scheduler dinámico
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                System.out.println("\n[INFO] Actualizando niveles de llenado de papeleras...");

                papeleraRepository.findAll().forEach(papelera -> {
                    int cambio = random.nextInt(30) - 10; // Cambio aleatorio
                    int nuevoNivel = Math.max(0, Math.min(250, papelera.getNivelLlenado() + cambio));
                    papelera.setNivelLlenado(nuevoNivel);
                    papelera.setUltimaActualizacion(LocalDateTime.now());
                    papeleraRepository.save(papelera);

                    System.out.println("Papelera en " + papelera.getUbicacion() + " - Nivel: " + nuevoNivel + "/250");
                });

            }, 0, 15, TimeUnit.SECONDS);
        };
    }
}
