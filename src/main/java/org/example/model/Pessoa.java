/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho.oo;

import java.util.InputMismatchException;

/**
 *
 * @author Lana S. Silva
 */
public abstract class Pessoa implements Interface {
    
    private String nome;
    private String telefone;
    private String cpf;

    public Pessoa(String nome, String telefone, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
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
    
    public static boolean validarCpf(String c) throws CpfException{
        String[] aux = c.split("[\\.-]");

        if(aux.length != 4)
            throw new CpfException();


        if(aux[0].length() != 3 || aux[1].length() != 3 || aux[2].length() != 3 || aux[3].length() != 2)
            throw new CpfException();
            else 
                return true;
    }
    
    public static boolean validarTelefone(String tel) throws TelefoneException{
        String[] aux = tel.split("[\\()-]");

        if(aux.length != 3)
            throw new TelefoneException();

        if(aux[0].length()!=2 ||  (aux[1].length() != 4 || aux[1].length()!=5) || aux[2].length() != 4)
            throw new TelefoneException();
        else
            return true;
    }
}
