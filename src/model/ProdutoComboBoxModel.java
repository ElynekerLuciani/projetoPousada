/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Elyneker Luciani
 */
public class ProdutoComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    private ArrayList<Produto> listaProduto;
    private Produto produtoSelecionado;
    private final static int FIRSTINDEX = 0;
    
    public ProdutoComboBoxModel() {
        this.listaProduto = new ArrayList<>();
    }
    
    public ProdutoComboBoxModel(ArrayList<Object> lista) throws Exception {
        listaProduto = new ArrayList<>();
        if(lista.size() > 0) {
            lista.forEach(obj -> listaProduto.add((Produto) obj ));
            setSelectedItem(this.listaProduto.get(FIRSTINDEX));
        } else {
            throw new Exception("Lista de Produtos Vazia");
        }
    }

    @Override
    public int getSize() {
        return listaProduto.size();
    }

    @Override
    public Object getElementAt(int i) {
        return this.listaProduto.get(i);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if(anItem instanceof Produto) {
            this.produtoSelecionado = (Produto) anItem;
            fireContentsChanged(this.listaProduto, 0, this.listaProduto.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.produtoSelecionado;
    }
    
    public int getSelectedItemCod() {
        return((Produto) getSelectedItem()).getIdProduto();
    }
    
    public BigDecimal getValor() {
        BigDecimal valor = ((Produto)getSelectedItem()).getValor();
        return valor;
    }
    
    public String getNomeProduto() {
        return ((Produto)getSelectedItem()).getProduto();
    }
    
    public void resert() {
        this.listaProduto.clear();
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
