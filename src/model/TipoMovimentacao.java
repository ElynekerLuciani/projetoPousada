package model;

/**
 *
 * @author Elyneker Luciani
 */
public enum TipoMovimentacao {
    Entrada, Saida;
    
    public static TipoMovimentacao getEntrada() {
        return Entrada;
    }

    public static TipoMovimentacao getSaida() {
        return Saida;
    }
}
