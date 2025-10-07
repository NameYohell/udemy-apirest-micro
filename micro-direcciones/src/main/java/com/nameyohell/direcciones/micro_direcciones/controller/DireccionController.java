package com.nameyohell.direcciones.micro_direcciones.controller;

import com.nameyohell.direcciones.micro_direcciones.model.Direccion;
import com.nameyohell.direcciones.micro_direcciones.service.DireccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestión de direcciones
 */
@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    private static final Logger logger = LoggerFactory.getLogger(DireccionController.class);
    
    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    /**
     * Obtiene todas las direcciones
     */
    @GetMapping
    public ResponseEntity<List<Direccion>> listarDirecciones() {
        logger.info("GET /direcciones - Obteniendo todas las direcciones");
        List<Direccion> direcciones = direccionService.obtenerTodasLasDirecciones();
        return ResponseEntity.ok(direcciones);
    }

    /**
     * Obtiene una dirección por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtenerDireccion(@PathVariable Long id) {
        logger.info("GET /direcciones/{} - Obteniendo dirección por ID", id);
        Optional<Direccion> direccion = direccionService.obtenerDireccionPorId(id);
        
        if (direccion.isPresent()) {
            return ResponseEntity.ok(direccion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crea una nueva dirección
     */
    @PostMapping
    public ResponseEntity<Direccion> crearDireccion(@RequestBody Direccion direccion) {
        logger.info("POST /direcciones - Creando nueva dirección");
        try {
            Direccion direccionCreada = direccionService.crearDireccion(direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(direccionCreada);
        } catch (IllegalArgumentException e) {
            logger.error("Error al crear dirección: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Actualiza una dirección existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        logger.info("PUT /direcciones/{} - Actualizando dirección", id);
        try {
            Direccion direccionActualizada = direccionService.actualizarDireccion(id, direccion);
            return ResponseEntity.ok(direccionActualizada);
        } catch (IllegalArgumentException e) {
            logger.error("Error al actualizar dirección: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Elimina una dirección por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Long id) {
        logger.info("DELETE /direcciones/{} - Eliminando dirección", id);
        try {
            direccionService.eliminarDireccion(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            logger.error("Error al eliminar dirección: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene direcciones por usuario ID
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Direccion>> obtenerDireccionesPorUsuario(@PathVariable Long usuarioId) {
        logger.info("GET /direcciones/usuario/{} - Obteniendo direcciones por usuario", usuarioId);
        List<Direccion> direcciones = direccionService.obtenerDireccionesPorUsuario(usuarioId);
        return ResponseEntity.ok(direcciones);
    }

    /**
     * Busca direcciones por ciudad
     */
    @GetMapping("/buscar/ciudad")
    public ResponseEntity<List<Direccion>> buscarPorCiudad(@RequestParam String ciudad) {
        logger.info("GET /direcciones/buscar/ciudad?ciudad={} - Buscando por ciudad", ciudad);
        List<Direccion> direcciones = direccionService.buscarDireccionesPorCiudad(ciudad);
        return ResponseEntity.ok(direcciones);
    }

    /**
     * Busca direcciones por código postal
     */
    @GetMapping("/buscar/codigo-postal")
    public ResponseEntity<List<Direccion>> buscarPorCodigoPostal(@RequestParam String codigoPostal) {
        logger.info("GET /direcciones/buscar/codigo-postal?codigoPostal={} - Buscando por código postal", codigoPostal);
        List<Direccion> direcciones = direccionService.buscarDireccionesPorCodigoPostal(codigoPostal);
        return ResponseEntity.ok(direcciones);
    }
}
