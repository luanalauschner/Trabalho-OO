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
    private JMenu menuVoltar;
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
        menuVoltar = new JMenu("Página Inicial");
        
        //inicializando os itens do menu cadastro
        miCadastraCliente = new JMenuItem("Cadastra Cliente");
        miCadastraCarro = new JMenuItem("Cadastra Carro");
        miCadastraFunc = new JMenuItem("Cadastra Funcionário");
        miCadastraFilial = new JMenuItem("Cadastra Filial");

        //defininca ação do menuVoltar
        menuVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenha();
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
        menuBarra.add(menuVoltar);
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
    
    public void desenhaSplitPanel(){
        
        painel_esq = new JPanel();
        painel_dir = new JPanel();
        
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

        painel.setLayout(new BorderLayout());
        painel.add(formulario_carro, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaCarro(this));

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

        painel.setLayout(new BorderLayout());
        painel.add(formulario_filial, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaFilial(this));

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

            painel_dir.add(tfNome_Cliente);
            painel_dir.add(tfCpf_Cliente);
            painel_dir.add(tfTelefone_Cliente);
            painel_dir.add(tfHabilitacao_Cliente);
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
            
            painel_dir.add(tfMarca);
            painel_dir.add(tfModelo);
            painel_dir.add(tfAno);
            painel_dir.add(tfPlaca);
            painel_dir.add(tfPrecoDiaria);

        }
        
    }
    
    public void atualizaPainelDir_Funcionarios(){

        int selectedIndex = jlFunc.getSelectedIndex();
        JTextField tfCargo, tfId;

        if(selectedIndex != -1){
            DefaultListModel<Funcionario> model = (DefaultListModel<Funcionario>)jlFunc.getModel();
            Funcionario func = model.get(selectedIndex);

            tfNome_Func.setText(func.getNome());
            tfCpf_Func.setText(func.getCpf());
            tfCargo = new JTextField(func.getCargo());
            tfSalario.setText(String.valueOf(func.getSalario()));
            tfId = new JTextField(String.valueOf(func.getId()));

            painel_dir.add(tfNome_Func);
            painel_dir.add(tfCpf_Func);
            painel_dir.add(tfCargo);
            painel_dir.add(tfSalario);
            painel_dir.add(tfId);

        }
        
    }
    
    public void atualizaPainelDir_Locacoes(){

        int selectedIndex = jlLocacao.getSelectedIndex();


        JTextField tfId, tfNome, tfCarro, tfDataInicio, tfDataFim, tfValidade, tfLocador;

        if(selectedIndex != -1){
            DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
            Locacao l = model.get(selectedIndex);

            //conversão do tipo date para String
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            tfId = new JTextField(String.valueOf(l.getId()));
            tfNome = new JTextField(l.getLocatario().getNome());
            tfCarro = new JTextField(l.getCarro_alugado().getPlaca());
            tfDataInicio = new JTextField(formato.format(l.getDataInicio()));
            tfDataFim = new JTextField(formato.format(l.getDataFim()));
            tfValidade = new JTextField(String.valueOf(Locacao.validade(l)));
            tfLocador = new JTextField(l.getLocador().getNome());

            painel_dir.add(tfId);
            painel_dir.add(tfNome);
            painel_dir.add(tfCarro);
            painel_dir.add(tfDataInicio);
            painel_dir.add(tfDataFim);
            painel_dir.add(tfValidade);
            painel_dir.add(tfLocador);
        }
        
    }
    
    public void atualizaPainelDir_Reservas(){

        int selectedIndex = jlReserva.getSelectedIndex();

        JTextField tfId, tfNome, tfCarro, tfDataInicio, tfDataFim;

        if(selectedIndex != -1){
            DefaultListModel<Reserva> model = (DefaultListModel<Reserva>)jlReserva.getModel();
            Reserva l = model.get(selectedIndex);

            //conversão do tipo date para String
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            tfId = new JTextField(String.valueOf(l.getId()));
            tfNome = new JTextField(l.getCliente().getNome());
            tfCarro = new JTextField(l.getCarro().getPlaca());
            tfDataInicio = new JTextField(formato.format(l.getDataInicio()));
            tfDataFim = new JTextField(formato.format(l.getDataFim()));

            painel_dir.add(tfId);
            painel_dir.add(tfNome);
            painel_dir.add(tfCarro);
            painel_dir.add(tfDataInicio);
            painel_dir.add(tfDataFim);

        }
        
    }
    
    public void atualizaPainelDir_Filiais(){

        int selectedIndex = jlFilial.getSelectedIndex();

        JTextField tfId, tfNome;

        if(selectedIndex != -1){
            DefaultListModel<Filial> model = (DefaultListModel<Filial>)jlFilial.getModel();
            Filial f = model.get(selectedIndex);

            tfId = new JTextField(String.valueOf(f.getId()));
            tfNome = new JTextField(f.getGerente().getNome());
            tfLogadouro.setText(f.getLogadouro());
            tfNumero.setText(String.valueOf(f.getNumero()));
            tfCidade.setText(f.getCidade());
            tfEstado.setText(f.getEstado());
            tfCep.setText(f.getCep());

            painel_dir.add(tfId);
            painel_dir.add(tfNome);
            painel_dir.add(tfLogadouro);
            painel_dir.add(tfNumero);
            painel_dir.add(tfCidade);
            painel_dir.add(tfEstado);
            painel_dir.add(tfCep);

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

        painel_esq = new JPanel();
        painel_dir = new JPanel();
        
        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>)jlClientes.getModel();
        JList<Cliente> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaClientes(this));

        painel_esq.add(new JScrollPane(modelList)); //adiciona a lista co rolamento ao painel esquerda
        
        //criação da borda dos paineis que vão compor o SplitPane
        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de clientes"));
        
        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        tela_adm.add(sp); //adiciona o splitPane ao JFrame
    }

    public void desenhaListaFuncionario(){

        painel_esq = new JPanel();
        painel_dir = new JPanel();

        DefaultListModel<Funcionario> model = (DefaultListModel<Funcionario>)jlFunc.getModel();
        JList<Funcionario> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaFuncionarios(this));

        painel_esq.add(new JScrollPane(modelList));

        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de funcionários"));

        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        tela_adm.add(sp); //adiciona o splitPane ao JFrame
    }

    public void desenhaListaFilial(){

        painel_esq = new JPanel();
        painel_dir = new JPanel();

        DefaultListModel<Filial> model = (DefaultListModel<Filial>)jlFilial.getModel();
        JList<Filial> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaFiliais(this));

        painel_esq.add(new JScrollPane(modelList));

        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de filiais"));

        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        tela_adm.add(sp); //adiciona o splitPane ao JFrame

    }

    public void desenhaListaCarro(){

        painel_esq = new JPanel();
        painel_dir = new JPanel();

        DefaultListModel<Carro> model = (DefaultListModel<Carro>)jlCarro.getModel();
        JList<Carro> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaCarros(this));

        painel_esq.add(new JScrollPane(modelList));

        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de carros"));

        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        tela_adm.add(sp); //adiciona o splitPane ao JFrame

    }

    public void desenhaListaReserva(){

        painel_esq = new JPanel();
        painel_dir = new JPanel();

        DefaultListModel<Reserva> model = (DefaultListModel<Reserva>)jlReserva.getModel();
        JList<Reserva> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaReservas(this));

        painel_esq.add(new JScrollPane(modelList));

        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de reservas"));

        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        tela_adm.add(sp); //adiciona o splitPane ao JFrame

    }

    public void desenhaListaLocacao(){

        painel_esq = new JPanel();
        painel_dir = new JPanel();

        DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
        JList<Locacao> modelList = new JList<>(model);

        modelList.addListSelectionListener(new SelecionaLocacoes(this));

        painel_esq.add(new JScrollPane(modelList));

        painel_esq.setBorder(BorderFactory.createTitledBorder("Listagem de locacoes"));

        //inicialização do splitPane
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painel_esq, painel_dir);
        
        sp.setDividerLocation(250); //confira o local inicial da divisão
        tela_adm.add(sp); //adiciona o splitPane ao JFrame
    }
}