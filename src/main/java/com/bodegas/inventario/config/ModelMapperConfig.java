package com.bodegas.inventario.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bodegas.inventario.controller.dto.BodegaResponse;
import com.bodegas.inventario.controller.dto.ProductoResponse;
import com.bodegas.inventario.model.Bodega;
import com.bodegas.inventario.model.Producto;

/**
 * Registra el mapeo Entity -> DTO de respuesta. Los DTO son records sin setters, por lo que
 * ModelMapper 3.2.0 no los resuelve por coincidencia de nombre: los converters son explicitos.
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.addConverter(new AbstractConverter<Producto, ProductoResponse>() {
            @Override
            protected ProductoResponse convert(Producto p) {
                return new ProductoResponse(p.getId(), p.getNombre(), p.getDescripcion(),
                        p.getPrecio(), p.getCantidad());
            }
        });

        // productos se deriva de la relacion 1:N; se mapea cada uno con el converter anterior
        // para que el JSON quede anidado sin referencias circulares.
        mapper.addConverter(new AbstractConverter<Bodega, BodegaResponse>() {
            @Override
            protected BodegaResponse convert(Bodega b) {
                return new BodegaResponse(b.getId(), b.getNombre(), b.getUbicacion(),
                        b.getProductos().stream().map(p -> mapper.map(p, ProductoResponse.class)).toList());
            }
        });

        return mapper;
    }
}
