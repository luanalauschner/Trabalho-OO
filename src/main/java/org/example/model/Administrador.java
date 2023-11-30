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
public class Administrador {
    private List <Cliente> clientes;
    private List <Funcionario> funcionarios;
    private static List <Carro> carros;
    private static List <Filial> filiais;
    private static List <Locacao> locacoes;
    private static List <Reserva> reservas;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public static List<Carro> getCarros() {
        return carros;
    }

    public static List<Filial> getFiliais() {
        return filiais;
    }

    public static List<Locacao> getLocacoes() {
        return locacoes;
    }

    public static List<Reserva> getReservas() {
        return reservas;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setCarros(List<Carro> carros) {
        Administrador.carros = carros;
    }

    public void setFiliais(List<Filial> filiais) {
        Administrador.filiais = filiais;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        Administrador.locacoes = locacoes;
    }

    public void setReservas(List<Reserva> reservas) {
        Administrador.reservas = reservas;
    }
    
    public static void removeLocacao(Locacao l){
        locacoes.remove(l);
    }

    public static void adicionaLocacao(Locacao l){
        locacoes.add(l);
    }

    public static void removeReserva(Reserva r){
        reservas.remove(r);
    }
    
    public static void adicionaReserva(Reserva r){
        reservas.add(r);
    }
}

