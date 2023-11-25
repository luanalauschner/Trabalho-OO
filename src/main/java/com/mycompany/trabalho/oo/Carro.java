
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho.oo;

import java.util.Objects;

/**
 *
 * @author Lana S. Silva
 */
public class Carro {
    
    private int ano;
    private String placa;
    private String cor;
    private boolean disponibilidade;
    private double preco_diaria;

    public Carro(int ano, String placa, String cor, boolean disponibilidade, double preco_diaria) {
        this.ano = ano;
        this.placa = placa;
        this.cor = cor;
        this.disponibilidade = disponibilidade;
        this.preco_diaria = preco_diaria;
    }

    public int getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    public String getCor() {
        return cor;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public double getPreco_diaria() {
        return preco_diaria;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setPreco_diaria(double preco_diaria) {
        this.preco_diaria = preco_diaria;
    }
    
    //método responsável por conferir se o carro tem determinada característica
    public boolean confereCarro(Object c){
        String aux_ano;
        String aux_preco;
        String aux_cor;
        String aux_exigencia;
        
        aux_ano = String.valueOf(ano);
        aux_preco = String.valueOf(preco_diaria);
        aux_cor = cor.toUpperCase();
        //inserir um tratamento de exceção para os casos em que o object c ser uma string

        if(c instanceof String){
            aux_exigencia = c.toUpperCase();
        }
        aux_exigencia = String.valueOf(c);
        aux_exigencia = aux_exigencia.toUpperCase();
        
        if(aux_exigencia.equals(aux_ano))
            return true;
        if(aux_exigencia.equals(aux_preco))
            return true;
        if(aux_exigencia.equals(aux_cor))
            return true;
        
        return false;
    }

    public boolean equals(Carro c) {
        if (this == c) 
            return true;

        if (c == null)
            return false;

        Carro carro = (Carro) c;
        return ano == carro.ano &&
                Objects.equals(preco_diaria, carro.preco_diaria) &&
                Objects.equals(cor, carro.cor);
    }
    
}
