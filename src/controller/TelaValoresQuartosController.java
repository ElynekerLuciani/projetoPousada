/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuartoDAO;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import model.CategoriaQuarto;
import model.TableModelValoresQuartos;
import view.TelaValoresQuartos;

/**
 *
 * @author Elyneker Luciani
 */
public class TelaValoresQuartosController {

    private TelaValoresQuartos telaValoresQuartos;
    private final QuartoDAO quartoDAO = new QuartoDAO();

    public TelaValoresQuartosController(TelaValoresQuartos telaValoresQuartos) {
        this.telaValoresQuartos = telaValoresQuartos;
    }

    public void executar(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Cadastrar":
                cadastrarCategoria();
                break;
            case "Editar":
                editarDados();
                break;
            case "Cancelar":
                limparCampos();
                break;
        }
    }

    public void carregarTabelaValores() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<String[]> valoresQuartos = quartoDAO.buscarCategoriasQuartoCadastrado();
            if (!valoresQuartos.isEmpty()) {
                telaValoresQuartos.getjTableCategoriasQuarto().setModel(new TableModelValoresQuartos(valoresQuartos));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Valor Definido.", "Valores Categorias Quartos", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            System.out.println("TelaValoresQuartosController.carregarTabelaValores: " + e);
        }
    }

    public ArrayList<Object> listarCategoriasQuartos() {
        ArrayList<Object> categoriaQuartos = new ArrayList<>();
        try {
            ArrayList<CategoriaQuarto> listaDeCategorias = quartoDAO.listarCategorias();
            listaDeCategorias.forEach(cat -> categoriaQuartos.add((CategoriaQuarto) cat));
        } catch (Exception e) {
            System.out.println("TelaValoresQuartosController.listarCategoriasQuarto: " + e);
        }
        return categoriaQuartos;
    }

    public void preencherCampos() {
        try {
            int idCategoria = (int) telaValoresQuartos.getjTableCategoriasQuarto().getModel().getValueAt(
                    telaValoresQuartos.getjTableCategoriasQuarto().getSelectedRow(), 0);

            CategoriaQuarto listaDeCategorias = quartoDAO.listarCategoriasId(idCategoria);
            telaValoresQuartos.getjComboBoxCategorias().setSelectedIndex((listaDeCategorias.getIdCategoriaQuarto()) - 1);

            String qntPessoas = (String) telaValoresQuartos.getjTableCategoriasQuarto().getModel().getValueAt(
                    telaValoresQuartos.getjTableCategoriasQuarto().getSelectedRow(), 2);
            telaValoresQuartos.getjTextFieldQntPessoas().setText(qntPessoas);

            String valor = (String) telaValoresQuartos.getjTableCategoriasQuarto().getModel().getValueAt(
                    telaValoresQuartos.getjTableCategoriasQuarto().getSelectedRow(), 3);
            telaValoresQuartos.getjFormattedTextFieldValor().setText(valor);
            alterarBotao();

        } catch (Exception e) {
            System.out.println("TelaValoresQuartoController.preencherCampos: " + e);
        }
    }

    private void alterarBotao() {
        telaValoresQuartos.getBtnCadastrar().setText("Editar");
    }

    private void cadastrarCategoria() {
       try {
            if (!telaValoresQuartos.getjTextFieldQntPessoas().getText().trim().isEmpty()
                    && Integer.parseInt(telaValoresQuartos.getjTextFieldQntPessoas().getText().trim()) >= 1
                    && !telaValoresQuartos.getjFormattedTextFieldValor().getText().trim().isEmpty()) {

                CategoriaQuarto novo = new CategoriaQuarto(
                        String.valueOf(telaValoresQuartos.getValorCategoria().getSelectedItemCod()),
                        Integer.parseInt(telaValoresQuartos.getjTextFieldQntPessoas().getText().trim()),
                        new BigDecimal(telaValoresQuartos.getjFormattedTextFieldValor()
                                .getText()
                                .replace(" ", "")
                                .replace(".", "")
                                .replace(",", ".")));

                quartoDAO.cadastrarValoresCategoriaQuarto(novo);
                JOptionPane.showMessageDialog(null, "Valor da Categoria Cadastrado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarTabelaValores();
            } else {
                JOptionPane.showMessageDialog(null, "Verifique a quantidade de hóspedes e o valor.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("TelaValoresQuartosController.cadastrarCategoria: " + e);
        }
    }

    private void editarDados() {
        try {
            if (Integer.parseInt(telaValoresQuartos.getjTextFieldQntPessoas().getText().trim()) >= 1
                    && !telaValoresQuartos.getjFormattedTextFieldValor().getText().trim().isEmpty()) {

                int idCategoria = (int) telaValoresQuartos.getjTableCategoriasQuarto().getModel().getValueAt(
                        telaValoresQuartos.getjTableCategoriasQuarto().getSelectedRow(), 0);

                CategoriaQuarto novo = new CategoriaQuarto(
                        idCategoria,
                        String.valueOf(telaValoresQuartos.getValorCategoria().getSelectedItemCod()),
                        Integer.parseInt(telaValoresQuartos.getjTextFieldQntPessoas().getText().trim()),
                        new BigDecimal(telaValoresQuartos.getjFormattedTextFieldValor()
                                .getText()
                                .replace(" ", "")
                                .replace(".", "")
                                .replace(",", ".")));

                quartoDAO.editarDados(novo);
                JOptionPane.showMessageDialog(null, "Valor da Categoria Editado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarTabelaValores();
            }
        } catch (Exception e) {
            System.out.println("TelaValoresQuartosController.editarDados: " + e);
        }
    }

    private void limparCampos() {
        try {
            telaValoresQuartos.getBtnCadastrar().setText("Cadastrar");
            telaValoresQuartos.getjFormattedTextFieldValor().setValue(null);
            telaValoresQuartos.getjTextFieldQntPessoas().setText("");
            carregarTabelaValores();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("TelaValoresQuartoController.limparCampos: " + e);
        }
    }

}
