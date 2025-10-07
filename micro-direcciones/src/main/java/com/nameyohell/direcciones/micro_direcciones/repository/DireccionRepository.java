package com.nameyohell.direcciones.micro_direcciones.repository;

import com.nameyohell.direcciones.micro_direcciones.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
