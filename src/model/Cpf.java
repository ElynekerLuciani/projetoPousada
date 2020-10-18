package model;

import exception.CpfInvalidoException;



/**
 *
 * @author mathe
 */
public final class Cpf {

    private int[] digitos;

    public Cpf(int[] digitos) throws CpfInvalidoException {
        this.setDigitos(digitos);
        if (!this.verificaCpf()) {
            throw new CpfInvalidoException();
        }
    }

    public Cpf() {
        super();
    }

    private int calculaDigito(int contador) {
        int resultado = 0;
        int contadorAux = contador;
        for (int index = 0; index < contadorAux - 1; index++) {
            resultado += (digitos[index] * contador);
            contador--;
        }
        resultado = resultado * 10;
        resultado = resultado % 11;
        if (resultado == 10) {
            resultado = 0;
        }
        return resultado;
    }

    private boolean verificaCpf() {
        boolean verificaPrimeiroDigito = calculaDigito(10) == digitos[9];
        boolean verificaSegundoDigito = calculaDigito(11) == digitos[10];
        return verificaPrimeiroDigito && verificaSegundoDigito;
    }

    public int[] getDigitos() {
        return digitos;
    }

    public void setDigitos(int[] digitos) throws CpfInvalidoException {
        if (digitos != null && digitos.length == 11) {
            this.digitos = digitos;
        } else {
            throw new CpfInvalidoException();
        }
    }

    @Override
    public String toString() {
        StringBuilder cpf = new StringBuilder();
        cpf.append(digitos[0]);
        cpf.append(digitos[1]);
        cpf.append(digitos[2]);
        cpf.append(".");
        cpf.append(digitos[3]);
        cpf.append(digitos[4]);
        cpf.append(digitos[5]);
        cpf.append(".");
        cpf.append(digitos[6]);
        cpf.append(digitos[7]);
        cpf.append(digitos[8]);
        cpf.append("-");
        cpf.append(digitos[9]);
        cpf.append(digitos[10]);
        return cpf.toString();
    }

    public static String converteCpf(int[] digitosCpf) {
        StringBuilder st = new StringBuilder();
        for (int n : digitosCpf) {
            st.append(String.valueOf(n));
        }
        return st.toString();
    }

    public static int[] converteCpf(String digitosCpf) {
        if (digitosCpf != null) {
            String[] digitos = digitosCpf.split("");
            int[] numerosCpf = new int[digitos.length];
            for (int i = 0; i < digitos.length; i++) {
                numerosCpf[i] = Integer.valueOf(digitos[i]);
            }
            return numerosCpf;
        }else{
            return null;
        }
    }

    public static String removerFormatacaoCpf(String cpfFormatado) {
        String cpfSemFormatacao = cpfFormatado.replace(".", "");
        cpfSemFormatacao = cpfSemFormatacao.replace("-", "");
        return cpfSemFormatacao;
    }

}
