package br.edu.cs.poo.ac.seguro.testes;

import java.io.File;

public class FileUtils {
    public static void limparDiretorio(String caminho) {
        File dir = new File(caminho);
        if (dir.exists() && dir.isDirectory()) {
            File[] arquivos = dir.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo != null && arquivo.isFile()) {
                        arquivo.delete();
                    }
                }
            }
        }
    }
}

