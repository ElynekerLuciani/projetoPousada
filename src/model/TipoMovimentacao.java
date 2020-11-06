package model;

/**
 *
 * @author Elyneker Luciani
 */
public class TipoMovimentacao {
   private int idTipoMovimentacao = 0;
   private String tipoMovimentacao;

    public TipoMovimentacao(int id, String tipoMovimentacao) {
        this.idTipoMovimentacao = id;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getIdTipoMovimentacao() {
        return idTipoMovimentacao;
    }

    public void setIdTipoMovimentacao(int idTipoMovimentacao) {
        this.idTipoMovimentacao = idTipoMovimentacao;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    @Override
    public String toString() {
        return getTipoMovimentacao();
    }
    
    
  
}
