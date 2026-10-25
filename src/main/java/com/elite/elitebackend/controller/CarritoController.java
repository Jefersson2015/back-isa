package com.elite.elitebackend.controller;

import com.elite.elitebackend.model.Carrito;
import com.elite.elitebackend.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {
    @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping
    public List<Carrito> listar() {
        return carritoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Carrito buscarPorId(@PathVariable Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Carrito crear(@RequestBody Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @PutMapping("/{id}")
    public Carrito actualizar(@PathVariable Integer id, @RequestBody Carrito carrito) {
        carrito.setId(id);
        return carritoRepository.save(carrito);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        carritoRepository.deleteById(id);
    }
}
