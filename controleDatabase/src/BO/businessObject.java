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

    carrinhoConnection carrinhoCon = new carrinhoConnection();
    cashbackConnection cashbackCon = new cashbackConnection();
    estabelecimentoConnection estabCon = new estabelecimentoConnection();
    franquiaConnection franquiaCon = new franquiaConnection();
    pedidoConnection pedidoCon = new pedidoConnection();
    produtoConnection produtoCon = new produtoConnection();
    usuarioConnection usuCon = new usuarioConnection();
    saldoFranqSistConnection saldoFranqCon = new saldoFranqSistConnection();
    
    
    public void realizarVenda(int[] prods, int[] qtds, int idUsu, float total) throws SQLException {
//        usuarioConnection usuCon = new usuarioConnection();
//        pedidoConnection pedidoCon = new pedidoConnection();
//        cashbackConnection cashbackCon = new cashbackConnection();
//        produtoConnection produtoCon = new produtoConnection();
//        carrinhoConnection carrinhoCon = new carrinhoConnection();
//        saldoFranqSistConnection saldoFranqCon = new saldoFranqSistConnection();
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
