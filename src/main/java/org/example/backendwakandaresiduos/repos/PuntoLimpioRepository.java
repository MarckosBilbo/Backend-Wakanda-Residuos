package org.example.backendwakandaresiduos.repos;

import org.example.backendwakandaresiduos.domain.PuntoLimpio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoLimpioRepository extends JpaRepository<PuntoLimpio, Long> {
}

