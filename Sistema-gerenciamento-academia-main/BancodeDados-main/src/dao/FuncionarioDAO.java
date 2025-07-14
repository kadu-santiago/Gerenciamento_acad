package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Funcionario;

public class FuncionarioDAO {
    public String cadastrarContaFuncionario(Funcionario funcionario) {
        String sqlConta = "INSERT INTO conta (nome_completo, cpf, telefone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmtConta = conn.prepareStatement(sqlConta, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            stmtConta.setString(1, funcionario.getNomeConta());
            stmtConta.setString(2, funcionario.getCpfConta());
            stmtConta.setString(3, funcionario.getTelefoneConta());
            stmtConta.setString(4, funcionario.getEmailConta());
            stmtConta.executeUpdate();

            try (ResultSet idGerado = stmtConta.getGeneratedKeys()) {
                if (idGerado.next()) {
                    int idConta = idGerado.getInt(1);

                    String sqlFuncionario = "INSERT INTO Funcionario (id_conta, funcao, salario) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario)) {
                        stmtFuncionario.setInt(1, idConta);
                        stmtFuncionario.setString(2, funcionario.getFuncao());
                        stmtFuncionario.setDouble(3,funcionario.getSalario());
                        stmtFuncionario.executeUpdate();
                    }
                    return "Funcionario cadastrado com sucesso!";
                } else {
                    return "Erro: Não foi possível obter o ID da conta para criar o Funcionario.";
                }
            }
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062){
                return "CPF já cadastrado!";
            }
            return "Erro ao cadastrar Funcionario: " + e.getMessage();
        }
    }

    public String cadastrarFuncionario(String cpf, String funcao, double salario){
        String sql = "SELECT * FROM conta WHERE cpf = ?";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                
                sql = "INSERT INTO Funcionario (id_conta, funcao, salario) VALUES (?,?,?)";
                try(
                    PreparedStatement stmt2 = conn.prepareStatement(sql)
                ){
                stmt2.setInt(1, rs.getInt("id_conta"));
                stmt2.setString(2, funcao);
                stmt2.setDouble(3, salario);
                stmt2.executeUpdate();
                return "Funcionario cadastrado com sucesso!";

                }catch(SQLException e){
                    return "Erro ao cadastrar funcionário: "+ e.getMessage();

                }
            }
            else{
                return "CPF não encontrado!";
            }
        }catch(SQLException e){
            return "Erro ao cadastrar funcionário:" + e.getMessage();
        }
        
    }
    public ArrayList<Funcionario> listartFuncionarios(){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM conta co, funcionario cl WHERE co.id_conta = cl.id_conta";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()

        ){
            while (rs.next()) {
                funcionarios.add(new Funcionario(rs.getInt("id_funcionario"),rs.getString("nome_completo"),rs.getString("cpf"),rs.getString("email"),rs.getString("telefone"),rs.getString("funcao"),rs.getDouble("salario")));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return funcionarios;
    }
    public Funcionario buscarFuncionario(String cpf){
        String sql = "SELECT * FROM conta c, funcionario f WHERE c.id_conta = f.id_conta and cpf = ?";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Funcionario(rs.getInt("id_funcionario"),rs.getString("nome_completo"),rs.getString("cpf"),rs.getString("email"),rs.getString("telefone"),rs.getString("funcao"),rs.getDouble("salario"));
            }
            else{
                System.out.println("CPF não cadastrado!");
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String atualizarFuncionario(String cpf, String funcao, double salario ){
        String sql = "SELECT * FROM conta WHERE cpf = ?";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                
                sql = "UPDATE funcionario SET funcao = ?, salario = ? WHERE id_conta = ? ";
                try(
                    PreparedStatement stmt2 = conn.prepareStatement(sql)
                ){
                stmt2.setString(1, funcao);
                stmt2.setDouble(2, salario);
                stmt2.setInt(3, rs.getInt("id_conta"));

                stmt2.executeUpdate();
                return "Funcionário atualizado com sucesso!";

                }catch(SQLException e){
                    return "Erro ao atualizar funcionário: "+ e.getMessage();

                }
            }
            else{
                return "CPF não encontrado!";
            }
        }catch(SQLException e){
            return "Erro ao atualizar funcionário:" + e.getMessage();
        }
    }

    public String deletarFuncionario(String cpf){
        String sql = "Select id_conta FROM conta where cpf = ?";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmtBusca = conn.prepareStatement(sql);
        ){
            stmtBusca.setString(1, cpf);
            ResultSet rs = stmtBusca.executeQuery();
            if(rs.next()){
                sql = "DELETE FROM funcionario WHERE id_conta = ?";
                try (
                    PreparedStatement stmtDelete = conn.prepareStatement(sql)
                ){
                    stmtDelete.setInt(1, rs.getInt("id_conta"));
                    stmtDelete.executeUpdate();
                    return "Funcionário removido com Sucesso!";

                } catch (SQLException e) {
                    return "Erro ao deletar funcionário "+ e.getMessage();
                }
            }
            return "Funcionário não enocntrado";

           
        }catch(SQLException e){
            return "Erro ao deletar funcionário" + e.getMessage();
        }
    } 


}
