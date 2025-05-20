package br.edu.cs.poo.ac.seguro.testes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoEmpresaMediator;

public class TesteSeguradoEmpresaMediator extends TesteMediator {
    private SeguradoEmpresaMediator med = SeguradoEmpresaMediator.getInstancia();

    @Override
    protected Class<?> getClasse() {
        return SeguradoEmpresa.class;
    }

    @Test
    public void test01() {
        String ret = med.validarCnpj(null);
        assertEquals("CNPJ deve ser informado.", ret);
        ret = med.validarCnpj("   ");
        assertEquals("CNPJ deve ser informado.", ret);
    }

    @Test
    public void test02() {
        String ret = med.validarCnpj("123456789012");
        assertEquals("CNPJ deve ter 14 caracteres.", ret);
    }

    @Test
    public void test03() {
        String ret = med.validarCnpj("11851715000171");
        assertEquals("CNPJ com dígito inválido.", ret);
    }

    @Test
    public void test04() {
        String ret = med.validarCnpj("11851715000174");
        assertNull(ret);
    }

    @Test
    public void test05() {
        String ret = med.validarFaturamento(-1.0);
        assertEquals("Faturamento deve ser maior que zero.", ret);
        ret = med.validarFaturamento(0.0);
        assertEquals("Faturamento deve ser maior que zero.", ret);
    }

    @Test
    public void test06() {
        String ret = med.validarFaturamento(100.0);
        assertNull(ret);
    }

    @Test
    public void test07() {
        String cnpj = "11851715000174";
        Endereco end = new Endereco("Rua A", "51020002", "22", "ap 201", "Brasil", "PE", "Recife");
        SeguradoEmpresa seg = new SeguradoEmpresa("Empresa X", end, LocalDate.now(), BigDecimal.ZERO, cnpj, 10000.0, false);
        cadastro.incluir(seg, cnpj);
        SeguradoEmpresa buscado = med.buscarSeguradoEmpresa(cnpj);
        assertNotNull(buscado);
    }

    @Test
    public void test08() {
        String cnpj = "11851715000274";
        Endereco end = new Endereco("Rua A", "51020002", "22", "ap 201", "Brasil", "PE", "Recife");
        SeguradoEmpresa seg = new SeguradoEmpresa("Empresa Y", end, LocalDate.now(), BigDecimal.ZERO, cnpj, 10000.0, false);
        cadastro.incluir(seg, cnpj);
        SeguradoEmpresa buscado = med.buscarSeguradoEmpresa("11851715000174");
        assertNull(buscado);
    }

    @Test
    public void test09() {
        Endereco end = new Endereco("Rua A", "51020002", "22", "ap 201", "Brasil", "PE", "Recife");
        SeguradoEmpresa seg = new SeguradoEmpresa(null, end, LocalDate.now(), BigDecimal.ZERO, "11851715000174", 10000.0, false);
        String ret = med.incluirSeguradoEmpresa(seg);
        assertEquals("Nome deve ser informado.", ret);

        seg = new SeguradoEmpresa("Empresa", null, LocalDate.now(), BigDecimal.ZERO, "11851715000174", 10000.0, false);
        ret = med.incluirSeguradoEmpresa(seg);
        assertEquals("Endereço deve ser informado.", ret);

        seg = new SeguradoEmpresa("Empresa", end, null, BigDecimal.ZERO, "11851715000174", 10000.0, false);
        ret = med.incluirSeguradoEmpresa(seg);
        assertEquals("Data de abertura deve ser informada.", ret);

        seg = new SeguradoEmpresa("Empresa", end, LocalDate.now(), BigDecimal.ZERO, "11851715000171", 10000.0, false);
        ret = med.incluirSeguradoEmpresa(seg);
        assertEquals("CNPJ com dígito inválido.", ret);

        seg = new SeguradoEmpresa("Empresa", end, LocalDate.now(), BigDecimal.ZERO, "11851715000174", 0.0, false);
        ret = med.incluirSeguradoEmpresa(seg);
        assertEquals("Faturamento deve ser maior que zero.", ret);
    }

    @Test
    public void test10() {
        String cnpj = "11851715000174";
        Endereco end = new Endereco("Rua A", "51020002", "22", "ap 201", "Brasil", "PE", "Recife");
        SeguradoEmpresa seg = new SeguradoEmpresa("Empresa Z", end, LocalDate.now(), BigDecimal.ZERO, cnpj, 10000.0, false);
        String ret = med.incluirSeguradoEmpresa(seg);
        assertNull(ret);
        assertNotNull(med.buscarSeguradoEmpresa(cnpj));
    }

    @Test
    public void test11() {
        String cnpj = "11851715000174";
        Endereco end = new Endereco("Rua A", "51020002", "22", "ap 201", "Brasil", "PE", "Recife");
        SeguradoEmpresa seg = new SeguradoEmpresa("Empresa Z", end, LocalDate.now(), BigDecimal.ZERO, cnpj, 10000.0, false);
        cadastro.incluir(seg, cnpj);
        String ret = med.incluirSeguradoEmpresa(seg);
        assertEquals("Já existe um segurado com este CNPJ.", ret);
    }

    @Test
    public void test12() {
        String cnpj = "11851715000174";
        Endereco end = new Endereco("Rua B", "51020002", "23", "ap 301", "Brasil", "SP", "São Paulo");
        SeguradoEmpresa seg = new SeguradoEmpresa("Empresa Z Atualizada", end, LocalDate.now(), BigDecimal.ZERO, cnpj, 20000.0, true);
        cadastro.incluir(seg, cnpj);
        String ret = med.alterarSeguradoEmpresa(seg);
        assertNull(ret);
    }

    @Test
    public void test13() {
        String cnpj = "11851715000174";
        Endereco end = new Endereco("Rua C", "51020002", "24", "ap 401", "Brasil", "RJ", "Rio");
        SeguradoEmpresa seg = new SeguradoEmpresa("Empresa X", end, LocalDate.now(), BigDecimal.ZERO, cnpj, 10000.0, false);
        cadastro.incluir(seg, cnpj);
        String ret = med.excluirSeguradoEmpresa(cnpj);
        assertNull(ret);
        assertNull(med.buscarSeguradoEmpresa(cnpj));
    }

    @Test
    public void test14() {
        String ret = med.excluirSeguradoEmpresa("00000000000000");
        assertEquals("Segurado com este CNPJ não existe.", ret);
    }
}
