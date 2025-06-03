package br.edu.cs.poo.ac.seguro.telas;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.excecoes.ExcecaoValidacaoDados;
import br.edu.cs.poo.ac.seguro.mediators.DadosSinistro;
import br.edu.cs.poo.ac.seguro.mediators.SinistroMediator;

public class TelaInclusaoSinistro {

    private JFrame frmInclusaoSinistro;
    private SinistroMediator mediator = SinistroMediator.getInstancia(); // [cite: 14]

    private JTextField txtPlaca;
    private JTextField txtDataHoraSinistro; // Format: dd/MM/yyyy HH:mm
    private JTextField txtUsuarioRegistro;
    private JTextField txtValorSinistro;
    private JComboBox<String> cmbTipoSinistro;

    private JButton btnIncluir;
    private JButton btnLimpar;

    private List<TipoSinistro> tiposSinistroOrdenados;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaInclusaoSinistro window = new TelaInclusaoSinistro();
                window.frmInclusaoSinistro.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaInclusaoSinistro() {
        initialize();
    }

    private void initialize() {
        frmInclusaoSinistro = new JFrame();
        frmInclusaoSinistro.setTitle("Inclusão de Sinistro");
        frmInclusaoSinistro.setBounds(100, 100, 480, 320);
        frmInclusaoSinistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmInclusaoSinistro.getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Placa
        gbc.gridx = 0;
        gbc.gridy = 0;
        frmInclusaoSinistro.getContentPane().add(new JLabel("Placa:"), gbc);
        txtPlaca = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoSinistro.getContentPane().add(txtPlaca, gbc);

        // Data e Hora do Sinistro
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        frmInclusaoSinistro.getContentPane().add(new JLabel("Data/Hora Sinistro (dd/MM/yyyy HH:mm):"), gbc);
        txtDataHoraSinistro = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoSinistro.getContentPane().add(txtDataHoraSinistro, gbc);

        // Usuário Registro
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        frmInclusaoSinistro.getContentPane().add(new JLabel("Usuário Registro:"), gbc);
        txtUsuarioRegistro = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoSinistro.getContentPane().add(txtUsuarioRegistro, gbc);

        // Valor do Sinistro
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        frmInclusaoSinistro.getContentPane().add(new JLabel("Valor do Sinistro:"), gbc);
        txtValorSinistro = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoSinistro.getContentPane().add(txtValorSinistro, gbc);

        // Tipo Sinistro
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        frmInclusaoSinistro.getContentPane().add(new JLabel("Tipo de Sinistro:"), gbc);
        cmbTipoSinistro = new JComboBox<>();
        popularTiposSinistro(); // [cite: 16]
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        frmInclusaoSinistro.getContentPane().add(cmbTipoSinistro, gbc);


        // Buttons Panel
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnIncluir = new JButton("Incluir"); // [cite: 17]
        panelBotoes.add(btnIncluir);
        btnLimpar = new JButton("Limpar"); // [cite: 17]
        panelBotoes.add(btnLimpar);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        frmInclusaoSinistro.getContentPane().add(panelBotoes, gbc);

        addListeners();
    }

    private void popularTiposSinistro() {
        TipoSinistro[] todosTipos = TipoSinistro.values();
        tiposSinistroOrdenados = new ArrayList<>(Arrays.asList(todosTipos));
        // Sort by name alphabetically
        tiposSinistroOrdenados.sort(Comparator.comparing(TipoSinistro::getNome)); // [cite: 16]

        for (TipoSinistro tipo : tiposSinistroOrdenados) {
            cmbTipoSinistro.addItem(tipo.getNome());
        }
        if (!tiposSinistroOrdenados.isEmpty()) {
            cmbTipoSinistro.setSelectedIndex(0); // [cite: 18]
        }
    }

    private void clearFields() {
        txtPlaca.setText("");
        txtDataHoraSinistro.setText("");
        txtUsuarioRegistro.setText("");
        txtValorSinistro.setText("");
        if (cmbTipoSinistro.getItemCount() > 0) {
            cmbTipoSinistro.setSelectedIndex(0); // [cite: 18]
        }
    }

    private void addListeners() {
        btnIncluir.addActionListener(e -> {
            String placa = txtPlaca.getText().trim();
            String dataHoraStr = txtDataHoraSinistro.getText().trim();
            String usuarioRegistro = txtUsuarioRegistro.getText().trim();
            String valorStr = txtValorSinistro.getText().trim();

            LocalDateTime dataHoraSinistro;
            double valorSinistro;
            int codigoTipoSinistro = -1;

            try {
                dataHoraSinistro = LocalDateTime.parse(dataHoraStr, dateTimeFormatter); // [cite: 15]
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frmInclusaoSinistro, "Data/Hora do Sinistro inválida. Use dd/MM/yyyy HH:mm.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                valorSinistro = Double.parseDouble(valorStr); // [cite: 15]
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frmInclusaoSinistro, "Valor do Sinistro deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int selectedIndex = cmbTipoSinistro.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < tiposSinistroOrdenados.size()) {
                codigoTipoSinistro = tiposSinistroOrdenados.get(selectedIndex).getCodigo();
            } else {
                JOptionPane.showMessageDialog(frmInclusaoSinistro, "Selecione um tipo de sinistro válido.", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DadosSinistro dadosSinistro = new DadosSinistro(placa, dataHoraSinistro, usuarioRegistro, valorSinistro, codigoTipoSinistro);

            try {
                // Pass current DateTime for registration time as per SinistroMediator signature
                String numeroSinistroGerado = mediator.incluirSinistro(dadosSinistro, LocalDateTime.now()); // [cite: 17]
                JOptionPane.showMessageDialog(frmInclusaoSinistro,
                        "Sinistro incluído com sucesso! Anote o número do sinistro: " + numeroSinistroGerado, // [cite: 19, 20]
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } catch (ExcecaoValidacaoDados ex) {
                JOptionPane.showMessageDialog(frmInclusaoSinistro,
                        ex.getMessage(), // [cite: 21]
                        "Erro na Inclusão", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpar.addActionListener(e -> { // [cite: 17]
            clearFields();
        });
    }
}