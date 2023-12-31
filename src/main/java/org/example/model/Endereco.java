/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import org.example.exception.*;

/**
 *
 * @author Lana S. Silva
 */
public class Endereco {
    
    private String logadouro;
    private int numero;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String logadouro, int numero, String cidade, String estado, String cep) throws FormatoException{
        this.logadouro = logadouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = validarCEP(cep);
    }

    public String getLogadouro() {
        return logadouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String validarCEP(String s) throws FormatoException{
        String[] aux = s.split("[\\-]");

        if(aux.length != 2)
            throw new FormatoException();

        if(aux[0].length() != 5 || aux[1].length() != 3)
            throw new FormatoException();
            else
                return s;
    }
}
