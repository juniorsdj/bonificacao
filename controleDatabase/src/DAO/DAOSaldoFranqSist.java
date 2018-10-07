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
public class DAOSaldoFranqSist extends DAOBase {

    private Connection connection;

    public DAOSaldoFranqSist() throws Exception {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public DAOSaldoFranqSist(Connection con) {
        connection = con;
    }

    public List getSaldoSist() throws Exception {
        try {
            stmt = connection.prepareStatement("use web select * from estabelecimento cod_franq  is null");
            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public List getSaldo(int cod_fran) throws Exception {
        try {
            stmt = connection.prepareStatement("use web"
                    + "                             select *\n"
                    + "                             from estabelecimento"
                    + "                             cod_franq = " + cod_fran + "\n");

            rs = stmt.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public void insertSaldoSist(saldoFranqSist saldo) throws Exception {
        System.out.println("Inserindo saldo no sistema");
        try {
            stmt = connection.prepareStatement("insert into saldoFranqSist values ( null, " + saldo.getSaldo() + " )");
            rs = stmt.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public void insertSaldoFranqSist(saldoFranqSist saldo) throws Exception {
        String complete;
        complete = " " + saldo.getCod_franq() + " ";
        System.out.println("Inserindo saldo da franquia ou sistema");
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into saldoFranqSist values ("
                    + complete + ", " + saldo.getSaldo() + " )");
            stmt.execute();

        } catch (Exception ex) {
            throw ex;
        } finally {
            close(connection, stmt, rs);
        }
    }

    private List<saldoFranqSist> rsToList(ResultSet rs) throws Exception {
        List<saldoFranqSist> listaResult = new ArrayList<>();
        while (rs.next()) {
            saldoFranqSist saldo = new saldoFranqSist();
            saldo.setCod_franq(rs.getInt("cod_est"));
            saldo.setSaldo(rs.getFloat("saldo"));
            listaResult.add(saldo);
        }
        return listaResult;
    }
}
