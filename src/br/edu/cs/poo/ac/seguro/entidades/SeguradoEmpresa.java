package br.edu.cs.poo.ac.seguro.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguradoEmpresa extends Segurado implements Registro {

    private static final long serialVersionUID = 1L;

    private String cnpj;
    private double faturamento;
    private boolean ehLocadoraDeVeiculos;

    public SeguradoEmpresa(String nome, Endereco endereco, LocalDate dataAbertura,
                           BigDecimal bonus, String cnpj, double faturamento, boolean ehLocadoraDeVeiculos) {
        super(nome, endereco, dataAbertura, bonus);
        this.cnpj = cnpj;
        this.faturamento = faturamento;
        this.ehLocadoraDeVeiculos = ehLocadoraDeVeiculos;
    }

    public LocalDate getDataAbertura() {
        return getDataCriacao();
    }

    public void setDataAbertura(LocalDate data) {
        setDataCriacao(data);
    }

    @Override
    public boolean isEmpresa() {
        return true;
    }

    @Override
    public String getIdUnico() {
        return cnpj;
    }
}
