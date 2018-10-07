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
public class DAOFranquia extends DAOBase {

    private Connection connection;

    public DAOFranquia() throws Exception {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public DAOFranquia(Connection con) {
        connection = con;
    }

    //pegar todas as franquias existentes retornando-os em um arraylist de franquia
    public List getFranquias() throws Exception {
        List<franquia> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("use webselect * from franquia\n");
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public void insertFranquia(franquia franquia) throws SQLException {
        System.out.println("Inserindo franquia");
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into franquia values (?)");
            stmt.setObject(1, franquia.getNom_franq());
            stmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

    private List<franquia> rsToList(ResultSet rs) throws Exception {
        List<franquia> listaResult = new ArrayList<>();
        while (rs.next()) {
            franquia franq = new franquia();
            franq.setCod_franq(rs.getInt("cod_franq"));
            franq.setNom_franq(rs.getString("nom_franq"));
            listaResult.add(franq);
        }
        return listaResult;
    }
}
