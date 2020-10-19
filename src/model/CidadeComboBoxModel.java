/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import model.Cidade;

/**
 *
 * @author Elyneker
 */
public class CidadeComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    private ArrayList<Cidade> listaCidade;
    private Cidade cidadeSelecionada;
    private final static int FIRSTINDEX = 0;
    
    public CidadeComboBoxModel() {
        this.listaCidade = new ArrayList<Cidade>();
    }
    
    public CidadeComboBoxModel(ArrayList<Object> lista) {
        listaCidade = new ArrayList<>();
        lista.forEach(obj -> listaCidade.add((Cidade) obj));
        if(getSize() > 0) {
            setSelectedItem(this.listaCidade.get(FIRSTINDEX));
        }
    }

    @Override
    public int getSize() {
        return listaCidade.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaCidade.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
       if(anItem instanceof Cidade) {
           this.cidadeSelecionada = (Cidade) anItem;
           fireContentsChanged(this.listaCidade, 0, this.listaCidade.size());
       }
    }

    @Override
    public Object getSelectedItem() {
        return this.cidadeSelecionada;
    }
    
    public int getSelectedItemCod() {
        return ((Cidade) getSelectedItem()).getIdCidade();
    }
    
    public void reset() {
        this.listaCidade.clear();
    }

    @Override
    public String toString() {
        return "";
    }
   
    
}
