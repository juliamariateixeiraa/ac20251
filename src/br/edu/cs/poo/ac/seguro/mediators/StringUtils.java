package br.edu.cs.poo.ac.seguro.util;

public class StringUtils {

    /**
     * Verifica se a string fornecida é nula ou contém apenas espaços em branco.
     *
     * @param str a string a ser verificada
     * @return true se a string for nula ou em branco, false caso contrário
     */
    public static boolean ehNuloOuBranco(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Remove os espaços em branco do início e do fim da string.
     *
     * @param str a string a ser ajustada
     * @return a string sem espaços em branco nas extremidades ou null se a entrada for null
     */
    public static String ajustarEspacos(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    /**
     * Converte a string fornecida para letras maiúsculas, após remover espaços nas extremidades.
     *
     * @param str a string a ser convertida
     * @return string em letras maiúsculas ou null se a entrada for null
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
     * @param str a string original
     * @return a string contendo apenas números, ou null se a entrada for null
     */
    public static String removerNaoNumericos(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("[^0-9]", "");
    }

    /**
     * Verifica se a string contém apenas caracteres numéricos.
     *
     * @param str a string a ser verificada
     * @return true se a string contiver apenas números, false caso contrário ou null se a entrada for null
     */
    public static boolean temSomenteNumeros(String str) {
    if (str == null) {
        return false;
    }
    return str.matches("\\d+");
}
}
