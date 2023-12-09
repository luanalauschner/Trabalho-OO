package org.example.controller.controllerCancela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.view.TelaCliente;

public class CancelaReserva implements ActionListener{

    private final TelaCliente tela;

    public CancelaReserva(TelaCliente tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tela.cancelaReserva();
    }
    
}
