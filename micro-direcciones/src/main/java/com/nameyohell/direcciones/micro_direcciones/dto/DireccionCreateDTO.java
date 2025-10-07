package com.nameyohell.direcciones.micro_direcciones.dto;

/**
 * DTO para crear una nueva dirección
 */
public class DireccionCreateDTO {
    
    private String calle;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String pais;
    private Long usuarioId;
    
    // Constructores
    public DireccionCreateDTO() {}
    
    public DireccionCreateDTO(String calle, String ciudad, String estado, String codigoPostal, String pais, Long usuarioId) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.usuarioId = usuarioId;
    }
    
    // Getters y setters
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
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
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
        return "DireccionCreateDTO{" +
                "calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estado='" + estado + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", pais='" + pais + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
}