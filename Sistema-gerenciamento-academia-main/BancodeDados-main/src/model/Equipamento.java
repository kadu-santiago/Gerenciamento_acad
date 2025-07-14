package model;

public class Equipamento {

    private int idEquipamento;
    private String descricao;
    private String marca;
    private String musculoAlvo;

    public Equipamento(String descricao, String marca, String musculoAlvo) {
        this.descricao = descricao;
        this.marca = marca;
        this.musculoAlvo = musculoAlvo;
    }


    public Equipamento( int idEquipamento, String descricao, String marca, String musculoAlvo) {
        this.idEquipamento = idEquipamento;
        this.descricao = descricao;
        this.marca = marca;
        this.musculoAlvo = musculoAlvo;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMusculoAlvo() {
        return musculoAlvo;
    }

    public void setMusculoAlvo(String musculoAlvo) {
        this.musculoAlvo = musculoAlvo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
