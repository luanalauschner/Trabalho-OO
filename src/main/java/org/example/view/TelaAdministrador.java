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
import java.util.List;

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

    private JList<Cliente> jlClientes;
    private JList<Carro> jlCarro;
    private JList<Locacao> jlLocacao;
    private JList<Filial> jlFilial;
    private JList<Funcionario> jlFunc;
    private JList<Reserva> jlReserva;
    
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
        desenhaSplitPanel();
        
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
        menuBarra.setVisible(true);
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
                    if("CLIENTE".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Cliente> model = new DefaultListModel<>();
                        jlClientes =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlClientes), BorderLayout.LEFT);
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
                    }
                    
                    if("CARROS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Carro> model = new DefaultListModel<>();
                        jlCarro =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlCarro), BorderLayout.LEFT);
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Carros"));
                    }
                    
                    if("FUNCIONÁRIOS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Funcionario> model = new DefaultListModel<>();
                        jlFunc =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlFunc), BorderLayout.LEFT);
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Funcionários"));
                    }
                    
                    if("FILIAIS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Filial> model = new DefaultListModel<>();
                        jlFilial =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlFilial), BorderLayout.LEFT);
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Filiais"));
                    }
                    
                    if("LOCAÇÕES".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Locacao> model = new DefaultListModel<>();
                        jlLocacao =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlLocacao), BorderLayout.LEFT);
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Locações"));
                    }
                    
                    if("RESERVAS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Reserva> model = new DefaultListModel<>();
                        jlReserva =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlReserva), BorderLayout.LEFT);
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Reservas"));
                    }
                }
            }
        });
        
        JScrollPane scroll= new JScrollPane (lista);
        painel_esq.add(scroll, BorderLayout.LEFT); //adiciona a lista co rolamento ao painel esquerda
        
        //criação da borda dos paineis que vão compor o SplitPane
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem"));
        
        //definição do tamanho dos paineis
        //painel_dir.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        //painel_esq.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
      
     
        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        tela_adm.add(sp); //adiciona o splitPane ao JFrame
        
        
    }
    
    public List<Contato> atualizaPainelDir_Clientes(){
        /*
        public List<Contato> listaContatos(){
        DefaultListModel<Contato> model = (DefaultListModel<Contato>)jlContatos.getModel();
        List<Contato> contatos = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            contatos.add(model.get(i));
        }

        return contatos;
    }
        */

       /*
       private void desenhaLista(){

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Contatos"));
        painel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
        painel.setLayout(new BorderLayout());

        DefaultListModel<Contato> model = new DefaultListModel<>();


        jlContatos = new JList<>(model);
        jlContatos.addListSelectionListener(new SelecionarContato(this));

        painel.add(new JScrollPane(jlContatos), BorderLayout.CENTER);

        tela.getContentPane().add(painel, BorderLayout.WEST);
    }
       */
        
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
