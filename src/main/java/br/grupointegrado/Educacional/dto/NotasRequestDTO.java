package br.grupointegrado.Educacional.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotasRequestDTO(
        @NotNull(message = "O valor da nota é obrigatório.")
        BigDecimal nota,

        @NotNull(message = "A data de lançamento é obrigatória.")
        LocalDate data_lancamento,

        @NotNull(message = "O ID da matricula é obrigatório.")
        Integer matriculaId,

        @NotNull(message = "O ID da disciplina é obrigatório.")
        Integer disciplinaId
) {}
