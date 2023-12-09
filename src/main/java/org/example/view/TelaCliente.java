/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.view;

//importação do package de controller
import org.example.controller.*;
import org.example.controller.controllerAdiciona.AdicionaCliente;
import org.example.controller.controllerRemove.RemoveLocacao;
import org.example.controller.controllerRemove.RemoveReserva;
import org.example.controller.controllerRenova.RenovaLocacao;
import org.example.controller.controllerCancela.CancelaLocacao;
import org.example.controller.controllerSeleciona.SelecionaLocacao;
import org.example.controller.controllerSeleciona.SelecionaReserva;


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
    //private JMenu menuAtualiza;
    private JMenu menuVoltar;
    private JMenu menuAtualiza;
    private JMenuItem miConsultaLocacao, miConsultaReservas;
    
    private JTextField tfId, tfCarro, tfDataInicio, tfDataFim, tfValidade, tfLocador;

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
        desenhaPaginaInicial();
        
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
                listaLocacao();
            }
        });

        miConsultaReservas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listaReserva();
            }
        });

        menuAtualiza.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                formularioAtualiza();
            }

        });

        menuVoltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                desenha(cliente);
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
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

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

        painel.add(painelLabel);
        painel.add(painelField);

        tela_cliente.getContentPane().add(painel, BorderLayout.WEST);

        //implementação painel direito
        JPanel painel2 = new JPanel();
        painel2.setBorder(BorderFactory.createTitledBorder("Lista de Locações ativas:"));

        DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
        JList<Locacao> modelList = new JList<>(model);

        painel2.add(new JScrollPane(modelList));
        tela_cliente.getContentPane().add(painel2, BorderLayout.CENTER);

    }

    public void exibeLocacao(){
        int selectedIndex = jlLocacao.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
            Locacao l = model.get(selectedIndex);

            //conversão do tipo date para String
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            tfId.setText(String.valueOf(l.getId()));
            tfCarro.setText(l.getCarro_alugado().getPlaca());
            tfDataInicio.setText(formato.format(l.getDataInicio()));
            tfDataFim.setText(formato.format(l.getDataFim()));
            tfValidade.setText(String.valueOf(Locacao.validade(l)));
            tfLocador.setText(l.getLocador().getNome());

        }    

    }

    public void exibeReserva(){

        int selectedIndex = jlLocacao.getSelectedIndex(); //JTextField tfId, tfCarro, tfDataInicio, tfDataFim;

        if(selectedIndex != -1){
            DefaultListModel<Reserva> model = (DefaultListModel<Reserva>)jlReserva.getModel();
            Reserva l = model.get(selectedIndex);

            //conversão do tipo date para String
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            tfId = new JTextField(String.valueOf(l.getId()));
            tfCarro = new JTextField(l.getCarro().getPlaca());
            tfDataInicio = new JTextField(formato.format(l.getDataInicio()));
            tfDataFim = new JTextField(formato.format(l.getDataFim()));

            
        } 

    }

    public void renovaLocacao(){

        int selectedIndex = jlLocacao.getSelectedIndex();

        if(selectedIndex != -1){

            DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
            Locacao locacao = model.get(selectedIndex);
            cliente.renovarLocacao(locacao);
        }

    }

    public void cancelaLocacao(){

        int selectedIndex = jlLocacao.getSelectedIndex();

        if(selectedIndex != -1){

            DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
            Locacao locacao = model.get(selectedIndex);
            cliente.cancelaLocacao(locacao, null);
            model.remove(selectedIndex);
        }

    }

    public void confirmaTermino(){

        int selectedIndex = jlLocacao.getSelectedIndex();

        if(selectedIndex != -1){

            DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
            Locacao locacao = model.get(selectedIndex);
            cliente.confirmarTermino(true, locacao);
            model.remove(selectedIndex);
        }

    }

    public void listaLocacao(){

        JPanel painel = new JPanel();
        JPanel painelLabel = new JPanel();
        JPanel painelField = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Lista de Locações ativas:"));

        DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
        JList<Locacao> modelList = new JList<>(model);
        modelList.addListSelectionListener(new SelecionaLocacao(this));

        tfId = new JTextField();
        tfCarro = new JTextField();
        tfDataInicio = new JTextField();
        tfDataFim = new JTextField();
        tfValidade = new JTextField();
        tfLocador = new JTextField();

        painelField.add(tfId);
        painelField.add(tfCarro);
        painelField.add(tfDataInicio);
        painelField.add(tfDataFim);
        painelField.add(tfValidade);
        painelField.add(tfLocador);

        painelLabel.add(new JLabel("ID"));
        painelLabel.add(new JLabel("Carro"));
        painelLabel.add(new JLabel("Data início"));
        painelLabel.add(new JLabel("Data Fim"));
        painelLabel.add(new JLabel("Validade"));
        painelLabel.add(new JLabel("Locador"));

        JButton btnRenova = new JButton("Renova");
        btnRenova.addActionListener(new RenovaLocacao(this));

        JButton btnCancela = new JButton("Cancela");
        btnCancela.addActionListener(new CancelaLocacao(this));

        JButton btnConfirma = new JButton("Confirma fim de locacao");
        btnConfirma.addActionListener(new RemoveLocacao(this));

        JPanel painelButton = new JPanel();

        painelButton.add(btnRenova);
        painelButton.add(btnCancela);
        painelButton.add(btnConfirma);

        JPanel exibicao = new JPanel();
        exibicao.add(painelLabel);
        exibicao.add(painelField);

        JPanel principal = new JPanel();

        principal.add(exibicao, BorderLayout.CENTER);
        principal.add(painelButton, BorderLayout.SOUTH);

        tela_cliente.getContentPane().add(principal, BorderLayout.CENTER);
        
    }


    public void listaReserva(){

        JPanel painelField = new JPanel();
        JPanel painelLabel = new JPanel();

        painelField.setBorder(BorderFactory.createTitledBorder("Lista de Reservas ativas:"));

        DefaultListModel<Reserva> model = (DefaultListModel<Reserva>)jlReserva.getModel();
        JList<Reserva> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaReserva(this));

        painelField.add(tfId);
        painelField.add(tfCarro);
        painelField.add(tfDataInicio);
        painelField.add(tfDataFim);

        painelLabel.add(new JLabel("ID"));
        painelLabel.add(new JLabel("Carro"));
        painelLabel.add(new JLabel("Data início"));
        painelLabel.add(new JLabel("Data Fim"));

        JButton btnCancela = new JButton("Cancela");
        btnCancela.addActionListener(new CancelaReserva(this));

        JPanel painelButton = new JPanel();

        painelButton.add(btnCancela);

        JPanel exibicao = new JPanel();
        exibicao.add(painelLabel);
        exibicao.add(painelField);

        JPanel principal = new JPanel();

        principal.add(exibicao, BorderLayout.CENTER);
        principal.add(painelButton, BorderLayout.SOUTH);

        tela_cliente.getContentPane().add(principal, BorderLayout.CENTER);
    }

    public void formularioAtualiza(){

        //criação do painel
        JPanel painel3 = new JPanel();
        painel3.setBorder(BorderFactory.createTitledBorder("Atualiza dados"));

        //criação do painel que contém o formulário
        JPanel formulario_cliente = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Nome"));
        painelLabel.add(new JLabel("CPF"));
        painelLabel.add(new JLabel("Telefone"));
        painelLabel.add(new JLabel("Habilitação"));

        //implementação que contém os espaços que o usuário preenche os dados do cliente
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfNome = new JTextField(20);
        tfCpf = new JTextField(20);
        tfTelefone = new JTextField(20);
        tfHabilitacao = new JTextField(20);

        painelField.add(tfNome);
        painelField.add(tfCpf);
        painelField.add(tfTelefone);
        painelField.add(tfHabilitacao);

        formulario_cliente.add(painelLabel);
        formulario_cliente.add(painelField);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AtualizaCliente(this));

        painel3.setLayout(new BorderLayout());
        painel3.add(formulario_cliente, BorderLayout.NORTH);

        JPanel principal = new JPanel();

        principal.add(painel3, BorderLayout.CENTER);
        principal.add(btnAdicionar, BorderLayout.SOUTH);

        tela_cliente.add(principal);
        tela_cliente.pack();
    }

}
