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
import PO.produto;

/**
 *
 * @author QueroDelivery
 */
public class DAOProduto extends DAOBase {

    private Connection connection;

    public DAOProduto() throws Exception {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public DAOProduto(Connection con) {
        connection = con;
    }

    //pegar todos os produtos existentes retornando-os em um arraylist de produto
    private List getProdutos(int id) throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from produto where cod_prod = " + id + " \n");

            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

    private List getAllProdutos() throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from produto");
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public void insertProduto(produto produto) {
        System.out.println("Inserindo produto");
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into produto values ("
                    + produto.getCod_estab() + ", '" + produto.getNom_prod() + "',"
                    + produto.getValor() + ")");
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
    }

    private List<produto> rsToList(ResultSet rs) throws Exception {
        List<produto> listaResult = new ArrayList<>();
        while (rs.next()) {
            produto prod = new produto();
            prod.setCod_prod(rs.getInt("cod_prod"));
            prod.setCod_estab(rs.getInt("cod_estab"));
            prod.setNom_prod(rs.getString("nom_prod"));
            prod.setValor(rs.getDouble("valor"));
            listaResult.add(prod);
        }
        return listaResult;
    }
}
