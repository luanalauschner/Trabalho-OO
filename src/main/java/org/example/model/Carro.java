
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import java.util.Objects;

/**
 *
 * @author Lana S. Silva
 */
public class Carro {
    
    private int ano;
    private String placa;
    private String cor;
    private String marca;
    private String modelo;
    private boolean disponibilidade;
    private double preco_diaria;

    public Carro(int ano, String placa, String cor, String marca, String modelo, boolean disponibilidade, double preco_diaria) {
        this.ano = ano;
        this.placa = placa;
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.disponibilidade = disponibilidade;
        this.preco_diaria = preco_diaria;

        Administrador.adicionaCarro(this);
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

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
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

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    //método responsável por conferir se o carro tem determinada característica
    public boolean confereCarro(Object c){
        String aux_ano;
        String aux_preco;
        String aux_cor = cor.toUpperCase();
        String aux_marca = marca.toUpperCase();
        String aux_modelo = modelo.toUpperCase();
        String aux_exigencia;
        
        aux_ano = String.valueOf(ano);
        aux_preco = String.valueOf(preco_diaria);

        /*try {
            aux_exigencia = (String) c;
            aux_exigencia = aux_exigencia.toUpperCase();
        } catch (ClassCastException e) {
            aux_exigencia = String.valueOf(c);
        }
        */

        if(c instanceof String){
            aux_exigencia = (String) c;
            aux_exigencia = aux_exigencia.toUpperCase();
        }else{
            aux_exigencia = String.valueOf(c);
        }

        
        if(aux_exigencia.equals(aux_ano))
            return true;
        if(aux_exigencia.equals(aux_preco))
            return true;
        if(aux_exigencia.equals(aux_cor))
            return true;
        if(aux_exigencia.equals(aux_marca))
            return true;
        if(aux_exigencia.equals(aux_modelo))
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
                Objects.equals(cor, carro.cor) && 
                Objects.equals(marca, carro.marca) &&
                Objects.equals(modelo, carro.modelo);
    }
    
    @Override
    
    public String toString(){
        return this.placa;
    }
}
