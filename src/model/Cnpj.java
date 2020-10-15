package model;

/**
 *
 * @author mathe
 */
public final class Cnpj {
    private int[] digitos;
    private final int[] primeiroDigitosValidadores = {5,4,3,2,9,8,7,6,5,4,3,2};
    private final int[] segundoDigitosValidadores = {6,5,4,3,2,9,8,7,6,5,4,3,2};

    public Cnpj(int[] digitos) throws CnpjInvalidoException {
        this.setDigitos(digitos);
        if(!validaCnpj()){
            throw new CnpjInvalidoException();
        }
    }
    
    private int calculaDigito(int contador,int[] validadores){
        int resultado = 0;
        for(int index=0;index<contador;index++){
            resultado+= digitos[index]*validadores[index];
        }
        resultado = resultado % 11;
        if(resultado<2){
            resultado=0;
        }else{
            resultado = 11 - resultado;
        }
        return resultado;
    }
    
    private boolean validaCnpj(){
        boolean verificaPrimeiroDigito = calculaDigito(12,primeiroDigitosValidadores)==digitos[12];
        boolean verificaSegundoDigito = calculaDigito(13,segundoDigitosValidadores)==digitos[13];
        return verificaPrimeiroDigito && verificaSegundoDigito;
    }

    public int[] getDigitos() {
        return digitos;
    }

    public void setDigitos(int[] digitos) throws CnpjInvalidoException {
        if(digitos!=null && digitos.length==14){
            this.digitos = digitos;
        }else{
            throw new CnpjInvalidoException();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder cnpj = new StringBuilder();
        cnpj.append(digitos[0]);
        cnpj.append(digitos[1]);
        cnpj.append(".");
        cnpj.append(digitos[2]);
        cnpj.append(digitos[3]);
        cnpj.append(digitos[4]);
        cnpj.append(".");
        cnpj.append(digitos[5]);
        cnpj.append(digitos[6]);
        cnpj.append(digitos[7]);
        cnpj.append("/");
        cnpj.append(digitos[8]);
        cnpj.append(digitos[9]);
        cnpj.append(digitos[10]);
        cnpj.append(digitos[11]);
        cnpj.append("-");
        cnpj.append(digitos[12]);
        cnpj.append(digitos[13]);
        return cnpj.toString(); 
    }
    
    public static String converteCnpj(int[] digitosCnpj){
        StringBuilder st = new StringBuilder();
        for(int n:digitosCnpj){
            st.append(String.valueOf(n));
        }
        return st.toString();
    }
    
    public static int[] converteCnpj(String digitosCnpj){
        String[] digitos = digitosCnpj.split("");
        int[] numerosCnpj = new int[digitos.length];
        
        for(int i=0;i<digitos.length;i++){
            numerosCnpj[i] = Integer.valueOf(digitos[i]);
        }
        return numerosCnpj;
    }
}
