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
    private static List<Locacao> locacoes;

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
        Funcionario.locacoes = locacoes;
    }
   
    //calculo da comissao que é adicionada ao salário, a mesma é calculada a partir do valor total do contrato
    //de locacao, sendo independente do cancelamento do mesmo.
    public void comissao(){
        
    }
    
    //pesquisa de veículos nas filiais para conferir a disponibilidade do carro solicitado pelo cliente.
    public List<Carro> pesquisaCarros(Filial f, Object exigencia){
        /* o parâmetro da exigência do carro pelo cliente é do tipo object por se tratar de uma variável a qual 
        não sabemos o tipo. Para realizar a pesquisa em si, convertemos o object e todos os parâmetros do carro 
        temporariamente em string, para realizarmos a comparação igualmente de objetos.
        */
        List<Carro> catalogo = f.getCarrosDisponiveis();
        
        for (Carro c : catalogo) {
            if(c.confereCarro(exigencia) == false){
                catalogo.remove(c);
            }
        }
        
        return catalogo;
    }
    
    /*
    dúvida: a função pesquisa deve retornar uma lista de carros de acordo com aquelas exigência ou um 
    boolean se aquele carro está disponível naquela determinada filial?
    public boolean pesquisaCarros(Filial f, Object exigencia){
        List<Carro> catalogo = f.getCarrosDisponiveis();
        
        for (Carro c : catalogo) {
            if(c.confereCarro(exigencia) == false){
                catalogo.remove(c);
            }
        }
        
        if(catalogo != null)
            return true;
    
        return false;  
    }
    
    */
    
    //realiza o contrato de locacao de um carro dado a disponibilidade do mesmo e o crédito positivo
    //do cliente.
    public void novaLocacao(){
        
    }
    
    public static void removeLocacao(Locacao l){
        locacoes.remove(l);
    }   
}
