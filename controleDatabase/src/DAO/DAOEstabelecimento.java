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
import PO.estabelecimento;

/**
 *
 * @author QueroDelivery
 */
public class DAOEstabelecimento extends DAOBase {

    private Connection connection;

    /**
     *
     */
    public DAOEstabelecimento() throws Exception {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public DAOEstabelecimento(Connection con) {
        connection = con;
    }
 
    public List getEstabelecimentos() throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from estabelecimento\n");
            rs = stmt.executeQuery();
           return rsToList(rs);
        } catch (SQLException ex) {
            throw ex;
        } finally {
              close(connection, stmt, rs);
        }
    }

    public void insertEstabelecimento(estabelecimento estabelecimento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<estabelecimento> rsToList(ResultSet rs) throws Exception {
        List<estabelecimento> listaResult = new ArrayList<>();
        while (rs.next()) {
            estabelecimento estab = new estabelecimento();
            estab.setCod_estab(rs.getInt("cod_est"));
            estab.setNom_estab(rs.getString("nom_est"));
            listaResult.add(estab);
        }
        return listaResult;
    }

}
