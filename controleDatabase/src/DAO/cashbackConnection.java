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
public class cashbackConnection {

    private Connection connection;

    public void open() {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public List getAllCashback() throws SQLException {
        return this.getCashback(0);
    }

    public List getCashbackPorUsuarioId(int id) throws SQLException {
        return this.getCashback(id);
    }

    //pegar o cashback do usuario recebendo o id como parametro, caso o id seja 0,
    // ele retorna todos os chashback cadastrados
    private List getCashback(int idUsuario) throws SQLException {
        List<cashback> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            this.open();
            // se tiver um id do usuario, prepara a consulta com o id, 
            // se não, faz uma consulta de todos
            if (idUsuario != 0) {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from cashback"
                        + "                         where " + idUsuario + " = usu_bene \n");
            } else {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from cashback\n");
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                cashback cash = new cashback();
                cash.setCod_cash(rs.getInt("cod_cash"));
                cash.setCod_ped(rs.getInt("pedido"));
                cash.setUsu_bene(rs.getInt("usu_bene"));
                cash.setValor(rs.getFloat("valor"));
                listaResult.add(cash);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            this.close();
            return listaResult;

        }
    }

    public void insertCashback(cashback cashback) throws SQLException {
        System.out.println("Inserindo cashback");
        PreparedStatement stmt = null;
        try {
            this.open();
            stmt = connection.prepareStatement("insert into cashback values ("
                    + cashback.getCod_ped() + ", " + cashback.getUsu_bene() + ", " + cashback.getValor() + " )");
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            this.close();
        }
    }

    public List selectCashbackUsuario(usuario usu) throws SQLException {
        System.out.println("trazendo cashback por usuario");
        List<cashback> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            this.open();
            stmt = connection.prepareStatement("select nom_usu, valor \n"
                    + "	from cashback c join usuario u on c.usu_bene = u.cod_usu\n"
                    + "	where c.usu_bene = " + usu.getCod_usu());
            rs = stmt.executeQuery();
            while (rs.next()) {
                cashback cash = new cashback();
                cash.setNomeUsuario(rs.getString("nom_usu"));
                cash.setValor(rs.getFloat("valor"));
                listaResult.add(cash);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
         finally {      
            this.close();
            return listaResult;
        }
        
    }

    public void close() throws SQLException {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
    }
}
