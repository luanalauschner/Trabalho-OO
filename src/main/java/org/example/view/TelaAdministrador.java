/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.view;

import org.example.controller.*;
import org.example.exception.*;
import org.example.model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Lana S. Silva
 */
public class TelaAdministrador {
    
    private JFrame tela_adm;
    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    private final int V_GAP = 10;
    private final int H_GAP = 5;
    
    private JMenuBar menuBarra;
    private JMenu menuCadastra;
    private JMenu menuConsulta;
    private JMenuItem miCadastraCliente, miCadastraCarro, miCadastraFunc, miCadastraFilial;
    private JMenuItem miConsultaCliente, miConsultaFunc, miConsultaCarro, miConsultaFilial, miConsultaLocacoes, miConsultaReservas;
    
    
    public void desenha(){

        tela_adm = new JFrame("Espaço do admnistrador");
        tela_adm.setSize(WIDTH, HEIGHT);
        tela_adm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela_adm.setLocationRelativeTo(null);
        tela_adm.setVisible(true);
        tela_adm.setLayout(new BorderLayout());
        
        inicializaComponentes();
        
        tela_adm.pack();
    }
    
    public void inicializaComponentes(){
        
        //inicializando os menus
        menuBarra = new JMenuBar();
        menuCadastra = new JMenu("Cadastro");
        menuCadastra.setMnemonic('C');
        menuConsulta = new JMenu("Consulta");
        menuConsulta.setMnemonic('L');
        
        //inicializando os itens do menu cadastro
        miCadastraCliente = new JMenuItem("Cadastra Cliente");
        miCadastraCarro = new JMenuItem("Cadastra Carro");
        miCadastraFunc = new JMenuItem("Cadastra Funcionário");
        miCadastraFilial = new JMenuItem("Cadastra Filial");
        
        //inicializando os itens do menu consulta ou listagem
        miConsultaCliente = new JMenuItem("Lista Clientes");
        miConsultaFunc = new JMenuItem("Lista Funcionários");
        miConsultaCarro = new JMenuItem("Lista Carros");
        miConsultaFilial = new JMenuItem("Lista Filiais");
        miConsultaLocacoes = new JMenuItem("Lista Locações");
        miConsultaReservas = new JMenuItem("Lista Reservar");
        
        //adicionando os itens ao menu cadastra
        menuCadastra.add(miCadastraCliente);
        menuCadastra.add(miCadastraCarro);
        menuCadastra.add(miCadastraFunc);
        menuCadastra.add(miCadastraFilial);
        
        //adicionando os itens ao menu consulta
        menuConsulta.add(miConsultaCliente);
        menuConsulta.add(miConsultaCarro);
        menuConsulta.add(miConsultaFunc);
        menuConsulta.add(miConsultaFilial);
        menuConsulta.add(miConsultaLocacoes);
        menuConsulta.add(miConsultaReservas);
        
        //adicionando ao menu barra
        menuBarra.add(menuCadastra);
        menuBarra.add(menuConsulta);
        
        //adicionando o menu barra
        tela_adm.setJMenuBar(menuBarra);
        liberaMenu();//deixa o menu visivel
    }
    
    public void liberaMenu(){
        menuCadastra.setEnabled(true);
        menuConsulta.setEnabled(true);
    }
    
    public void desenhaSplitPanel(){
        
        JPanel painel_esq = new JPanel();
        JPanel painel_dir = new JPanel();
        
        //inicialização da lista do painel esquerda, onde apresenta o que o administrador pode listar
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Clientes");
        modeloLista.addElement("Carros");
        modeloLista.addElement("Funcionários");
        modeloLista.addElement("Locações");
        modeloLista.addElement("Reservas");
        JList<String> lista = new JList<>(modeloLista);
        
        //inicialização do addListSelectionListener
        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if("CLIENTE".equals(lista.getSelectedValue().toUpperCase()))
                        new SelecionaClientes(this);
                    
                    if("CARROS".equals(lista.getSelectedValue().toUpperCase()))
                        new SelecionaCarros(this);
                    
                    if("FUNCIONÁRIOS".equals(lista.getSelectedValue().toUpperCase()))
                        new SelecionaFuncionarios(this);
                    
                    if("FILIAIS".equals(lista.getSelectedValue().toUpperCase()))
                        new SelecionaFiliais(this);
                    
                    if("LOCAÇÕES".equals(lista.getSelectedValue().toUpperCase()))
                        new SelecionaLocacoes(this);
                    
                    if("RESERVAS".equals(lista.getSelectedValue().toUpperCase()))
                        new SelecionaReservas(this);
                }
            }
        });
        
        JScrollPane scroll= new JScrollPane (lista);
        painel_esq.add(scroll); //adiciona a lista co rolamento ao painel esquerda
        
        //criação da borda dos paineis que vão compor o SplitPane
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem"));
        painel_dir.setBorder(BorderFactory.createTitledBorder(""));
        
        //definição do tamanho dos paineis
        //painel_dir.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        //painel_esq.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
      
     
        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        //tela_adm.add(sp); //adiciona o splitPane ao JFrame
        
        
    }
    
    public void atualizaPainelDir_Clientes(){
        
        
    }
    
    public void atualizaPainelDir_Carros(){
        
    }
    
    public void atualizaPainelDir_Funcionarios(){
        
    }
    
    public void atualizaPainelDir_Locacoes(){
        
    }
    
    public void atualizaPainelDir_Reservas(){
        
    }
    
    public void atualizaPainelDir_Filiais(){
        
    }
    
    public void itemSelecionado(ListSelectionEvent e){
        
    }
}
