// ✅ SinistroMediator.java — com método incluirSinistro completo e correção de Arrays.asList()
package br.edu.cs.poo.ac.seguro.mediators;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;
import br.edu.cs.poo.ac.seguro.excecoes.ExcecaoValidacaoDados;

public class SinistroMediator {

	private VeiculoDAO daoVeiculo = new VeiculoDAO();
	private ApoliceDAO daoApolice = new ApoliceDAO();
	private SinistroDAO daoSinistro = new SinistroDAO();
	private static SinistroMediator instancia;

	public static SinistroMediator getInstancia() {
		if (instancia == null)
			instancia = new SinistroMediator();
		return instancia;
	}

	private SinistroMediator() {}

	public String incluirSinistro(DadosSinistro dados, LocalDateTime dataHoraAtual) throws ExcecaoValidacaoDados {
		ExcecaoValidacaoDados excecao = new ExcecaoValidacaoDados();

		if (dados == null) {
			excecao.adicionarMensagem("Dados do sinistro devem ser informados.");
			throw excecao;
		}

		if (dados.getDataHoraSinistro() == null) {
			excecao.adicionarMensagem("Data e hora do sinistro devem ser informadas.");
		} else if (dados.getDataHoraSinistro().isAfter(dataHoraAtual)) {
			excecao.adicionarMensagem("Data do sinistro deve ser anterior à data atual.");
		}

		if (dados.getPlaca() == null || dados.getPlaca().trim().isEmpty()) {
			excecao.adicionarMensagem("Placa deve ser informada.");
		}

		Veiculo veiculo = daoVeiculo.buscar(dados.getPlaca());
		if (veiculo == null) {
			excecao.adicionarMensagem("Veículo informado não está cadastrado.");
		}

		if (dados.getUsuarioRegistro() == null || dados.getUsuarioRegistro().trim().isEmpty()) {
			excecao.adicionarMensagem("Usuário de registro deve ser informado.");
		}

		if (dados.getValorSinistro() <= 0) {
			excecao.adicionarMensagem("Valor do sinistro deve ser maior que zero.");
		}

		TipoSinistro tipo = TipoSinistro.getTipoSinistro(dados.getCodigoTipoSinistro());
		if (tipo == null) {
			excecao.adicionarMensagem("Código do tipo de sinistro inválido.");
		}

		// Busca apólice vigente para o veículo
		List<Apolice> apolices = Arrays.asList(daoApolice.buscarTodos());
		Apolice apoliceEncontrada = null;
		for (Apolice ap : apolices) {
			if (ap.getVeiculo().getPlaca().equalsIgnoreCase(dados.getPlaca())) {
				LocalDateTime inicio = ap.getDataInicioVigencia().atStartOfDay();
				LocalDateTime fim = inicio.plusYears(1);
				if (!dados.getDataHoraSinistro().isBefore(inicio) && dados.getDataHoraSinistro().isBefore(fim)) {
					apoliceEncontrada = ap;
					break;
				}
			}
		}

		if (apoliceEncontrada == null) {
			excecao.adicionarMensagem("Não existe apólice vigente para o veículo informado.");
		} else {
			if (BigDecimal.valueOf(dados.getValorSinistro()).compareTo(apoliceEncontrada.getValorMaximoSegurado()) > 0) {
				excecao.adicionarMensagem("Valor do sinistro excede valor máximo segurado pela apólice.");
			}
		}

		if (excecao.possuiErros()) {
			throw excecao;
		}

		// Gerar número e sequencial
		List<Sinistro> sinistros = Arrays.asList(daoSinistro.buscarTodos());
		List<Sinistro> relacionados = new ArrayList<>();
		for (Sinistro s : sinistros) {
			if (s.getNumeroApolice().equals(apoliceEncontrada.getNumero())) {
				relacionados.add(s);
			}
		}

		int sequencial = 1;
		if (!relacionados.isEmpty()) {
			Collections.sort(relacionados, new ComparadorSinistroSequencial());
			int ultimo = relacionados.get(relacionados.size() - 1).getSequencial();
			sequencial = ultimo + 1;
		}

		String sequencialFormatado = String.format("%03d", sequencial);
		String numeroSinistro = "S" + apoliceEncontrada.getNumero() + sequencialFormatado;

		Sinistro novoSinistro = new Sinistro(
				numeroSinistro,
				veiculo,
				dados.getDataHoraSinistro(),
				dataHoraAtual,
				dados.getUsuarioRegistro(),
				BigDecimal.valueOf(dados.getValorSinistro()),
				tipo
		);

		novoSinistro.setSequencial(sequencial);
		novoSinistro.setNumeroApolice(apoliceEncontrada.getNumero());

		daoSinistro.incluir(novoSinistro);

		return numeroSinistro;
	}
}
