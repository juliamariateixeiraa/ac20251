package br.edu.cs.poo.ac.seguro.testes;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.Segurado;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoEmpresaMediator;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoMediator;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoPessoaMediator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteMediator {

    public static void main(String[] args) {
        testeSeguradoMediator();
        testeSeguradoEmpresaMediator();
        testeSeguradoPessoaMediator();
    }

    public static void testeSeguradoMediator() {
        System.out.println("--- Testes SeguradoMediator ---");
        SeguradoMediator mediator = SeguradoMediator.getInstancia();

        // Teste validarNome
        System.out.println("Teste validarNome:");
        System.out.println("  Nome válido: " + (mediator.validarNome("João da Silva") == null));
        System.out.println("  Nome nulo: " + (mediator.validarNome(null) != null));
        System.out.println("  Nome longo: " + (mediator.validarNome("Nome muito longo com mais de 100 caracteres...") != null));

        // Teste validarEndereco
        System.out.println("\nTeste validarEndereco:");
        Endereco enderecoValido = new Endereco("Rua A", "12345678", "10", "Apto 101", "Brasil", "SP", "São Paulo");
        Endereco enderecoIncompleto = new Endereco("Rua A", null, "10", "Apto 101", "Brasil", "SP", "São Paulo");
        System.out.println("  Endereço válido: " + (mediator.validarEndereco(enderecoValido) == null));
        System.out.println("  Endereço incompleto: " + (mediator.validarEndereco(enderecoIncompleto) != null));

        // Teste validarDataCriacao
        System.out.println("\nTeste validarDataCriacao:");
        System.out.println("  Data válida: " + (mediator.validarDataCriacao(LocalDate.now()) == null));
        System.out.println("  Data futura: " + (mediator.validarDataCriacao(LocalDate.now().plusDays(1)) != null));
        System.out.println("  Data nula: " + (mediator.validarDataCriacao(null) != null));

        // Teste ajustarDebitoBonus
        System.out.println("\nTeste ajustarDebitoBonus:");
        BigDecimal bonus = new BigDecimal(100);
        BigDecimal debitoMenor = new BigDecimal(50);
        BigDecimal debitoMaior = new BigDecimal(150);
        System.out.println("  Débito menor que bônus: " + (mediator.ajustarDebitoBonus(bonus, debitoMenor).equals(debitoMenor)));
        System.out.println("  Débito maior que bônus: " + (mediator.ajustarDebitoBonus(bonus, debitoMaior).equals(bonus)));
        System.out.println("  Bônus ou débito nulo: " + (mediator.ajustarDebitoBonus(null, debitoMenor).equals(BigDecimal.ZERO)));
    }

    public static void testeSeguradoEmpresaMediator() {
        System.out.println("\n--- Testes SeguradoEmpresaMediator ---");
        SeguradoEmpresaMediator mediator = SeguradoEmpresaMediator.getInstancia();

        // Criar objetos para teste
        Endereco endereco = new Endereco("Rua B", "87654321", "20", "Sala 202", "Brasil", "RJ", "Rio de Janeiro");
        LocalDate dataAbertura = LocalDate.of(2020, 1, 1);
        SeguradoEmpresa empresaValida = new SeguradoEmpresa("Empresa X", endereco, dataAbertura, new BigDecimal(1000), "12345678901234", 1000000, false);
        SeguradoEmpresa empresaCnpjInvalido = new SeguradoEmpresa("Empresa Y", endereco, dataAbertura, new BigDecimal(1000), "123", 1000000, false);
        SeguradoEmpresa empresaFaturamentoInvalido = new SeguradoEmpresa("Empresa Z", endereco, dataAbertura, new BigDecimal(1000), "98765432109876", -1000, false);

        // Teste incluir
        System.out.println("\nTeste incluir:");
        System.out.println("  Incluir válido: " + (mediator.incluir(empresaValida) == null));
        System.out.println("  Incluir CNPJ inválido: " + (mediator.incluir(empresaCnpjInvalido) != null));
        System.out.println("  Incluir nulo: " + (mediator.incluir(null) != null));

        // Teste alterar
        System.out.println("\nTeste alterar:");
        System.out.println("  Alterar válido: " + (mediator.alterar(empresaValida) == null)); // Assumindo que já foi incluído
        empresaValida.setFaturamento(2000000);
        System.out.println("  Alterar não existente: " + (mediator.alterar(empresaFaturamentoInvalido) != null));
        System.out.println("  Alterar nulo: " + (mediator.alterar(null) != null));

        // Teste excluir
        System.out.println("\nTeste excluir:");
        System.out.println("  Excluir válido: " + (mediator.excluir(empresaValida.getCnpj()) == null)); // Assumindo que já foi incluído e alterado
        System.out.println("  Excluir não existente: " + (mediator.excluir("00000000000000") != null));
        System.out.println("  Excluir CNPJ nulo: " + (mediator.excluir(null) != null));

        // Teste buscar
        System.out.println("\nTeste buscar:");
        System.out.println("  Buscar existente: " + (mediator.buscar(empresaValida.getCnpj()) != null));
        System.out.println("  Buscar não existente: " + (mediator.buscar("00000000000000") == null));
        System.out.println("  Buscar CNPJ nulo: " + (mediator.buscar(null) == null));
    }

    public static void testeSeguradoPessoaMediator() {
        System.out.println("\n--- Testes SeguradoPessoaMediator ---");
        SeguradoPessoaMediator mediator = SeguradoPessoaMediator.getInstancia();

        // Criar objetos para teste
        Endereco endereco = new Endereco("Rua C", "11223344", "30", "Casa 3", "Brasil", "MG", "Belo Horizonte");
        LocalDate dataNascimento = LocalDate.of(1990, 5, 15);
        SeguradoPessoa pessoaValida = new SeguradoPessoa("Maria Souza", endereco, dataNascimento, new BigDecimal(500), "12345678901", 5000);
        SeguradoPessoa pessoaCpfInvalido = new SeguradoPessoa("João Pereira", endereco, dataNascimento, new BigDecimal(500), "123", 5000);
        SeguradoPessoa pessoaRendaInvalida = new SeguradoPessoa("Ana Silva", endereco, dataNascimento, new BigDecimal(500), "98765432109", -100, 5000);
        SeguradoPessoa pessoaDataNascimentoInvalida = new SeguradoPessoa("Carlos Rocha", endereco, LocalDate.now().plusDays(1), new BigDecimal(500), "11223344556", 5000);

        // Teste incluirSeguradoPessoa
        System.out.println("\nTeste incluirSeguradoPessoa:");
        System.out.println("  Incluir válido: " + (mediator.incluirSeguradoPessoa(pessoaValida) == null));
        System.out.println("  Incluir CPF inválido: " + (mediator.incluirSeguradoPessoa(pessoaCpfInvalido) != null));
        System.out.println("  Incluir renda inválida: " + (mediator.incluirSeguradoPessoa(pessoaRendaInvalida) != null));
        System.out.println("  Incluir data nascimento inválida: " + (mediator.incluirSeguradoPessoa(pessoaDataNascimentoInvalida) != null));
        System.out.println("  Incluir nulo: " + (mediator.incluirSeguradoPessoa(null) != null));

        // Teste alterarSeguradoPessoa
        System.out.println("\nTeste alterarSeguradoPessoa:");
        System.out.println("  Alterar válido: " + (mediator.alterarSeguradoPessoa(pessoaValida) == null)); // Assumindo que já foi incluído
        pessoaValida.setRenda(6000);
        System.out.println("  Alterar não existente: " + (mediator.alterarSeguradoPessoa(pessoaCpfInvalido) != null));
        System.out.println("  Alterar nulo: " + (mediator.alterarSeguradoPessoa(null) != null));

        // Teste excluirSeguradoPessoa
        System.out.println("\nTeste excluirSeguradoPessoa:");
        System.out.println("  Excluir válido: " + (mediator.excluirSeguradoPessoa(pessoaValida.getCpf()) == null)); // Assumindo que já foi incluído e alterado
        System.out.println("  Excluir não existente: " + (mediator.excluirSeguradoPessoa("00000000000") != null));
        System.out.println("  Excluir CPF nulo: " + (mediator.excluirSeguradoPessoa(null) != null));

        // Teste buscarSeguradoPessoa
        System.out.println("\nTeste buscarSeguradoPessoa:");
        System.out.println("  Buscar existente: " + (mediator.buscarSeguradoPessoa(pessoaValida.getCpf()) != null));
        System.out.println("  Buscar não existente: " + (mediator.buscarSeguradoPessoa("00000000000") == null));
        System.out.println("  Buscar CPF nulo: " + (mediator.buscarSeguradoPessoa(null) == null));
    }
}
