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
import PO.usuario;

/**
 *
 * @author QueroDelivery
 */
public class usuarioConnection {

    private Connection connection;

    public usuarioConnection() {
        if (connection == null) {
            connection = new ConnectionFactory().getConnection();
        }
    }

    public List getAllUsuarios() throws SQLException {
        return this.getUsuarios(0);
    }

    public usuario getUsuarioPorId(int id) throws SQLException {
        return (usuario) this.getUsuarios(id).get(0);
    }

    //Trazer todos os usuários retornando-os em um arraylist de usuario
    private List getUsuarios(int id) throws SQLException {
        List<usuario> listaResult = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (id == 0) {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from usuario\n");
            } else {
                stmt = connection.prepareStatement("use web"
                        + "                             select *\n"
                        + "                             from usuario\n"
                        + "                           where cod_usu = " + id + "");

            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                //enquanto tiver linha de resultado, vá criando um novo usuário
                // com os dados da linha
                usuario usu = new usuario();
                usu.setCod_franq(rs.getInt("cod_franq"));
                usu.setNom_usu(rs.getString("nom_usu"));
                usu.setCod_usu(rs.getInt("cod_usu"));
                usu.setCod_pai(rs.getInt("cod_pai"));
                usu.setMicro_franq(rs.getBoolean("micro_franq"));
                usu.setDt_cad(rs.getDate("dt_cad"));
                usu.setDt_mf(rs.getDate("dt_mf"));
                listaResult.add(usu);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            return listaResult;
        }
    }

    public void updateMicroFranq(usuario usu) {
        System.out.println("Transformando em microfranqueado");
        PreparedStatement stmt = null;
        try {

            stmt = connection.prepareStatement("UPDATE usuario"
                    + "   SET\n"
                    + "      micro_franq = 1,"
                    + "      dt_mf = GETDATE()"
                    + " WHERE cod_usu = " + usu.getCod_usu());
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
    }

    public int insertUsuario(usuario usuario) throws SQLException {
        System.out.println("inserindo usuario");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = connection.prepareStatement("insert into usuario values ('"
                    + usuario.getNom_usu() + "' , " + usuario.getCod_franq() + ","
                    + usuario.getCod_pai() + ", " + usuario.isMicro_franq() + ","
                    + usuario.getDt_mf() + ", GETDATE())"
                    + "select IDENT_CURRENT('usuario') as cod");
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }
        return rs.getInt("cod");
    }

}
