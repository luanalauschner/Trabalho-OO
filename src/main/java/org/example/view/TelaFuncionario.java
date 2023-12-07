/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.view;

import org.example.controller.*;
import org.example.exception.*;

//import do controller model
import org.example.model.*;
import org.example.model.Funcionario;
import org.example.model.Locacao;
import org.example.model.Reserva;

//import da biblioteca swing
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author Lana S. Silva
 */
public class TelaFuncionario {

    private JFrame tela_cliente;
    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    private Funcionario funcionario = null;
}
