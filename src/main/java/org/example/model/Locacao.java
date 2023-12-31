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
import java.util.List;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Locacao(String dataInicio, String dataFim, String id, String placa, boolean validade) throws ParseException{
        this.id = gerarId();
        this.dataInicio = retornaData(dataInicio);
        this.dataFim = retornaData(dataFim);
        this.locatario = retornaCliente(id);
        this.carro_alugado = retornaCarro(placa);
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

    public Date retornaData(String s) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();

        d = formato.parse(s);
        return d;
    }

    public Carro retornaCarro(String p){
        List<Carro> carros= Administrador.getCarros();

        for(Carro c : carros){
            if(c.confereCarro(p))
                return c;
        }

        return null;

    }

    public Cliente retornaCliente(String id){
        List<Cliente> clientes= Administrador.getClientes();

        for(Cliente c : clientes){
            if(c.getId() == (Integer.parseInt(id)))
                return c;
        }

        return null;
    }

    public static int gerarId() {
        
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000);

        return numeroAleatorio;
    }
}
