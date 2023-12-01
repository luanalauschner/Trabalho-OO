package org.example.controllerSeleciona;

import org.example.view.TelaAdministrador;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Lana S. Silva
 */
public class SelecionaCarros implements ListSelectionListener{
    
    private final TelaAdministrador tela;

    public SelecionaCarros(TelaAdministrador tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        tela.atualizaPainelDir_Carros();
    }
    
}
