package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elyneker Luciani
 */
public class TableModelPedidos extends AbstractTableModel {

    private ArrayList<String[]> linhas;
    private String[] colunas = new String[]{"Id", "Produto", "Preço", "Qnt", "Total"};

    public TableModelPedidos(ArrayList<String[]> linha) {
        if (!linha.isEmpty()) {
            this.linhas = new ArrayList<String[]>(linha);
        }
    }

    public TableModelPedidos() {
        linhas = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linhaIndex, int colunaIndex) {
        String pedidos[] = linhas.get(linhaIndex);
        switch (colunaIndex) {
            case 0:
                return Integer.parseInt(pedidos[0]);
            case 1:
                return pedidos[1];
            case 2:
                return pedidos[2];
            case 3:
                return pedidos[3];
            case 4:
                return pedidos[4];
            default:
                throw new IndexOutOfBoundsException("Coluna vazia");
        }
    }

    @Override
    public void setValueAt(Object obj, int linhaIndex, int colunaIndex) {
        String dado[] = linhas.get(linhaIndex);
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
        String t[] = linhas.get(linhaIndex);
        t[0] = dado[0];
        t[1] = dado[1];
        fireTableCellUpdated(linhaIndex, 0);
        fireTableCellUpdated(linhaIndex, 1);
    }

    @Override
    public String getColumnName(int colunaIndex) {
        return colunas[colunaIndex];
    }

}
