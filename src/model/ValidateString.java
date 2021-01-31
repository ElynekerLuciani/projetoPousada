package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidateString {

    /**
     * Método responsável por verificar se uma String possui apenas letras
     *
     * @param entrada
     * @return
     */
    /**
     * Verificamos nesse método se entrada do tipo String é válido. Primeiro é
     * testado se a variável não está vazia e depois verificamos se a entrada
     * possui apenas caracteres válidos.
     */
    public static boolean verifyOnlyLetters(String entrada) {
        if (entrada.isEmpty()) {
            return false;
        }
        return entrada.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü\\s]+$");
    }

    /**
     * Método responsável por verificar se uma String possui apenas números
     *
     * @param entrada
     * @return
     */
    public static boolean verifyOnlyNumbers(String entrada) {
        return entrada.matches("[0-9]+");
    }

    public static boolean verificarData(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
