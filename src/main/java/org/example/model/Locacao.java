/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import org.example.model.Carro;
import org.example.model.Funcionario;
import org.example.model.Cliente;
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

    public void setLocador(Funcionario locador) {
        this.locador = locador;
    }

    public static int validade(Locacao l){
        int dias, data_inicio, data_fim;


        Calendar c = Calendar.getInstance();
        c.setTime(l.dataInicio);
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(l.dataFim);
        
        //da a data exata do ano 
        data_inicio = c.get(Calendar.DAY_OF_YEAR);
        data_fim = c2.get(Calendar.DAY_OF_YEAR);

        if(c.get(Calendar.YEAR) != c2.get(Calendar.YEAR))
            dias = (365-data_inicio) + data_fim;
            else 
                dias = data_fim - data_inicio;

        return dias;

    }
    
    public double valorTotalDoContrato(){
        double valor;
        int dias_totais;
        
        dias_totais = validade(this);
        
        valor = carro_alugado.getPreco_diaria() * (double)dias_totais;
        
        return valor;
    }

    public boolean equals(Locacao l){
        if(l == null)
            return false;

        if(this.id == l.getId())
            return true;

        return false;
    }

    @Override
    public String toString(){
        return String.valueOf(this.id);
    }
}
