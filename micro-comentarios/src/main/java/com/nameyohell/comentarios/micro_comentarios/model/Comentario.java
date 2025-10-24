package com.nameyohell.comentarios.micro_comentarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Entidad que representa un comentario asociado a un usuario
 */
@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del usuario es obligatorio")
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @NotBlank(message = "El candidato es obligatorio")
    @Size(max = 100, message = "El candidato no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String candidato;

    @Column(name = "fecha_comentario", nullable = false)
    private LocalDateTime fechaComentario;

    @NotBlank(message = "El texto del comentario es obligatorio")
    @Size(max = 1000, message = "El comentario no puede tener más de 1000 caracteres")
    @Column(name = "texto_comentario", nullable = false, length = 1000)
    private String textoComentario;

    // Constructores
    public Comentario() {
        this.fechaComentario = LocalDateTime.now();
    }

    public Comentario(Long usuarioId, String candidato, String textoComentario) {
        this();
        this.usuarioId = usuarioId;
        this.candidato = candidato;
        this.textoComentario = textoComentario;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    public LocalDateTime getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDateTime fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", candidato='" + candidato + '\'' +
                ", fechaComentario=" + fechaComentario +
                ", textoComentario='" + textoComentario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comentario that = (Comentario) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}