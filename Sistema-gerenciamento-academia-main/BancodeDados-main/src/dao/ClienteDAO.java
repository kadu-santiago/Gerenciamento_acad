package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDAO {
    public String cadastrarContaCliente(Cliente cliente) {
        String sqlConta = "INSERT INTO conta (nome_completo, cpf, telefone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmtConta = conn.prepareStatement(sqlConta, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            stmtConta.setString(1, cliente.getNomeConta());
            stmtConta.setString(2, cliente.getCpfConta());
            stmtConta.setString(3, cliente.getTelefoneConta());
            stmtConta.setString(4, cliente.getEmailConta());
            stmtConta.executeUpdate();

            try (ResultSet idGerado = stmtConta.getGeneratedKeys()) {
                if (idGerado.next()) {
                    int idConta = idGerado.getInt(1);

                    String sqlCliente = "INSERT INTO cliente (id_conta, data_cadastro, observacao) VALUES (?, CURDATE(), ?)";
                    try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {
                        stmtCliente.setInt(1, idConta);
                        stmtCliente.setString(2, cliente.getObsCliente());
                        stmtCliente.executeUpdate();
                    }
                    return "Cliente cadastrado com sucesso!";
                } else {
                    return "Erro: Não foi possível obter o ID da conta para criar o cliente.";
                }
            }
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062){
                return "CPF já cadastrado!";
            }
            return "Erro ao cadastrar cliente: " + e.getMessage();
        }
    }

    public String cadastrarCliente(String cpf, String obs){
        String sql = "SELECT * FROM conta WHERE cpf = ?";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                
                sql = "INSERT INTO cliente (id_conta, data_cadastro, observacao) VALUES (?,CURDATE(),?)";
                try(
                    PreparedStatement stmt2 = conn.prepareStatement(sql)
                ){
                stmt2.setInt(1, rs.getInt("id_conta"));
                stmt2.setString(2, obs);
                stmt2.executeUpdate();
                return "Cliente cadastrado com sucesso!";

                }catch(SQLException e){
                    return "Erro ao cadastrar cliente: "+ e.getMessage();

                }
            }
            else{
                return "CPF não encontrado!";
            }
        }catch(SQLException e){
            return "Erro ao cadastrar cliente:" + e.getMessage();
        }
        
    }
    public ArrayList<Cliente> listartClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM conta co, cliente cl WHERE co.id_conta = cl.id_conta";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()

        ){
            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("id_cliente"),rs.getString("nome_completo"),rs.getString("cpf"),rs.getString("email"),rs.getString("telefone"),rs.getString("data_cadastro"),rs.getString("observacao")));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }
    public Cliente buscarCliente(String cpf){
        String sql = "SELECT * FROM conta co, cliente cl WHERE cl.id_conta = co.id_conta and cpf = ?";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Cliente(rs.getInt("id_cliente"),rs.getString("nome_completo"),rs.getString("cpf"),rs.getString("email"),rs.getString("telefone"),rs.getString("data_cadastro"),rs.getString("observacao"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String atualizarConta(String cpf, String nome, String telefone, String email){
        String sql = "UPDATE conta SET nome_completo = ?, telefone = ?, email = ? WHERE cpf = ?";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, email);
            stmt.setString(4, cpf);
            stmt.executeUpdate();
            return "Conta atualizado com sucesso!";
        } catch(SQLException e){
            return "Erro ao atualizar conta " + e.getMessage();
        }
    }

}
