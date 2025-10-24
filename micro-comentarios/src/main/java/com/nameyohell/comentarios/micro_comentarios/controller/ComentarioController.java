package com.nameyohell.comentarios.micro_comentarios.controller;

import com.nameyohell.comentarios.micro_comentarios.model.Comentario;
import com.nameyohell.comentarios.micro_comentarios.service.ComentarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private static final Logger logger = LoggerFactory.getLogger(ComentarioController.class);

    @Autowired
    private ComentarioService comentarioService;

    /**
     * Crear un nuevo comentario
     */
    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@Valid @RequestBody Comentario comentario) {
        try {
            logger.info("Solicitud para crear comentario para usuario ID: {}", comentario.getUsuarioId());
            Comentario nuevoComentario = comentarioService.crearComentario(comentario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
        } catch (Exception e) {
            logger.error("Error al crear comentario: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener todos los comentarios
     */
    @GetMapping
    public ResponseEntity<List<Comentario>> obtenerTodosLosComentarios() {
        try {
            logger.info("Solicitud para obtener todos los comentarios");
            List<Comentario> comentarios = comentarioService.obtenerTodosLosComentarios();
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            logger.error("Error al obtener comentarios: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener comentario por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Long id) {
        try {
            logger.info("Solicitud para obtener comentario con ID: {}", id);
            Optional<Comentario> comentario = comentarioService.obtenerComentarioPorId(id);
            return comentario.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error al obtener comentario por ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener comentarios por usuario ID
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorUsuario(@PathVariable Long usuarioId) {
        try {
            logger.info("Solicitud para obtener comentarios del usuario ID: {}", usuarioId);
            List<Comentario> comentarios = comentarioService.obtenerComentariosPorUsuario(usuarioId);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            logger.error("Error al obtener comentarios por usuario ID {}: {}", usuarioId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener comentarios por candidato
     */
    @GetMapping("/candidato/{candidato}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorCandidato(@PathVariable String candidato) {
        try {
            logger.info("Solicitud para obtener comentarios del candidato: {}", candidato);
            List<Comentario> comentarios = comentarioService.obtenerComentariosPorCandidato(candidato);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            logger.error("Error al obtener comentarios por candidato {}: {}", candidato, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Buscar comentarios por texto
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Comentario>> buscarComentariosPorTexto(@RequestParam String texto) {
        try {
            logger.info("Solicitud para buscar comentarios con texto: {}", texto);
            List<Comentario> comentarios = comentarioService.buscarComentariosPorTexto(texto);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            logger.error("Error al buscar comentarios por texto '{}': {}", texto, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener comentarios por rango de fechas
     */
    @GetMapping("/fechas")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        try {
            logger.info("Solicitud para obtener comentarios entre fechas: {} y {}", fechaInicio, fechaFin);
            List<Comentario> comentarios = comentarioService.obtenerComentariosPorRangoFechas(fechaInicio, fechaFin);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            logger.error("Error al obtener comentarios por rango de fechas: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener comentarios por usuario y candidato
     */
    @GetMapping("/usuario/{usuarioId}/candidato/{candidato}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorUsuarioYCandidato(
            @PathVariable Long usuarioId,
            @PathVariable String candidato) {
        try {
            logger.info("Solicitud para obtener comentarios del usuario ID: {} y candidato: {}", usuarioId, candidato);
            List<Comentario> comentarios = comentarioService.obtenerComentariosPorUsuarioYCandidato(usuarioId, candidato);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            logger.error("Error al obtener comentarios por usuario ID {} y candidato {}: {}", usuarioId, candidato, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Contar comentarios por usuario
     */
    @GetMapping("/usuario/{usuarioId}/count")
    public ResponseEntity<Long> contarComentariosPorUsuario(@PathVariable Long usuarioId) {
        try {
            logger.info("Solicitud para contar comentarios del usuario ID: {}", usuarioId);
            long count = comentarioService.contarComentariosPorUsuario(usuarioId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            logger.error("Error al contar comentarios por usuario ID {}: {}", usuarioId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Actualizar comentario
     */
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Long id, @Valid @RequestBody Comentario comentario) {
        try {
            logger.info("Solicitud para actualizar comentario con ID: {}", id);
            Optional<Comentario> comentarioActualizado = comentarioService.actualizarComentario(id, comentario);
            return comentarioActualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error al actualizar comentario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Eliminar comentario por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        try {
            logger.info("Solicitud para eliminar comentario con ID: {}", id);
            boolean eliminado = comentarioService.eliminarComentario(id);
            return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error al eliminar comentario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Eliminar todos los comentarios de un usuario
     */
    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> eliminarComentariosPorUsuario(@PathVariable Long usuarioId) {
        try {
            logger.info("Solicitud para eliminar todos los comentarios del usuario ID: {}", usuarioId);
            comentarioService.eliminarComentariosPorUsuario(usuarioId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar comentarios del usuario ID {}: {}", usuarioId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}