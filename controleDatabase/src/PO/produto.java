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
public class produto {
    private int cod_prod;
    private int cod_estab;
    private String nom_prod;
    private double valor;

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public int getCod_estab() {
        return cod_estab;
    }

    public void setCod_estab(int cod_estab) {
        this.cod_estab = cod_estab;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "produto{" + "cod_prod=" + cod_prod + ", cod_estab=" + cod_estab + ", nom_prod=" + nom_prod + ", valor=" + valor + '}';
    }
    
    
}
