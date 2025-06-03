package br.edu.cs.poo.ac.seguro.telas;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.mediators.ApoliceMediator;
import br.edu.cs.poo.ac.seguro.mediators.DadosVeiculo;
import br.edu.cs.poo.ac.seguro.mediators.RetornoInclusaoApolice;

public class TelaInclusaoApolice {

    private JFrame frmInclusaoApolice;
    private ApoliceMediator mediator = ApoliceMediator.getInstancia(); // [cite: 26]

    private JTextField txtCpfOuCnpj;
    private JTextField txtPlaca;
    private JTextField txtAno;
    private JTextField txtValorMaximoSegurado;
    private JComboBox<String> cmbCategoriaVeiculo;

    private JButton btnIncluir;
    private JButton btnLimpar;

    private List<CategoriaVeiculo> categoriasOrdenadas;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaInclusaoApolice window = new TelaInclusaoApolice();
                window.frmInclusaoApolice.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaInclusaoApolice() {
        initialize();
    }

    private void initialize() {
        frmInclusaoApolice = new JFrame();
        frmInclusaoApolice.setTitle("Inclusão de Apólice");
        frmInclusaoApolice.setBounds(100, 100, 450, 300);
        frmInclusaoApolice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to not exit app
        frmInclusaoApolice.getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // CPF ou CNPJ
        gbc.gridx = 0;
        gbc.gridy = 0;
        frmInclusaoApolice.getContentPane().add(new JLabel("CPF/CNPJ:"), gbc);
        txtCpfOuCnpj = new JTextField(); // [cite: 27]
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoApolice.getContentPane().add(txtCpfOuCnpj, gbc);

        // Placa
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        frmInclusaoApolice.getContentPane().add(new JLabel("Placa:"), gbc);
        txtPlaca = new JTextField(); // [cite: 27]
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoApolice.getContentPane().add(txtPlaca, gbc);

        // Ano
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        frmInclusaoApolice.getContentPane().add(new JLabel("Ano:"), gbc);
        txtAno = new JTextField(); // [cite: 27]
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoApolice.getContentPane().add(txtAno, gbc);

        // Valor Máximo Segurado
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        frmInclusaoApolice.getContentPane().add(new JLabel("Valor Máx. Segurado:"), gbc);
        txtValorMaximoSegurado = new JTextField(); // [cite: 27]
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoApolice.getContentPane().add(txtValorMaximoSegurado, gbc);

        // Categoria Veículo
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        frmInclusaoApolice.getContentPane().add(new JLabel("Categoria:"), gbc);
        cmbCategoriaVeiculo = new JComboBox<>(); // [cite: 29]
        popularCategorias(); // [cite: 29]
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoApolice.getContentPane().add(cmbCategoriaVeiculo, gbc);


        // Buttons Panel
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnIncluir = new JButton("Incluir"); // [cite: 30]
        panelBotoes.add(btnIncluir);
        btnLimpar = new JButton("Limpar"); // [cite: 30]
        panelBotoes.add(btnLimpar);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        frmInclusaoApolice.getContentPane().add(panelBotoes, gbc);

        addListeners();
    }

    private void popularCategorias() {
        CategoriaVeiculo[] todasCategorias = CategoriaVeiculo.values();
        categoriasOrdenadas = new ArrayList<>(Arrays.asList(todasCategorias));
        // Sort by name alphabetically [cite: 29]
        categoriasOrdenadas.sort(Comparator.comparing(CategoriaVeiculo::getNome));

        for (CategoriaVeiculo cat : categoriasOrdenadas) {
            cmbCategoriaVeiculo.addItem(cat.getNome());
        }
        if (!categoriasOrdenadas.isEmpty()) {
            cmbCategoriaVeiculo.setSelectedIndex(0); // [cite: 31]
        }
    }

    private void clearFields() {
        txtCpfOuCnpj.setText("");
        txtPlaca.setText("");
        txtAno.setText("");
        txtValorMaximoSegurado.setText("");
        if (cmbCategoriaVeiculo.getItemCount() > 0) {
            cmbCategoriaVeiculo.setSelectedIndex(0); // [cite: 31]
        }
    }

    private void addListeners() {
        btnIncluir.addActionListener(e -> {
            String cpfOuCnpj = txtCpfOuCnpj.getText().trim();
            String placa = txtPlaca.getText().trim();
            String anoStr = txtAno.getText().trim();
            String valorMaxStr = txtValorMaximoSegurado.getText().trim();

            int ano;
            BigDecimal valorMaximoSegurado;
            int codigoCategoria = -1;

            try {
                ano = Integer.parseInt(anoStr); // [cite: 28]
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frmInclusaoApolice, "Ano deve ser um número inteiro válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                valorMaximoSegurado = new BigDecimal(valorMaxStr); // [cite: 28]
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frmInclusaoApolice, "Valor Máximo Segurado deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int selectedIndex = cmbCategoriaVeiculo.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < categoriasOrdenadas.size()) {
                codigoCategoria = categoriasOrdenadas.get(selectedIndex).getCodigo();
            } else {
                JOptionPane.showMessageDialog(frmInclusaoApolice, "Selecione uma categoria válida.", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DadosVeiculo dadosVeiculo = new DadosVeiculo(cpfOuCnpj, placa, ano, valorMaximoSegurado, codigoCategoria);

            // The PDF (item 6.d) states "chama o mediator de sinistro"[cite: 30],
            // but item 6.a specifies "ApoliceMediator" [cite: 26] and 6.e/6.f refer to policy results[cite: 32, 33, 34].
            // Assuming ApoliceMediator is correct for policy inclusion.
            RetornoInclusaoApolice retorno = mediator.incluirApolice(dadosVeiculo);

            if (retorno.getMensagemErro() == null) {
                JOptionPane.showMessageDialog(frmInclusaoApolice,
                        "Apólice incluída com sucesso! Anote o número da apólice: " + retorno.getNumeroApolice(), // [cite: 32, 33]
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frmInclusaoApolice,
                        retorno.getMensagemErro(), // [cite: 34]
                        "Erro na Inclusão", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpar.addActionListener(e -> { // [cite: 30]
            clearFields();
        });
    }
}