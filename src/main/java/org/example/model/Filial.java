/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import org.example.model.Reserva;
import org.example.model.Locacao;
import org.example.model.Carro;
import org.example.model.Funcionario;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import org.example.exception.*;

/**
 *
 * @author Lana S. Silva
 */
public class Filial extends Endereco{

    private int id;
    private List<Carro> carrosDisponiveis;
    private List<Locacao> locacoes;
    private List<Reserva> reservas;
    private Funcionario gerente;

    public Filial(Funcionario gerente, String logadouro, int numero, String cidade, String estado, String cep) throws FormatoException {
        super(logadouro, numero, cidade, estado, cep);
        this.id = gerarId();
        this.carrosDisponiveis = new ArrayList<>();
        this.locacoes = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.gerente = gerente;

        Administrador.adicionaFilial(this);
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setCarrosDisponiveis(List<Carro> carrosDisponiveis) {
        this.carrosDisponiveis = carrosDisponiveis;
    }

    /*public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }*/

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setGerente(Funcionario gerente) {
        this.gerente = gerente;
    }
    
    //função responsavel por atualizar a lista de veículos, seja para adicionar ou remover o veículo 
    //da lista de carros disponíveis
    public void atualizacaoCarros(Carro c, boolean b){
        if(b)
            this.carrosDisponiveis.add(c);
        else
            this.carrosDisponiveis.remove(c);
    }

    public void removeLocacao(Locacao l){
        locacoes.remove(l);
    }

    public void adicionaLocacao(Locacao l){
        locacoes.add(l);
    }

    @Override

    public String toString(){
        return String.valueOf(this.id);
    }

    public static int gerarId() {
        
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000);

        return numeroAleatorio;
    }
}
