package br.grupointegrado.Educacional.dto;

import java.time.LocalDate;

public record NotaResponseDTO(java.math.BigDecimal nota, LocalDate data_lancamento, DisciplinaResponseDTO disciplina) {
}
