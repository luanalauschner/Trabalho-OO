/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.main;

import org.example.view.TelaAdministrador;
import org.example.view.TelaCliente;
import org.example.view.TelaFuncionario;
import org.example.exception.FormatoException;
import org.example.model.Cliente;
import org.example.model.Funcionario;

public class main {
    
    public static void main(String args[]) throws FormatoException{
        TelaAdministrador tela = new TelaAdministrador();
        tela.desenha();
        Cliente c = null;
        Funcionario f = null;

        try{
            c = new Cliente("12345678910", true, "Lana", "(32)99990-7137", "144.078.896-08");
        }catch(FormatoException e){

        }

        TelaCliente tela2 = new TelaCliente();
        tela2.desenha(c);

        try{
            f = new Funcionario("Gerente", 1200.50, "Luana", "(32)99919-8630", "728.053.076-15");
        }catch(FormatoException e){

        }

        TelaFuncionario tela3 = new TelaFuncionario();
        tela3.desenha(f);
    }
}
