/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho.oo;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
/**
 *
 * @author Lana S. Silva
 */
public class Cliente extends Pessoa{
    
    private int id;
    private String habilitacao;
    private List<Locacao> locacoes;
    private List<Reserva> reservas;
    private double credito;

    public Cliente(int id, String habilitacao, List<Locacao> locacoes, List<Reserva> reservas, double credito, String nome, String telefone, String cpf) {
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

    public double isCredito() {
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

    public void setCredito(double credito) {
        this.credito = credito;
    }
    
    public boolean validarCredito(){
        return true;
    }
    
    public boolean cancelaLocacao(Locacao l, Date atual){
        //No cancelamento do contrato de locação, o cliente paga uma taxa por quebra de contrato se o contrato 
        //for cancelado antes de pelo menos um mês antes do término do contrato.
        //O valor da taxa é calculado pelo tanto de dias que procede o fim do contrato, se for cancelado antes de 30
        //dias do fim do contrato, é cobrado uma taxa de 30% sobre o valor total do contrato.
        int data_atual, data_final, dias_taxa;
        double valor_taxa;
        
        Calendar c = Calendar.getInstance();
        c.setTime(atual);
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(l.getDataFim());
        
        //conferir condição para data atual ser antes do fim do contrato, o contrato
        //quando chega ao fim, encerra sozinho
        data_atual = c.get(Calendar.MONTH)*30 + c.get(Calendar.DAY_OF_MONTH);
        data_final = c2.get(Calendar.MONTH)*30 + c2.get(Calendar.DAY_OF_MONTH);
        
        dias_taxa = data_final - data_atual;
        
        //o valor da taxa é colocado no crédito do cliente, o qual fica negativado e para realizar uma nova locação 
        //ele precisa realizar o pagamento. 
        if(dias_taxa > 30){
            valor_taxa = ((double)dias_taxa/100) * l.valorTotalDoContrato();
            this.credito = valor_taxa * (-1);
            //retira a locação da lista de locações do funcionario e do administrador.
            //função na classe locação para disponibilizar o carro quando o contrato encerra
        }
        
        
        Funcionario.removeLocacao(l);
        Administrador.removeLocacao(l);
        locacoes.remove(l);
        return true;
    }
    
    //a partir da data atual, é gerado um novo contrato de locacao a partir da data de finalizacao do antigo,
    //sendo definido com a mesma duração, mesmo funcionário e o mesmo valor total do contrato.
    public void renovarLocacao(){
    
    }
    
    //função responsável pela confirmação do cliente que o mesmo está ciente que o contrato chegou ao fim
    public void confirmarTermino(){
        
    }
}
