/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import PO.saldoFranqSist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QueroDelivery
 */
public class saldoFranqSistConnection {

    private Connection connection;

    public saldoFranqSistConnection() {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    private List getSaldo(int cod_fran) throws SQLException {
        String complete;
        if (cod_fran == 0) {
            complete = " is null";
        } else {
            complete = " = " + cod_fran;
        }
        List<saldoFranqSist> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("use web"
                    + "                             select *\n"
                    + "                             from estabelecimento"
                    + "                             cod_franq  " + complete + "\n");

            rs = stmt.executeQuery();
            while (rs.next()) {
                saldoFranqSist saldo = new saldoFranqSist();
                saldo.setCod_franq(rs.getInt("cod_est"));
                saldo.setSaldo(rs.getFloat("saldo"));
                listaResult.add(saldo);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            return listaResult;
        }
    }

    public void insertSaldoFranqSist(saldoFranqSist saldo) {
        String complete;
        if (saldo.getCod_franq() == 0) {
            complete = "null";
        } else {
            complete = " " + saldo.getCod_franq() + " ";
        }
        System.out.println("Inserindo saldo da franquia ou sistema");
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into saldoFranqSist values ("
                    + complete + ", " + saldo.getSaldo() + " )");
            stmt.execute();

        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
    }
}
