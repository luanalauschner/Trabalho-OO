package org.example.exception;

public class CpfException extends Exception{
    public CpfException(){
        super("CPF inválido!");
    }
}