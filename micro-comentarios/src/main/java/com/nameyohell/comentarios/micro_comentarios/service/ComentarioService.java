package com.nameyohell.comentarios.micro_comentarios.service;

import com.nameyohell.comentarios.micro_comentarios.model.Comentario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ComentarioService {

    /**
     * Crear un nuevo comentario
     */
    Comentario crearComentario(Comentario comentario);

    /**
     * Obtener un comentario por ID
     */
    Optional<Comentario> obtenerComentarioPorId(Long id);

    /**
     * Obtener todos los comentarios
     */
    List<Comentario> obtenerTodosLosComentarios();

    /**
     * Obtener comentarios por usuario
     */
    List<Comentario> obtenerComentariosPorUsuario(Long usuarioId);

    /**
     * Obtener comentarios por candidato
     */
    List<Comentario> obtenerComentariosPorCandidato(String candidato);

    /**
     * Obtener comentarios por rango de fechas
     */
    List<Comentario> obtenerComentariosPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Obtener comentarios por usuario y candidato
     */
    List<Comentario> obtenerComentariosPorUsuarioYCandidato(Long usuarioId, String candidato);

    /**
     * Buscar comentarios por texto
     */
    List<Comentario> buscarComentariosPorTexto(String texto);

    /**
     * Contar comentarios por usuario
     */
    long contarComentariosPorUsuario(Long usuarioId);

    /**
     * Actualizar un comentario existente
     */
    Optional<Comentario> actualizarComentario(Long id, Comentario comentarioActualizado);

    /**
     * Eliminar un comentario por ID
     */
    boolean eliminarComentario(Long id);

    /**
     * Eliminar todos los comentarios de un usuario
     */
    void eliminarComentariosPorUsuario(Long usuarioId);
}