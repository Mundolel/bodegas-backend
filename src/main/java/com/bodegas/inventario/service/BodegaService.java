package com.bodegas.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bodegas.inventario.model.Bodega;
import com.bodegas.inventario.repository.BodegaRepository;

import lombok.RequiredArgsConstructor;

/** Registro y consulta de bodegas. */
@Service
@RequiredArgsConstructor
public class BodegaService {

    private final BodegaRepository bodegaRepository;

    @Transactional
    public Bodega registrar(String nombre, String ubicacion) {
        Bodega bodega = new Bodega();
        bodega.setNombre(nombre);
        bodega.setUbicacion(ubicacion);
        return bodegaRepository.save(bodega);
    }

    /** Devuelve todas las bodegas con sus productos ya cargados. */
    @Transactional(readOnly = true)
    public List<Bodega> listar() {
        return bodegaRepository.findAllConProductos();
    }
}
