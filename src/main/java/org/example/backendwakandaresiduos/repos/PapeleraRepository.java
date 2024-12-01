package org.example.backendwakandaresiduos.repos;

import org.example.backendwakandaresiduos.domain.Papelera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapeleraRepository extends JpaRepository<Papelera, Long> {
}
