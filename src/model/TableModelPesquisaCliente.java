package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elyneker Luciani
 */
public class TableModelPesquisaCliente extends AbstractTableModel {

    private List<String[]> linhas;
    private String[] colunas = new String[]{"Id", "Nome", "Documento", "Celular"};

    public TableModelPesquisaCliente(List<String[]> linhas) {
        this.linhas = new ArrayList<String[]>(linhas);
    }

    public TableModelPesquisaCliente() {
        linhas = new ArrayList<String[]>();
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
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int linhaIndex, int colunaIndex) {
        String dado[] = linhas.get(linhaIndex);
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
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String t[] = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                t[0] = aValue.toString();
                break;
            case 1:
                t[1] = aValue.toString();
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void setValueAt(String aValue[], int rowIndex) {
        String t[] = linhas.get(rowIndex);
        t[0] = aValue[0];
        t[1] = aValue[1];
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
    }
}
