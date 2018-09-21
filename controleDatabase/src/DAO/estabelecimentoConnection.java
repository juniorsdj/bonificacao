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
public class estabelecimentoConnection {
     private Connection connection;

    /**
     *
     */
    public estabelecimentoConnection() {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }
    
     //pegar todos os estabelecimentos existentes retornando-os em um arraylist de estabelecimento
    public List getEstabelecimentos() throws SQLException {
        List<estabelecimento> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("use web"
                    + "                             select *\n"
                    + "                             from estabelecimento\n");
            rs = stmt.executeQuery();
            while (rs.next()) {
                estabelecimento estab = new estabelecimento();
                estab.setCod_estab(rs.getInt("cod_est"));
                estab.setNom_estab(rs.getString("nom_est"));
                listaResult.add(estab);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            return listaResult;
        }
    }
      public void insertEstabelecimento(estabelecimento estabelecimento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}
