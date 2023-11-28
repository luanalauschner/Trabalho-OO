/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho.oo;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
/**
 *
 * @author Lana S. Silva
 */
public class Funcionario extends Pessoa {
    
    private int id;
    private String cargo;
    private int salario;
    private static List<Locacao> locacoes;

    public Funcionario(int id, String cargo, int salario, List<Locacao> locacoes, String nome, String telefone, String cpf) throws CpfException, TelefoneException {
        super(nome, telefone, cpf);
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
        this.locacoes = locacoes;
    }

    public int getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public int getSalario() {
        return salario;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        Funcionario.locacoes = locacoes;
    }
   
    //calculo da comissao que é adicionada ao salário, a mesma é calculada a partir do valor total do contrato
    //de locacao, sendo independente do cancelamento do mesmo.
    //o funcionário pode ser locador ou gerente, caso seja gerente a comissão é 10% do valor total de cada locacao
    // e caso seja locador a comissao é de 5%.
    public double calcularComissao(){
        String aux_cargo = cargo.toLowerCase();
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
    
    public double calcularPagamento(){
        double comissao;
        comissao = calcularComissao();
        
        return comissao + salario;
    }
    
    //pesquisa de veículos nas filiais para conferir a disponibilidade do carro solicitado pelo cliente.
    public List<Carro> pesquisaCarros(Filial f, Object exigencia){
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

    /*public Map<Filial,Carro> pesquisaCarros(Object exigencia){
        Map<Filial,Carro> catalogo = new HashMap();
        List<Filial> filais = Administrador.getFiliais();
        List<Carro> carros = null;

        for(Filial f: filais){
            carros = f.getCarrosDisponiveis();
            for(Carro c : carros) {
                if(c.confereCarro(exigencia)){
                    
                }
            }
        }
        return catalogo;
    }*/

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
        if(locatario.getCredito() < 0)
            return false;

        List<Reserva> reservas = Administrador.getReservas();
        id = gerarId();
        Locacao l = new Locacao(id, inicio, fim, locatario, c, true);

        for(Reserva r : reservas){
            if(r.getCarro().equals(c)){
                if(r.sobreposicaoReserva(l)){
                    return true;
                }
            }
        }
        
        //olhar geração de id, fazer no main ou na classe?
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
}
