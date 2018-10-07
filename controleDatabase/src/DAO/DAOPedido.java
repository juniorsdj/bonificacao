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
import PO.pedido;

/**
 *
 * @author QueroDelivery
 */
public class DAOPedido extends DAOBase {

    private Connection connection;

    public DAOPedido() throws Exception {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public DAOPedido(Connection con) {
        connection = con;
    }

    public List getAllPedidos() throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from pedido");
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public List getPedidoPorId(int id) throws Exception {
        try {
            stmt = connection.prepareStatement("use web"
                    + "                             select *\n"
                    + "                             from pedido\n"
                    + "                         where cod_ped = " + id + "");
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public int insertPedido(pedido pedido) throws SQLException {
        System.out.println("Inserindo pedido");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("insert into pedido values ("
                    + pedido.getValor() + ", " + pedido.getCod_usu() + ")");
            stmt.execute();
            stmt = connection.prepareStatement("select IDENT_CURRENT('pedido') as cod_ped");

            rs = stmt.executeQuery();
            rs.next();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
        return rs.getInt("cod_ped");
    }

    private List<pedido> rsToList(ResultSet rs) throws Exception {
        List<pedido> listaResult = new ArrayList<>();
        while (rs.next()) {
            pedido ped = new pedido();
            ped.setCod_ped(rs.getInt("cod_ped"));
            ped.setCod_usu(rs.getInt("usuario"));
            ped.setValor(rs.getDouble("valor"));
            listaResult.add(ped);
        }
        return listaResult;
    }
}
