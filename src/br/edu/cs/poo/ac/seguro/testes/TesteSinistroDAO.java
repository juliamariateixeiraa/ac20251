package br.edu.cs.poo.ac.seguro.testes;

import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TesteSinistroDAO {

    public static void main(String[] args) {
        teste01(); // Testar criar
        teste02(); // Testar buscar
        teste03(); // Testar atualizar
        teste04(); // Testar deletar
    }

    public static void teste01() {
        System.out.println("--- TESTE 01: CRIAR SINISTRO ---");
        SinistroDAO dao = new SinistroDAO();
        Veiculo veiculo = new Veiculo("ABC-1234", "Fiat Uno", 2020);
        Sinistro sinistro = new Sinistro(
                "SIN-001",
                veiculo,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "admin",
                BigDecimal.valueOf(5000),
                TipoSinistro.COLISAO
        );

        dao.criar(sinistro);
        System.out.println("Sinistro criado com sucesso!");
    }

    public static void teste02() {
        System.out.println("\n--- TESTE 02: BUSCAR SINISTRO ---");
        SinistroDAO dao = new SinistroDAO();
        Sinistro sinistro = dao.buscarPorNumero("SIN-001");

        if (sinistro != null) {
            System.out.println("Sinistro encontrado: " + sinistro.getNumero());
        } else {
            System.out.println("Sinistro não encontrado!");
        }
    }

    public static void teste03() {
        System.out.println("\n--- TESTE 03: ATUALIZAR SINISTRO ---");
        SinistroDAO dao = new SinistroDAO();
        Sinistro sinistro = dao.buscarPorNumero("SIN-001");

        if (sinistro != null) {
            sinistro.setValorSinistro(BigDecimal.valueOf(6000));
            dao.atualizar(sinistro);
            System.out.println("Sinistro atualizado!");
        } else {
            System.out.println("Sinistro não encontrado para atualização!");
        }
    }

    public static void teste04() {
        System.out.println("\n--- TESTE 04: DELETAR SINISTRO ---");
        SinistroDAO dao = new SinistroDAO();
        Sinistro sinistro = dao.buscarPorNumero("SIN-001");

        if (sinistro != null) {
            dao.deletar(sinistro.getNumero());
            System.out.println("Sinistro deletado!");
        } else {
            System.out.println("Sinistro não encontrado para exclusão!");
        }
    }
}