package br.edu.cs.poo.ac.seguro.mediators;

import java.time.LocalDate;

import br.edu.cs.poo.ac.seguro.daos.SeguradoPessoaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;

public class SeguradoPessoaMediator {

	private static SeguradoPessoaMediator instancia = new SeguradoPessoaMediator();

	private SeguradoPessoaDAO dao = new SeguradoPessoaDAO();
	private SeguradoMediator seguradoMediator = SeguradoMediator.getInstancia();

	private SeguradoPessoaMediator() {
	}

	public static SeguradoPessoaMediator getInstancia() {
		return instancia;
	}

	public String validarCpf(String cpf) {
		if (StringUtils.ehNuloOuBranco(cpf)) {
			return "CPF deve ser informado";
		}
		if (cpf.length() != 11) {
			return "CPF deve ter 11 caracteres";
		}
		if (!ValidadorCpfCnpj.ehCpfValido(cpf)) {
			return "CPF com dígito inválido";
		}
		return null;
	}

	public String validarRenda(double renda) {
		if (renda < 0) {
			return "Renda deve ser maior ou igual à zero";
		}
		return null;
	}

	public String validarDataNascimento(LocalDate dataNascimento) {
		if (dataNascimento == null) {
			return "Data do nascimento deve ser informada";
		}
		if (dataNascimento.isAfter(LocalDate.now())) {
			return "Data do nascimento deve ser menor ou igual à data atual";
		}
		return null;
	}

	public String validarSeguradoPessoa(SeguradoPessoa seg) {
		if (seg == null) {
			return "Segurado não pode ser nulo";
		}
		String erro = seguradoMediator.validarNome(seg.getNome());
		if (erro != null) return erro;

		erro = seguradoMediator.validarEndereco(seg.getEndereco());
		if (erro != null) return erro;

		erro = validarCpf(seg.getCpf());
		if (erro != null) return erro;

		erro = validarRenda(seg.getRenda());
		if (erro != null) return erro;

		erro = validarDataNascimento(seg.getDataNascimento());
		if (erro != null) return erro;

		return null;
	}

	public String incluirSeguradoPessoa(SeguradoPessoa seg) {
		String erro = validarSeguradoPessoa(seg);
		if (erro != null) return erro;

		boolean sucesso = dao.incluir(seg);
		if (!sucesso) {
			return "CPF do segurado pessoa já existente";
		}
		return null;
	}

	public String alterarSeguradoPessoa(SeguradoPessoa seg) {
		String erro = validarSeguradoPessoa(seg);
		if (erro != null) return erro;

		boolean sucesso = dao.alterar(seg);
		if (!sucesso) {
			return "CPF do segurado pessoa não existente";
		}
		return null;
	}

	public String excluirSeguradoPessoa(String cpf) {
		if (StringUtils.ehNuloOuBranco(cpf)) {
			return "CPF deve ser informado";
		}
		boolean sucesso = dao.excluir(cpf);
		if (!sucesso) {
			return "CPF do segurado pessoa não existente";
		}
		return null;
	}

	public SeguradoPessoa buscarSeguradoPessoa(String cpf) {
		if (StringUtils.ehNuloOuBranco(cpf)) {
			return null;
		}
		return dao.buscar(cpf);
	}
}
