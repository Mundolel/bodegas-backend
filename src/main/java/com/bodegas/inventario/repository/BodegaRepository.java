package com.bodegas.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bodegas.inventario.model.Bodega;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    @Query("select distinct b from Bodega b left join fetch b.productos order by b.id")
    List<Bodega> findAllConProductos();
}
