package org.example.exception;

public class CepException extends Exception{
    public CepException(){
        super("CEP inválido!");
    }
}