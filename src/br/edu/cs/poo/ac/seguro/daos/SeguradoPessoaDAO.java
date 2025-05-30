package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;

public class SeguradoPessoaDAO extends SeguradoDAO {

    @Override
    protected Class<SeguradoPessoa> getClasseEntidade() {
        return SeguradoPessoa.class;
    }

    public SeguradoPessoa buscar(String cpf) {
        return (SeguradoPessoa) super.buscar(cpf);
    }

    public boolean incluir(SeguradoPessoa segurado) {
        return super.incluir(segurado);
    }

    public boolean alterar(SeguradoPessoa segurado) {
        return super.alterar(segurado);
    }

    public boolean excluir(String cpf) {
        return super.excluir(cpf);
    }
}
