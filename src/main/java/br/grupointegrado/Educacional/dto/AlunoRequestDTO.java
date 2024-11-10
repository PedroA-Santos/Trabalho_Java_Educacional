package br.grupointegrado.Educacional.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record AlunoRequestDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome pode ter no máximo 100 caracteres")
        String nome,
        @NotBlank(message = "O E-mail não pode estar em branco")
        @Email(message = "O E-mail deve ser válido")
        String email,
        @NotBlank(message = "A matrícula não pode estar em branco")
        @Size(max = 20, message = "A matrícula deve ter no máximo 20 caracteres")
        String matricula,
        @NotNull
        Date data_nascimento) {
}
