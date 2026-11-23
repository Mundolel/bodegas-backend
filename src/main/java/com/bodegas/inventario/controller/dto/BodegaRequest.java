package com.bodegas.inventario.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record BodegaRequest(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "La ubicacion es obligatoria") String ubicacion) {
}
