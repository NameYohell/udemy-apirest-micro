package com.nameyohell.direcciones.micro_direcciones.controller;

import com.nameyohell.direcciones.micro_direcciones.model.Direccion;
import com.nameyohell.direcciones.micro_direcciones.repository.DireccionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    private final DireccionRepository direccionRepository;

    public DireccionController(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @GetMapping
    public List<Direccion> listarDirecciones() {
        return direccionRepository.findAll();
    }

    @PostMapping
    public Direccion crearDireccion(@RequestBody Direccion direccion) {
        return direccionRepository.save(direccion);
    }
}
