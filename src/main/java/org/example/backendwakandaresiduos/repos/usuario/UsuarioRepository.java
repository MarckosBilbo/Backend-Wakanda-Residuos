package org.example.backendwakandaresiduos.repos.usuario;


import org.example.backendwakandaresiduos.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
