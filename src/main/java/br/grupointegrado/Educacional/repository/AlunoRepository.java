package br.grupointegrado.Educacional.repository;

import br.grupointegrado.Educacional.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno,Integer> {
}
