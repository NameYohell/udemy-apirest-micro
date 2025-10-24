package com.nameyohell.direcciones.micro_direcciones.model;

import jakarta.persistence.*;

/**
 * Entidad que representa una dirección
 */
@Entity
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String calle;
    
    @Column(nullable = false)
    private String ciudad;
    
    private String comuna;
    
    @Column(name = "codigo_postal")
    private String codigoPostal;
    
    @Column(nullable = false)
    private String pais;
    
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    // Constructores
    public Direccion() {}

    public Direccion(String calle, String ciudad, String comuna, String codigoPostal, String pais, Long usuarioId) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.usuarioId = usuarioId;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", comuna='" + comuna + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", pais='" + pais + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
