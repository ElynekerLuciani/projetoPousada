package componentes;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Elyneker Luciani
 */
public class Cbx_QuantidadeHospede extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private ArrayList<Integer> qntPessoaQuarto;
    private Integer qntSelecionada;
    private final static int FIRSTINDEX = 0;

    public Cbx_QuantidadeHospede() {
        this.qntPessoaQuarto = new ArrayList<>();
    }

    public Cbx_QuantidadeHospede(ArrayList<Object> numero) {
        try {
            this.qntPessoaQuarto = new ArrayList<>();
            numero.forEach(obj -> qntPessoaQuarto.add((Integer) obj));
            if (getSize() > 0) {
                setSelectedItem(this.qntPessoaQuarto.get(FIRSTINDEX));
            }
        } catch (Exception e) {
            System.out.println("cbx_quantidade" + e);
        }
    }

    @Override
    public int getSize() {
        return this.qntPessoaQuarto.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.qntPessoaQuarto.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        try {
            if (anItem instanceof Integer) {
                this.qntSelecionada = (Integer) anItem;
                fireContentsChanged(this.qntPessoaQuarto, 0, this.qntPessoaQuarto.size());
            }
        } catch (Exception e) {
            System.out.println("erro no modelo" + e);
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.qntSelecionada;
    }

    public void resert() {
        this.qntPessoaQuarto.clear();
    }

    @Override
    public String toString() {
        return "";
    }

}
