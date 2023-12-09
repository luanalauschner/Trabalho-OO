package org.example.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.view.TelaFuncionario;

public class ValidaClientes implements ActionListener{

    private final TelaFuncionario tela;

    public ValidaClientes(TelaFuncionario tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tela.validaCredito();
    }
    
}
