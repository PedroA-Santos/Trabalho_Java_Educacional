package br.grupointegrado.Educacional.exceptions;

public class TurmaNotFoundException extends RuntimeException{
    public TurmaNotFoundException(String message){
        super(message);
    }
}
