package com.nameyohell.usuarios.micro_usuarios.exception;

/**
 * Excepción personalizada para manejar casos de emails duplicados
 */
public class EmailDuplicadoException extends RuntimeException {
    
    public EmailDuplicadoException(String email) {
        super("El email '" + email + "' ya está registrado en el sistema");
    }
    
    public EmailDuplicadoException(String email, Throwable cause) {
        super("El email '" + email + "' ya está registrado en el sistema", cause);
    }
}