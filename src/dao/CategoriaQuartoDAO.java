/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.CategoriaQuarto;

/**
 *
 * @author Elyneker Luciani
 */
public class CategoriaQuartoDAO {
    
    public CategoriaQuarto buscarDadosCategoria(int qnthospede) {
        CategoriaQuarto categoria = new CategoriaQuarto();
        try {
            String sql ="";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
        } catch (Exception e) {
        } finally {
            
        }
        return categoria;
    }
    
}
