package model;

public class Matricula {
    private int idConta;
    private int idPlano;
    private String dataInicio;
    private int duracaoMeses;
    private String dataFim;
    private String dataPagamento;
    private String dataVencimento;
    private boolean estaAtivo;

    public Matricula(int idConta, int idPlano, String dataInicio, int duracaoMeses, String dataPagamento,
            boolean estaAtivo) {
        this.idConta = idConta;
        this.idPlano = idPlano;
        this.dataInicio = dataInicio;
        this.duracaoMeses = duracaoMeses;
        this.dataPagamento = dataPagamento;
        this.estaAtivo = estaAtivo;
    }

    public Matricula(int idPlano, int duracaoMeses) {
        this.idPlano = idPlano;
        this.duracaoMeses = duracaoMeses;
    }

    public Matricula() {}

    public String getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isEstaAtivo() {
        return estaAtivo;
    }

    public void setEstaAtivo(boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }



}
