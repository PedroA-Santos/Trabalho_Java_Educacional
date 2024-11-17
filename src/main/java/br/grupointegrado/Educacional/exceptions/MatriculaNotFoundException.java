package br.grupointegrado.Educacional.exceptions;

import br.grupointegrado.Educacional.model.Disciplina;

public class MatriculaNotFoundException extends RuntimeException{
    public MatriculaNotFoundException(String message){
        super(message);
    }
}
