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
public class produtoConnection {

    private Connection connection;

    public produtoConnection() {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public List getAllProdutos() throws SQLException {
        return this.getProdutos(0);
    }

    public produto getProdutoId(int id) throws SQLException {
        return (produto) this.getProdutos(id).get(0);
    }

    //pegar todos os produtos existentes retornando-os em um arraylist de produto
    private List getProdutos(int id) throws SQLException {
        List<produto> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (id == 0) {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from produto\n");
            } else {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from produto"
                        + "                             where cod_prod = " + id + " \n");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                produto prod = new produto();
                prod.setCod_prod(rs.getInt("cod_prod"));
                prod.setCod_estab(rs.getInt("cod_estab"));
                prod.setNom_prod(rs.getString("nom_prod"));
                prod.setValor(rs.getDouble("valor"));
                listaResult.add(prod);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            return listaResult;
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
}
