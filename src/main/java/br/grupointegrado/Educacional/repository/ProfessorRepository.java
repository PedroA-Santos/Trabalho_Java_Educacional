package br.grupointegrado.Educacional.repository;

import br.grupointegrado.Educacional.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
}
