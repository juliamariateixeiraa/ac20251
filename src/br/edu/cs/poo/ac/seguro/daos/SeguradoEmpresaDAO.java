package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaDAO extends SeguradoDAO {

    @Override
    protected Class<SeguradoEmpresa> getClasseEntidade() {
        return SeguradoEmpresa.class;
    }

    public SeguradoEmpresa buscar(String cnpj) {
        return (SeguradoEmpresa) super.buscar(cnpj);
    }

    public boolean incluir(SeguradoEmpresa seguradoEmpresa) {
        return super.incluir(seguradoEmpresa);
    }

    public boolean alterar(SeguradoEmpresa seguradoEmpresa) {
        return super.alterar(seguradoEmpresa);
    }

    public boolean excluir(String cnpj) {
        return super.excluir(cnpj);
    }
}
