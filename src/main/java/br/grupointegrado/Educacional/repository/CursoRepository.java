package br.grupointegrado.Educacional.repository;

import br.grupointegrado.Educacional.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Integer> {
}
