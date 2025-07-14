package control;

import dao.FuncionarioDAO;
import java.util.ArrayList;
import model.Funcionario;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public String cadastrarContaFuncionario(String nome, String cpf, String telefone, String email, String funcao, double salario){
        Funcionario funcionario = new Funcionario(nome, cpf, email, telefone, funcao, salario);
        return funcionarioDAO.cadastrarContaFuncionario(funcionario);
    }

    public String cadastrarFuncionario(String cpf, String funcao, double salario){
        return funcionarioDAO.cadastrarFuncionario(cpf, funcao, salario);
    }

    public ArrayList<Funcionario> listarFuncionarios(){
        return funcionarioDAO.listartFuncionarios();
    }

    public Funcionario buscarFuncionario(String cpf){
        return funcionarioDAO.buscarFuncionario(cpf);
    }

    public String atualizarFuncionario(String cpf, String funcao, double salario){
        return funcionarioDAO.atualizarFuncionario(cpf,funcao, salario);
    }

    public String deletarFuncionario(String cpf){
        return funcionarioDAO.deletarFuncionario(cpf);
    }

}
