package com.bodegas.inventario.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bodegas.inventario.controller.dto.BodegaResponse;
import com.bodegas.inventario.controller.dto.ProductoResponse;
import com.bodegas.inventario.model.Bodega;
import com.bodegas.inventario.model.Producto;

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
