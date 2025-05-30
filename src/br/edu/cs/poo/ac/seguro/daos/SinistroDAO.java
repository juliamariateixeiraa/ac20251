package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Sinistro;

public class SinistroDAO extends DAOGenerico<Sinistro> {

    public SinistroDAO() {
        super();
    }

    @Override
    protected Class<Sinistro> getClasseEntidade() {
        return Sinistro.class;
    }
}
