package br.edu.cs.poo.ac.seguro.telas;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoEmpresaMediator;

public class TelaSeguradoEmpresaCRUD {

    private JFrame frmCrudSeguradoEmpresa;
    private SeguradoEmpresaMediator mediator = SeguradoEmpresaMediator.getInstancia();

    private JTextField txtCnpj;
    private JTextField txtNome;
    private JTextField txtFaturamento;
    private JTextField txtDataAbertura;
    private JCheckBox chkEhLocadora;
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
                TelaSeguradoEmpresaCRUD window = new TelaSeguradoEmpresaCRUD();
                window.frmCrudSeguradoEmpresa.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaSeguradoEmpresaCRUD() {
        initialize();
        setInitialState();
    }

    private void initialize() {
        frmCrudSeguradoEmpresa = new JFrame();
        frmCrudSeguradoEmpresa.setTitle("CRUD Segurado Empresa");
        frmCrudSeguradoEmpresa.setBounds(100, 100, 650, 600);
        frmCrudSeguradoEmpresa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCrudSeguradoEmpresa.getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // CNPJ
        gbc.gridx = 0;
        gbc.gridy = 0;
        frmCrudSeguradoEmpresa.getContentPane().add(new JLabel("CNPJ:"), gbc);
        txtCnpj = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmCrudSeguradoEmpresa.getContentPane().add(txtCnpj, gbc);
        gbc.weightx = 0.0;

        JPanel panelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        btnNovo = new JButton("Novo");
        panelBusca.add(btnNovo);
        btnBuscar = new JButton("Buscar");
        panelBusca.add(btnBuscar);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        frmCrudSeguradoEmpresa.getContentPane().add(panelBusca, gbc);
        gbc.gridwidth = 1;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        frmCrudSeguradoEmpresa.getContentPane().add(new JLabel("Nome:"), gbc);
        txtNome = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frmCrudSeguradoEmpresa.getContentPane().add(txtNome, gbc);
        gbc.gridwidth = 1;

        // Data de Abertura
        gbc.gridx = 0;
        gbc.gridy = 2;
        frmCrudSeguradoEmpresa.getContentPane().add(new JLabel("Data Abertura (dd/MM/yyyy):"), gbc);
        txtDataAbertura = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frmCrudSeguradoEmpresa.getContentPane().add(txtDataAbertura, gbc);
        gbc.gridwidth = 1;

        // Faturamento
        gbc.gridx = 0;
        gbc.gridy = 3;
        frmCrudSeguradoEmpresa.getContentPane().add(new JLabel("Faturamento:"), gbc);
        txtFaturamento = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frmCrudSeguradoEmpresa.getContentPane().add(txtFaturamento, gbc);
        gbc.gridwidth = 1;

        // É Locadora de Veículos
        gbc.gridx = 0;
        gbc.gridy = 4;
        frmCrudSeguradoEmpresa.getContentPane().add(new JLabel("Locadora de Veículos:"), gbc);
        chkEhLocadora = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        frmCrudSeguradoEmpresa.getContentPane().add(chkEhLocadora, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;

        // Bônus
        gbc.gridx = 0;
        gbc.gridy = 5;
        frmCrudSeguradoEmpresa.getContentPane().add(new JLabel("Bônus:"), gbc);
        txtBonus = new JTextField();
        txtBonus.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frmCrudSeguradoEmpresa.getContentPane().add(txtBonus, gbc);
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
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        frmCrudSeguradoEmpresa.getContentPane().add(panelEndereco, gbc);
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
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        frmCrudSeguradoEmpresa.getContentPane().add(panelBotoesAcao, gbc);

        addListeners();
    }

    private void setInitialState() {
        txtCnpj.setEnabled(true);
        txtNome.setEnabled(false);
        txtFaturamento.setEnabled(false);
        txtDataAbertura.setEnabled(false);
        chkEhLocadora.setEnabled(false);
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
        txtFaturamento.setEnabled(enabled);
        txtDataAbertura.setEnabled(enabled);
        chkEhLocadora.setEnabled(enabled);

        txtLogradouro.setEnabled(enabled);
        txtCep.setEnabled(enabled);
        txtNumeroEndereco.setEnabled(enabled);
        txtComplemento.setEnabled(enabled);
        txtPais.setEnabled(enabled);
        txtEstado.setEnabled(enabled);
        txtCidade.setEnabled(enabled);
    }

    private void clearFields() {
        if (txtCnpj.isEnabled()) {
            txtCnpj.setText("");
        }
        txtNome.setText("");
        txtFaturamento.setText("");
        txtDataAbertura.setText("");
        chkEhLocadora.setSelected(false);
        txtBonus.setText("");

        txtLogradouro.setText("");
        txtCep.setText("");
        txtNumeroEndereco.setText("");
        txtComplemento.setText("");
        txtPais.setText("");
        txtEstado.setText("");
        txtCidade.setText("");
    }

    private void populateFields(SeguradoEmpresa segurado) {
        txtNome.setText(segurado.getNome());
        txtDataAbertura.setText(segurado.getDataAbertura() != null ? segurado.getDataAbertura().format(dateFormatter) : "");
        txtFaturamento.setText(String.valueOf(segurado.getFaturamento()));
        chkEhLocadora.setSelected(segurado.isEhLocadoraDeVeiculos());
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

    private SeguradoEmpresa getSeguradoFromFields() {
        String cnpj = txtCnpj.getText().trim();
        String nome = txtNome.getText().trim();

        LocalDate dataAbertura = null;
        if (!txtDataAbertura.getText().trim().isEmpty()) {
            try {
                dataAbertura = LocalDate.parse(txtDataAbertura.getText().trim(), dateFormatter);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, "Formato de Data de Abertura inválido. Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        double faturamento = 0.0;
        if (!txtFaturamento.getText().trim().isEmpty()) {
            try {
                faturamento = Double.parseDouble(txtFaturamento.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, "Faturamento deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        boolean ehLocadora = chkEhLocadora.isSelected();
        BigDecimal bonus = BigDecimal.ZERO;
        if(txtBonus.getText() != null && !txtBonus.getText().isEmpty()){
            try {
                bonus = new BigDecimal(txtBonus.getText().trim());
            } catch (NumberFormatException e) {
                // Default to zero
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
        return new SeguradoEmpresa(nome, endereco, dataAbertura, bonus, cnpj, faturamento, ehLocadora);
    }

    private void addListeners() {
        btnNovo.addActionListener(e -> {
            String cnpjInput = txtCnpj.getText().trim();
            if (cnpjInput.isEmpty()) {
                JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, "CNPJ deve ser informado para Novo.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SeguradoEmpresa existente = mediator.buscarSeguradoEmpresa(cnpjInput);
            if (existente != null) {
                JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, "Segurado com este CNPJ já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                // ***** CORREÇÃO APLICADA AQUI *****
                txtCnpj.setEnabled(false); // Desabilita o campo CNPJ PRIMEIRO

                // Limpa os outros campos
                txtNome.setText("");
                txtFaturamento.setText("");
                txtDataAbertura.setText("");
                chkEhLocadora.setSelected(false);
                txtBonus.setText("0.00");
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
            String cnpj = txtCnpj.getText().trim();
            if (cnpj.isEmpty()) {
                JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, "CNPJ deve ser informado para busca.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SeguradoEmpresa segurado = mediator.buscarSeguradoEmpresa(cnpj);
            if (segurado == null) {
                JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, "Segurado não encontrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                String cnpjBuscado = txtCnpj.getText();
                clearFields();
                txtCnpj.setText(cnpjBuscado);
                txtCnpj.setEnabled(true);
            } else {
                populateFields(segurado);
                setFormEnabled(true);
                txtCnpj.setEnabled(false);
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                btnIncluirAlterar.setEnabled(true);
                btnIncluirAlterar.setText("Alterar");
                btnExcluir.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });

        btnIncluirAlterar.addActionListener(e -> {
            SeguradoEmpresa segurado = getSeguradoFromFields();
            if (segurado == null) return;

            String msg;
            if (btnIncluirAlterar.getText().equals("Incluir")) {
                msg = mediator.incluirSeguradoEmpresa(segurado);
            } else {
                msg = mediator.alterarSeguradoEmpresa(segurado);
            }

            if (msg == null) {
                JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, "Operação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                setInitialState();
            } else {
                JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, msg, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            String cnpj = txtCnpj.getText().trim();
            int confirm = JOptionPane.showConfirmDialog(frmCrudSeguradoEmpresa,
                    "Tem certeza que deseja excluir este segurado?", "Confirmação de Exclusão",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String msg = mediator.excluirSeguradoEmpresa(cnpj);
                if (msg == null) {
                    JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, "Segurado excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    setInitialState();
                } else {
                    JOptionPane.showMessageDialog(frmCrudSeguradoEmpresa, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> {
            setInitialState();
        });

        btnLimparCampos.addActionListener(e -> {
            if(txtCnpj.isEnabled()) {
                clearFields();
            } else {
                txtNome.setText("");
                txtFaturamento.setText("");
                txtDataAbertura.setText("");
                chkEhLocadora.setSelected(false);
                // txtBonus é display
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