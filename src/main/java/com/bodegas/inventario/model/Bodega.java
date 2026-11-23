package com.bodegas.inventario.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Bodega de la empresa. Lado inverso de la relacion 1:N con Producto: la llave
 * foranea vive en la tabla productos (columna bodega_id).
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bodegas")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ubicacion;

    @OneToMany(mappedBy = "bodega")
    private List<Producto> productos = new ArrayList<>();

    /** Mantiene sincronizados los dos lados de la relacion. */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.setBodega(this);
    }
}
