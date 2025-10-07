package com.nameyohell.direcciones.micro_direcciones.service;

import com.nameyohell.direcciones.micro_direcciones.model.Direccion;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del servicio para gestión de direcciones
 */
public interface DireccionService {
    
    /**
     * Obtiene todas las direcciones
     * @return Lista de direcciones
     */
    List<Direccion> obtenerTodasLasDirecciones();
    
    /**
     * Obtiene una dirección por su ID
     * @param id ID de la dirección
     * @return Dirección encontrada o empty si no existe
     */
    Optional<Direccion> obtenerDireccionPorId(Long id);
    
    /**
     * Crea una nueva dirección
     * @param direccion Dirección a crear
     * @return Dirección creada
     */
    Direccion crearDireccion(Direccion direccion);
    
    /**
     * Actualiza una dirección existente
     * @param id ID de la dirección a actualizar
     * @param direccion Datos actualizados de la dirección
     * @return Dirección actualizada
     */
    Direccion actualizarDireccion(Long id, Direccion direccion);
    
    /**
     * Elimina una dirección por su ID
     * @param id ID de la dirección a eliminar
     */
    void eliminarDireccion(Long id);
    
    /**
     * Busca direcciones por usuario ID
     * @param usuarioId ID del usuario
     * @return Lista de direcciones del usuario
     */
    List<Direccion> obtenerDireccionesPorUsuario(Long usuarioId);
    
    /**
     * Busca direcciones por ciudad
     * @param ciudad Ciudad a buscar
     * @return Lista de direcciones en la ciudad
     */
    List<Direccion> buscarDireccionesPorCiudad(String ciudad);
    
    /**
     * Busca direcciones por código postal
     * @param codigoPostal Código postal a buscar
     * @return Lista de direcciones con el código postal
     */
    List<Direccion> buscarDireccionesPorCodigoPostal(String codigoPostal);
}