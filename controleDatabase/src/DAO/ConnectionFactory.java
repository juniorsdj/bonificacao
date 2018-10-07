/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author QueroDelivery
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionFactory {

    private Connection connection;

    /**
     *
     * @return
     */
    public Connection getConnection() throws Exception {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro de comunicação com o servidor de banco de dados "
                        + "\n porque esta faltando um driver");
            }
            String url = "jdbc:sqlserver://DESKTOP-16MQ6QE\\SQLEXPRESS;databaseName=Web;user=sa;password=senhasa";
            try {
                connection = DriverManager.getConnection(url);
                System.out.println(connection);
            } // Erro caso o driver JDBC não foi instalado
            catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }

    public Connection getConnection(boolean beginTransaction) throws Exception {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                throw new Exception("Ocorreu um erro de comunicação com o servidor de banco de dados "
                        + "\n porque esta faltando um driver");
            }
            String url = "jdbc:sqlserver://DESKTOP-16MQ6QE\\SQLEXPRESS;databaseName=Web;user=sa;password=senhasa";
            try {
                connection = DriverManager.getConnection(url);
                System.out.println(connection);
            } // Erro caso o driver JDBC não foi instalado
            catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (connection != null) {
            connection.setAutoCommit(false);
        }
        return connection;
    }
    
    public void commit() throws Exception {
        try {
            connection.commit();
        } catch (Exception e) {
        }
        close();
    }
    
    public void rolback() throws Exception{
        try {            
            connection.rollback();
        } catch (Exception e) {
        }            
        close();
    }
    public void close() {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
        }
    }

}
