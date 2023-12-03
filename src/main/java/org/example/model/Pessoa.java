/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import java.util.InputMismatchException;
import org.example.exception.CpfException;
import org.example.exception.FormatoException;
import org.example.exception.TelefoneException;

/**
 *
 * @author Lana S. Silva
 */
public abstract class Pessoa {
    
    private String nome;
    private String telefone;
    private String cpf;

    public Pessoa(String nome, String telefone, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /*public static boolean confereCaracter(String s){
        try {
            valor = teclado.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("");
        }

        return true;
    }*/
    
    public static boolean validarCpf(String c) throws FormatoException{
        String[] aux = c.split("[\\.-]");

        if(aux.length != 4)
            throw new FormatoException();


        if(aux[0].length() != 3 || aux[1].length() != 3 || aux[2].length() != 3 || aux[3].length() != 2)
            throw new FormatoException();
            else 
                return true;
    }
    
    public static boolean validarTelefone(String tel) throws FormatoException{
        String[] aux = tel.split("[\\()-]");

        if(aux.length != 3)
            throw new FormatoException();

        if(aux[0].length()!=2 ||  (aux[1].length() != 4 || aux[1].length()!=5) || aux[2].length() != 4)
            throw new FormatoException();
        else
            return true;
    }
}
