package org.example.backendwakandaresiduos.repos.usuario;


import org.example.backendwakandaresiduos.domain.usuario.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales, Long> {


}