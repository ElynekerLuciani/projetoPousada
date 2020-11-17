/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elyneker Luciani
 */
public class TableModelReservas extends AbstractTableModel {

    private ArrayList<String[]> linhas;
    private String[] colunas = new String[]{
        "Id Reserva",
        "Cliente",
        "Quarto",
        "Data Entrada",
        "Data Saída"};

    public TableModelReservas(ArrayList<String[]> reservas) {
        if (!reservas.isEmpty()) {
            this.linhas = new ArrayList<String[]>(reservas);
        }
    }

    public TableModelReservas() {
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
        String reserva[] = linhas.get(linhaIndex);
        switch (colunaIndex) {
            case 0:
                return Integer.parseInt(reserva[0]);
            case 1:
                return reserva[1];
            case 2:
                return reserva[2];
            case 3:
                return reserva[3];
            case 4:
                return reserva[4];
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
