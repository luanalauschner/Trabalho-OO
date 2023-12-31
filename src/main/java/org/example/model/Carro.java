
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import java.util.Objects;
import org.example.exception.*;
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

    public Carro(int ano, String placa, String cor, String marca, String modelo, boolean disponibilidade, double preco_diaria) throws FormatoException{
        this.ano = ano;
        this.placa = validaPlaca(placa);
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
    public boolean confereCarro(String c){
        String aux_ano;
        String aux_preco;
        String aux_cor = cor.toUpperCase();
        String aux_marca = marca.toUpperCase();
        String aux_modelo = modelo.toUpperCase();
        String aux_exigencia;
        
        aux_ano = String.valueOf(ano);
        aux_preco = String.valueOf(preco_diaria);

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

    public String validaPlaca(String s) throws FormatoException{
        //placa do tipo LLLNLNN (L=letra e N=numero)
        String aux_s = s.toUpperCase();
        char[] aux = aux_s.toCharArray();

        for(int i=0; i < aux_s.length(); i++){
            if(i>=0 && i<3 || i==4){
                if(!Character.toString(aux[i]).matches("[A-Z]")){
                    throw new FormatoException();
                }
            }

            if(i==3 || i==5 || i==6){
                if(aux[i] < '0' && aux[i] > '9'){
                    throw new FormatoException();
                }
            }
        }
        return s;
    }
}
