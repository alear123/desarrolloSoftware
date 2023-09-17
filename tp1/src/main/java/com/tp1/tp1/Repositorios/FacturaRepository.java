package com.tp1.tp1.Repositorios;

import com.tp1.tp1.Entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
}
