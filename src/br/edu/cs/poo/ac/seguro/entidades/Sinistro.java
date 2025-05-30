package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sinistro implements Registro {

    private static final long serialVersionUID = 1L;

    private String numero;
    private Veiculo veiculo;
    private LocalDateTime dataHoraSinistro;
    private LocalDateTime dataHoraRegistro;
    private String usuarioRegistro;
    private BigDecimal valorSinistro;
    private TipoSinistro tipo;

    // 🔹 Melhoria 4: Novos atributos
    private int sequencial;
    private String numeroApolice;

    // ✅ Construtor manual com 7 parâmetros (compatível com Mediator)
    public Sinistro(String numero, Veiculo veiculo, LocalDateTime dataHoraSinistro,
                    LocalDateTime dataHoraRegistro, String usuarioRegistro,
                    BigDecimal valorSinistro, TipoSinistro tipo) {
        this.numero = numero;
        this.veiculo = veiculo;
        this.dataHoraSinistro = dataHoraSinistro;
        this.dataHoraRegistro = dataHoraRegistro;
        this.usuarioRegistro = usuarioRegistro;
        this.valorSinistro = valorSinistro;
        this.tipo = tipo;
    }

    @Override
    public String getIdUnico() {
        return numero;
    }
}
