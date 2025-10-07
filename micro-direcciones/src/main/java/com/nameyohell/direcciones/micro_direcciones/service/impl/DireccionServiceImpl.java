package com.nameyohell.direcciones.micro_direcciones.service.impl;

import com.nameyohell.direcciones.micro_direcciones.model.Direccion;
import com.nameyohell.direcciones.micro_direcciones.repository.DireccionRepository;
import com.nameyohell.direcciones.micro_direcciones.service.DireccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de direcciones
 */
@Service
@Transactional
public class DireccionServiceImpl implements DireccionService {
    
    private static final Logger logger = LoggerFactory.getLogger(DireccionServiceImpl.class);
    
    private final DireccionRepository direccionRepository;
    
    public DireccionServiceImpl(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Direccion> obtenerTodasLasDirecciones() {
        logger.info("Obteniendo todas las direcciones");
        return direccionRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Direccion> obtenerDireccionPorId(Long id) {
        logger.info("Buscando dirección con ID: {}", id);
        if (id == null) {
            logger.warn("ID de dirección es null");
            return Optional.empty();
        }
        return direccionRepository.findById(id);
    }
    
    @Override
    public Direccion crearDireccion(Direccion direccion) {
        logger.info("Creando nueva dirección para usuario: {}", direccion.getUsuarioId());
        
        // Validaciones
        validarDireccion(direccion);
        
        Direccion direccionGuardada = direccionRepository.save(direccion);
        logger.info("Dirección creada exitosamente con ID: {}", direccionGuardada.getId());
        return direccionGuardada;
    }
    
    @Override
    public Direccion actualizarDireccion(Long id, Direccion direccion) {
        logger.info("Actualizando dirección con ID: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("ID de dirección no puede ser null");
        }
        
        Optional<Direccion> direccionExistente = direccionRepository.findById(id);
        if (direccionExistente.isEmpty()) {
            throw new IllegalArgumentException("Dirección no encontrada con ID: " + id);
        }
        
        validarDireccion(direccion);
        
        Direccion direccionAActualizar = direccionExistente.get();
        direccionAActualizar.setCalle(direccion.getCalle());
        direccionAActualizar.setCiudad(direccion.getCiudad());
        direccionAActualizar.setEstado(direccion.getEstado());
        direccionAActualizar.setCodigoPostal(direccion.getCodigoPostal());
        direccionAActualizar.setPais(direccion.getPais());
        direccionAActualizar.setUsuarioId(direccion.getUsuarioId());
        
        Direccion direccionActualizada = direccionRepository.save(direccionAActualizar);
        logger.info("Dirección actualizada exitosamente con ID: {}", direccionActualizada.getId());
        return direccionActualizada;
    }
    
    @Override
    public void eliminarDireccion(Long id) {
        logger.info("Eliminando dirección con ID: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("ID de dirección no puede ser null");
        }
        
        if (!direccionRepository.existsById(id)) {
            throw new IllegalArgumentException("Dirección no encontrada con ID: " + id);
        }
        
        direccionRepository.deleteById(id);
        logger.info("Dirección eliminada exitosamente con ID: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Direccion> obtenerDireccionesPorUsuario(Long usuarioId) {
        logger.info("Obteniendo direcciones para usuario: {}", usuarioId);
        
        if (usuarioId == null) {
            return List.of();
        }
        
        return direccionRepository.findByUsuarioId(usuarioId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Direccion> buscarDireccionesPorCiudad(String ciudad) {
        logger.info("Buscando direcciones en ciudad: {}", ciudad);
        
        if (!StringUtils.hasText(ciudad)) {
            return List.of();
        }
        
        return direccionRepository.findByCiudadContainingIgnoreCase(ciudad);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Direccion> buscarDireccionesPorCodigoPostal(String codigoPostal) {
        logger.info("Buscando direcciones con código postal: {}", codigoPostal);
        
        if (!StringUtils.hasText(codigoPostal)) {
            return List.of();
        }
        
        return direccionRepository.findByCodigoPostal(codigoPostal);
    }
    
    /**
     * Valida los datos básicos de la dirección
     */
    private void validarDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException("Dirección no puede ser null");
        }
        
        if (!StringUtils.hasText(direccion.getCalle())) {
            throw new IllegalArgumentException("Calle es requerida");
        }
        
        if (!StringUtils.hasText(direccion.getCiudad())) {
            throw new IllegalArgumentException("Ciudad es requerida");
        }
        
        if (!StringUtils.hasText(direccion.getPais())) {
            throw new IllegalArgumentException("País es requerido");
        }
        
        if (direccion.getUsuarioId() == null) {
            throw new IllegalArgumentException("Usuario ID es requerido");
        }
        
        if (direccion.getCalle().length() < 5) {
            throw new IllegalArgumentException("Calle debe tener al menos 5 caracteres");
        }
        
        if (direccion.getCiudad().length() < 2) {
            throw new IllegalArgumentException("Ciudad debe tener al menos 2 caracteres");
        }
    }
}