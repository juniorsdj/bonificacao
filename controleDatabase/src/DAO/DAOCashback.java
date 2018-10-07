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
import PO.cashback;
import PO.estabelecimento;
import PO.franquia;
import PO.pedido;
import PO.produto;
import PO.usuario;

/**
 *
 * @author QueroDelivery
 */
public class DAOCashback extends DAOBase {

    private Connection connection;

    public DAOCashback() throws Exception {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public DAOCashback(Connection con) {
        connection = con;
    }

    public List getAllCashback() throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from cashback");
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close(connection, stmt, rs);
        }

    }

    public List getCashbackPorUsuarioId(int id) throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from cashback where usu_bene=?");
            stmt.setObject(1, id);
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public List selectCashbackUsuario(usuario usu) throws Exception {
        return getCashbackPorUsuarioId(usu.getCod_usu());
    }

    public void insertCashback(cashback cashback) throws SQLException {
        System.out.println("Inserindo cashback");
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into cashback values ( ?,?,? )");
            stmt.setObject(1, cashback.getCod_ped());
            stmt.setObject(2, cashback.getUsu_bene());
            stmt.setObject(3, cashback.getValor());
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
             close(connection, stmt, rs);
        }
    }

    private List<cashback> rsToList(ResultSet rs) throws Exception {
        List<cashback> listaResult = new ArrayList<>();
        while (rs.next()) {
            cashback cash = new cashback();
            cash.setCod_cash(rs.getInt("cod_cash"));
            cash.setCod_ped(rs.getInt("pedido"));
            cash.setUsu_bene(rs.getInt("usu_bene"));
            cash.setValor(rs.getFloat("valor"));
            listaResult.add(cash);
        }
        return listaResult;
    }

}
