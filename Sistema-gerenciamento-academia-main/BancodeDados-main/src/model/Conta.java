package model;

public abstract class Conta {
    protected String nomeConta;
    protected String cpfConta;
    protected String emailConta;
    protected String telefoneConta;

    public Conta(String nomeConta, String cpfConta, String emailConta, String telefoneConta) {
        this.nomeConta = nomeConta;
        this.cpfConta = cpfConta;
        this.emailConta = emailConta;
        this.telefoneConta = telefoneConta;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public String getCpfConta() {
        return cpfConta;
    }

    public void setCpfConta(String cpfConta) {
        this.cpfConta = cpfConta;
    }

    public String getEmailConta() {
        return emailConta;
    }

    public void setEmailConta(String emailConta) {
        this.emailConta = emailConta;
    }

    public String getTelefoneConta() {
        return telefoneConta;
    }

    public void setTelefoneConta(String telefoneConta) {
        this.telefoneConta = telefoneConta;
    }
}
