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
public class Funcionario extends Pessoa {
    
    private int id;
    private String cargo;
    private int salario;
    private List<Locacao> locacoes;

    public Funcionario(int id, String cargo, int salario, List<Locacao> locacoes, String nome, String telefone, String cpf) {
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
        this.locacoes = locacoes;
    }
   
    //calculo da comissao que é adicionada ao salário, a mesma é calculada a partir do valor total do contrato
    //de locacao, sendo independente do cancelamento do mesmo.
    public void comissao(Locacao l){
        
    }

    //realiza o contrato de locacao de um carro dado a disponibilidade do mesmo e o crédito positivo
    //do cliente.
    public void novaLocacao(){
        
    }
    
    //pesquisa de veículos nas filiais para conferir a disponibilidade do carro solicitado pelo cliente.
    public void pesquisaCarros(){
        
    }    
}
