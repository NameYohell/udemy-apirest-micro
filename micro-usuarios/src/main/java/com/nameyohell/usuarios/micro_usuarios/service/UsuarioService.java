package com.nameyohell.usuarios.micro_usuarios.service;

import com.nameyohell.usuarios.micro_usuarios.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del servicio para gestión de usuarios
 */
public interface UsuarioService {
    
    /**
     * Obtiene todos los usuarios
     * @return Lista de usuarios
     */
    List<Usuario> obtenerTodosLosUsuarios();
    
    /**
     * Obtiene un usuario por su ID
     * @param id ID del usuario
     * @return Usuario encontrado o empty si no existe
     */
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    
    /**
     * Crea un nuevo usuario
     * @param usuario Usuario a crear
     * @return Usuario creado
     */
    Usuario crearUsuario(Usuario usuario);
    
    /**
     * Actualiza un usuario existente
     * @param id ID del usuario a actualizar
     * @param usuario Datos actualizados del usuario
     * @return Usuario actualizado
     */
    Usuario actualizarUsuario(Long id, Usuario usuario);
    
    /**
     * Elimina un usuario por su ID
     * @param id ID del usuario a eliminar
     */
    void eliminarUsuario(Long id);
    
    /**
     * Busca usuarios por nombre
     * @param nombre Nombre a buscar
     * @return Lista de usuarios que coinciden
     */
    List<Usuario> buscarUsuariosPorNombre(String nombre);
    
    /**
     * Verifica si existe un usuario con el email dado
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existeUsuarioPorEmail(String email);
}