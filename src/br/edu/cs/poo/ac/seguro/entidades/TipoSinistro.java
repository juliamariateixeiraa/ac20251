package br.edu.cs.poo.ac.seguro.entidades;

public enum TipoSinistro {

    COLISAO(1, "Colisão"),
    INCENDIO(2, "Incêndio"),
    FURTO(3, "Furto"),
    ENCHENTE(4, "Enchente"),
    DEPREDACAO(5, "Depredação");

    private final int codigo;
    private final String nome;

    private TipoSinistro(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public static TipoSinistro getTipoSinistro(int codigo) {
        for (TipoSinistro tipo : TipoSinistro.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        return null;
    }
}
