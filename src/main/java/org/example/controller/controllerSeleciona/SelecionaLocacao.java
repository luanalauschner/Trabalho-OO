package org.example.controller.controllerSeleciona;

import org.example.view.TelaAdministrador;
import org.example.view.TelaCliente;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Lana S. Silva
 */
public class SelecionaLocacao implements ListSelectionListener{
    
    private final TelaCliente tela;

    public SelecionaLocacao(TelaCliente tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        tela.exibeLocacao();
    }
    
}