package br.edu.cs.poo.ac.seguro.entidades;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Apolice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String numero;
    private Veiculo veiculo;
    private BigDecimal valorFranquia;
    private BigDecimal valorPremio;
    private BigDecimal valorMaximoSegurado;
    private LocalDate dataInicioVigencia;

    // ✅ Construtor completo
    public Apolice(String numero, Veiculo veiculo, BigDecimal valorFranquia,
                   BigDecimal valorPremio, BigDecimal valorMaximoSegurado,
                   LocalDate dataInicioVigencia) {
        this.numero = numero;
        this.veiculo = veiculo;
        this.valorFranquia = valorFranquia;
        this.valorPremio = valorPremio;
        this.valorMaximoSegurado = valorMaximoSegurado;
        this.dataInicioVigencia = dataInicioVigencia;
    }

    // ✅ Construtor antigo para compatibilidade com os testes
    public Apolice(Veiculo veiculo, BigDecimal valorFranquia,
                   BigDecimal valorPremio, BigDecimal valorMaximoSegurado) {
        this.veiculo = veiculo;
        this.valorFranquia = valorFranquia;
        this.valorPremio = valorPremio;
        this.valorMaximoSegurado = valorMaximoSegurado;
    }
}
