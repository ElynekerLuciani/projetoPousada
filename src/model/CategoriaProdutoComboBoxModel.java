package model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Elyneker Luciani
 */
public class CategoriaProdutoComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private ArrayList<CategoriaProduto> listaCategoriaProduto;
    private CategoriaProduto categoriaSelecionada;
    private final static int FIRSTINDEX = 0;

    public CategoriaProdutoComboBoxModel(ArrayList<Object> lista) throws Exception {
        listaCategoriaProduto = new ArrayList<>();
        if (lista.size() > 0) {
            lista.forEach(obj -> listaCategoriaProduto.add((CategoriaProduto) obj));
            setSelectedItem(this.listaCategoriaProduto.get(FIRSTINDEX));
        } else {
            throw new Exception("Lista Categoria Vazia");
        }
    }

    @Override
    public int getSize() {
        return listaCategoriaProduto.size();
    }

    @Override
    public Object getElementAt(int i) {
        return this.listaCategoriaProduto.get(i);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof CategoriaProduto) {
            this.categoriaSelecionada = (CategoriaProduto) anItem;
            fireContentsChanged(this.listaCategoriaProduto, 0, this.listaCategoriaProduto.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.categoriaSelecionada;
    }

    public int getSelectedItemCod() {
        return ((CategoriaProduto) getSelectedItem()).getIdCatProduto();
    }

    public void resert() {
        this.listaCategoriaProduto.clear();
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
