package com.tp1.tp1.Repositorios;

import com.tp1.tp1.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
