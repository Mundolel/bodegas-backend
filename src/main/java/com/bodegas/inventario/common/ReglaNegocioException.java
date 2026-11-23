package com.bodegas.inventario.common;

/** Se lanza cuando una operacion viola una regla del dominio (p. ej. nombre duplicado). */
public class ReglaNegocioException extends RuntimeException {

    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
