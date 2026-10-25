package com.elite.elitebackend.repository;

import com.elite.elitebackend.model.CarritoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Integer> {
}
