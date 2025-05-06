
# 🛡️ Sistema de Gestão de Seguros - Projeto POO

Bem-vindo ao projeto de **Gestão de Seguros**, desenvolvido como parte da disciplina de **Programação Orientada a Objetos** da CESAR School. Este sistema tem como objetivo gerenciar apólices, segurados, veículos e sinistros com persistência de dados e validações estruturadas.

## 📦 Estrutura do Projeto

```
src/
├── br.edu.cs.poo.ac.seguro.entidades     # Classes de entidades
├── br.edu.cs.poo.ac.seguro.daos          # Objetos de Acesso a Dados (DAOs)
├── br.edu.cs.poo.ac.seguro.mediators     # Classes de mediação e validação
└── br.edu.cs.poo.ac.seguro.testes        # Testes automatizados (JUnit)
```

---

## 🧩 Funcionalidades

### ✅ Entidades Principais (`.entidades`)
- **SeguradoPessoa** e **SeguradoEmpresa** com herança de `Segurado`
- `Veiculo`, `Apolice`, `Sinistro`, `Endereco`
- Enum `TipoSinistro` com tipos como `COLISAO`, `INCENDIO`, etc.
- Atributos com `get/set`, construtores e lógica de bônus e idade
- Lombok utilizado para reduzir boilerplate (`@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`)

### 💾 Persistência (`.daos`)
- Persistência realizada com `PersistenciaObjetos.jar`
- DAOs individuais: `SeguradoPessoaDAO`, `SeguradoEmpresaDAO`, `VeiculoDAO`, `ApoliceDAO`, `SinistroDAO`
- Operações: `buscar`, `incluir`, `alterar`, `excluir`
- Baseados em modelo comum, com polimorfismo introduzido futuramente

### 🧠 Mediators (`.mediators`)
- Padrão **Singleton** para mediadores centrais:
  - `SeguradoMediator`, `SeguradoPessoaMediator`, `SeguradoEmpresaMediator`
- Validação de CPF/CNPJ, campos obrigatórios e lógica compartilhada
- Auxílio de classes utilitárias:
  - `StringUtils` e `ValidadorCpfCnpj`

### 🧪 Testes Automatizados (`.testes`)
- JUnit testando operações de DAO e validações dos mediators
- Exemplos:
  - `TesteSeguradoPessoaDAO`, `TesteApoliceDAO`
  - `TesteSeguradoPessoaMediator`, `TesteSinistroDAO`

---

## 🧰 Tecnologias e Ferramentas

- **Java 17**
- **IntelliJ IDEA**
- **Lombok**
- **JUnit 5**
- **Persistência via serialização (PersistenciaObjetos.jar)**

---

## 🚧 Requisitos

- Instalar o Lombok no IntelliJ:
  - `Preferences > Plugins > Lombok Plugin`
  - Ativar "Annotation Processing" em `Preferences > Build, Execution, Deployment > Compiler > Annotation Processors`
- Adicionar `PersistenciaObjetos.jar` ao classpath do projeto:
  - Clique com botão direito no `.jar` > `Add as Library` ou configure no `.iml`

---

## 🗂️ Principais Classes

| Classe                   | Descrição                                               |
|--------------------------|----------------------------------------------------------|
| `SeguradoPessoa`         | Representa pessoa física com CPF e renda                 |
| `SeguradoEmpresa`        | Representa empresa com CNPJ, faturamento e locadora      |
| `Veiculo`                | Dados de veículo, associado a um segurado                |
| `Apolice`                | Apólice com valores de franquia, prêmio e seguro         |
| `Sinistro`               | Representa ocorrência com data, valor e tipo             |
| `TipoSinistro`           | Enum com tipos de sinistro                               |

---

## 🎯 Objetivos do Projeto

- Aplicar os conceitos de **POO** com herança, encapsulamento e composição
- Trabalhar com **persistência de objetos**
- Criar testes automatizados e boas práticas de software
- Praticar o uso de **Lombok**, **Singleton**, **Enum**, **DAO** e **Validação**

---

## 👩‍💻 Autora

**Fabiana Coelho**  
Estudante de Ciência da Computação na [CESAR School - Recife](https://www.cesar.school)  
**Julia Maria Teixeira**  
Estudante de Ciência da Computação na [CESAR School - Recife](https://www.cesar.school)  

---

## 📜 Licença

Este projeto é acadêmico e não possui licença comercial.
