package br.edu.cs.poo.ac.seguro.mediator;

import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.util.StringUtils; 
import br.edu.cs.poo.ac.seguro.util.ValidadorCpfCnpj; 

public class SeguradoEmpresaMediator {

    private SeguradoEmpresaDAO dao = new SeguradoEmpresaDAO();

    // Implementando Singleton (opcional, mas recomendado pelo segundo código)
    private static SeguradoEmpresaMediator instancia = new SeguradoEmpresaMediator();

    private SeguradoEmpresaMediator() {}  // Construtor privado para o Singleton

    public static SeguradoEmpresaMediator getInstancia() {
        return instancia;
    }

    // Métodos de Validação (extraídos do segundo código para melhor organização)

    private String validarCnpj(String cnpj) {
        if (StringUtils.ehNuloOuBranco(cnpj)) {
            return "CNPJ deve ser informado.";
        }
        if (cnpj.length() != 14) {
            return "CNPJ deve ter 14 caracteres.";
        }
        if (!StringUtils.temSomenteNumeros(cnpj) || !ValidadorCpfCnpj.ehCnpjValido(cnpj)) {
            return "CNPJ com dígito inválido.";
        }
        return null;
    }

    private String validarFaturamento(double faturamento) {
        if (faturamento <= 0) {
            return "Faturamento deve ser maior que zero.";
        }
        return null;
    }

    private String validarSeguradoEmpresa(SeguradoEmpresa segurado) {
        if (segurado == null) {
            return "Segurado não pode ser nulo.";
        }
        if (StringUtils.ehNuloOuBranco(segurado.getNome())) {
            return "Nome deve ser informado.";
        }
        if (segurado.getEndereco() == null) {
            return "Endereço deve ser informado.";
        }
        if (segurado.getDataAbertura() == null) {
            return "Data de abertura deve ser informada.";
        }

        String cnpjMsg = validarCnpj(segurado.getCnpj());
        if (cnpjMsg != null) {
            return cnpjMsg;
        }

        return validarFaturamento(segurado.getFaturamento());
    }

    // Métodos CRUD (modificados para usar os métodos de validação)

    public String incluir(SeguradoEmpresa segurado) {
        String mensagemValidacao = validarSeguradoEmpresa(segurado);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }

        if (dao.buscar(segurado.getCnpj()) != null) {
            return "Já existe um segurado com este CNPJ.";
        }

        boolean sucesso = dao.incluir(segurado);
        return sucesso ? null : "Erro ao incluir segurado.";
    }

    public String alterar(SeguradoEmpresa segurado) {
        String mensagemValidacao = validarSeguradoEmpresa(segurado);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }

        if (dao.buscar(segurado.getCnpj()) == null) {
            return "Segurado com este CNPJ não existe.";
        }

        boolean sucesso = dao.alterar(segurado);
        return sucesso ? null : "Erro ao alterar segurado.";
    }

    public String excluir(String cnpj) {
        String mensagemValidacaoCnpj = validarCnpj(cnpj);
        if (mensagemValidacaoCnpj != null) {
            return mensagemValidacaoCnpj;
        }

        if (dao.buscar(cnpj) == null) {
            return "Segurado com este CNPJ não existe.";
        }

        boolean sucesso = dao.excluir(cnpj);
        return sucesso ? null : "Erro ao excluir segurado.";
    }

    public SeguradoEmpresa buscar(String cnpj) {
        if (StringUtils.ehNuloOuBranco(cnpj)) {  // Use StringUtils para verificar nulo ou branco
            return null;
        }
        return dao.buscar(cnpj);
    }
}
