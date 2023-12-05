/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;

import org.example.model.Pessoa;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import org.example.exception.*;
/**
 *
 * @author Lana S. Silva
 */
public class Cliente extends Pessoa{
    
    private int id;
    private String habilitacao;
    private List<Locacao> locacoes;
    private List<Reserva> reservas;
    private boolean credito;

    public Cliente(String habilitacao, boolean credito, String nome, String telefone, String cpf) throws FormatoException
    {
        super(nome, telefone, cpf);
        this.id = gerarId();
        this.habilitacao = habilitacao;
        this.locacoes = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.credito = credito;

        Administrador.adicionaCliente(this);
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

    public boolean getCredito() {
        return credito;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }


    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }
    
    public void validarCredito(){
        this.credito = true;
    }

    public void locacaoFilial(Locacao l){
        List<Filial> filiais = Administrador.getFiliais();
        List<Locacao> locacoes_filiais = null;

        for(Filial f : filiais){
            locacoes_filiais = f.getLocacoes();
            for(Locacao l2 : locacoes_filiais){
                if(l.equals(l2))
                    f.removeLocacao(l);
                    f.atualizacaoCarros(l.getCarro_alugado(), true);
            }
        }
    }
    
    public boolean cancelaLocacao(Locacao l, Date atual){
        //No cancelamento do contrato de locação, o cliente paga uma taxa por quebra de contrato se o contrato 
        //for cancelado antes de pelo menos um mês antes do término do contrato.
        //O valor da taxa é calculado pelo tanto de dias que procede o fim do contrato, se for cancelado antes de 30
        //dias do fim do contrato, é cobrado uma taxa de 30% sobre o valor total do contrato.
        int dias_taxa;
        
        dias_taxa = Locacao.validade(l);
        
        //o credito do cliente não armazena o valor que ele deve, e sim se ele tá positivado ou negativado 
        if(dias_taxa > 30){
            this.credito = false;
        }
        
        locacaoFilial(l);
        l.getLocador().calcularComissao(l);
        Funcionario.removeLocacao(l);
        Administrador.removeLocacao(l);
        locacoes.remove(l);
        return true;
    }
    
    //a partir da data atual, é gerado um novo contrato de locacao a partir da data de finalizacao do antigo,
    //sendo definido com a mesma duração, mesmo funcionário e o mesmo valor total do contrato.
    public void renovarLocacao(Locacao l){
        int dias_de_contrato, diaInicio, diaFim, aux;

        Calendar dataFimAtualizada = Calendar.getInstance();
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(l.getDataInicio());

        Calendar fim = Calendar.getInstance();
        fim.setTime(l.getDataFim());

        diaInicio = inicio.get(Calendar.DAY_OF_YEAR);
        diaFim = fim.get(Calendar.DAY_OF_YEAR);
        dias_de_contrato = Locacao.validade(l);

        diaFim = diaFim + dias_de_contrato;

        if(diaFim > 365){
            aux = diaFim - 365;
            if(aux < 30){
                dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux);
                dataFimAtualizada.set(Calendar.MONTH, 1);
                dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
            }else{
                int mes;
                mes = aux/30;

                switch (mes) {
                    case 2:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 2);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;
                    
                    case 3:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 3);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 4: 
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 4);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 5:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 5);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 6:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 2);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 7:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 2);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 8:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 2);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 9:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 2);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 10:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 2);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 11:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 2);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;

                    case 12:
                        dataFimAtualizada.set(Calendar.DAY_OF_MONTH,aux%30);
                        dataFimAtualizada.set(Calendar.MONTH, 2);
                        dataFimAtualizada.set(Calendar.YEAR, fim.get(Calendar.YEAR) + 1);
                        break;
                
                    default:
                        break;
                }

            }
        }
    
    }
    
    //função responsável pela confirmação do cliente que o mesmo está ciente que o contrato chegou ao fim,
    //caso seja true, é chamado os métodos de disponibilição do carro e caso seja false é chamada o 
    //método de renovação de contrato/locacao
    public boolean confirmarTermino(boolean b, Locacao l){
        if(b){
           Administrador.removeLocacao(l);
           l.getLocador().calcularComissao(l);
           Funcionario.removeLocacao(l);
           locacaoFilial(l);
           //método na classe locação ou filial para disponibilizar o carro quando o contrato encerra
           return true;
        }else{
            renovarLocacao(l);
        }

        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public static int gerarId() {
        
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000);

        return numeroAleatorio;
    }
}
