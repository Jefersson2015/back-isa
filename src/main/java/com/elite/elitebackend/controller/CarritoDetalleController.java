package com.elite.elitebackend.controller;

import com.elite.elitebackend.model.CarritoDetalle;
import com.elite.elitebackend.repository.CarritoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito-detalles")
@CrossOrigin(origins = "*")
public class CarritoDetalleController {
    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

    @GetMapping
    public List<CarritoDetalle> listar() {
        return carritoDetalleRepository.findAll();
    }

    @GetMapping("/{id}")
    public CarritoDetalle buscarPorId(@PathVariable Integer id) {
        return carritoDetalleRepository.findById(id).orElse(null);
    }

    @PostMapping
    public CarritoDetalle crear(@RequestBody CarritoDetalle detalle) {
        return carritoDetalleRepository.save(detalle);
    }

    @PutMapping("/{id}")
    public CarritoDetalle actualizar(@PathVariable Integer id, @RequestBody CarritoDetalle detalle) {
        detalle.setId(id);
        return carritoDetalleRepository.save(detalle);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        carritoDetalleRepository.deleteById(id);
    }
}
