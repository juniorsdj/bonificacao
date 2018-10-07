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
import PO.carrinho;

/**
 *
 * @author QueroDelivery
 */
public class DAOCarrinho extends DAOBase {

    

    public DAOCarrinho() throws Exception {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }
    
    public DAOCarrinho(Connection con) {
        connection = con;
    }

    public List<carrinho> getAll() throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from carrinho");
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }


    public List<carrinho> getCarrinho(int pedidoId) throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from carrinho where cod_ped=?");
            stmt.setObject(1, pedidoId);
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

    private List<carrinho> rsToList(ResultSet rs) throws Exception {
        List<carrinho> listaResult = new ArrayList<>();
        while (rs.next()) {
            carrinho carrinho = new carrinho();
            carrinho.setCod_carrinho(rs.getInt("cod_carrinho"));
            carrinho.setCod_ped(rs.getInt("cod_ped"));
            carrinho.setCod_prod(rs.getInt("cod_prod"));
            carrinho.setQtd(rs.getInt("qtd"));
            carrinho.setValor_un(rs.getFloat("valor_un"));
            listaResult.add(carrinho);
        }
        return listaResult;
    }

    public void insertCarrinho(carrinho carrinho) throws Exception{
        System.out.println("inserindo no carrinho");
        try {
            stmt = connection.prepareStatement("insert into carrinho values (?, ?, ?, ?)");
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

}
