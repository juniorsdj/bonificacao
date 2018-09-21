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
public class pedido {

    private int cod_ped;
    private double valor;
    private int cod_usu;

    public int getCod_ped() {
        return cod_ped;
    }

    public void setCod_ped(int cod_ped) {
        this.cod_ped = cod_ped;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(int cod_usu) {
        this.cod_usu = cod_usu;
    }

    @Override
    public String toString() {
        return "pedido{" + "cod_ped=" + cod_ped + ", valor=" + valor + ", cod_usu=" + cod_usu + '}';
    }
}
