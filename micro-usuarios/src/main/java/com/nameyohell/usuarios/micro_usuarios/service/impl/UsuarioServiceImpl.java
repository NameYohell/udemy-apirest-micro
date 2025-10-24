package com.nameyohell.usuarios.micro_usuarios.service.impl;

import com.nameyohell.usuarios.micro_usuarios.exception.EmailDuplicadoException;
import com.nameyohell.usuarios.micro_usuarios.model.Usuario;
import com.nameyohell.usuarios.micro_usuarios.repository.UsuarioRepository;
import com.nameyohell.usuarios.micro_usuarios.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de usuarios
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    
    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosLosUsuarios() {
        logger.info("Obteniendo todos los usuarios");
        return usuarioRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        logger.info("Buscando usuario con ID: {}", id);
        if (id == null) {
            logger.warn("ID de usuario es null");
            return Optional.empty();
        }
        return usuarioRepository.findById(id);
    }
    
    @Override
    public Usuario crearUsuario(Usuario usuario) {
        logger.info("Creando nuevo usuario: {}", usuario.getNombre());
        
        // Validaciones
        validarUsuario(usuario);
        
        // Verificar si ya existe un usuario con el mismo email
        if (StringUtils.hasText(usuario.getEmail()) && existeUsuarioPorEmail(usuario.getEmail())) {
            throw new EmailDuplicadoException(usuario.getEmail());
        }
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        logger.info("Usuario creado exitosamente con ID: {}", usuarioGuardado.getId());
        return usuarioGuardado;
    }
    
    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        logger.info("Actualizando usuario con ID: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser null");
        }
        
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        
        validarUsuario(usuario);
        
        // Verificar email único (excluyendo el usuario actual)
        if (StringUtils.hasText(usuario.getEmail())) {
            Optional<Usuario> usuarioConEmail = usuarioRepository.findByEmail(usuario.getEmail());
            if (usuarioConEmail.isPresent() && !usuarioConEmail.get().getId().equals(id)) {
                throw new IllegalArgumentException("Ya existe otro usuario con el email: " + usuario.getEmail());
            }
        }
        
        Usuario usuarioAActualizar = usuarioExistente.get();
        usuarioAActualizar.setNombre(usuario.getNombre());
        usuarioAActualizar.setEmail(usuario.getEmail());
        usuarioAActualizar.setTelefono(usuario.getTelefono());
        
        Usuario usuarioActualizado = usuarioRepository.save(usuarioAActualizar);
        logger.info("Usuario actualizado exitosamente con ID: {}", usuarioActualizado.getId());
        return usuarioActualizado;
    }
    
    @Override
    public void eliminarUsuario(Long id) {
        logger.info("Eliminando usuario con ID: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser null");
        }
        
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        
        usuarioRepository.deleteById(id);
        logger.info("Usuario eliminado exitosamente con ID: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        logger.info("Buscando usuarios por nombre: {}", nombre);
        
        if (!StringUtils.hasText(nombre)) {
            return List.of();
        }
        
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }
        return usuarioRepository.existsByEmail(email);
    }
    
    /**
     * Valida los datos básicos del usuario
     */
    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no puede ser null");
        }
        
        if (!StringUtils.hasText(usuario.getNombre())) {
            throw new IllegalArgumentException("Nombre del usuario es requerido");
        }
        
        if (usuario.getNombre().length() < 2) {
            throw new IllegalArgumentException("Nombre del usuario debe tener al menos 2 caracteres");
        }
        
        if (StringUtils.hasText(usuario.getEmail()) && !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email debe tener un formato válido");
        }
    }
}