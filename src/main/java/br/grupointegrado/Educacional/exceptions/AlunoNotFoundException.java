package br.grupointegrado.Educacional.exceptions;

public class AlunoNotFoundException extends RuntimeException{
    public AlunoNotFoundException(String message){
        super(message);
    }
}