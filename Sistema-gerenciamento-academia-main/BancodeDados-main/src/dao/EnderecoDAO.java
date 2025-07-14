package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Endereco;

public class EnderecoDAO {
    public String cadastrarEndereco(Endereco endereco) {
        String verificarSQL = "SELECT COUNT(*) FROM conta WHERE id_conta = ?";

        String inserirSQL = "INSERT INTO endereco (id_conta, cep, logradouro, bairro, numero ) VALUES (?,?,?,?,?)";

        try(Connection conn = ConexaoDAO.getConnection())
        {
            try (PreparedStatement stmt1 = conn.prepareStatement(verificarSQL);){
                stmt1.setInt(1, endereco.getIdConta());
                try (ResultSet rs = stmt1.executeQuery(); ) {
                    if (rs.next()){
                        int count = rs.getInt(1);
                        if (count == 0){
                            return "Não existe um registro de conta com esse ID";
                        }
                    }
                }
            }
            try(PreparedStatement stmt = conn.prepareStatement(inserirSQL);){
                stmt.setInt(1, endereco.getIdConta());
                stmt.setString(2, endereco.getCep());
                stmt.setString(3, endereco.getLogradouro());
                stmt.setString(4, endereco.getBairro());
                stmt.setInt(5, endereco.getNumero());

                stmt.executeUpdate();
                return ("Endereco cadastrado com sucesso!");
            } catch (SQLException e){
                return ("Erro ao cadastrar endereco" + e.getMessage());
            }
        } catch (SQLException e) {
            return ("Erro ao cadastrar endereco" + e.getMessage());
        }
    }

    public String alterarEndereco(int idEndereco, String cep, String logradouro, String bairro, int numero) {
        String verificarSQL = "SELECT * FROM endereco WHERE id_endereco = ?";

        String alterarSQL = "UPDATE endereco SET cep = ?, logradouro = ?, bairro = ?, numero = ? WHERE id_endereco = ?";

        try (Connection conn = ConexaoDAO.getConnection()) {
            try (PreparedStatement stmt1 = conn.prepareStatement(verificarSQL);) {
                stmt1.setInt(1, idEndereco);
                try (ResultSet rs = stmt1.executeQuery();) {
                    if (!rs.next()) {
                            return "Não existe endereços com esse ID";
                    }
                }
            }
            try (PreparedStatement stmt2 = conn.prepareStatement(alterarSQL)) {
                stmt2.setString(1, cep);
                stmt2.setString(2, logradouro);
                stmt2.setString(3, bairro);
                stmt2.setInt(4, numero);
                stmt2.setInt(5,idEndereco);

                stmt2.executeUpdate();
                return("Endereco alterado com sucesso!");
            } catch (SQLException e) {
                return ("Erro ao cadastrar endereco" + e.getMessage());
            }
        } catch (SQLException e) {
            return("Erro ao cadastrar endereco" + e.getMessage());
        }
    }

    public String excluirEndereco(int idEndereco){
        String SQL = "DELETE FROM endereco WHERE id_endereco = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt= conn.prepareStatement(SQL);){
                stmt.setInt(1, idEndereco);

                int linhas = stmt.executeUpdate() ;
                if(linhas > 0){
                    return("Endereço excluído com sucesso!");
                }
                else
                    return("Endereço não encontrado");
        }
        catch (SQLException e){
            return("Erro ao excluir endereco" + e.getMessage());
        }
    }

    public ArrayList<Endereco> listarEnderecos(){
        ArrayList<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco"; // SQL para selecionar todos os endereços

        try(
                Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            while (rs.next()) {
                enderecos.add(new Endereco(rs.getInt("id_endereco"), rs.getInt("id_conta"), rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getInt("numero"), rs.getString("complemento")));            }
        } catch(SQLException e){
            System.out.println("Erro ao listar endereços: " + e.getMessage());
            e.printStackTrace(); // Para depuração
        }
        return enderecos;
    }
}
