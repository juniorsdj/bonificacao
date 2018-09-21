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
public class cashback {
    //para banco
    private int cod_cash;
    private int cod_ped;
    private int usu_bene;
    private float valor;
    //nao ta no banco
    private String nomeUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public int getCod_cash() {
        return cod_cash;
    }

    public void setCod_cash(int cod_cash) {
        this.cod_cash = cod_cash;
    }

    public int getCod_ped() {
        return cod_ped;
    }

    public void setCod_ped(int cod_ped) {
        this.cod_ped = cod_ped;
    }

    public int getUsu_bene() {
        return usu_bene;
    }

    public void setUsu_bene(int usu_bene) {
        this.usu_bene = usu_bene;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "cashback{" + "cod_cash=" + cod_cash + ", cod_ped=" + cod_ped + ", usu_bene=" + usu_bene + ", valor=" + valor + ", nomeUsuario=" + nomeUsuario + '}';
    }

  
    
}
