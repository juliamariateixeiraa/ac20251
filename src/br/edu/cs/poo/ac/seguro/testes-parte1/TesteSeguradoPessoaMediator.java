package br.edu.cs.poo.ac.seguro.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoPessoaMediator;

public class TesteSeguradoPessoaMediator extends TesteMediator {
	private SeguradoPessoaMediator med = SeguradoPessoaMediator.getInstancia();

	@Override
	protected Class<?> getClasse() {
		return SeguradoPessoa.class;
	}

	@Test
	public void testValidarCpfNuloOuVazio() {
		assertEquals("CPF deve ser informado", med.validarCpf(null));
		assertEquals("CPF deve ser informado", med.validarCpf(" "));
	}

	@Test
	public void testValidarCpfTamanhoInvalido() {
		assertEquals("CPF deve ter 11 caracteres", med.validarCpf("123456789012"));
	}

	@Test
	public void testValidarCpfDigitoInvalido() {
		assertEquals("CPF com dígito inválido", med.validarCpf("07255431081"));
	}

	@Test
	public void testValidarCpfValido() {
		assertNull(med.validarCpf("07255431089"));
	}

	@Test
	public void testValidarRenda() {
		assertEquals("Renda deve ser maior ou igual à zero", med.validarRenda(-10.0));
		assertNull(med.validarRenda(0.0));
		assertNull(med.validarRenda(10.0));
	}

	@Test
	public void testBuscarSeguradoPessoaExistente() {
		String cpf = "07255431089";
		SeguradoPessoa seg = criarSeguradoPessoa(cpf);
		cadastro.incluir(seg, cpf);
		assertNotNull(med.buscarSeguradoPessoa(cpf));
	}

	@Test
	public void testBuscarSeguradoPessoaInexistente() {
		criarSeguradoPessoa("07255431089");
		assertNull(med.buscarSeguradoPessoa("00000000000"));
	}

	@Test
	public void testIncluirSeguradoPessoaValidacoes() {
		Endereco end = criarEndereco();
		SeguradoPessoa seg = new SeguradoPessoa(" ", end, LocalDate.now(), BigDecimal.ZERO, "07255431089", 1000.0);
		assertEquals("Nome deve ser informado", med.incluirSeguradoPessoa(seg));

		seg = new SeguradoPessoa("PAULA", null, LocalDate.now(), BigDecimal.ZERO, "07255431089", 1000.0);
		assertEquals("Endereço deve ser informado", med.incluirSeguradoPessoa(seg));

		seg = new SeguradoPessoa("PAULA", end, null, BigDecimal.ZERO, "07255431089", 1000.0);
		assertEquals("Data do nascimento deve ser informada", med.incluirSeguradoPessoa(seg));

		seg = new SeguradoPessoa("PAULA", end, LocalDate.now(), BigDecimal.ZERO, "07255431081", 1000.0);
		assertEquals("CPF com dígito inválido", med.incluirSeguradoPessoa(seg));

		seg = new SeguradoPessoa("PAULA", end, LocalDate.now(), BigDecimal.ZERO, "07255431089", -100.0);
		assertEquals("Renda deve ser maior ou igual à zero", med.incluirSeguradoPessoa(seg));
	}

	@Test
	public void testIncluirSeguradoPessoaComSucesso() {
		String cpf = "07255431089";
		SeguradoPessoa seg = criarSeguradoPessoa(cpf);
		assertNull(med.incluirSeguradoPessoa(seg));
		assertNotNull(med.buscarSeguradoPessoa(cpf));
	}

	@Test
	public void testIncluirSeguradoPessoaDuplicado() {
		String cpf = "07255431089";
		SeguradoPessoa seg = criarSeguradoPessoa(cpf);
		cadastro.incluir(seg, cpf);
		assertEquals("CPF do segurado pessoa já existente", med.incluirSeguradoPessoa(seg));
		assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(seg, med.buscarSeguradoPessoa(cpf)));
	}

	@Test
	public void testAlterarSeguradoPessoaComSucesso() {
		String cpf = "07255431089";
		cadastro.incluir(criarSeguradoPessoa(cpf), cpf);
		SeguradoPessoa alterado = new SeguradoPessoa("Alterado", criarEndereco(), LocalDate.now(), BigDecimal.ONE, cpf, 1500.0);
		assertNull(med.alterarSeguradoPessoa(alterado));
		assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(alterado, med.buscarSeguradoPessoa(cpf)));
	}

	@Test
	public void testAlterarSeguradoPessoaInexistente() {
		String cpf = "12345678909"; // CPF válido não usado
		SeguradoPessoa seg = criarSeguradoPessoa(cpf);
		assertEquals("CPF do segurado pessoa não existente", med.alterarSeguradoPessoa(seg));
	}

	@Test
	public void testExcluirSeguradoPessoaComSucesso() {
		String cpf = "07255431089";
		cadastro.incluir(criarSeguradoPessoa(cpf), cpf);
		assertNull(med.excluirSeguradoPessoa(cpf));
		assertNull(med.buscarSeguradoPessoa(cpf));
	}

	@Test
	public void testExcluirSeguradoPessoaInexistente() {
		String cpf = "00000000000";
		assertEquals("CPF do segurado pessoa não existente", med.excluirSeguradoPessoa(cpf));
	}

	private Endereco criarEndereco() {
		return new Endereco("Rua A", "51020002", "22", "ap 201", "Brasil", "PE", "Recife");
	}

	private SeguradoPessoa criarSeguradoPessoa(String cpf) {
		return new SeguradoPessoa("PAULA", criarEndereco(), LocalDate.now(), BigDecimal.ZERO, cpf, 1000.0);
	}
}
