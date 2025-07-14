package model;


public class Cliente extends Conta{
    private int id_cliente;
    private String dataCadastro;
    private String obsCliente;

    public Cliente (int id_cliente, String nomeConta, String cpfConta, String emailConta, String telefoneConta,String dataCadastro, String obsCliente) {
        super(nomeConta, cpfConta, emailConta, telefoneConta);
        this.id_cliente = id_cliente;
        this.obsCliente = obsCliente;
        this.dataCadastro = dataCadastro;
    }


    public Cliente(String nomeConta, String cpfConta, String emailConta, String telefoneConta,
            String obsCliente) {
        super(nomeConta, cpfConta, emailConta, telefoneConta);
        this.obsCliente = obsCliente;
    }



    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObsCliente() {
        return obsCliente;
    }

    public void setObsCliente(String obsCliente) {
        this.obsCliente = obsCliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    

}
