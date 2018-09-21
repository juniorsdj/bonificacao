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
public class estabelecimento {
    private int cod_estab;
    private String nom_estab;

    public int getCod_estab() {
        return cod_estab;
    }

    public void setCod_estab(int cod_estab) {
        this.cod_estab = cod_estab;
    }

    public String getNom_estab() {
        return nom_estab;
    }

    public void setNom_estab(String nom_estab) {
        this.nom_estab = nom_estab;
    }

    @Override
    public String toString() {
        return "estabelecimento{" + "cod_estab=" + cod_estab + ", nom_estab=" + nom_estab + '}';
    }
    
    
}
