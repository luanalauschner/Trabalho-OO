/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho.oo;

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
    
    public static boolean validarCpf(String c) throws CpfException{
        String[] aux = c.split("[..-]");

        if((aux[0].length() == 0 || aux[0].length() != 3) || !aux[1].equals('.') ||
            (aux[2].length() == 0 || aux[2].length() != 3) || !aux[3].equals('.') ||
             (aux[4].length() == 0 || aux[4].length() != 3) || !aux[5].equals('-') ||
             (aux[6].length() == 0 || aux[6].length() != 2))
            throw new CpfException();
            else 
                return true;
    }
    
    public static boolean validarTelefone(String tel) throws TelefoneException{
        String[] aux = tel.split("[\\()-]");

        if(!aux[0].equals('(') || aux[1].length()!=2 || !aux[2].equals(')') || 
                (aux[3].length() != 4 || aux[3].length()!=5) || !aux[4].equals('-') || aux[5].length() != 4)
            throw new TelefoneException();
        else
            return true;
    }
}
