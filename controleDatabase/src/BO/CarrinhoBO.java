/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import DAO.ConnectionFactory;
import DAO.DAOCarrinho;
import DAO.DAOPedido;
import PO.carrinho;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author 20161863120268
 */
public class CarrinhoBO {

    private static CarrinhoBO myInstance;

    private CarrinhoBO() {

    }

    public static synchronized CarrinhoBO getInstance() {
        if (myInstance == null) {
            myInstance = new CarrinhoBO();
        }
        return myInstance;
    }

    public List<carrinho> getAll() throws Exception {
        
        ConnectionFactory connFac = new ConnectionFactory();
        try {
            Connection con = connFac.getConnection(true);
            List<carrinho> list = new DAOCarrinho(con).getAll();
            //new DAOPedido(con);
            for (int i = 0; i < list.size(); i++) {
                carrinho car = list.get(i);
                //capsdifi
            }
            connFac.commit();
        } catch (Exception e) {
            connFac.rolback();
            throw e;
        } finally {
            connFac.close();
        }
        return null;
    }

}
