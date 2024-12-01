package org.example.backendwakandaresiduos.services;

import org.example.backendwakandaresiduos.domain.PuntoLimpio;
import org.example.backendwakandaresiduos.repos.PuntoLimpioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntoLimpioService {

    private final PuntoLimpioRepository puntoLimpioRepository;

    public PuntoLimpioService(PuntoLimpioRepository puntoLimpioRepository) {
        this.puntoLimpioRepository = puntoLimpioRepository;
    }

    public List<PuntoLimpio> findAll() {
        return puntoLimpioRepository.findAll();
    }
}
