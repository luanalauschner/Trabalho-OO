/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import org.example.model.Cliente;
import org.example.model.Pessoa;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import org.example.exception.*;
/**
 *
 * @author Lana S. Silva
 */
public class Funcionario extends Pessoa {
    
    private int id;
    private String cargo;
    private double salario;
    private static List<Locacao> locacoes;
    private static List<Reserva> reservas;
    private double comissao;

    public Funcionario(String cargo, double salario, String nome, String telefone, String cpf) throws FormatoException{
        super(nome, telefone, cpf);
        this.id = gerarId();
        this.cargo = cargo;
        this.salario = salario;
        Funcionario.locacoes = new ArrayList<>();
        Funcionario.reservas = new ArrayList<>();

        Administrador.adicionaFuncionario(this);
    }

    public int getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public List<Reserva> getReservas(){
        return reservas;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    /*public void setLocacoes(List<Locacao> locacoes) {
        Funcionario.locacoes = locacoes;
    }*/
   
    //calculo da comissao que é adicionada ao salário, a mesma é calculada a partir do valor total do contrato
    //de locacao, sendo independente do cancelamento do mesmo.
    //o funcionário pode ser locador ou gerente, caso seja gerente a comissão é 10% do valor total de cada locacao
    // e caso seja locador a comissao é de 5%.
    /*public double calcularComissao(){
        String aux_cargo = cargo.toUpperCase();
        double comissao_gerada, porcentagem = 0, comissao_total = 0;
        
        if(aux_cargo.equals("GERENTE"))
            porcentagem = 0.1;
        
        if(aux_cargo.equals("LOCADOR"))
            porcentagem = 0.05;
        
        List<Locacao> aux_locacoes = getLocacoes();
        for(Locacao l: aux_locacoes){
            comissao_gerada = l.valorTotalDoContrato()*porcentagem;
            comissao_total += comissao_gerada;
        }
        
        return comissao_total;
    }
    */

    public void calcularComissao(Locacao l){
        String aux_cargo = cargo.toUpperCase();
        double comissao_gerada, porcentagem = 0;
        
        if(aux_cargo.equals("GERENTE"))
            porcentagem = 0.1;
        
        if(aux_cargo.equals("LOCADOR"))
            porcentagem = 0.05;

        comissao_gerada = porcentagem * l.valorTotalDoContrato();
        this.comissao += comissao_gerada;
    }
    
    public double calcularPagamento(){
        return comissao + salario;
    }
    
    //pesquisa de veículos nas filiais para conferir a disponibilidade do carro solicitado pelo cliente.
    public List<Carro> pesquisaCarros(Filial f, String exigencia){
        /* o parâmetro da exigência do carro pelo cliente é do tipo object por se tratar de uma variável a qual 
        não sabemos o tipo. Para realizar a pesquisa em si, convertemos o object e todos os parâmetros do carro 
        temporariamente em string, para realizarmos a comparação igualmente de objetos.
        */
        List<Carro> catalogo = f.getCarrosDisponiveis();
        
        for(Carro c : catalogo) {
            if(c.confereCarro(exigencia) == false){
                catalogo.remove(c);
            }
        }
        
        return catalogo;
    }

    public static int gerarId() {
        
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000);

        return numeroAleatorio;
    }
    
    //realiza o contrato de locacao de um carro dado a disponibilidade do mesmo e o crédito positivo
    //do cliente.
    public boolean novaLocacao(Cliente locatario, Carro c, Date inicio, Date fim, Filial f){
        int id;
        //confere disponibilidade do carro
        if(!c.isDisponibilidade())
            return false;
        
        //confere crédito do cliente
        if(!locatario.getCredito())
            return false;

        List<Reserva> reservas = Administrador.getReservas();
        id = gerarId();
        Locacao l = new Locacao(id, inicio, fim, locatario, c, true);

        for(Reserva r : reservas){
            if(r.getCarro().equals(c)){
                if(!r.sobreposicaoReserva(l)){
                    return true;
                }
            }
        }
        
        c.setDisponibilidade(false);
        Administrador.adicionaLocacao(l);
        Funcionario.addLocacao(l);
        f.atualizacaoCarros(c, false);
        
        return true;
    }
    
    public static void removeLocacao(Locacao l){
        locacoes.remove(l);
    }
    
    public static void addLocacao(Locacao l){
        locacoes.add(l);
    } 

    //realiza a reserva de um carro dado a disponibilidade do mesmo e o crédito positiva do cliente.
    public boolean novaReserva(Cliente locatario, Carro c, Date inicio, Date fim, Filial f){
        int id;
        //confere disponibilidade do carro
        if(!c.isDisponibilidade())
            return false;
        
        //confere crédito do cliente
        if(!locatario.getCredito())
            return false;

        List<Reserva> reservas = Administrador.getReservas();
        id = gerarId();
        Reserva r = new Reserva( c, locatario, inicio, fim);

        for(Reserva r1 : reservas){
            if(r1.getCarro().equals(c)){
                if(!r1.sobreposicaoReserva(r1)){
                    return true;
                }
            }
        }
        
        c.setDisponibilidade(false);
        Administrador.adicionaReserva(r);
        Funcionario.addReserva(r);
        f.atualizacaoCarros(c, false);
        
        return true;
    }

    public static void removeReserva(Reserva r){
        reservas.remove(r);
    }
    
    public static void addReserva(Reserva r){
        reservas.add(r);
    } 

    //função responsável por validar o crédito do cliente, o funcionário recebe o pagamento para positivar o credito
    //do cliente e positiva ele no sistema 
    public static boolean validaCliente(Cliente c){
        c.validarCredito();
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
