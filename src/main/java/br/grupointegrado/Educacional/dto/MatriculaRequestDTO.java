package br.grupointegrado.Educacional.dto;

import br.grupointegrado.Educacional.model.Aluno;
import br.grupointegrado.Educacional.model.Turma;

public record MatriculaRequestDTO(Integer aluno_id, Integer turma_id) {
}
