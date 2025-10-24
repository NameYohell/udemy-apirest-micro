package com.nameyohell.comentarios.micro_comentarios.repository;

import com.nameyohell.comentarios.micro_comentarios.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    /**
     * Buscar todos los comentarios de un usuario específico
     */
    List<Comentario> findByUsuarioIdOrderByFechaComentarioDesc(Long usuarioId);

    /**
     * Buscar comentarios por candidato
     */
    List<Comentario> findByCandidatoContainingIgnoreCaseOrderByFechaComentarioDesc(String candidato);

    /**
     * Buscar comentarios por rango de fechas
     */
    List<Comentario> findByFechaComentarioBetweenOrderByFechaComentarioDesc(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Contar comentarios por usuario
     */
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.usuarioId = :usuarioId")
    long countByUsuarioId(@Param("usuarioId") Long usuarioId);

    /**
     * Buscar comentarios por usuario y candidato
     */
    List<Comentario> findByUsuarioIdAndCandidatoContainingIgnoreCaseOrderByFechaComentarioDesc(Long usuarioId, String candidato);

    /**
     * Buscar comentarios que contengan texto específico
     */
    List<Comentario> findByTextoComentarioContainingIgnoreCaseOrderByFechaComentarioDesc(String texto);
}