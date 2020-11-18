package model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Elyneker Luciani
 */
public class TipoMovimentacaoComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private ArrayList<TipoMovimentacao> tipoMovimentacao;
    private TipoMovimentacao movimentacaoSelecionada;
    private final static int FIRSTINDEX = 0;

    public TipoMovimentacaoComboBoxModel(ArrayList<Object> lista) throws Exception {
        tipoMovimentacao = new ArrayList<>();
        if (lista.size() > 0) {
            lista.forEach(obj -> tipoMovimentacao.add((TipoMovimentacao) obj));
            setSelectedItem(this.tipoMovimentacao.get(FIRSTINDEX));
        } else {
            throw new Exception("Nenhum Tipo de Movimentação Financeira");
        }
    }

    @Override
    public int getSize() {
        return tipoMovimentacao.size();
    }

    @Override
    public Object getElementAt(int i) {
        return this.tipoMovimentacao.get(i);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof TipoMovimentacao) {
            this.movimentacaoSelecionada = (TipoMovimentacao) anItem;
            fireContentsChanged(this.tipoMovimentacao, 0, this.tipoMovimentacao.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.movimentacaoSelecionada;
    }

    public int getSelectedItemCod() {
        return ((TipoMovimentacao) getSelectedItem()).getIdTipoMovimentacao();
    }

    public void resert() {
        this.tipoMovimentacao.clear();
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
