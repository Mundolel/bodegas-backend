package com.bodegas.inventario.controller.dto;

public record ProductoResponse(Long id, String nombre, String descripcion, Double precio, Integer cantidad) {
}
