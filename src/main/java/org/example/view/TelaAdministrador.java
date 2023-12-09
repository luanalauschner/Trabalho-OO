/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.view;

//importação do package contorller
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

    private JPanel panel;
    
    //inicialização do menu
    private JMenuBar menuBarra;
    private JMenu menuCadastra;
    private JMenu menuConsulta;
    private JMenuItem menuVoltar;
    private JMenu menuPaginaP;
    private JMenuItem miCadastraCliente, miCadastraCarro, miCadastraFunc, miCadastraFilial;
    private JMenuItem miConsultaCliente, miConsultaFunc, miConsultaCarro, miConsultaFilial, miConsultaLocacoes, miConsultaReservas;
   
    //inicialização dos textFields do cliente e do funcionário, utilizados no formulário para o cadastro
    private JTextField tfNome_Cliente, tfNome_Func;
    private JTextField tfCpf_Cliente, tfCpf_Func;
    private JTextField tfTelefone_Cliente, tfTelefone_Func;
    private JTextField tfHabilitacao_Cliente;
    private JTextField tfSalario;
    private JTextField tfCargo, tfId;
    private JTextField tfNome_filial;
    private JTextField tfNome_reserva, tfCarro, tfDataInicio, tfDataFim, tfValidade, tfLocador;

    //inicialização dos textFields do carro
    private JTextField tfMarca, tfCor, tfAno, tfPlaca, tfPrecoDiaria, tfModelo;

    //inicialização dos textFields da filial
    private JTextField tfLogadouro, tfCidade, tfEstado, tfCep, tfNumero;
    
    //inicialização do combo box para seleção do gerente
    private JComboBox<Funcionario> jcGerentes;

    //inicialização do combo box para seleção do cargo do funcionário
    private JComboBox<String> jcLista_cargos;

    //definição dos paineis usados no ScrollPanel
    private JPanel painel_esq, painel_dir;
    
    public void desenha(){

        tela_adm = new JFrame("Espaço do admnistrador");
        tela_adm.setSize(WIDTH, HEIGHT);
        tela_adm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela_adm.setLocationRelativeTo(null);
        tela_adm.setVisible(true);
        tela_adm.setLayout(new BorderLayout());
        
        inicializaComponentes();
        desenhaLista();
        
        tela_adm.pack();
    }
    
    public void inicializaComponentes(){
        
        //inicializando os menus
        menuBarra = new JMenuBar();
        menuCadastra = new JMenu("Cadastro");
        menuCadastra.setMnemonic('C');
        menuConsulta = new JMenu("Consulta");
        menuConsulta.setMnemonic('L');
        menuPaginaP = new JMenu("Página Inicial");

        //inicializando o menu para voltar para a página inicial
        menuVoltar = new JMenuItem("Voltar para página inicial");
        
        //inicializando os itens do menu cadastro
        miCadastraCliente = new JMenuItem("Cadastra Cliente");
        miCadastraCarro = new JMenuItem("Cadastra Carro");
        miCadastraFunc = new JMenuItem("Cadastra Funcionário");
        miCadastraFilial = new JMenuItem("Cadastra Filial");

        //defininca ação do menuVoltar
        menuVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaLista();
            } 
        });

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

        //definindo a ação do actionListener do menu item do menu consulta ou listagem
        miConsultaCliente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                desenhaListaClientes();
            }
        });

        miConsultaFunc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                desenhaListaFuncionario();
            }
        });

        miConsultaCarro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                desenhaListaCarro();
            }
        });

        miConsultaFilial.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                desenhaListaFilial();
            }
        });

        miConsultaLocacoes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                desenhaListaLocacao();
            }
        });

        miConsultaReservas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                desenhaListaReserva();
            }
        });

        //adicionando  o menuItem voltar ao menu página inicial
        menuPaginaP.add(menuVoltar);
        
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
        menuBarra.add(menuPaginaP);
        menuBarra.add(menuCadastra);
        menuBarra.add(menuConsulta);
        
        //adicionando o menu barra
        tela_adm.setJMenuBar(menuBarra);
        liberaMenu();//deixa o menu visivel
    }
    
    public void liberaMenu(){
        menuCadastra.setEnabled(true);
        menuConsulta.setEnabled(true);
        menuVoltar.setEnabled(true);
        menuBarra.setVisible(true);
    }

    
    public void desenhaLista(){

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Listagem"));
        painel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
        painel.setLayout(new BorderLayout());

        JPanel painel2 = new JPanel();
        painel2.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
        painel2.setLayout(new BorderLayout());
        
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
                    if("CLIENTES".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Cliente> model = new DefaultListModel<>();
                        jlClientes =  new JList<>(model);
                        painel2.add(new JScrollPane(jlClientes));
                        painel2.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
                    }
                    
                    if("CARROS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Carro> model = new DefaultListModel<>();
                        jlCarro =  new JList<>(model);
                        painel2.add(new JScrollPane(jlCarro));
                        painel2.setBorder(BorderFactory.createTitledBorder("Lista de Carros"));
                    }
                    
                    if("FUNCIONÁRIOS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Funcionario> model = new DefaultListModel<>();
                        jlFunc =  new JList<>(model);
                        painel2.add(new JScrollPane(jlFunc));
                        painel2.setBorder(BorderFactory.createTitledBorder("Lista de Funcionários"));
                    }
                    
                    if("FILIAIS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Filial> model = new DefaultListModel<>();
                        jlFilial =  new JList<>(model);
                        painel2.add(new JScrollPane(jlFilial));
                        painel2.setBorder(BorderFactory.createTitledBorder("Lista de Filiais"));
                    }
                    
                    if("LOCAÇÕES".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Locacao> model = new DefaultListModel<>();
                        jlLocacao =  new JList<>(model);
                        painel2.add(new JScrollPane(jlLocacao));
                        painel2.setBorder(BorderFactory.createTitledBorder("Lista de Locações"));
                    }
                    
                    if("RESERVAS".equals(lista.getSelectedValue().toUpperCase())){
                        DefaultListModel<Reserva> model = new DefaultListModel<>();
                        jlReserva =  new JList<>(model);
                        painel2.add(new JScrollPane(jlReserva));
                        painel2.setBorder(BorderFactory.createTitledBorder("Lista de Reservas"));
                    }
                }
            }
        });
        
        JScrollPane scroll= new JScrollPane (lista);
      
     
        //inicialização do splitPane
        painel.add(scroll, BorderLayout.CENTER);
        painel.setVisible(true);
        painel.setVisible(true);

        tela_adm.getContentPane().add(painel, BorderLayout.WEST);
        tela_adm.getContentPane().add(painel2, BorderLayout.CENTER);
    }

    public void desenhaFormulario_Cliente(){

        //criação do painel
        JPanel painel3 = new JPanel();
        painel3.setBorder(BorderFactory.createTitledBorder("Formulário novo cliente"));

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

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaCliente(this));

        painel3.setLayout(new BorderLayout());
        painel3.add(formulario_cliente, BorderLayout.NORTH);

        JPanel principal = new JPanel();

        principal.add(painel3, BorderLayout.CENTER);
        principal.add(btnAdicionar, BorderLayout.SOUTH);

        tela_adm.add(principal);
        tela_adm.pack();
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

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaFuncionario(this));

        painel.setLayout(new BorderLayout());
        painel.add(btnAdicionar);
        painel.add(formulario_func, BorderLayout.CENTER);

        JPanel principal = new JPanel();

        principal.add(painel, BorderLayout.NORTH);
        principal.add(btnAdicionar, BorderLayout.SOUTH);

        
        principal.setVisible(true);
        //splitPanel.setVisible(false);

        tela_adm.add(principal);
        tela_adm.pack();


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

        //implementação que contém os espaços que o usuário preenche os dados do carro
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

        formulario_carro.add(painelLabel);
        formulario_carro.add(painelField);

        painel.add(formulario_carro, BorderLayout.SOUTH);
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaCarro(this));

        JPanel principal = new JPanel();

        principal.add(painel, BorderLayout.CENTER);
        principal.add(btnAdicionar, BorderLayout.SOUTH);

        tela_adm.add(principal);
        tela_adm.pack();
    }


    public void desenhaFormulario_filial(){

        //criação do painel
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Formulário nova filial"));

        //criação do painel que contém o formulário
        JPanel formulario_filial = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Logadouro"));
        painelLabel.add(new JLabel("Número"));
        painelLabel.add(new JLabel("Cidade"));
        painelLabel.add(new JLabel("Estado"));
        painelLabel.add(new JLabel("CEP"));
        painelLabel.add(new JLabel("Gerente"));

        //implementação que contém os espaços que o usuário preenche os dados do cliente
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfLogadouro = new JTextField(20);
        tfNumero = new JTextField(20);
        tfCidade = new JTextField(20);
        tfCep = new JTextField(20);
        tfEstado = new JTextField(20);

        //implementação do combo box dos funcionários que são gerentes
        DefaultComboBoxModel<Funcionario> model = new DefaultComboBoxModel<>();
        for(Funcionario f: Administrador.getFuncionarios()){
            if(f.getCargo().toUpperCase().equals("GERENTE"))
                model.addElement(f);
        }

        jcGerentes = new JComboBox<>(model);
        

        //inserção dos elementos textField e do combo box
        painelField.add(tfLogadouro);
        painelField.add(tfNumero);
        painelField.add(tfCidade);
        painelField.add(tfCep);
        painelField.add(tfEstado);
        painelField.add(jcGerentes);
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaFilial(this));
        painel.add(formulario_filial);
        
        JPanel principal = new JPanel();

        principal.add(painel, BorderLayout.CENTER);
        principal.add(btnAdicionar, BorderLayout.SOUTH);

        tela_adm.add(principal);
        tela_adm.pack();
    }

    
    public void atualizaPainelDir_Clientes(){

        int selectedIndex = jlClientes.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Cliente> model = (DefaultListModel<Cliente>)jlClientes.getModel();
            Cliente cliente = model.get(selectedIndex);

            tfNome_Cliente.setText(cliente.getNome());
            tfCpf_Cliente.setText(cliente.getCpf());
            tfTelefone_Cliente.setText(cliente.getTelefone());
            tfHabilitacao_Cliente.setText(cliente.getHabilitacao());
        }
        
    }
    
    public void atualizaPainelDir_Carros(){

        int selectedIndex = jlCarro.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Carro> model = (DefaultListModel<Carro>)jlCarro.getModel();
            Carro carro = model.get(selectedIndex);

            tfMarca.setText(carro.getMarca());
            tfModelo.setText(carro.getModelo());
            tfAno.setText(String.valueOf(carro.getAno()));
            tfPlaca.setText(carro.getPlaca());
            tfPrecoDiaria.setText(String.valueOf(carro.getPreco_diaria()));

        }
    }
    
    public void atualizaPainelDir_Funcionarios(){

        int selectedIndex = jlFunc.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Funcionario> model = (DefaultListModel<Funcionario>)jlFunc.getModel();
            Funcionario func = model.get(selectedIndex);

            tfNome_Func.setText(func.getNome());
            tfCpf_Func.setText(func.getCpf());
            tfCargo = new JTextField(func.getCargo());
            tfSalario.setText(String.valueOf(func.getSalario()));
            tfId = new JTextField(String.valueOf(func.getId()));

        }
        
    }
    
    public void atualizaPainelDir_Locacoes(){

        int selectedIndex = jlLocacao.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
            Locacao l = model.get(selectedIndex);

            //conversão do tipo date para String
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            tfId = new JTextField(String.valueOf(l.getId()));
            tfNome_Cliente = new JTextField(l.getLocatario().getNome());
            tfCarro = new JTextField(l.getCarro_alugado().getPlaca());
            tfDataInicio = new JTextField(formato.format(l.getDataInicio()));
            tfDataFim = new JTextField(formato.format(l.getDataFim()));
            tfValidade = new JTextField(String.valueOf(Locacao.validade(l)));
            tfLocador = new JTextField(l.getLocador().getNome());
        }
    }
    
    public void atualizaPainelDir_Reservas(){

        int selectedIndex = jlReserva.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Reserva> model = (DefaultListModel<Reserva>)jlReserva.getModel();
            Reserva l = model.get(selectedIndex);

            //conversão do tipo date para String
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            tfId = new JTextField(String.valueOf(l.getId()));
            tfNome_reserva = new JTextField(l.getCliente().getNome());
            tfCarro = new JTextField(l.getCarro().getPlaca());
            tfDataInicio = new JTextField(formato.format(l.getDataInicio()));
            tfDataFim = new JTextField(formato.format(l.getDataFim()));

        }
    }
    
    public void atualizaPainelDir_Filiais(){

        int selectedIndex = jlFilial.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Filial> model = (DefaultListModel<Filial>)jlFilial.getModel();
            Filial f = model.get(selectedIndex);

            tfId = new JTextField(String.valueOf(f.getId()));
            tfNome_filial = new JTextField(f.getGerente().getNome());
            tfLogadouro.setText(f.getLogadouro());
            tfNumero.setText(String.valueOf(f.getNumero()));
            tfCidade.setText(f.getCidade());
            tfEstado.setText(f.getEstado());
            tfCep.setText(f.getCep());
        }
        
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
            }catch(FormatoException e){
                System.out.println("Formato da placa inválida!");
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
        DefaultListModel<Filial> model = (DefaultListModel<Filial>)jlFilial.getModel();
        try{
            Funcionario f = (Funcionario)jcGerentes.getSelectedItem();
            int i = Integer.parseInt(tfNumero.getText());
            model.addElement(new Filial(f, tfLogadouro.getText(), i, tfCidade.getText(), tfEstado.getText(), tfCep.getText()));
            JOptionPane.showMessageDialog(tela_adm, "Filial cadastrado com sucesso!");
        }catch(FormatoException e){
            JOptionPane.showMessageDialog(tela_adm, "CEP no formato inválido!");
        }
    }

    public void desenhaListaClientes(){

        JPanel formulario = new JPanel();
        JPanel painel_esq = new JPanel();
        JPanel painel_dir = new JPanel();
        JPanel painelField = new JPanel();

        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>)jlClientes.getModel();
        JList<Cliente> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaClientes(this));
        painel_esq.add(new JScrollPane(modelList));
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de funcionários"));

        painel_dir.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painel_dir.add(new JLabel("Nome Cliente"));
        painel_dir.add(new JLabel("CPF"));
        painel_dir.add(new JLabel("Telefone"));
        painel_dir.add(new JLabel("Habilitação"));

        tfNome_Cliente = new JTextField(20);
        tfCpf_Cliente = new JTextField(20);
        tfTelefone_Cliente = new JTextField(20);
        tfHabilitacao_Cliente = new JTextField(20);

        painel_dir.add(tfNome_Cliente);
        painel_dir.add(tfCpf_Cliente);
        painel_dir.add(tfTelefone_Cliente);
        painel_dir.add(tfHabilitacao_Cliente);

        formulario.add(painel_dir);
        formulario.add(painelField);

        tela_adm.getContentPane().add(formulario, BorderLayout.CENTER);
    }

    public void desenhaListaFuncionario(){

        JPanel formulario = new JPanel();
        JPanel painel_esq = new JPanel();
        JPanel painel_dir = new JPanel();
        JPanel painelField = new JPanel();

        DefaultListModel<Funcionario> model = (DefaultListModel<Funcionario>)jlFunc.getModel();
        JList<Funcionario> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaFuncionarios(this));
        painel_esq.add(new JScrollPane(modelList));
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de funcionários"));

        painel_dir.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painel_dir.add(new JLabel("Nome Funcionário"));
        painel_dir.add(new JLabel("CPF"));
        painel_dir.add(new JLabel("Cargo"));
        painel_dir.add(new JLabel("Salário"));
        painel_dir.add(new JLabel("ID"));

        tfId = new JTextField(20);
        tfNome_Func = new JTextField(20);
        tfCpf_Func= new JTextField(20);
        tfSalario = new JTextField(20);
        tfCargo = new JTextField(20);

        painelField.add(tfNome_Func);
        painelField.add(tfCpf_Func);
        painelField.add(tfSalario);
        painelField.add(tfCargo);
        painelField.add(tfId);

        formulario.add(painel_dir);
        formulario.add(painelField);

        tela_adm.getContentPane().add(formulario, BorderLayout.CENTER);
    }    

    public void desenhaListaFilial(){

        JPanel formulario = new JPanel();
        JPanel painel_esq = new JPanel();
        JPanel painel_dir = new JPanel();
        JPanel painelField = new JPanel();

        DefaultListModel<Filial> model = (DefaultListModel<Filial>)jlFilial.getModel();
        JList<Filial> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaFiliais(this));
        painel_esq.add(new JScrollPane(modelList));
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de filiais"));

        painel_dir.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painel_dir.add(new JLabel("ID"));
        painel_dir.add(new JLabel("Nome"));
        painel_dir.add(new JLabel("Logadouro"));
        painel_dir.add(new JLabel("Número"));
        painel_dir.add(new JLabel("Cidade"));
        painel_dir.add(new JLabel("Estado"));
        painel_dir.add(new JLabel("CEP"));

        tfId = new JTextField(20);
        tfNome_filial = new JTextField(20);
        tfLogadouro = new JTextField(20);
        tfNumero = new JTextField(20);
        tfCidade = new JTextField(20);
        tfEstado = new JTextField(20);
        tfCep = new JTextField(20);

        painelField.add(tfId);
        painelField.add(tfNome_filial);
        painelField.add(tfLogadouro);
        painelField.add(tfNumero);
        painelField.add(tfCidade);
        painelField.add(tfEstado);
        painelField.add(tfCep);

        formulario.add(painel_dir);
        formulario.add(painelField);

        tela_adm.getContentPane().add(formulario, BorderLayout.CENTER);

    }

    public void desenhaListaCarro(){

        JPanel formulario = new JPanel();
        JPanel painel_esq = new JPanel();
        JPanel painel_dir = new JPanel();
        JPanel painelField = new JPanel();

        DefaultListModel<Carro> model = (DefaultListModel<Carro>)jlCarro.getModel();
        JList<Carro> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaCarros(this));
        painel_esq.add(new JScrollPane(modelList));
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de carros"));

        painel_dir.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painel_dir.add(new JLabel("Placa"));
        painel_dir.add(new JLabel("Marca"));
        painel_dir.add(new JLabel("Modelo"));
        painel_dir.add(new JLabel("Cor"));
        painel_dir.add(new JLabel("Ano"));
        painel_dir.add(new JLabel("Preço da diária"));

        tfPlaca = new JTextField(20);
        tfModelo = new JTextField(20);
        tfMarca = new JTextField(20);
        tfCor = new JTextField(20);
        tfAno = new JTextField(20);
        tfPrecoDiaria = new JTextField(20);

        painelField.add(tfPlaca);
        painelField.add(tfModelo);
        painelField.add(tfMarca);
        painelField.add(tfCor);
        painelField.add(tfAno);
        painelField.add(tfPrecoDiaria);

        formulario.add(painel_dir);
        formulario.add(painelField);

        tela_adm.getContentPane().add(formulario, BorderLayout.CENTER);


    }

    public void desenhaListaReserva(){
        
        JPanel formulario = new JPanel();
        JPanel painel_esq = new JPanel();
        JPanel painel_dir = new JPanel();
        JPanel painelField = new JPanel();

        DefaultListModel<Reserva> model = (DefaultListModel<Reserva>)jlReserva.getModel();
        JList<Reserva> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaReservas(this));
        painel_esq.add(new JScrollPane(modelList));
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de locações"));

        painel_dir.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painel_dir.add(new JLabel("ID"));
        painel_dir.add(new JLabel("Nome Cliente"));
        painel_dir.add(new JLabel("Placa do carro"));
        painel_dir.add(new JLabel("Data Início"));
        painel_dir.add(new JLabel("Data Final"));

        tfId = new JTextField(20);
        tfNome_Cliente = new JTextField(20);
        tfPlaca = new JTextField(20);
        tfDataInicio = new JTextField(20);
        tfDataFim = new JTextField(20);

        painelField.add(tfId);
        painelField.add(tfNome_Cliente);
        painelField.add(tfPlaca);
        painelField.add(tfDataInicio);
        painelField.add(tfDataFim);

        formulario.add(painel_dir);
        formulario.add(painelField);

        tela_adm.getContentPane().add(formulario, BorderLayout.CENTER);
        
    }

    public void desenhaListaLocacao(){

        JPanel formulario = new JPanel();
        JPanel painel_esq = new JPanel();
        JPanel painel_dir = new JPanel();
        JPanel painelField = new JPanel();

        DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
        JList<Locacao> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaLocacoes(this));
        painel_esq.add(new JScrollPane(modelList));
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de locações"));

        painel_dir.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painel_dir.add(new JLabel("ID"));
        painel_dir.add(new JLabel("Nome Cliente"));
        painel_dir.add(new JLabel("Placa do carro"));
        painel_dir.add(new JLabel("Data Início"));
        painel_dir.add(new JLabel("Data Final"));
        painel_dir.add(new JLabel("Valor"));
        painel_dir.add(new JLabel("Locador"));

        tfId = new JTextField(20);
        tfNome_Cliente = new JTextField(20);
        tfPlaca = new JTextField(20);
        tfDataInicio = new JTextField(20);
        tfDataFim = new JTextField(20);
        tfValidade = new JTextField(20);
        tfLocador = new JTextField(20);

        painelField.add(tfId);
        painelField.add(tfNome_Cliente);
        painelField.add(tfPlaca);
        painelField.add(tfDataInicio);
        painelField.add(tfDataFim);
        painelField.add(tfValidade);
        painelField.add(tfLocador);

        formulario.add(painel_dir);
        formulario.add(painelField);

        tela_adm.getContentPane().add(formulario, BorderLayout.CENTER);
    }
}