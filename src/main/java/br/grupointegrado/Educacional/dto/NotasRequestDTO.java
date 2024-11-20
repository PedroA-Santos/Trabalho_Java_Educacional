package br.grupointegrado.Educacional.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotasRequestDTO(BigDecimal nota, LocalDate data_lancamento) {
}
