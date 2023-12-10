/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import org.example.exception.FormatoException;

/**
 *
 * @author Lana S. Silva
 */
public abstract class Pessoa {
    
    private String nome;
    private String telefone;
    private String cpf;

    public Pessoa(String nome, String telefone, String cpf) throws FormatoException{
        this.nome = nome;
        this.cpf = validarCpf(cpf);
        this.telefone = validarTelefone(telefone);
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

    public void setTelefone(String telefone) throws FormatoException{
        this.telefone = validarTelefone(telefone);
    }

    public void setCpf(String cpf) throws FormatoException{
        this.cpf = validarCpf(cpf);
    }

    
    public static String validarCpf(String c) throws FormatoException{
        String[] aux = c.split("[\\.-]");

        if(aux.length != 4)
            throw new FormatoException();


        if(aux[0].length() != 3 || aux[1].length() != 3 || aux[2].length() != 3 || aux[3].length() != 2)
            throw new FormatoException();
            else 
                return c;
    }
    
    public static String validarTelefone(String tel) throws FormatoException{
        String[] aux = tel.split("[\\()-]");

        if(aux.length != 3)
            throw new FormatoException();

        if(aux[0].length()!=2 ||  (aux[1].length() != 4 || aux[1].length()!=5) || aux[2].length() != 4)
            throw new FormatoException();
        else
            return tel;
    }
}
