/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.model;
import org.example.model.Locacao;
import org.example.model.Carro;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author Lana S. Silva
 */
public class Reserva {
    private Locacao locacao;
    private Carro carro;
    private Date dataInicio;
    private Date dataFim;

    public Reserva (Locacao locacao, Carro carro, Date dataInicio, Date dataFim){
        this.locacao = locacao;
        this.carro = carro;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    public void setLocacao (Locacao locacao){
        this.locacao = locacao;
    }

    public void setCarro (Carro carro){
        this.carro = carro;
    }

    public void setDateInicio (Date dataInicio){
        this.dataInicio = dataInicio;
    }

    public void setDataFim (Date dataFim){
        this.dataFim = dataFim;
    }

    public Locacao getLocacao(){
        return locacao;
    }

    public Carro getCarro(){
        return carro;
    }

    public Date getDataInicio(){
        return dataInicio;
    }

    public Date getDataFim()
    {
        return dataFim;
    }

    public boolean sobreposicaoReserva(Reserva r){
        int aux_reservaInicio, aux_reservaFim;
        int aux_reserva2Inicio, aux_reserva2Fim;

        Calendar c1 = Calendar.getInstance();
        c1.setTime(this.dataInicio);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(this.dataFim);

        Calendar r1 = Calendar.getInstance();
        r1.setTime(r.dataInicio);

        Calendar r2 = Calendar.getInstance();
        r2.setTime(r.dataFim);
        
        aux_reservaInicio = c1.get(Calendar.MONTH)*30 + c1.get(Calendar.DAY_OF_MONTH);
        aux_reservaFim = c2.get(Calendar.MONTH)*30 + c2.get(Calendar.DAY_OF_MONTH);
        aux_reserva2Inicio = r1.get(Calendar.MONTH)*30 + r1.get(Calendar.DAY_OF_MONTH);
        aux_reserva2Fim = r2.get(Calendar.MONTH)*30 + r2.get(Calendar.DAY_OF_MONTH);


        if(aux_reserva2Inicio < aux_reservaInicio && aux_reserva2Fim < aux_reservaInicio)
            return false;

        if(aux_reserva2Inicio > aux_reservaInicio && aux_reserva2Inicio > aux_reservaFim)
            return false;
        
        return true;

    }

    public boolean sobreposicaoReserva(Locacao l){
        int aux_reservaInicio, aux_reservaFim;
        int aux_locacaoInicio, aux_locacaoFim;

        Calendar c1 = Calendar.getInstance();
        c1.setTime(this.dataInicio);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(this.dataFim);

        Calendar l1 = Calendar.getInstance();
        l1.setTime(l.getDataInicio());

        Calendar l2 = Calendar.getInstance();
        l2.setTime(l.getDataFim());
        
        aux_reservaInicio = c1.get(Calendar.DAY_OF_YEAR);
        aux_reservaFim = c2.get(Calendar.DAY_OF_YEAR);
        aux_locacaoInicio = l1.get(Calendar.DAY_OF_YEAR);
        aux_locacaoFim = l2.get(Calendar.DAY_OF_YEAR);


        if(aux_locacaoInicio < aux_reservaInicio && aux_locacaoFim < aux_reservaInicio)
            return false;

        if(aux_locacaoInicio > aux_reservaInicio && aux_locacaoInicio > aux_reservaFim)
            return false;
        
        return true;

    }

    public String toString(){
        return String.valueOf(this.id);
    }
}
