package org.example.controller.controllerAtualiza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.view.TelaFuncionario;

public class AtualizaFuncionario implements ActionListener{

    private final TelaFuncionario tela;

    public AtualizaFuncionario(TelaFuncionario tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tela.atualizaFuncionario();
    }
    
}
