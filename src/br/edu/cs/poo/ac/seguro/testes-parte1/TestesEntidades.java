package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.entidades.*;

public class TestesEntidades {

    @Test
    public void teste01_tipoSinistroValido() {
        Assertions.assertEquals(TipoSinistro.COLISAO, TipoSinistro.getTipoSinistro(1));
        Assertions.assertEquals(TipoSinistro.INCENDIO, TipoSinistro.getTipoSinistro(2));
        Assertions.assertEquals(TipoSinistro.FURTO, TipoSinistro.getTipoSinistro(3));
        Assertions.assertEquals(TipoSinistro.ENCHENTE, TipoSinistro.getTipoSinistro(4));
        Assertions.assertEquals(TipoSinistro.DEPREDACAO, TipoSinistro.getTipoSinistro(5));
    }

    @Test
    public void teste02_tipoSinistroInvalido() {
        Assertions.assertNull(TipoSinistro.getTipoSinistro(0));
        Assertions.assertNull(TipoSinistro.getTipoSinistro(6));
    }

    @Test
    public void teste03_creditarBonusEmpresa() {
        SeguradoEmpresa seg = new SeguradoEmpresa("Empresa X", null, null, BigDecimal.ZERO, null, 0.0, false);
        seg.creditarBonus(new BigDecimal("100.00"));
        seg.creditarBonus(new BigDecimal("50.00"));
        Assertions.assertEquals(new BigDecimal("150.00"), seg.getBonus());
    }

    @Test
    public void teste04_debitarBonusPessoa() {
        SeguradoPessoa seg = new SeguradoPessoa("Maria", null, null, new BigDecimal("200.00"), null, 0.0);
        seg.debitarBonus(new BigDecimal("40.00"));
        seg.debitarBonus(new BigDecimal("20.00"));
        Assertions.assertEquals(new BigDecimal("140.00"), seg.getBonus());
    }

    @Test
    public void teste05_idadePessoa() {
        int ano = LocalDate.now().getYear() - 10;
        int mes = LocalDate.now().getMonthValue();
        int dia = LocalDate.now().getDayOfMonth();
        SeguradoPessoa seg = new SeguradoPessoa("Joao", null, LocalDate.of(ano, mes, dia), null, null, 0.0);
        Assertions.assertEquals(10, seg.getIdade());
    }

    @Test
    public void teste06_precoAno() {
        PrecoAno pa = new PrecoAno(2020, 50000.00);
        Assertions.assertEquals(2020, pa.getAno());
        Assertions.assertEquals(50000.00, pa.getPreco());
    }
}
