/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.view;

//importação do package contorller
import org.example.controller.*;
import org.example.controller.controllerAdiciona.AdicionaCliente;
import org.example.controller.controllerAdiciona.AdicionaCarro;
import org.example.controller.controllerAdiciona.AdicionaFilial;
import org.example.controller.controllerAdiciona.AdicionaFuncionario;

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

    //inicialização das listas que vão ser exibidas na tela
    private JList<Cliente> jlClientes;
    private JList<Carro> jlCarro;
    private JList<Locacao> jlLocacao;
    private JList<Filial> jlFilial;
    private JList<Funcionario> jlFunc;
    private JList<Reserva> jlReserva;
    
    //inicialização do menu
    private JMenuBar menuBarra;
    private JMenu menuCadastra;
    private JMenu menuConsulta;
    private JMenuItem miCadastraCliente, miCadastraCarro, miCadastraFunc, miCadastraFilial;
    private JMenuItem miConsultaCliente, miConsultaFunc, miConsultaCarro, miConsultaFilial, miConsultaLocacoes, miConsultaReservas;
   
    //inicialização dos textFields do cliente e do funcionário, utilizados no formulário para o cadastro
    private JTextField tfNome_Cliente, tfNome_Func;
    private JTextField tfCpf_Cliente, tfCpf_Func;
    private JTextField tfTelefone_Cliente, tfTelefone_Func;
    private JTextField tfHabilitacao_Cliente;
    private JTextField tfSalario;

    //inicialização dos textFields do carro
    private JTextField tfMarca, tfCor, tfAno, tfPlaca, tfPrecoDiaria, tfModelo;

    //inicialização do combo box para seleção do cargo do funcionário
    private JComboBox jcLista_cargos;
    
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

        //definindo a função do addActionListener para os menu itens do menu cadastro
        miCadastraCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaFormulario_Cliente();
            }
        });
        
        miCadastraFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaFormulario_Func();
            }
        });


        miCadastraCarro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                desenhaFormulario_carro();
            }
        });

        miCadastraFilial.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaFormulario_filial();
            }
        });

        
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
                        painel_dir.add(new JScrollPane(jlClientes));
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
                    }
                    
                    if("CARROS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Carro> model = new DefaultListModel<>();
                        jlCarro =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlCarro));
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Carros"));
                    }
                    
                    if("FUNCIONÁRIOS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Funcionario> model = new DefaultListModel<>();
                        jlFunc =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlFunc));
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Funcionários"));
                    }
                    
                    if("FILIAIS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Filial> model = new DefaultListModel<>();
                        jlFilial =  new JList<>(model);
                        painel_dir.add(new JScrollPane(jlFilial));
                        painel_dir.setBorder(BorderFactory.createTitledBorder("Lista de Filiais"));
                    }
                    
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
        tela_adm.add(sp); //adiciona o splitPane ao JFrame
    
    }

    public void desenhaFormulario_Cliente(){

        //criação do painel
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Formulário novo cliente"));

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
        tfNome_Cliente = new JTextField(20);
        tfCpf_Cliente = new JTextField(20);
        tfTelefone_Cliente = new JTextField(20);
        tfHabilitacao_Cliente = new JTextField(20);

        painelField.add(tfNome_Cliente);
        painelField.add(tfCpf_Cliente);
        painelField.add(tfTelefone_Cliente);
        painelField.add(tfHabilitacao_Cliente);


        formulario_cliente.add(painelLabel);
        formulario_cliente.add(painelField);

        painel.setLayout(new BorderLayout());
        painel.add(formulario_cliente, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaCliente(this));

    }

    public void desenhaFormulario_Func(){

        //criação do painel
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Formulário novo funcionário"));

        //criação do painel que contém o formulário
        JPanel formulario_func = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Nome"));
        painelLabel.add(new JLabel("CPF"));
        painelLabel.add(new JLabel("Telefone"));
        painelLabel.add(new JLabel("Cargo"));
        painelLabel.add(new JLabel("Salário"));

        //implementação que contém os espaços que o usuário preenche os dados do funcionário
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfNome_Func = new JTextField(20);
        tfCpf_Func = new JTextField(20);
        tfTelefone_Func = new JTextField(20);
        tfSalario = new JTextField(20);

        //inicialização do jComboBox para seleção do cargo
        String[] list = {"Locador", "Gerente"};
        jcLista_cargos = new JComboBox(list);

        //inserção do elementos TextField
        painelField.add(tfNome_Func);
        painelField.add(tfCpf_Func);
        painelField.add(tfTelefone_Func);
        painelField.add(jcLista_cargos);
        painelField.add(tfSalario);

        formulario_func.add(painelLabel);
        formulario_func.add(painelField);

        painel.setLayout(new BorderLayout());
        painel.add(formulario_func, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaFuncionario(this));
    }

    public void desenhaFormulario_carro(){

        //criação do painel
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Formulário novo carro"));

        //criação do painel que contém o formulário
        JPanel formulario_carro = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Marca"));
        painelLabel.add(new JLabel("Modelo"));
        painelLabel.add(new JLabel("Placa"));
        painelLabel.add(new JLabel("Ano"));
        painelLabel.add(new JLabel("Preço diária"));

        //implementação que contém os espaços que o usuário preenche os dados do cliente
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfMarca = new JTextField(20);
        tfModelo = new JTextField(20);
        tfAno = new JTextField(20);
        tfCor = new JTextField(20);
        tfPlaca = new JTextField(20);
        tfPrecoDiaria = new JTextField(20);

        //inserção do elementos TextField
        painelField.add(tfMarca);
        painelField.add(tfModelo);
        painelField.add(tfAno);
        painelField.add(tfCor);
        painelField.add(tfPlaca);
        painelField.add(tfPrecoDiaria);

        painel.setLayout(new BorderLayout());
        painel.add(formulario_carro, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaCarro(this));

    }

    public void desenhaFormulario_filial(){

    }
    
    public void atualizaPainelDir_Clientes(){


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
    
    public void cadastraCliente(){
        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>)jlClientes.getModel();
        try{
            model.addElement(new Cliente(tfHabilitacao_Cliente.getText(), true, tfNome_Cliente.getText(), tfTelefone_Cliente.getText(), tfCpf_Cliente.getText()));
            JOptionPane.showMessageDialog(tela_adm, "Cliente cadastrado com sucesso!");
        } catch(FormatoException e){
            JOptionPane.showMessageDialog(tela_adm, "O telefone ou o CPf apresenta um formato inválido!");
        }
    }

    public void cadastraCarro(){
        DefaultListModel<Carro> model = (DefaultListModel<Carro>)jlCarro.getModel();
        try{
            model.addElement(new Carro(Integer.parseInt(tfAno.getText()), tfPlaca.getText(), tfCor.getText(), tfMarca.getText(), tfModelo.getText(), true, Double.parseDouble(tfPrecoDiaria.getText())));
            JOptionPane.showMessageDialog(tela_adm, "Carro cadastrado com sucesso!");
        } catch(FormatoException e){
            JOptionPane.showMessageDialog(tela_adm, "Placa no formato errado");
        }

    }

    public void cadastraFunc(){
        DefaultListModel<Funcionario> model = (DefaultListModel<Funcionario>)jlFunc.getModel();
        try{
            double d = Double.parseDouble(tfSalario.getText());
            model.addElement(new Funcionario((String) jcLista_cargos.getSelectedItem(), d, tfNome_Func.getText(), tfTelefone_Func.getText(), tfCpf_Func.getText()));
            JOptionPane.showMessageDialog(tela_adm, "Funcionário cadastrado com sucesso!");
        } catch(FormatoException e){
            JOptionPane.showMessageDialog(tela_adm, "O telefone ou o CPf apresenta um formato inválido!");
        }
    }

    public void cadastraFilial(){

    }
}
