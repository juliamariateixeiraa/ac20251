package br.edu.cs.poo.ac.seguro.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoPessoaMediator;

public class TelaSeguradoPessoaCRUD {

    private JFrame frmCrudSeguradoPessoa;
    private SeguradoPessoaMediator mediator = SeguradoPessoaMediator.getInstancia();

    private JTextField txtCpf;
    private JTextField txtNome;
    private JTextField txtRenda;
    private JTextField txtDataNascimento;
    private JTextField txtBonus;

    // Endereco fields
    private JTextField txtLogradouro;
    private JTextField txtCep;
    private JTextField txtNumeroEndereco;
    private JTextField txtComplemento;
    private JTextField txtPais;
    private JTextField txtEstado;
    private JTextField txtCidade;

    private JButton btnNovo;
    private JButton btnBuscar;
    private JButton btnIncluirAlterar;
    private JButton btnExcluir;
    private JButton btnCancelar;
    private JButton btnLimparCampos;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaSeguradoPessoaCRUD window = new TelaSeguradoPessoaCRUD();
                window.frmCrudSeguradoPessoa.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaSeguradoPessoaCRUD() {
        initialize();
        setInitialState();
    }

    private void initialize() {
        frmCrudSeguradoPessoa = new JFrame();
        frmCrudSeguradoPessoa.setTitle("CRUD Segurado Pessoa");
        frmCrudSeguradoPessoa.setBounds(100, 100, 650, 550);
        frmCrudSeguradoPessoa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCrudSeguradoPessoa.getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 0;
        frmCrudSeguradoPessoa.getContentPane().add(new JLabel("CPF:"), gbc);
        txtCpf = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmCrudSeguradoPessoa.getContentPane().add(txtCpf, gbc);
        gbc.weightx = 0.0; // Reset weightx

        JPanel panelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        btnNovo = new JButton("Novo");
        panelBusca.add(btnNovo);
        btnBuscar = new JButton("Buscar");
        panelBusca.add(btnBuscar);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        frmCrudSeguradoPessoa.getContentPane().add(panelBusca, gbc);
        gbc.gridwidth = 1; // Reset gridwidth

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        frmCrudSeguradoPessoa.getContentPane().add(new JLabel("Nome:"), gbc);
        txtNome = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frmCrudSeguradoPessoa.getContentPane().add(txtNome, gbc);
        gbc.gridwidth = 1;

        // Data de Nascimento
        gbc.gridx = 0;
        gbc.gridy = 2;
        frmCrudSeguradoPessoa.getContentPane().add(new JLabel("Data Nasc. (dd/MM/yyyy):"), gbc);
        txtDataNascimento = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frmCrudSeguradoPessoa.getContentPane().add(txtDataNascimento, gbc);
        gbc.gridwidth = 1;

        // Renda
        gbc.gridx = 0;
        gbc.gridy = 3;
        frmCrudSeguradoPessoa.getContentPane().add(new JLabel("Renda:"), gbc);
        txtRenda = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frmCrudSeguradoPessoa.getContentPane().add(txtRenda, gbc);
        gbc.gridwidth = 1;

        // Bônus
        gbc.gridx = 0;
        gbc.gridy = 4;
        frmCrudSeguradoPessoa.getContentPane().add(new JLabel("Bônus:"), gbc);
        txtBonus = new JTextField();
        txtBonus.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frmCrudSeguradoPessoa.getContentPane().add(txtBonus, gbc);
        gbc.gridwidth = 1;

        // Endereço Panel
        JPanel panelEndereco = new JPanel(new GridBagLayout());
        panelEndereco.setBorder(BorderFactory.createTitledBorder("Endereço"));
        GridBagConstraints gbcEndereco = new GridBagConstraints();
        gbcEndereco.insets = new Insets(2, 2, 2, 2);
        gbcEndereco.fill = GridBagConstraints.HORIZONTAL;

        gbcEndereco.gridx = 0; gbcEndereco.gridy = 0;
        panelEndereco.add(new JLabel("Logradouro:"), gbcEndereco);
        txtLogradouro = new JTextField();
        gbcEndereco.gridx = 1; gbcEndereco.weightx = 1.0;
        panelEndereco.add(txtLogradouro, gbcEndereco);

        gbcEndereco.gridx = 0; gbcEndereco.gridy = 1; gbcEndereco.weightx = 0.0;
        panelEndereco.add(new JLabel("CEP:"), gbcEndereco);
        txtCep = new JTextField();
        gbcEndereco.gridx = 1; gbcEndereco.weightx = 1.0;
        panelEndereco.add(txtCep, gbcEndereco);

        gbcEndereco.gridx = 0; gbcEndereco.gridy = 2; gbcEndereco.weightx = 0.0;
        panelEndereco.add(new JLabel("Número:"), gbcEndereco);
        txtNumeroEndereco = new JTextField();
        gbcEndereco.gridx = 1; gbcEndereco.weightx = 1.0;
        panelEndereco.add(txtNumeroEndereco, gbcEndereco);

        gbcEndereco.gridx = 0; gbcEndereco.gridy = 3; gbcEndereco.weightx = 0.0;
        panelEndereco.add(new JLabel("Complemento:"), gbcEndereco);
        txtComplemento = new JTextField();
        gbcEndereco.gridx = 1; gbcEndereco.weightx = 1.0;
        panelEndereco.add(txtComplemento, gbcEndereco);

        gbcEndereco.gridx = 0; gbcEndereco.gridy = 4; gbcEndereco.weightx = 0.0;
        panelEndereco.add(new JLabel("Cidade:"), gbcEndereco);
        txtCidade = new JTextField();
        gbcEndereco.gridx = 1; gbcEndereco.weightx = 1.0;
        panelEndereco.add(txtCidade, gbcEndereco);

        gbcEndereco.gridx = 0; gbcEndereco.gridy = 5; gbcEndereco.weightx = 0.0;
        panelEndereco.add(new JLabel("Estado (UF):"), gbcEndereco);
        txtEstado = new JTextField();
        gbcEndereco.gridx = 1; gbcEndereco.weightx = 1.0;
        panelEndereco.add(txtEstado, gbcEndereco);

        gbcEndereco.gridx = 0; gbcEndereco.gridy = 6; gbcEndereco.weightx = 0.0;
        panelEndereco.add(new JLabel("País:"), gbcEndereco);
        txtPais = new JTextField();
        gbcEndereco.gridx = 1; gbcEndereco.weightx = 1.0;
        panelEndereco.add(txtPais, gbcEndereco);
        gbcEndereco.weightx = 0.0;

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        frmCrudSeguradoPessoa.getContentPane().add(panelEndereco, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;


        // Buttons Panel
        JPanel panelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnIncluirAlterar = new JButton("Incluir");
        panelBotoesAcao.add(btnIncluirAlterar);
        btnExcluir = new JButton("Excluir");
        panelBotoesAcao.add(btnExcluir);
        btnCancelar = new JButton("Cancelar");
        panelBotoesAcao.add(btnCancelar);
        btnLimparCampos = new JButton("Limpar Campos");
        panelBotoesAcao.add(btnLimparCampos);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        frmCrudSeguradoPessoa.getContentPane().add(panelBotoesAcao, gbc);

        addListeners();
    }

    private void setInitialState() {
        txtCpf.setEnabled(true);
        txtNome.setEnabled(false);
        txtRenda.setEnabled(false);
        txtDataNascimento.setEnabled(false);
        txtBonus.setEnabled(false);

        txtLogradouro.setEnabled(false);
        txtCep.setEnabled(false);
        txtNumeroEndereco.setEnabled(false);
        txtComplemento.setEnabled(false);
        txtPais.setEnabled(false);
        txtEstado.setEnabled(false);
        txtCidade.setEnabled(false);

        btnNovo.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnIncluirAlterar.setEnabled(false);
        btnIncluirAlterar.setText("Incluir");
        btnExcluir.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnLimparCampos.setEnabled(true);

        clearFields();
    }

    private void setFormEnabled(boolean enabled) {
        txtNome.setEnabled(enabled);
        txtRenda.setEnabled(enabled);
        txtDataNascimento.setEnabled(enabled);

        txtLogradouro.setEnabled(enabled);
        txtCep.setEnabled(enabled);
        txtNumeroEndereco.setEnabled(enabled);
        txtComplemento.setEnabled(enabled);
        txtPais.setEnabled(enabled);
        txtEstado.setEnabled(enabled);
        txtCidade.setEnabled(enabled);
    }

    private void clearFields() {
        if (txtCpf.isEnabled()) { // Só limpa CPF se estiver habilitado (estado inicial ou após cancelar busca/novo)
            txtCpf.setText("");
        }
        txtNome.setText("");
        txtRenda.setText("");
        txtDataNascimento.setText("");
        txtBonus.setText(""); // Bonus é sempre display, mas limpar ao resetar é bom

        txtLogradouro.setText("");
        txtCep.setText("");
        txtNumeroEndereco.setText("");
        txtComplemento.setText("");
        txtPais.setText("");
        txtEstado.setText("");
        txtCidade.setText("");
    }

    private void populateFields(SeguradoPessoa segurado) {
        txtNome.setText(segurado.getNome());
        txtDataNascimento.setText(segurado.getDataNascimento() != null ? segurado.getDataNascimento().format(dateFormatter) : "");
        txtRenda.setText(String.valueOf(segurado.getRenda()));
        txtBonus.setText(segurado.getBonus() != null ? segurado.getBonus().setScale(2).toString() : "0.00");

        if (segurado.getEndereco() != null) {
            Endereco end = segurado.getEndereco();
            txtLogradouro.setText(end.getLogradouro());
            txtCep.setText(end.getCep());
            txtNumeroEndereco.setText(end.getNumero());
            txtComplemento.setText(end.getComplemento());
            txtCidade.setText(end.getCidade());
            txtEstado.setText(end.getEstado());
            txtPais.setText(end.getPais());
        }
    }

    private SeguradoPessoa getSeguradoFromFields() {
        String cpf = txtCpf.getText().trim(); // CPF é lido do campo, que estará desabilitado mas com valor
        String nome = txtNome.getText().trim();

        LocalDate dataNascimento = null;
        if (!txtDataNascimento.getText().trim().isEmpty()) {
            try {
                dataNascimento = LocalDate.parse(txtDataNascimento.getText().trim(), dateFormatter);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, "Formato de Data de Nascimento inválido. Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        double renda = 0.0;
        if (!txtRenda.getText().trim().isEmpty()) {
            try {
                renda = Double.parseDouble(txtRenda.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, "Renda deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        BigDecimal bonus = BigDecimal.ZERO;
        if (txtBonus.getText() != null && !txtBonus.getText().isEmpty()) {
            try {
                bonus = new BigDecimal(txtBonus.getText().trim());
            } catch (NumberFormatException e) {
                // Usa zero se não for um número válido no campo de bônus (que é display only)
            }
        }

        Endereco endereco = new Endereco(
                txtLogradouro.getText().trim(),
                txtCep.getText().trim(),
                txtNumeroEndereco.getText().trim(),
                txtComplemento.getText().trim(),
                txtPais.getText().trim(),
                txtEstado.getText().trim(),
                txtCidade.getText().trim()
        );
        return new SeguradoPessoa(nome, endereco, dataNascimento, bonus, cpf, renda);
    }

    private void addListeners() {
        btnNovo.addActionListener(e -> {
            String cpfInput = txtCpf.getText().trim();
            if (cpfInput.isEmpty()) {
                JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, "CPF deve ser informado para Novo.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SeguradoPessoa existente = mediator.buscarSeguradoPessoa(cpfInput);
            if (existente != null) {
                JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, "Segurado com este CPF já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                // ***** CORREÇÃO APLICADA AQUI *****
                txtCpf.setEnabled(false); // Desabilita o campo CPF PRIMEIRO, mantendo o valor.

                // Limpa apenas os outros campos (Nome, Renda, Endereço etc.)
                // O campo txtCpf não será afetado por clearFields() por estar desabilitado,
                // ou limpamos manualmente os demais como abaixo para maior clareza.
                txtNome.setText("");
                txtRenda.setText("");
                txtDataNascimento.setText("");
                txtBonus.setText("0.00"); // Define um valor padrão para bônus visual
                txtLogradouro.setText("");
                txtCep.setText("");
                txtNumeroEndereco.setText("");
                txtComplemento.setText("");
                txtPais.setText("");
                txtEstado.setText("");
                txtCidade.setText("");

                setFormEnabled(true); // Habilita o restante do formulário

                // Configura os botões para o modo de inclusão
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                btnIncluirAlterar.setEnabled(true);
                btnIncluirAlterar.setText("Incluir");
                btnExcluir.setEnabled(false);
                btnCancelar.setEnabled(true);
                // ***** FIM DA CORREÇÃO *****
            }
        });

        btnBuscar.addActionListener(e -> {
            String cpf = txtCpf.getText().trim();
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, "CPF deve ser informado para busca.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SeguradoPessoa segurado = mediator.buscarSeguradoPessoa(cpf);
            if (segurado == null) {
                JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, "Segurado não encontrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                // Mantém o CPF digitado e limpa os outros campos se não encontrado
                String cpfBuscado = txtCpf.getText();
                clearFields();
                txtCpf.setText(cpfBuscado); // Restaura o CPF que foi buscado
                txtCpf.setEnabled(true); // Permite nova busca ou novo
            } else {
                populateFields(segurado);
                setFormEnabled(true);
                txtCpf.setEnabled(false); // CPF não pode ser alterado após busca bem-sucedida
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                btnIncluirAlterar.setEnabled(true);
                btnIncluirAlterar.setText("Alterar");
                btnExcluir.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });

        btnIncluirAlterar.addActionListener(e -> {
            SeguradoPessoa segurado = getSeguradoFromFields();
            if (segurado == null) return;

            String msg;
            if (btnIncluirAlterar.getText().equals("Incluir")) {
                msg = mediator.incluirSeguradoPessoa(segurado);
            } else {
                msg = mediator.alterarSeguradoPessoa(segurado);
            }

            if (msg == null) {
                JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, "Operação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                setInitialState();
            } else {
                JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, msg, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            String cpf = txtCpf.getText().trim(); // CPF está desabilitado, mas tem valor
            int confirm = JOptionPane.showConfirmDialog(frmCrudSeguradoPessoa,
                    "Tem certeza que deseja excluir este segurado?", "Confirmação de Exclusão",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String msg = mediator.excluirSeguradoPessoa(cpf);
                if (msg == null) {
                    JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, "Segurado excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    setInitialState();
                } else {
                    JOptionPane.showMessageDialog(frmCrudSeguradoPessoa, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> {
            setInitialState();
        });

        btnLimparCampos.addActionListener(e -> {
            // Limpa todos os campos se o CPF estiver habilitado (estado inicial)
            // ou apenas os campos de dados se o CPF estiver desabilitado (durante novo/alterar)
            if(txtCpf.isEnabled()) {
                clearFields(); // Limpa tudo, incluindo CPF
            } else { // CPF está desabilitado (modo novo/alterar), não limpa CPF
                txtNome.setText("");
                txtRenda.setText("");
                txtDataNascimento.setText("");
                // txtBonus já é não editável e será preenchido ou default
                txtLogradouro.setText("");
                txtCep.setText("");
                txtNumeroEndereco.setText("");
                txtComplemento.setText("");
                txtPais.setText("");
                txtEstado.setText("");
                txtCidade.setText("");
            }
        });
    }
}