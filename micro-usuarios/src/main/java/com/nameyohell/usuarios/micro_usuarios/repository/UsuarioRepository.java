package com.nameyohell.usuarios.micro_usuarios.repository;

import com.nameyohell.usuarios.micro_usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
