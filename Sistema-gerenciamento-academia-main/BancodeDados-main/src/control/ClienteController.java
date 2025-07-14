package control;

import dao.ClienteDAO;
import java.util.ArrayList;
import model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public String cadastrarContaCliente(String nome_completo, String cpf, String telefone, String email, String observacao){
        Cliente cliente = new Cliente(nome_completo, cpf, email, telefone, observacao);
        return clienteDAO.cadastrarContaCliente(cliente);
    }
    public ArrayList<Cliente> listarClientes(){
        return clienteDAO.listartClientes();
    }

    public Cliente buscarCliente(String cpf){
        return clienteDAO.buscarCliente(cpf);
    }

    public String atualiarConta(String cpf, String nome, String email, String telefone){
        return clienteDAO.atualizarConta(cpf, nome, telefone, email);
    }

    public String cadastrarCliente(String cpf, String obs){
        return clienteDAO.cadastrarCliente(cpf, obs);
    }
}
