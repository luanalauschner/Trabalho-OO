/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho.oo;

import java.util.Date;
import java.util.Calendar;
/**
 *
 * @author Lana S. Silva
 */
public class Locacao {
    
    private int id;
    private Date dataInicio;
    private Date dataFim;
    private Cliente locatario;
    private Carro carro_alugado;
    private Funcionario locador;
    
    private static boolean validade;
    //true = contrato em período de validade; false = contrato encerrado ou cancelado (é mesmo necessário?)

    public Locacao(int id, Date dataInicio, Date dataFim, Cliente locatario, Carro carro_alugado, boolean validade) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.locatario = locatario;
        this.carro_alugado = carro_alugado;
        //this.locador = locador;
        Locacao.validade = validade;
    }

    public int getId() {
        return id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public Cliente getLocatario() {
        return locatario;
    }

    public Carro getCarro_alugado() {
        return carro_alugado;
    }

    public Funcionario getLocador() {
        return locador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setLocatario(Cliente locatario) {
        this.locatario = locatario;
    }

    public void setCarro_alugado(Carro carro_alugado) {
        this.carro_alugado = carro_alugado;
    }
    
    public double valorTotalLocacao(Carro c){
        
        return 0;
    }

    public void setLocador(Funcionario locador) {
        this.locador = locador;
    }
    
    public double valorTotalDoContrato(){
        double valor;
        int data_inicio, data_fim, dias_totais;
        
        Calendar c = Calendar.getInstance();
        c.setTime(dataInicio);
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(dataFim);
        
       
        data_inicio = c.get(Calendar.MONTH)*30 + c.get(Calendar.DAY_OF_MONTH);
        data_fim = c2.get(Calendar.MONTH)*30 + c2.get(Calendar.DAY_OF_MONTH);
        dias_totais = data_fim - data_inicio;
        
        valor = carro_alugado.getPreco_diaria() * (double)dias_totais;
        
        return valor;
    }
    
    //esse método é o responsável por disponibilizar novamente o carro quando o contrato termina por
    //cancelamento ou por termino da validade.
    public static void terminoContrato(){
        
    }
}
