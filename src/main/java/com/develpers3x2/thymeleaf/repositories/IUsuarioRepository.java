package com.develpers3x2.thymeleaf.repositories;

import com.develpers3x2.thymeleaf.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}
