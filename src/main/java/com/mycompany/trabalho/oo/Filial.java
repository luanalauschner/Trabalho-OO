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
public class Filial {
    
    private Endereco endereco;
    private int id;
    private String nome;
    private List<Carro> carrosDisponiveis;
    private List<Locacao> locacoes;
    private List<Reserva> reservas;
    private Funcionario gerente;

    public Filial(Endereco endereco, int id, String nome, List<Carro> carrosDisponiveis, List<Locacao> locacoes, List<Reserva> reservas, Funcionario gerente) {
        this.endereco = endereco;
        this.id = id;
        this.nome = nome;
        this.carrosDisponiveis = carrosDisponiveis;
        this.locacoes = locacoes;
        this.reservas = reservas;
        this.gerente = gerente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Carro> getCarrosDisponiveis() {
        return carrosDisponiveis;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Funcionario getGerente() {
        return gerente;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCarrosDisponiveis(List<Carro> carrosDisponiveis) {
        this.carrosDisponiveis = carrosDisponiveis;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setGerente(Funcionario gerente) {
        this.gerente = gerente;
    }
    
    
}
