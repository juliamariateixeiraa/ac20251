package br.edu.cs.poo.ac.seguro.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Apolice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String numero;
    private Veiculo veiculo;
    private BigDecimal valorFranquia;
    private BigDecimal valorPremio;
    private BigDecimal valorMaximoSegurado;

    public Apolice(Veiculo veiculo, BigDecimal valorFranquia, BigDecimal valorPremio, BigDecimal valorMaximoSegurado) {
        this.veiculo = veiculo;
        this.valorFranquia = valorFranquia;
        this.valorPremio = valorPremio;
        this.valorMaximoSegurado = valorMaximoSegurado;
    }
}
