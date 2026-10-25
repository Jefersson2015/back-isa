package com.elite.elitebackend.controller;

import com.elite.elitebackend.model.PedidoDetalle;
import com.elite.elitebackend.repository.PedidoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido-detalles")
@CrossOrigin(origins = "*")
public class PedidoDetalleController {
    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    @GetMapping
    public List<PedidoDetalle> listar() {
        return pedidoDetalleRepository.findAll();
    }

    @GetMapping("/{id}")
    public PedidoDetalle buscarPorId(@PathVariable Integer id) {
        return pedidoDetalleRepository.findById(id).orElse(null);
    }

    @PostMapping
    public PedidoDetalle crear(@RequestBody PedidoDetalle detalle) {
        return pedidoDetalleRepository.save(detalle);
    }

    @PutMapping("/{id}")
    public PedidoDetalle actualizar(@PathVariable Integer id, @RequestBody PedidoDetalle detalle) {
        detalle.setId(id);
        return pedidoDetalleRepository.save(detalle);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        pedidoDetalleRepository.deleteById(id);
    }
}
