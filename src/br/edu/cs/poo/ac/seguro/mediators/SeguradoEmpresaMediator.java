package br.edu.cs.poo.ac.seguro.mediator;

import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaMediator {

    private SeguradoEmpresaDAO dao = new SeguradoEmpresaDAO();

    public String incluir(SeguradoEmpresa segurado) {
        if (segurado == null) {
            return "Segurado não pode ser nulo.";
        }
        if (segurado.getCnpj() == null || segurado.getCnpj().trim().isEmpty()) {
            return "CNPJ inválido.";
        }
        if (dao.buscar(segurado.getCnpj()) != null) {
            return "Já existe um segurado com este CNPJ.";
        }

        boolean sucesso = dao.incluir(segurado);
        return sucesso ? null : "Erro ao incluir segurado.";
    }

    public String alterar(SeguradoEmpresa segurado) {
        if (segurado == null || segurado.getCnpj() == null || segurado.getCnpj().trim().isEmpty()) {
            return "Segurado ou CNPJ inválido.";
        }
        if (dao.buscar(segurado.getCnpj()) == null) {
            return "Segurado com este CNPJ não existe.";
        }

        boolean sucesso = dao.alterar(segurado);
        return sucesso ? null : "Erro ao alterar segurado.";
    }

    public String excluir(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return "CNPJ inválido.";
        }
        if (dao.buscar(cnpj) == null) {
            return "Segurado com este CNPJ não existe.";
        }

        boolean sucesso = dao.excluir(cnpj);
        return sucesso ? null : "Erro ao excluir segurado.";
    }

    public SeguradoEmpresa buscar(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return null;
        }
        return dao.buscar(cnpj);
    }
}
