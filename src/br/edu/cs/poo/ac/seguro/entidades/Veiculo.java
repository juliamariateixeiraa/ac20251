package br.edu.cs.poo.ac.seguro.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String placa;
    private int ano;
    private SeguradoEmpresa proprietarioEmpresa;
    private SeguradoPessoa proprietarioPessoa;
    private CategoriaVeiculo categoria;
}
