package com.nameyohell.usuarios.micro_usuarios.dto;

/**
 * DTO para crear un nuevo usuario
 */
public class UsuarioCreateDTO {
    
    private String nombre;
    private String email;
    private String telefono;
    
    // Constructores
    public UsuarioCreateDTO() {}
    
    public UsuarioCreateDTO(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString() {
        return "UsuarioCreateDTO{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}