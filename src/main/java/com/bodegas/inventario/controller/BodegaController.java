package com.bodegas.inventario.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bodegas.inventario.controller.dto.BodegaRequest;
import com.bodegas.inventario.controller.dto.BodegaResponse;
import com.bodegas.inventario.model.Bodega;
import com.bodegas.inventario.service.BodegaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bodegas")
@RequiredArgsConstructor
public class BodegaController {

    private final BodegaService bodegaService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<BodegaResponse> registrar(@Validated @RequestBody BodegaRequest request) {
        Bodega bodega = bodegaService.registrar(request.nombre(), request.ubicacion());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/bodegas/{id}").buildAndExpand(bodega.getId()).toUri())
                .body(modelMapper.map(bodega, BodegaResponse.class));
    }

    @GetMapping
    public List<BodegaResponse> listar() {
        return bodegaService.listar().stream()
                .map(b -> modelMapper.map(b, BodegaResponse.class))
                .toList();
    }
}
