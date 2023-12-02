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
import java.util.List;
/**
 *
 * @author Lana S. Silva
 */
public class TelaFuncionario {

    private JFrame tela_funcionario;
    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    private JList<Cliente> jlClientes;
    private JList<Carro> jlCarro;
    private JList<Locacao> jlLocacao;
    //private JList<Filial> jlFilial;
    //private JList<Reserva> jlReserva;
    
    private JMenuBar menuBarra;
    private JMenu menuNovaLocacao;
    private JMenu menuNovaReserva;
    private JMenu menuCalculaSalario;
    private JMenu menuCalculaComissao;

    private JMenuItem miCadastraLocacao;
    private JMenuItem miCadastraReserva;
    private JMenuItem miConsultaSalario;
    private JMenuItem miConsultaComissao;

    public void desenha(){

        tela_funcionario = new JFrame("Espaço do admnistrador");
        tela_funcionario.setSize(WIDTH, HEIGHT);
        tela_funcionario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela_funcionario.setLocationRelativeTo(null);
        tela_funcionario.setVisible(true);
        tela_funcionario.setLayout(new BorderLayout());
        
        inicializaComponentes();
        desenhaSplitPanel();
        
        tela_funcionario.pack();
    }

    public void inicializaComponentes(){
        
        //inicializando os menus
        menuBarra = new JMenuBar();
        menuNovaLocacao = new JMenu("Nova Locacao");
        menuNovaLocacao.setMnemonic('L');
        menuNovaReserva = new JMenu("Nova Reserva");
        menuNovaReserva.setMnemonic('R');
        menuCalculaSalario = new JMenu("Calcula Salario");
        menuCalculaSalario.setMnemonic('S');
        menuCalculaComissao = new JMenu("Calcula Comissao");
        menuCalculaComissao.setMnemonic('C');
        
        //inicializando os itens da locacao
        miCadastraLocacao = new JMenuItem("Cadastra Locacao");

        //inicializando os itens da reserva
        miCadastraReserva= new JMenuItem("Cadastra Reserva");

        //inicializando os itens da comissao
        miConsultaComissao = new JMenuItem("Consulta Funcionário");

        //inicilaizando os itens do salario
        miConsultaSalario = new JMenuItem("Consulta Salario");
        
        //inicializando os itens do menu consulta ou listagem
        /*miConsultaCliente = new JMenuItem("Lista Clientes");
        miConsultaFunc = new JMenuItem("Lista Funcionários");
        miConsultaCarro = new JMenuItem("Lista Carros");
        miConsultaFilial = new JMenuItem("Lista Filiais");
        miConsultaLocacoes = new JMenuItem("Lista Locações");
        miConsultaReservas = new JMenuItem("Lista Reservar");

        talvez use isso
        */

        //adicionando os itens ao menu NovaLocacao
        menuNovaLocacao.add(miCadastraLocacao);

        //adicionando os itens ao menu NovaReserva
        menuNovaReserva.add(miCadastraReserva);
        
        //adicionando os itens ao menu ConsultaSalario
        menuCalculaSalario.add(miConsultaSalario);

        //adicionando os itens ao menu ConsultaComissao
        menuCalculaComissao.add(miConsultaComissao);

        //adicionando o menu barra
        tela_funcionario.setJMenuBar(menuBarra);
        liberaMenu();//deixa o menu visivel
    }
   
    public void liberaMenu(){
        menuNovaLocacao.setEnabled(true);
        menuNovaReserva.setEnabled(true);
        menuCalculaComissao.setEnabled(true);
        menuCalculaSalario.setEnabled(true);
        menuBarra.setVisible(true);
    }

        public void desenhaSplitPanel(){
        
        JPanel painel_esq = new JPanel();
        JPanel painel_dir = new JPanel();
        
        //inicialização da lista do painel esquerda, onde apresenta o que o administrador pode listar
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Locações");
        modeloLista.addElement("Reservas");
        JList<String> lista = new JList<>(modeloLista);
        
        //inicialização do addListSelectionListener
        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    
                    if("LOCAÇÕES".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Locacao> model = new DefaultListModel<>();
                        jlLocacao =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlLocacao));
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Locações"));
                    }
                    
                    if("RESERVAS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Reserva> model = new DefaultListModel<>();
                        jlReserva =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlReserva));
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Reservas"));
                    }
                }
            }
        });
        
        JScrollPane scroll= new JScrollPane (lista);
        painel_esq.add(scroll); //adiciona a lista co rolamento ao painel esquerda
        
        //criação da borda dos paineis que vão compor o SplitPane
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem"));
        
        //definição do tamanho dos paineis
        //painel_dir.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        //painel_esq.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
      
     
        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        tela_funcionario.add(sp); //adiciona o splitPane ao JFrame

    }
}
