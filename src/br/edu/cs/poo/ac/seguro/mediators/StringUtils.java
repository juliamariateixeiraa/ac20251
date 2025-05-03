package br.edu.cs.poo.ac.seguro.util;

public class StringUtils {

    /**
     * Verifica se a string fornecida é nula ou contém apenas espaços em branco.
     */
    public static boolean ehNuloOuBranco(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Remove os espaços em branco do início e do fim da string.
     */
    public static String ajustarEspacos(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    /**
     * Converte a string fornecida para letras maiúsculas, após remover espaços nas extremidades.
     */
    public static String paraCaixaAlta(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().toUpperCase();
    }

    /**
     * Remove todos os caracteres não numéricos de uma string.
     *
     */
    public static String removerNaoNumericos(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("[^0-9]", "");
    }

    /**
     * Verifica se a string contém apenas caracteres numéricos.

     */
    public static boolean temSomenteNumeros(String str) {
    if (str == null) {
        return false;
    }
    return str.matches("\\d+");
}
}
