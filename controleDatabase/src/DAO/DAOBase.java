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

/**
 *
 * @author 20161863120268
 */
public class DAOBase {

    protected Connection connection;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public void close(Connection con, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            close(con);
        } catch (Exception e) {
        }
    }
    
    public void close(Connection con) {
        try {
            if (con != null && con.getAutoCommit()) {
                con.close();
                con = null;
            }
        } catch (Exception e) {
        }
    }
}
