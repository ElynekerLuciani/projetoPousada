/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import model.Estado;

/**
 *
 * @author Elyneker
 */
public class EstadoComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    private ArrayList<Estado> listaEstado;
    private Estado estadoSelecionado;
    private final static int FIRSTINDEX = 0;
    
    public EstadoComboBoxModel() {
        this.listaEstado = new ArrayList<Estado>();
    }
    
    public EstadoComboBoxModel(ArrayList<Object> lista) {
        listaEstado = new ArrayList<>();
        lista.forEach(obj -> listaEstado.add((Estado) obj));
        if(getSize() > 0) {
            setSelectedItem(this.listaEstado.get(FIRSTINDEX));
        }
    }
    
    @Override
    public int getSize() {
        return listaEstado.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaEstado.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if(anItem instanceof Estado) {
            this.estadoSelecionado = (Estado) anItem;
            fireContentsChanged(this.listaEstado, 0, this.listaEstado.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.estadoSelecionado;
    }
    
    public int getSelecteditemCod() {
        return ((Estado) getSelectedItem()).getIdEstado();
    }
    
    public void reset() {
        this.listaEstado.clear();
    }
    
    @Override
    public String toString() {
        return "";
    }
    
    
}
