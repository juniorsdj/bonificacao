/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import PO.franquia;

/**
 *
 * @author QueroDelivery
 */
public class franquiaConnection {

    private Connection connection;

    public franquiaConnection() {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    //pegar todas as franquias existentes retornando-os em um arraylist de franquia
    public List getFranquias() throws SQLException {
        List<franquia> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("use web"
                    + "                             select *\n"
                    + "                             from franquia\n");
            rs = stmt.executeQuery();
            while (rs.next()) {
                franquia franq = new franquia();
                franq.setCod_franq(rs.getInt("cod_franq"));
                franq.setNom_franq(rs.getString("nom_franq"));
                listaResult.add(franq);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            return listaResult;
        }
    }

    public void insertFranquia(franquia franquia) {
        System.out.println("Inserindo franquia");
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into franquia values ('"
                    + franquia.getNom_franq() + "' )");
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
    }

}
