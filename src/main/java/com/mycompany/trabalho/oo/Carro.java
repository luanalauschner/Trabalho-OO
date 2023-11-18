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
    
    
}
