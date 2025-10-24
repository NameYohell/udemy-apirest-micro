package com.nameyohell.direcciones.micro_direcciones.repository;

import com.nameyohell.direcciones.micro_direcciones.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Direccion
 */
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    
    /**
     * Busca direcciones por usuario ID
     * @param usuarioId ID del usuario
     * @return Lista de direcciones del usuario
     */
    List<Direccion> findByUsuarioId(Long usuarioId);
    
    /**
     * Busca direcciones que contengan el texto en la ciudad (ignorando mayúsculas/minúsculas)
     * @param ciudad Texto a buscar en la ciudad
     * @return Lista de direcciones que coinciden
     */
    List<Direccion> findByCiudadContainingIgnoreCase(String ciudad);
    
    /**
     * Busca direcciones por código postal exacto
     * @param codigoPostal Código postal a buscar
     * @return Lista de direcciones con el código postal
     */
    List<Direccion> findByCodigoPostal(String codigoPostal);
    
    /**
     * Busca direcciones por país (ignorando mayúsculas/minúsculas)
     * @param pais País a buscar
     * @return Lista de direcciones del país
     */
    List<Direccion> findByPaisIgnoreCase(String pais);
    
    /**
     * Busca direcciones por comuna (ignorando mayúsculas/minúsculas)
     * @param comuna Comuna a buscar
     * @return Lista de direcciones de la comuna
     */
    List<Direccion> findByComunaIgnoreCase(String comuna);
}
