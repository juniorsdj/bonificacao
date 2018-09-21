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
public class franquia {
    private int cod_franq;
    private String nom_franq;

    public int getCod_franq() {
        return cod_franq;
    }

    public void setCod_franq(int cod_franq) {
        this.cod_franq = cod_franq;
    }

    public String getNom_franq() {
        return nom_franq;
    }

    public void setNom_franq(String nom_franq) {
        this.nom_franq = nom_franq;
    }

    @Override
    public String toString() {
        return "franquia{" + "cod_franq=" + cod_franq + ", nom_franq=" + nom_franq + '}';
    }
    
}
