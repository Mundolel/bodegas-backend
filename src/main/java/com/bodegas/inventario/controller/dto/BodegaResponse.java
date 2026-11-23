package com.bodegas.inventario.controller.dto;

import java.util.List;

/** Bodega con sus productos anidados; los productos no referencian de vuelta a la bodega. */
public record BodegaResponse(Long id, String nombre, String ubicacion, List<ProductoResponse> productos) {
}
