package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class VeiculoDAO extends DAOGenerico<Veiculo> {

    @Override
    protected Class<Veiculo> getClasseEntidade() {
        return Veiculo.class;
    }
}
