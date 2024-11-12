package br.grupointegrado.Educacional.exceptions;

import br.grupointegrado.Educacional.model.Professor;

public class ProfessorNotFoundException extends RuntimeException{
    public ProfessorNotFoundException(String message){
        super(message);
    }
}
