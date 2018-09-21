/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PO;

import java.util.Date;

/**
 *
 * @author QueroDelivery
 */
public class usuario {

    private int cod_franq;
    private String nom_usu;
    private int cod_usu;
    private int cod_pai;
    private int micro_franq;
    private Date dt_mf;
    private Date dt_cad;
    private int raiz;

    public int getRaiz() {
        return raiz;
    }

    public void setRaiz(int raiz) {
        this.raiz = raiz;
    }

    public int getCod_franq() {
        return cod_franq;
    }

    public void setCod_franq(int cod_franq) {
        this.cod_franq = cod_franq;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public int getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(int cod_usu) {
        this.cod_usu = cod_usu;
    }

    public int getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(int cod_pai) {
        this.cod_pai = cod_pai;
    }

    public boolean isMicro_franq() {
        if (this.micro_franq == 1) {
            return true;
        }
        return false;
    }

    public void setMicro_franq(boolean micro_franq) {
        if (micro_franq) {
            this.micro_franq = 1;
        } else {
            this.micro_franq = 0;
        }

    }

    public Date getDt_mf() {
        return dt_mf;
    }

    public void setDt_mf(Date dt_mf) {
        this.dt_mf = dt_mf;
    }

    public Date getDt_cad() {
        return dt_cad;
    }

    public void setDt_cad(Date dt_cad) {
        this.dt_cad = dt_cad;
    }

    @Override
    public String toString() {
        return "usuario{" + "cod_franq=" + cod_franq + ", nom_usu=" + nom_usu + ", cod_usu=" + cod_usu + ", cod_pai=" + cod_pai + ", micro_franq=" + micro_franq + ", dt_mf=" + dt_mf + ", dt_cad=" + dt_cad + '}';
    }

}
