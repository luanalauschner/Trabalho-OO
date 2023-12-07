package org.example.controller.controllerCancela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.view.TelaCliente;

/**
 *
 * @author Lana S. Silva
 */
public class CancelaLocacao implements ActionListener{

    private final TelaCliente tela;

    public CancelaLocacao(TelaCliente tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tela.cancelaLocacao();
    }
    
}
