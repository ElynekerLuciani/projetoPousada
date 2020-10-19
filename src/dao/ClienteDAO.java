/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;
import model.Cpf;

/**
 *
 * @author Elyneker Luciani
 */
public class ClienteDAO {

    public void cadastrarClientePF(Cliente cliente) throws SQLException, ClassNotFoundException {
        String sqlDocumento
                = "INSERT INTO documento(documento, id_tipo) VALUES(?, 1);"; //1 - Valor para CPF
        String sqlCliente
                = "INSERT INTO cliente(nome, celular, celular_opcional, telefone, tipo_cliente, id_documento) "
                + "VALUES(?, ?, ?, ?, ?, ?);";
        String sqlEndereco
                = "INSERT INTO endereco(id_cliente, endereco, id_cidade) VALUES(?, ?, ?);";
        try {
            //INSERINDO DADOS NA TABELA DOCUMENTOS
            PreparedStatement stmt_1 = connection.ConnectionFactory.getConnection().prepareStatement(sqlDocumento);
            stmt_1.setString(1, cliente.getDocumento().getCpf() != null ? Cpf.converteCpf(cliente.getDocumento().getCpf().getDigitos()) : null);
            stmt_1.executeUpdate();
            stmt_1.close();

            //BUSCANDO O ID DESSA INSERÇÃO DO DOCUMENTO
            String idDocumento = buscarIdDocumento(Cpf.converteCpf(cliente.getDocumento().getCpf().getDigitos()));

            //INSERINDO DADOS NA TABELA CLIENTE
            PreparedStatement stmt_2 = connection.ConnectionFactory.getConnection().prepareStatement(sqlCliente);
            stmt_2.setString(1, cliente.getNomeCliente());
            stmt_2.setString(2, cliente.getContatoCliente().getCelular());
            stmt_2.setString(3, cliente.getContatoCliente().getCelularOpcional());
            stmt_2.setString(4, cliente.getContatoCliente().getTelefone());
            stmt_2.setString(5, String.valueOf(1)); //1 - PESSOA FÍSICA
            stmt_2.setString(6, String.valueOf(idDocumento));
            stmt_2.executeUpdate();
            stmt_2.close();

            //BUSCANDO O ID DO CLIENTE INSERIDO
            String idCliente = buscarIdNovoClienteCadastrado(idDocumento);

            //INSERINDO DADOS NA TABELA DE ENDEREÇO
            PreparedStatement stmt_3 = connection.ConnectionFactory.getConnection().prepareStatement(sqlEndereco);
            stmt_3.setString(1, idCliente);
            stmt_3.setString(2, cliente.getEnderecoCliente().getEndereco());
            stmt_3.setString(3, String.valueOf(cliente.getEnderecoCliente().getCidade().getIdCidade()));
            stmt_3.executeUpdate();
            stmt_3.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao cadastrar um novo cliente: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
    }

    private String buscarIdDocumento(String cpf) throws SQLException, ClassNotFoundException {
        String idDocumento = null;
        String sql = "SELECT id_documento FROM documento WHERE documento = ?;";
        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idDocumento = rs.getString("id_documento");
            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao buscar id do documento: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return idDocumento;
    }

    private String buscarIdNovoClienteCadastrado(String idDocumento) throws SQLException, ClassNotFoundException {
        String idCliente = null;
        String sql
                = "SELECT Cliente.id_cliente, cliente.id_documento "
                + "FROM cliente "
                + "LEFT JOIN documento "
                + "ON documento.id_documento = ?;";
        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setString(1, idDocumento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCliente = rs.getString("id_cliente");
            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao buscar id do novo cliente: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return idCliente;
    }

    private String buscarIdTipoCliente(String tipo) throws SQLException, ClassNotFoundException {
        String idTipo = null;
        String sql = "SELECT id_tipo_cliente FROM tipo_cliente WHERE tipo_cliente = ?;";
        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idTipo = rs.getString("id_tipo_cliente");
            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao buscar id do documento: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return idTipo;
    }

    public ArrayList<String[]> buscarNomeDocumentoCliente(String buscar) {
        ArrayList<String[]> dados = new ArrayList<>();
        String sql
                = "SELECT cliente.id_cliente, cliente.nome, documento.documento "
                + "FROM cliente "
                + "LEFT JOIN documento "
                + "ON documento.id_documento = cliente.id_documento "
                + "WHERE cliente.nome LIKE ? "
                + "OR documento.documento LIKE ?;";
        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setString(1, buscar + "%");
            stmt.setString(2, buscar + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String dadosClientes[] = new String[3];
                dadosClientes[0] = rs.getString("id_cliente");
                dadosClientes[1] = rs.getString("nome");
                dadosClientes[2] = rs.getString("documento");
                dados.add(dadosClientes);
            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ClienteDAO.buscarNomeDocumentoCliente: " + e);
        }
        return dados;
    }

}
