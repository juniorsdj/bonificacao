
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.*;
import BO.businessObject;
import PO.carrinho;
import PO.cashback;
import PO.estabelecimento;
import PO.franquia;
import PO.pedido;
import PO.produto;
import PO.usuario;
import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author QueroDelivery
 */
public class teste {

    public static void main(String args[]) throws SQLException {
//        acessDataBase acesso = new acessDataBase();
        businessObject regra = new businessObject();
//        List<carrinho> listaCarrinho = new ArrayList<>();
//        int[] item = new int[2];
//        int[] qtd = new int[2];
//        item[0] = 1;
//        item[1] = 2;
//        qtd[0] = 3;
//        qtd[1] = 5;
//        regra.realizarVenda(item, qtd, 2, 12);
        List<cashback> listaResult = new ArrayList<>();
        listaResult = regra.getCashbackUsuario(1);
        for (cashback object : listaResult) {
            System.out.println(object.toString());
        }
        
    }

}
