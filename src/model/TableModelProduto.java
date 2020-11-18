package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elyneker Luciani
 */
public class TableModelProduto extends AbstractTableModel {

    private ArrayList<String[]> linha;
    private String[] coluna = new String[]{"Id", "Produto", "Categoria", "Valor"};

    public TableModelProduto(ArrayList<String[]> linhas) {
        if (!linhas.isEmpty()) {
            this.linha = new ArrayList<String[]>(linhas);
        }
    }

    public TableModelProduto() {
        linha = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return linha.size();
    }

    @Override
    public int getColumnCount() {
        return coluna.length;
    }

    @Override
    public Object getValueAt(int linhaIndex, int colunaIndex) {
        String dado[] = linha.get(linhaIndex);
        switch (colunaIndex) {
            case 0:
                return Integer.parseInt(dado[0]);
            case 1:
                return dado[1];
            case 2:
                return dado[2];
            case 3:
                return dado[3];
            default:
                throw new IndexOutOfBoundsException("Coluna vazia");
        }
    }

    public void setValueAt(Object obj, int linhaIndex, int colunaIndex) {
        String dado[] = linha.get(linhaIndex);
        switch (colunaIndex) {
            case 0:
                dado[0] = obj.toString();
                break;
            case 1:
                dado[1] = obj.toString();
                break;
        }
        fireTableCellUpdated(linhaIndex, linhaIndex);
    }

    public void setValueAt(String dado[], int linhaIndex) {
        String t[] = linha.get(linhaIndex);
        t[0] = dado[0];
        t[1] = dado[1];
        fireTableCellUpdated(linhaIndex, 0);
        fireTableCellUpdated(linhaIndex, 1);
    }

    public String getColumnName(int colunaIndex) {
        return coluna[colunaIndex];
    }

}
