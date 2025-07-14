package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Plano;

public class PlanoDAO {
    public String inserirPlano(Plano plano){
        String sql = "INSERT INTO plano (descricao, preco) VALUES (?,?)";
        try (Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, plano.getDescricaoPlano());
            stmt.setDouble(2, plano.getPrecoPlano());
            stmt.executeUpdate();
            return "Plano cadastrado com sucesso!";
            
        } catch (SQLException e) {
            return "Erro ao cadastrar plano.";
        }
    }

    public Plano buscarPlano(int id_plano){
        String sql = "SELECT * FROM plano WHERE id_plano = ?";
        try (
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id_plano);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Plano(rs.getInt("id_plano"), rs.getString("descricao"), rs.getDouble("preco"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Plano> listarPlanos(){
        ArrayList<Plano> planos = new ArrayList<>();
        String sql = "SELECT * FROM plano";
        try (
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                planos.add(new Plano(rs.getInt("id_plano"), rs.getString("descricao"),rs.getDouble("preco")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planos;
    }

    public String atualizarPlano(int id_plano, double preco){
        String sql = "UPDATE plano SET preco = ? WHERE id_plano = ?";
        try (
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setDouble(1, preco);
            stmt.setInt(2, id_plano);
            stmt.executeUpdate();
            return "Plano atualizado com sucesso!";
            
        } catch (SQLException e) {
            return "Erro ao atualizar plano" + e.getMessage();
        }
    }

    public String deletarPlano(int id_plano){
        String sql = "DELETE FROM plano WHERE id_plano = ?";
        try (
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1,id_plano);
            stmt.executeUpdate();
            return "Plano deletado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao deletar plano: "+e.getMessage();
        }
    }
}
