/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProdutoDAO;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.bind.ParseConversionEvent;
import model.CategoriaProduto;
import model.Produto;
import model.TableModelProduto;
import view.TelaCadastroProduto;

/**
 *
 * @author Elyneker Luciani
 */
public class TelaCadastroProdutoController {

    private TelaCadastroProduto telaCadastroProduto;
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public TelaCadastroProdutoController(TelaCadastroProduto t) {
        this.telaCadastroProduto = t;
    }

    public void executa(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Cadastrar":
                cadastrarProduto();
                break;
            case "Cancelar":
                limparCampos();
                break;
        }
    }

    private void cadastrarProduto() {
        try {
            if (!telaCadastroProduto.getjTextFieldProduto().getText().trim().isEmpty()
                    && !telaCadastroProduto.getjFormattedTextFieldPreco().getText().trim().isEmpty()) {
                Produto prod = new Produto();
                prod.setProduto(telaCadastroProduto.getjTextFieldProduto().getText().toUpperCase().trim());
                BigDecimal valor = new BigDecimal(telaCadastroProduto.getjFormattedTextFieldPreco()
                        .getText()
                        .replace(" ", "")
                        .replace(".", "")
                        .replace(",", "."));
                prod.setValor(valor);
                prod.setCatProduto(new CategoriaProduto(telaCadastroProduto.getCategoriaProdModelo().getSelectedItemCod(), ""));
                produtoDAO.cadastrarProduto(prod);
                JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Cadastro não realizado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Object> listarCategoriaProdutoCombobox() {
        ArrayList<Object> categoriaProduto = new ArrayList<>();
        try {
            ArrayList<CategoriaProduto> listaDeCategorias = produtoDAO.listarCategorias();
            listaDeCategorias.forEach(cat -> categoriaProduto.add((CategoriaProduto) cat));
        } catch (Exception e) {
            System.out.println("ReservaController.buscarCategoriaProduto: " + e);
        }
        return categoriaProduto;
    }
    
    private void limparCampos() {
        telaCadastroProduto.getjTextFieldProduto().setText("");
        telaCadastroProduto.getjFormattedTextFieldPreco().setText("");
    }

    public void carregarTabelaProduto() {
        try {
            ArrayList<String[]> produtosCadastrados = produtoDAO.buscarProdutosCadastrados();
            if(!produtosCadastrados.isEmpty()) {
                telaCadastroProduto.getjTableProdutos().setModel(new TableModelProduto(produtosCadastrados));
            } else {
                JOptionPane.showMessageDialog(null, "Não produtos cadastrados.", "Pesquisa não realizada!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
    public void preencherCampos() {
        try {
            limparCampos();
            String produto = (String) telaCadastroProduto.getjTableProdutos().getModel().getValueAt(
                    telaCadastroProduto.getjTableProdutos().getSelectedRow(), 1);
            telaCadastroProduto.getjTextFieldProduto().setText(produto);
            
            telaCadastroProduto.getjComboBoxCategoria().setSelectedItem(
                    telaCadastroProduto.getjTableProdutos().getModel().getValueAt(
                            telaCadastroProduto.getjTableProdutos().getSelectedRow(), 2));
            
            
            
            System.out.println(telaCadastroProduto.getjTableProdutos().getModel().getValueAt(
                            telaCadastroProduto.getjTableProdutos().getSelectedRow(), 2));
        } catch (Exception e) {
            System.out.println("TelaCadastroProdutoController.preencherCampos: " + e);
        }
    }
}
