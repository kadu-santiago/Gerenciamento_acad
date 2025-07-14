package model;

public class Endereco {
    private int idConta;
    private int idEndereco;
    private String cep;
    private String logradouro;
    private String bairro;
    private String complemento;
    private int numero;

    public Endereco(int idConta, String cep, String logradouro, String bairro, int numero) {
        this.idConta = idConta;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
    }

    public Endereco(int idConta, String cep, String logradouro, String bairro, String complemento, int numero) {
        this.idConta = idConta;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
    }

    public Endereco(int idEndereco, int idConta, String cep, String logradouro, String bairro, int numero, String complemento) {
        this.idEndereco = idEndereco;
        this.idConta = idConta;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public int getIdConta() { return idConta;}

    public void setIdConta(int idConta) { this.idConta = idConta;}

    public int getIdEndereco() { return idEndereco;}

    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco;}

    public String getCep() { return cep;}

    public void setCep(String cep) { this.cep = cep;}

    public String getLogradouro() { return logradouro;}

    public void setLogradouro(String logradouro) { this.logradouro = logradouro;}

    public String getBairro() { return bairro;}

    public void setBairro(String bairro) { this.bairro = bairro;}

    public String getComplemento() { return complemento;}

    public void setComplemento(String complemento) { this.complemento = complemento;}

    public int getNumero() { return numero;}

    public void setNumero(int numero) { this.numero = numero;}
}
