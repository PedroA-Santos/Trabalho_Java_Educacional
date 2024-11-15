package br.grupointegrado.Educacional.exceptions;

public class CursoNotFoundException extends RuntimeException{
    public CursoNotFoundException(String message){
        super(message);
    }
}
