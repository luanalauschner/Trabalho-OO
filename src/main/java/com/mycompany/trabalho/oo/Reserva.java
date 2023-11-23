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

    public boolean sobreposicaoReserva(Reserva r, Carro carro){
        int aux_reserva, aux_reservaFim;
        int aux_reserva2, aux_reserva2Fim;

        Calendar c1 = Calendar.getInstance();
        c1.getTime(this.dataInicio);

        Calendar c2 = Calendar.getInstance();
        c2.getTime(this.dataFim);

        Calendar r1 = Calendar.getInstance();
        r1.setTime(r.dataInicio);

        Calendar r2 = Calendar.getInstance();
        r2.setTime(r.dataFim);
        
        aux_reserva = c1.get(Calendar.MONTH)*30 + c1.get(Calendar.DAY_OF_MONTH);
        aux_reservaFim = c2.get(Calendar.MONTH)*30 + c2.get(Calendar.DAY_OF_MONTH);
        aux_reserva = r1.get(Calendar.MONTH)*30 + r1.get(Calendar.DAY_OF_MONTH);
        aux_reserva2Fim = r2.get(Calendar.MONTH)*30 + r2.get(Calendar.DAY_OF_MONTH);

        if(carros.isDisponibilidade==true){
            if(r1 > c1 && r1 < c2 || r1 < c1 && r2 > c1 || r1 > c1 && r2 < c2 || r1 < c1 && r2 > c2){
                return true;
            }
            return false;
        }

    }
}
