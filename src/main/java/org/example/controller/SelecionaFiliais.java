package org.example.controller;

import org.example.view.TelaAdministrador;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author Lana S. Silva
 */
public class SelecionaFiliais implements ListSelectionListener{
    
    private final TelaAdministrador tela;

    public SelecionaFiliais(TelaAdministrador tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        tela.atualizaPainelDir_Filiais();
    }
    
}