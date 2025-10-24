package com.nameyohell.comentarios.micro_comentarios.service.impl;

import com.nameyohell.comentarios.micro_comentarios.model.Comentario;
import com.nameyohell.comentarios.micro_comentarios.repository.ComentarioRepository;
import com.nameyohell.comentarios.micro_comentarios.service.ComentarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComentarioServiceImpl implements ComentarioService {

    private static final Logger logger = LoggerFactory.getLogger(ComentarioServiceImpl.class);

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario crearComentario(Comentario comentario) {
        logger.info("Creando nuevo comentario para usuario ID: {}", comentario.getUsuarioId());
        comentario.setFechaComentario(LocalDateTime.now());
        Comentario comentarioGuardado = comentarioRepository.save(comentario);
        logger.info("Comentario creado con ID: {}", comentarioGuardado.getId());
        return comentarioGuardado;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comentario> obtenerComentarioPorId(Long id) {
        logger.info("Buscando comentario con ID: {}", id);
        return comentarioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> obtenerTodosLosComentarios() {
        logger.info("Obteniendo todos los comentarios");
        return comentarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> obtenerComentariosPorUsuario(Long usuarioId) {
        logger.info("Obteniendo comentarios para usuario ID: {}", usuarioId);
        return comentarioRepository.findByUsuarioIdOrderByFechaComentarioDesc(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> obtenerComentariosPorCandidato(String candidato) {
        logger.info("Obteniendo comentarios para candidato: {}", candidato);
        return comentarioRepository.findByCandidatoContainingIgnoreCaseOrderByFechaComentarioDesc(candidato);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> obtenerComentariosPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        logger.info("Obteniendo comentarios entre fechas: {} y {}", fechaInicio, fechaFin);
        return comentarioRepository.findByFechaComentarioBetweenOrderByFechaComentarioDesc(fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> obtenerComentariosPorUsuarioYCandidato(Long usuarioId, String candidato) {
        logger.info("Obteniendo comentarios para usuario ID: {} y candidato: {}", usuarioId, candidato);
        return comentarioRepository.findByUsuarioIdAndCandidatoContainingIgnoreCaseOrderByFechaComentarioDesc(usuarioId, candidato);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> buscarComentariosPorTexto(String texto) {
        logger.info("Buscando comentarios que contengan el texto: {}", texto);
        return comentarioRepository.findByTextoComentarioContainingIgnoreCaseOrderByFechaComentarioDesc(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public long contarComentariosPorUsuario(Long usuarioId) {
        logger.info("Contando comentarios para usuario ID: {}", usuarioId);
        return comentarioRepository.countByUsuarioId(usuarioId);
    }

    @Override
    public Optional<Comentario> actualizarComentario(Long id, Comentario comentarioActualizado) {
        logger.info("Actualizando comentario con ID: {}", id);
        
        return comentarioRepository.findById(id).map(comentarioExistente -> {
            // Solo actualizar campos específicos, mantener los datos originales importantes
            comentarioExistente.setCandidato(comentarioActualizado.getCandidato());
            comentarioExistente.setTextoComentario(comentarioActualizado.getTextoComentario());
            // La fecha de comentario y usuarioId no se actualizan por seguridad
            
            Comentario comentarioGuardado = comentarioRepository.save(comentarioExistente);
            logger.info("Comentario actualizado correctamente con ID: {}", comentarioGuardado.getId());
            return comentarioGuardado;
        });
    }

    @Override
    public boolean eliminarComentario(Long id) {
        logger.info("Eliminando comentario con ID: {}", id);
        
        if (comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
            logger.info("Comentario eliminado correctamente con ID: {}", id);
            return true;
        } else {
            logger.warn("No se encontró comentario con ID: {}", id);
            return false;
        }
    }

    @Override
    public void eliminarComentariosPorUsuario(Long usuarioId) {
        logger.info("Eliminando todos los comentarios del usuario ID: {}", usuarioId);
        List<Comentario> comentarios = comentarioRepository.findByUsuarioIdOrderByFechaComentarioDesc(usuarioId);
        comentarioRepository.deleteAll(comentarios);
        logger.info("Eliminados {} comentarios del usuario ID: {}", comentarios.size(), usuarioId);
    }
}