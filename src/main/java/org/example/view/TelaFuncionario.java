package org.example.view;

import org.example.controller.*;
import org.example.controller.controllerAdiciona.AdicionaFuncionario;
import org.example.controller.controllerAdiciona.AdicionaLocacao;
import org.example.controller.controllerAdiciona.AdicionaReserva;
import org.example.controller.controllerAtualiza.AtualizaFuncionario;
import org.example.controller.controllerCancela.CancelaLocacao;
import org.example.controller.controllerRemove.RemoveLocacao;
import org.example.controller.controllerRenova.RenovaLocacao;
import org.example.exception.*;

//import do controller model
import org.example.model.*;

//import da biblioteca swing
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

 
public class TelaFuncionario {

    private JFrame tela_funcionario;
    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    private Funcionario funcionario = null;

    private JList<Locacao> jlLocacao;
    private JList<Reserva> jlReserva;

    //inicialização do menu
    private JMenuBar menuBarra;
    private JMenu menuVoltar;
    private JMenu menuAtualiza;


    //inicializacao dos botões utilizados
    private JButton adicionaLocacao, adicionaReserva;
    private JButton validaCredito;

    private JComboBox<String> jcLista_cargos;

    private JTextField tfNome, tfSalario, tfCpf, tfTelefone;
    private JTextField tfDataInicio, tfDataFim, tfId, tfPlaca;
    private JTextField tfValidade, tfLocador;

    public void desenha(Funcionario f){
        
        funcionario = f;

        tela_funcionario = new JFrame("Espaço do admnistrador");
        tela_funcionario.setSize(WIDTH, HEIGHT);
        tela_funcionario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela_funcionario.setLocationRelativeTo(null);
        tela_funcionario.setVisible(true);
        tela_funcionario.setLayout(new BorderLayout());
        
        inicializaComponentes();
        desenhaPaginaInicial();
        
        tela_funcionario.pack();
    }

