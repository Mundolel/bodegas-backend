package com.bodegas.inventario.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bodegas.inventario.model.Bodega;
import com.bodegas.inventario.model.Producto;
import com.bodegas.inventario.repository.BodegaRepository;
import com.bodegas.inventario.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

/**
 * Carga dos bodegas y tres productos de prueba en el primer arranque, unicamente si la base
 * de datos esta vacia: H2 persiste en archivo (./data), asi que no se duplican al reiniciar.
 * Producto no tiene servicio ni controlador; se persiste directamente con su repositorio.
 */
@Component
@Profile("default")
@Transactional
@RequiredArgsConstructor
public class DatosPruebaInitializer implements CommandLineRunner {

    private final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;

    @Override
    public void run(String... args) {
        if (bodegaRepository.count() > 0) {
            return;
        }

        Bodega norte = crearBodega("Bodega Norte", "Calle 100 #15-20, Bogotá");
        Bodega sur = crearBodega("Bodega Sur", "Autopista Sur #60-45, Bogotá");

        crearProducto(norte, "Computador portátil", "Portátil 15 pulgadas, 16 GB RAM", 2_500_000.0, 15);
        crearProducto(norte, "Monitor", "Monitor 24 pulgadas Full HD", 750_000.0, 20);
        crearProducto(sur, "Impresora", "Impresora láser multifuncional", 1_200_000.0, 8);
    }

    private Bodega crearBodega(String nombre, String ubicacion) {
        Bodega bodega = new Bodega();
        bodega.setNombre(nombre);
        bodega.setUbicacion(ubicacion);
        return bodegaRepository.save(bodega);
    }

    private void crearProducto(Bodega bodega, String nombre, String descripcion, Double precio,
            Integer cantidad) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);
        bodega.agregarProducto(producto);
        productoRepository.save(producto);
    }
}
