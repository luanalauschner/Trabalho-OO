/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho.oo;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Lana S. Silva
 */
public class Cliente extends Pessoa{
    
    private int id;
    private String habilitacao;
    private List<Locacao> locacoes;
    private List<Reserva> reservas;
    private boolean credito;

    public Cliente(int id, String habilitacao, List<Locacao> locacoes, List<Reserva> reservas, boolean credito, String nome, String telefone, String cpf) {
        super(nome, telefone, cpf);
        this.id = id;
        this.habilitacao = habilitacao;
        this.locacoes = locacoes;
        this.reservas = reservas;
        this.credito = credito;
    }

    public int getId() {
        return id;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }
    
    public boolean validarCredito(){
        return true;
    }
}
