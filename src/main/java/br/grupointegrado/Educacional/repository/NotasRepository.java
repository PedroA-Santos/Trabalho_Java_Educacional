package br.grupointegrado.Educacional.repository;

import br.grupointegrado.Educacional.model.Notas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotasRepository extends JpaRepository<Notas,Integer> {
}
