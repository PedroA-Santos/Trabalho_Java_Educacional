package br.grupointegrado.Educacional.exceptions;

import br.grupointegrado.Educacional.model.Disciplina;

public class DisciplinaNotFoundException extends RuntimeException{
    public DisciplinaNotFoundException(String message){
        super(message);
    }
}
