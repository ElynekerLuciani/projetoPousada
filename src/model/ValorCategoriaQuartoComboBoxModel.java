package model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Elyneker Luciani
 */
public class ValorCategoriaQuartoComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private ArrayList<CategoriaQuarto> listaCategoriaQuarto;
    private CategoriaQuarto categoriaSelecionada;
    private final static int FIRSTINDEX = 0;

    public ValorCategoriaQuartoComboBoxModel(ArrayList<Object> lista) throws Exception {
        listaCategoriaQuarto = new ArrayList<>();
        if (lista.size() > 0) {
            lista.forEach(obj -> listaCategoriaQuarto.add((CategoriaQuarto) obj));
            setSelectedItem(this.listaCategoriaQuarto.get(FIRSTINDEX));
        } else {
            throw new Exception("Lista Categoria Vazia");
        }
    }

    @Override
    public int getSize() {
        return listaCategoriaQuarto.size();
    }

    @Override
    public Object getElementAt(int i) {
        return this.listaCategoriaQuarto.get(i);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof CategoriaQuarto) {
            this.categoriaSelecionada = (CategoriaQuarto) anItem;
            fireContentsChanged(this.listaCategoriaQuarto, 0, this.listaCategoriaQuarto.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.categoriaSelecionada;
    }

    public int getSelectedItemCod() {
        return ((CategoriaQuarto) getSelectedItem()).getIdCategoriaQuarto();
    }

    public void resert() {
        this.listaCategoriaQuarto.clear();
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
