package model;

public class Funcao {
    private float salarioBase;
    private String descricao;

    public Funcao(Float salarioBase,String descricao) {
        this.salarioBase = salarioBase;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }
}
