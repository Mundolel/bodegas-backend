package com.bodegas.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodegas.inventario.model.Producto;

/** Solo se usa para cargar los datos de prueba; Producto no tiene servicio ni controlador. */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
