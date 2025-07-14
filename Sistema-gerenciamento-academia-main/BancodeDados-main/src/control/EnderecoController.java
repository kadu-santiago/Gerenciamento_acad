package control;

import dao.EnderecoDAO;
import java.util.ArrayList;
import model.Endereco;

public class EnderecoController {
    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    public String cadastraEndereco(int idConta, String cep, String logradouro,  String bairro, int numero) {
        Endereco endereco = new Endereco(idConta, cep, logradouro, bairro, numero);
        return enderecoDAO.cadastrarEndereco(endereco);
    }

    public ArrayList<Endereco> listarEnderecos(){ return enderecoDAO.listarEnderecos();}

    public String deletarEndereco(int idEndereco){ return enderecoDAO.excluirEndereco(idEndereco);}

    public String alterarEndereco(int idEndereco, String cep, String logradouro,  String bairro, int numero) {
        return enderecoDAO.alterarEndereco(idEndereco, cep, logradouro, bairro, numero);}
}
