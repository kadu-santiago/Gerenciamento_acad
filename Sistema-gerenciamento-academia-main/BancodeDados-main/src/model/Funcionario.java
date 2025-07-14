package model;

public class Funcionario extends Conta{
    private int id_funcionario;
    private String funcao;
    private double salario;
    public Funcionario ( int id_funcionario, String nomeConta, String cpfConta, String emailConta, String telefoneConta, String funcao, double salario) {
        super(nomeConta, cpfConta, emailConta, telefoneConta);
        this.id_funcionario = id_funcionario;
        this.funcao = funcao;
        this.salario = salario;
    }

    public Funcionario( String nomeConta, String cpfConta, String emailConta, String telefoneConta, String funcao, double salario) {
        super(nomeConta, cpfConta, emailConta, telefoneConta);
        this.funcao = funcao;
        this.salario = salario;
    }



    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }



    
}
