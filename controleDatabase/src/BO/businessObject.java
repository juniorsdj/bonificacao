/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import DAO.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import PO.*;

/**
 *
 * @author QueroDelivery
 */
public class businessObject {

    DAOCarrinho carrinhoCon = new DAOCarrinho();
    DAOCashback cashbackCon = new DAOCashback();
    DAOEstabelecimento estabCon = new DAOEstabelecimento();
    DAOFranquia franquiaCon = new DAOFranquia();
    DAOPedido pedidoCon = new DAOPedido();
    DAOProduto produtoCon = new DAOProduto();
    DAOUsuario usuCon = new DAOUsuario();
    DAOSaldoFranqSist saldoFranqCon = new DAOSaldoFranqSist();
    
    
    public void realizarVenda(int[] prods, int[] qtds, int idUsu, float total) throws SQLException {
//        DAOUsuario usuCon = new DAOUsuario();
//        DAOPedido pedidoCon = new DAOPedido();
//        DAOCashback cashbackCon = new DAOCashback();
//        DAOProduto produtoCon = new DAOProduto();
//        DAOCarrinho carrinhoCon = new DAOCarrinho();
//        DAOSaldoFranqSist saldoFranqCon = new DAOSaldoFranqSist();
        usuario usu = usuCon.getUsuarioPorId(idUsu);
        float cashbackPai = (float) (total * 0.05);
        float cashbackMF = cashbackPai;
        float cashbackSis = cashbackPai;
        float cashbackFr = cashbackPai;

        pedido pedido = new pedido();

        pedido.setCod_usu(idUsu);
        pedido.setValor(total);
        int idPedido = pedidoCon.insertPedido(pedido);

        List<carrinho> carrinho = new ArrayList<>();

        cashback cash = new cashback();
        cash.setCod_ped(idPedido);
        cash.setUsu_bene(usu.getCod_pai());
        cash.setValor(cashbackPai);
        cashbackCon.insertCashback(cash);

        if (usu.getRaiz() > 0 && usuCon.getUsuarioPorId(usu.getRaiz()).isMicro_franq()) {
            cashback newCash = new cashback();
            newCash.setCod_ped(idPedido);
            newCash.setValor(cashbackMF);
            newCash.setUsu_bene(usu.getRaiz());
            cashbackCon.insertCashback(newCash);
        } else {
            saldoFranqSist saldo = new saldoFranqSist();
            saldo.setSaldo(cashbackMF);
            saldo.setCod_franq(0);
            saldoFranqCon.insertSaldoFranqSist(saldo);
        }

        for (int i = 0; i < prods.length; i++) {
            carrinho item = new carrinho();
            item.setCod_ped(idPedido);
            item.setCod_prod(prods[i]);
            item.setValor_un((float) (produtoCon.getProdutoId(prods[i]).getValor()));
            item.setQtd(qtds[i]);
            carrinho.add(item);
        }
        for (carrinho item : carrinho) {
            carrinhoCon.insertCarrinho(item);
        }
        saldoFranqSist saldo = new saldoFranqSist();
        saldo.setSaldo(cashbackSis);
        saldo.setCod_franq(1);
        saldoFranqCon.insertSaldoFranqSist(saldo);
        saldo.setCod_franq(0);
        saldoFranqCon.insertSaldoFranqSist(saldo);
    }

//transformar em private
    private boolean isFilhoMF(int usuId) throws SQLException {
        usuario usu = usuCon.getUsuarioPorId(usuId);
        if (usuCon.getUsuarioPorId(usu.getRaiz()).isMicro_franq()) {
            return true;
        } else {
            return false;
        }
    }

    public void cadastrarUsuario(usuario pai, usuario filho) throws SQLException {
        filho.setCod_pai(pai.getCod_usu());
        if (usuCon.getUsuarioPorId(pai.getRaiz()).isMicro_franq()) {
            filho.setRaiz(pai.getRaiz());
        }
        usuCon.insertUsuario(filho);
    }

    public void tornarMicroFranqueado(int id) throws SQLException {
        usuario usu = usuCon.getUsuarioPorId(id);
        usuCon.updateMicroFranq(usu);
    }

    public List getCashbackUsuario(int id) throws SQLException {
        return cashbackCon.getCashbackPorUsuarioId(id);
    }
}
