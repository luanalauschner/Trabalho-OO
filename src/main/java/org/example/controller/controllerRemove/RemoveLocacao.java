package org.example.controller.controllerRemove;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.view.TelaCliente;

/**
 *
 * @author Lana S. Silva
 */
public class RemoveLocacao implements ActionListener{

    private final TelaCliente tela;

    public RemoveLocacao(TelaCliente tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tela.cancelaLocacao();;
    }
    
}
