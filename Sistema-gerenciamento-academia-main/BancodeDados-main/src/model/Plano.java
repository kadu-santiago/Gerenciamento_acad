package model;
public class Plano {
    private int idPlano;
    private String descricaoPlano;
    private double precoPlano;
    
    public Plano(int idPlano, String descricaoPlano, double precoPlano) {
        this.idPlano = idPlano;
        this.descricaoPlano = descricaoPlano;
        this.precoPlano = precoPlano;
    }

    public Plano(String descricaoPlano, double precoPlano) {
        this.descricaoPlano = descricaoPlano;
        this.precoPlano = precoPlano;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getDescricaoPlano() {
        return descricaoPlano;
    }

    public void setDescricaoPlano(String descricaoPlano) {
        this.descricaoPlano = descricaoPlano;
    }

    public double getPrecoPlano() {
        return precoPlano;
    }

    public void setPrecoPlano(double precoPlano) {
        this.precoPlano = precoPlano;
    }

}