    public void inicializaComponentes(){

        //inicializa os menus
        menuBarra = new JMenuBar();
        menuVoltar = new JMenu("Página Inicial");

        menuAtualiza.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                formularioAtualiza();
            }

        });

        menuVoltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                desenha(funcionario);
            }
        });

        //adicionando ao menu barra
        menuBarra.add(menuVoltar);

        tela_funcionario.setJMenuBar(menuBarra);

    }

    public void desenhaPaginaInicial(){

        JPanel painel = new JPanel();
        JPanel painel2 = new JPanel();
        JPanel painelButton = new JPanel();

        DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
        JList<Locacao> modelList = new JList<>(model);

        painel.add(new JScrollPane(modelList));
        
        DefaultListModel<Reserva> model2 = (DefaultListModel<Reserva>)jlReserva.getModel();
        JList<Reserva> modelList2 = new JList<>(model2);

        painel2.add(new JScrollPane(modelList2));

        adicionaLocacao = new JButton("Adiciona locação");
        adicionaLocacao.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                desenhaFormulario();
            }

        });

        adicionaReserva = new JButton("Adiciona reserva");
        adicionaReserva.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaFormulario2();
            }
        });

        JButton btnConfirma = new JButton("Valida crédito");
        btnConfirma.addActionListener(new ValidaClientes(this));

        painelButton.add(adicionaLocacao);
        painelButton.add(adicionaReserva);
        painelButton.add(btnConfirma);

        JPanel listas = new JPanel();
        listas.add(painel, BorderLayout.WEST);
        listas.add(painel2, BorderLayout.EAST);

        JPanel principal = new JPanel();
        principal.add(listas, BorderLayout.CENTER);
        principal.add(painelButton, BorderLayout.SOUTH);

        tela_funcionario.getContentPane().add(principal);

    }

    public void formularioAtualiza(){
        //criação do painel
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Atualiza dados"));

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
        tfNome = new JTextField(20);
        tfCpf = new JTextField(20);
        tfTelefone = new JTextField(20);
        tfSalario = new JTextField(20);

        //inicialização do jComboBox para seleção do cargo
        String[] list = {"Locador", "Gerente"};
        jcLista_cargos = new JComboBox(list);

        //inserção do elementos TextField
        painelField.add(tfNome);
        painelField.add(tfCpf);
        painelField.add(tfTelefone);
        painelField.add(jcLista_cargos);
        painelField.add(tfSalario);

        formulario_func.add(painelLabel);
        formulario_func.add(painelField);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AtualizaFuncionario(this));

        painel.setLayout(new BorderLayout());
        painel.add(btnAdicionar);
        painel.add(formulario_func, BorderLayout.CENTER);

        JPanel principal = new JPanel();

        principal.add(painel, BorderLayout.NORTH);
        principal.add(btnAdicionar, BorderLayout.SOUTH);

        
        principal.setVisible(true);
        //splitPanel.setVisible(false);

        tela_funcionario.add(principal);
        tela_funcionario.pack();

    }

    public void atualizaFuncionario(){
        
        if(tfNome.getText() != null)
            funcionario.setNome(tfNome.getText());

        if(tfCpf.getText()!=null){
            try{
                funcionario.setCpf(tfCpf.getText());
            }catch(FormatoException e){
                JOptionPane.showMessageDialog(tela_funcionario, "O CPf apresenta um formato inválido!");
            }
        }
        
        if(jcLista_cargos.getSelectedItem() != null)
            funcionario.setCargo((String)jcLista_cargos.getSelectedItem());
        
        if(tfSalario.getText() != null)
            funcionario.setSalario(Double.parseDouble(tfSalario.getText()));
        
        if(tfTelefone.getText()!= null){
            try{
                funcionario.setTelefone(tfTelefone.getText());
            }catch(FormatoException e){
                JOptionPane.showMessageDialog(tela_funcionario, "O telefone apresenta um formato inválido!");
            }
        }
    }

    public void adicionaReserva(){
        DefaultListModel<Reserva> model = (DefaultListModel<Reserva>)jlReserva.getModel();

        try {
            if(funcionario.novaReserva(tfPlaca.getText(), tfId.getText(), tfDataInicio.getText(), tfDataFim.getText()))
                model.addElement(new Reserva(tfPlaca.getText(), tfId.getText(), tfDataInicio.getText(), tfDataFim.getText()));
            else
                JOptionPane.showMessageDialog(tela_funcionario, "Carro indiponível nessa data");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(tela_funcionario, "Reserva realizada com sucesso");
    }

    public void adicionaLocacao(){
        DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();

        try {
            if(funcionario.novaLocacao(tfPlaca.getText(), tfId.getText(), tfDataInicio.getText(), tfDataFim.getText()))
                model.addElement(new Locacao(tfDataInicio.getText(), tfDataFim.getText(), tfId.getText(), tfPlaca.getText(), true));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(tela_funcionario, "Locacao realizada com sucesso");
    }

    public void validaCredito(){
    }

    public void desenhaFormulario(){

        //criação do painel
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Nova Locação"));

        //criação do painel que contém o formulário
        JPanel formulario_locacao = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("ID do cliente"));
        painelLabel.add(new JLabel("Placa do carro"));
        painelLabel.add(new JLabel("Data início"));
        painelLabel.add(new JLabel("Data fim"));

        //implementação que contém os espaços que o usuário preenche os dados do funcionário
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfPlaca = new JTextField(20);
        tfId = new JTextField(20);
        tfDataInicio = new JTextField(20);
        tfDataFim = new JTextField(20);

        //inserção do elementos TextField
        painelField.add(tfId);
        painelField.add(tfPlaca);
        painelField.add(tfDataInicio);
        painelField.add(tfDataFim);

        formulario_locacao.add(painelLabel);
        formulario_locacao.add(painelField);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaLocacao(this));

        painel.setLayout(new BorderLayout());
        painel.add(btnAdicionar);
        painel.add(formulario_locacao, BorderLayout.CENTER);

        JPanel principal = new JPanel();

        principal.add(painel, BorderLayout.NORTH);
        principal.add(btnAdicionar, BorderLayout.SOUTH);

        
        principal.setVisible(true);
        //splitPanel.setVisible(false);

        tela_funcionario.add(principal);
        tela_funcionario.pack();

    }

    public void desenhaFormulario2(){

        //criação do painel
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Nova Reserva"));

        //criação do painel que contém o formulário
        JPanel formulario_locacao = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("ID do cliente"));
        painelLabel.add(new JLabel("Placa do carro"));
        painelLabel.add(new JLabel("Data início"));
        painelLabel.add(new JLabel("Data fim"));

        //implementação que contém os espaços que o usuário preenche os dados do funcionário
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfPlaca = new JTextField(20);
        tfId = new JTextField(20);
        tfDataInicio = new JTextField(20);
        tfDataFim = new JTextField(20);

        //inserção do elementos TextField
        painelField.add(tfId);
        painelField.add(tfPlaca);
        painelField.add(tfDataInicio);
        painelField.add(tfDataFim);

        formulario_locacao.add(painelLabel);
        formulario_locacao.add(painelField);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionaReserva(this));

        painel.setLayout(new BorderLayout());
        painel.add(btnAdicionar);
        painel.add(formulario_locacao, BorderLayout.CENTER);

        JPanel principal = new JPanel();

        principal.add(painel, BorderLayout.NORTH);
        principal.add(btnAdicionar, BorderLayout.SOUTH);

        
        principal.setVisible(true);
        //splitPanel.setVisible(false);

        tela_funcionario.add(principal);
        tela_funcionario.pack();

    }

    public void atualizaPainelDir_Locacoes(){

        int selectedIndex = jlLocacao.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Locacao> model = (DefaultListModel<Locacao>)jlLocacao.getModel();
            Locacao l = model.get(selectedIndex);

            //conversão do tipo date para String
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            tfId.setText(String.valueOf(l.getId()));
            tfNome.setText(l.getLocatario().getNome());
            tfPlaca.setText(l.getCarro_alugado().getPlaca());
            tfDataInicio.setText(formato.format(l.getDataInicio()));
            tfDataFim.setText(formato.format(l.getDataFim()));
            tfValidade.setText(String.valueOf(Locacao.validade(l)));
            tfLocador.setText(l.getLocador().getNome());
        }
    }
    
    public void atualizaPainelDir_Reservas(){

        int selectedIndex = jlReserva.getSelectedIndex();

        if(selectedIndex != -1){
            DefaultListModel<Reserva> model = (DefaultListModel<Reserva>)jlReserva.getModel();
            Reserva l = model.get(selectedIndex);

            //conversão do tipo date para String
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            tfId.setText(String.valueOf(l.getId()));
            tfNome.setText(l.getCliente().getNome());
            tfPlaca.setText(l.getCarro().getPlaca());
            tfDataInicio.setText(formato.format(l.getDataInicio()));
            tfDataFim.setText(formato.format(l.getDataFim()));

        }
    }
}

