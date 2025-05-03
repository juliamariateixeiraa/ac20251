package br.edu.cs.poo.ac.seguro.testes;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ComparadoraObjetosSerial {
    
    /**
     * Compara dois objetos serializáveis convertendo-os em arrays de bytes
     * e verificando se todos os bytes são idênticos.
     * 
     * @param s1 primeiro objeto
     * @param s2 segundo objeto
     * @return true se os dois objetos forem iguais na serialização, false caso contrário
     */
    public static boolean compareObjectsSerial(Serializable s1, Serializable s2) {
        try {
            ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
            ObjectOutputStream oos1 = new ObjectOutputStream(bos1);
            oos1.writeObject(s1);

            ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(bos2);
            oos2.writeObject(s2);

            byte[] b1 = bos1.toByteArray();
            byte[] b2 = bos2.toByteArray();

            if (b1.length != b2.length) {
                return false;
            }

            for (int i = 0; i < b1.length; i++) {
                if (b1[i] != b2[i]) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Erro na comparação de objetos serializados", e);
        }
    }
}

