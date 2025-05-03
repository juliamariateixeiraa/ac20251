package br.edu.cs.poo.ac.seguro.testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class TesteVeiculoDAO extends TesteDAO {
    
    private VeiculoDAO dao = new VeiculoDAO();

    @Override
    protected Class<?> getClasse() {
        return Veiculo.class;
    }

    @Test
    public void teste01_inclusaoVeiculoComSucesso() {
        String placa = "00000000";
        cadastro.incluir(new Veiculo(placa, 2000, null, null, CategoriaVeiculo.BASICO), placa);
        Veiculo ve = dao.buscar(placa);
        Assertions.assertNotNull(ve); 
    }

    @Test
    public void teste02_buscaVeiculoInexistente() {
        String placa = "10000000";
        cadastro.incluir(new Veiculo(placa, 2001, null, null, CategoriaVeiculo.BASICO), placa);
        Veiculo ve = dao.buscar("11000000");
        Assertions.assertNull(ve);
    }

    @Test
    public void teste03_exclusaoVeiculoExistente() {
        String placa = "20000000";
        cadastro.incluir(new Veiculo(placa, 2002, null, null, CategoriaVeiculo.BASICO), placa);
        boolean ret = dao.excluir(placa);
        Assertions.assertTrue(ret);
    }

    @Test
    public void teste04_exclusaoVeiculoInexistente() {
        String placa = "30000000";
        cadastro.incluir(new Veiculo(placa, 2003, null, null, CategoriaVeiculo.BASICO), placa);
        boolean ret = dao.excluir("31000000");
        Assertions.assertFalse(ret);
    }

    @Test
    public void teste05_inclusaoDiretaRetornaTrue() {
        String placa = "40000000";		
        boolean ret = dao.incluir(new Veiculo(placa, 2004, null, null, CategoriaVeiculo.BASICO));		
        Assertions.assertTrue(ret);
        Veiculo ve = dao.buscar(placa);
        Assertions.assertNotNull(ve);		
    }

    @Test
    public void teste06_inclusaoDuplicadaRetornaFalse() {
        String placa = "50000000";		
        Veiculo ve = new Veiculo(placa, 2005, null, null, CategoriaVeiculo.BASICO);
        cadastro.incluir(ve, placa);
        boolean ret = dao.incluir(ve);
        Assertions.assertFalse(ret);
    }

    @Test
    public void teste07_alteracaoVeiculoInexistente() {
        String placa = "60000000";			
        boolean ret = dao.alterar(new Veiculo(placa, 2006, null, null, CategoriaVeiculo.BASICO));		
        Assertions.assertFalse(ret);
        Veiculo ve = dao.buscar(placa);
        Assertions.assertNull(ve);		
    }

    @Test
    public void teste08_alteracaoVeiculoExistente() {
        String placa = "70000000";			
        Veiculo ve = new Veiculo(placa, 2007, null, null, CategoriaVeiculo.BASICO);
        cadastro.incluir(ve, placa);
        ve = new Veiculo(placa, 2008, null, null, CategoriaVeiculo.ESPORTIVO);
        boolean ret = dao.alterar(ve);
        Assertions.assertTrue(ret);
    }
}
