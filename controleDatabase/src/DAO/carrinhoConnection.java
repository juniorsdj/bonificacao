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
public class carrinhoConnection {

    private Connection connection;

    public carrinhoConnection() {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

     // pegar todos os Itens de carrinhos se tiver com o id = 0, caso exista pedidoId
    // trazer os itens daquele pedido
    public List getCarrinho(int pedidoId) throws SQLException {
        List<carrinho> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // se tiver pedidoId ele faz o select com o pedido id, 
            //se não traz tudo
            if (pedidoId != 0) {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from carrinho\n"
                        + "                             where " + pedidoId + " = cod_ped");
            } else {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from carrinho\n");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                carrinho carrinho = new carrinho();
                carrinho.setCod_carrinho(rs.getInt("cod_carrinho"));
                carrinho.setCod_ped(rs.getInt("cod_ped"));
                carrinho.setCod_prod(rs.getInt("cod_prod"));
                carrinho.setQtd(rs.getInt("qtd"));
                carrinho.setValor_un(rs.getFloat("valor_un"));
                listaResult.add(carrinho);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            return listaResult;
        }
    }

    public void insertCarrinho(carrinho carrinho) {
        System.out.println("inserindo no carrinho");
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into carrinho values ( "
                    + carrinho.getCod_prod() + ", " + carrinho.getCod_ped()
                    + ", " + carrinho.getValor_un() + ", " + carrinho.getQtd() + ")");
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
    }
    
}
