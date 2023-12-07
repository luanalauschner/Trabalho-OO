package org.example.controller.controllerRenova;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.view.TelaCliente;

/**
 *
 * @author Lana S. Silva
 */
public class RenovaLocacao implements ActionListener{

    private final TelaCliente tela;

    public RenovaLocacao(TelaCliente tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tela.renovaLocacao();
    }
    
}
