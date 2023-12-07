/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.view;

//importação do package de controller
import org.example.controller.*;
import org.example.controller.controllerAdiciona.AdicionaCliente;
import org.example.controller.controllerAdiciona.AdicionaCarro;
import org.example.controller.controllerAdiciona.AdicionaFilial;
import org.example.controller.controllerAdiciona.AdicionaFuncionario;
import org.example.controller.controllerSeleciona.SelecionaCarros;
import org.example.controller.controllerSeleciona.SelecionaClientes;
import org.example.controller.controllerSeleciona.SelecionaFiliais;
import org.example.controller.controllerSeleciona.SelecionaFuncionarios;
import org.example.controller.controllerSeleciona.SelecionaLocacoes;
import org.example.controller.controllerSeleciona.SelecionaReservas;

//importação do package exception
import org.example.exception.FormatoException;

//importação do package model
import org.example.model.*;

//importação da biblioteca utilizada
import javax.swing.*;
import javax.swing.event.*;

//importação dos packages do awt utilizados
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lana S. Silva
 */
public class TelaCliente {

    private JFrame tela_cliente;
    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    private Cliente cliente = null;

    private JList<Locacao> jlLocacao;
    private JList<Reserva> jlReserva;

    //inicialização do menu
    private JMenuBar menuBarra;
    private JMenu menuConsulta;
    private JMenu menuAtualiza;
    private JMenu menuVoltar;
    private JMenuItem miConsultaLocacao, miConsultaReservas;
    

    //inicializa os paineis
    private JPanel painel_dir;
    private JPanel painel_esq;

    //inicializa os text fields utilizados
    private JTextField tfNome, tfHabilitacao, tfTelefone, tfCpf, tfCredito;

    public void desenha(Cliente c){

        //inicializa o cliente
        cliente = c;

        tela_cliente = new JFrame("Espaço do admnistrador");
        tela_cliente.setSize(WIDTH, HEIGHT);
        tela_cliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela_cliente.setLocationRelativeTo(null);
        tela_cliente.setVisible(true);
        tela_cliente.setLayout(new BorderLayout());
        
        inicializaComponentes();
        //desenhaSplitPanel();
        
        tela_cliente.pack();
    }
    
    public void inicializaComponentes(){

        //inicializa os menus
        menuBarra = new JMenuBar();
        menuConsulta = new JMenu("Consulta");
        menuAtualiza = new JMenu("Atualiza dados");
        menuVoltar = new JMenu("Página Inicial");

        //inicializa os itens do menu consulta
        miConsultaLocacao = new JMenuItem("Consulta Locações Ativas");
        miConsultaReservas = new JMenuItem("Consulta Reservas");

        //definindo o actionListener
        miConsultaLocacao.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }

        });

        miConsultaReservas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }

        });

        menuAtualiza.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }

        });

        //adicionando os itens ao menu consulta
        menuConsulta.add(miConsultaLocacao);
        menuConsulta.add(miConsultaReservas);

        //adicionando ao menu barra
        menuBarra.add(menuVoltar);
        menuBarra.add(menuAtualiza);
        menuBarra.add(menuConsulta);

        tela_cliente.setJMenuBar(menuBarra);
        liberaMenu();

    }

    public void liberaMenu(){
        menuVoltar.setEnabled(true);
        menuConsulta.setEnabled(true);
        menuAtualiza.setEnabled(true);
        menuBarra.setVisible(true);
    }

    public void desenhaPaginaInicial(){

        //implementação painel esquerdo
        painel_esq = new JPanel();
        painel_esq.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        //no painel esq vai ser exibido os dados do cliente
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Nome"));
        painelLabel.add(new JLabel("Habilitação:"));
        painelLabel.add(new JLabel("Telefone:"));
        painelLabel.add(new JLabel("CPF:"));
        painelLabel.add(new JLabel("Crédito:"));

        //implementação dos espaços com as informações do cliente
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        
        tfNome = new JTextField(cliente.getNome());
        tfHabilitacao = new JTextField(cliente.getHabilitacao());
        tfTelefone = new JTextField(cliente.getTelefone());
        tfCpf = new JTextField(cliente.getCpf());

        if(cliente.getCredito())
            tfCredito = new JTextField("Positivo");
            else
            tfCredito = new JTextField("Negativo");

        //insercao dos elemento TextField
        painelField.add(tfNome);
        painelField.add(tfHabilitacao);
        painelField.add(tfTelefone);
        painelField.add(tfCpf);

        painel_esq.add(painelLabel);
        painel_esq.add(painelField);

        painel_esq.setLayout(new BorderLayout());
        //painel_esq.add(painelLabel, BorderLayout.CENTER);



        //implementação painel direito
        painel_dir = new JPanel();
        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Locações ativas:"));

        DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
        JList<Locacao> modelList = new JList<>(model);

        painel_dir.add(new JScrollPane(modelList));

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);

        sp.setDividerLocation(250);
        tela_cliente.add(sp);
        
    }
}
