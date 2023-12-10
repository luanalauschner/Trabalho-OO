/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import org.example.model.Carro;
import org.example.model.Funcionario;
import org.example.model.Cliente;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Lana S. Silva
 */
public class Administrador {
    private static List <Cliente> clientes;
    private static List <Funcionario> funcionarios;
    private static List <Carro> carros;
    private static List <Filial> filiais;
    private static List <Locacao> locacoes;
    private static List <Reserva> reservas;

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static List<Funcionario> getFuncionarios() {
        return Administrador.funcionarios;
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

    /*public void setClientes(List<Cliente> clientes) {
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
    }*/
    
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
    
    public static void adicionaCliente(Cliente c){
        clientes.add(c);
    }

    public static void adicionaFuncionario(Funcionario f){
        funcionarios.add(f);
    }

    public static void adicionaCarro(Carro c){
        carros.add(c);
    }

    public static void adicionaFilial(Filial f){
        filiais.add(f);
    }

}

