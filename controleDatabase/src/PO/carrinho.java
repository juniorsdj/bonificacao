/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PO;

/**
 *
 * @author QueroDelivery
 */
public class carrinho {
    private int cod_carrinho;
    private int cod_prod;
    private int cod_ped;
    private float valor_un;
    private int qtd;

    public int getCod_carrinho() {
        return cod_carrinho;
    }

    public void setCod_carrinho(int cod_carrinho) {
        this.cod_carrinho = cod_carrinho;
    }

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public int getCod_ped() {
        return cod_ped;
    }

    public void setCod_ped(int cod_ped) {
        this.cod_ped = cod_ped;
    }

    public float getValor_un() {
        return valor_un;
    }

    public void setValor_un(float valor_un) {
        this.valor_un = valor_un;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "carrinho{" + "cod_carrinho=" + cod_carrinho + ", cod_prod=" + cod_prod + ", cod_ped=" + cod_ped + ", valor_un=" + valor_un + ", qtd=" + qtd + '}';
    }
    
}
