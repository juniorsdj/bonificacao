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
public class pedidoConnection {

    private Connection connection;

    public pedidoConnection() {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public List getAllPedidos() throws SQLException {
        return this.getPedidos(0);
    }

    public List getPedidoPorId(int id) throws SQLException {
        return this.getPedidos(id);
    }

    //pegar todos os pedidos existentes retornando-os em um arraylist de pedido
    private List getPedidos(int id) throws SQLException {
        List<pedido> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (id == 0) {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from pedido\n");
            } else {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from pedido\n"
                        + "                         where cod_ped = " + id + "");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido ped = new pedido();
                ped.setCod_ped(rs.getInt("cod_ped"));
                ped.setCod_usu(rs.getInt("usuario"));
                ped.setValor(rs.getDouble("valor"));
                listaResult.add(ped);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            return listaResult;
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

}
