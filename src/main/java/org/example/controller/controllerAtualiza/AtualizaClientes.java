package org.example.controller.controllerAtualiza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.view.TelaCliente;

public class AtualizaClientes implements ActionListener{

    private final TelaCliente tela;

    public AtualizaClientes(TelaCliente tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tela.atualizaCliente();
    }
    
}
