package com.bodegas.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodegas.inventario.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
