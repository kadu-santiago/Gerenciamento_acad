package dao;

import model.Equipamento;

import java.sql.*;
import java.util.ArrayList;

public class EquipamentoDAO {
    public String cadastroEquipamento(Equipamento equipamento) {
        String sql = "INSERT INTO equipamento (descricao, marca, musculo_alvo) VALUES (?,?,?)";

        try(Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, equipamento.getDescricao());
            stmt.setString(2, equipamento.getMarca());
            stmt.setString(3, equipamento.getMusculoAlvo());
            stmt.executeUpdate();
            return "Equipamento cadastrado com sucesso!";
        }
        catch (SQLException e) {
            return "Erro ao cadastrar equipamento." + e.getMessage();
        }
    }

    public ArrayList<Equipamento> listartEquipamentos(){
        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM equipamento";
        try(
                Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmtListar = conn.prepareStatement(sql);
                ResultSet rs = stmtListar.executeQuery()

        ){
            while (rs.next()) {
                equipamentos.add(new Equipamento(rs.getInt("id_equipamento"), rs.getString("descricao"),rs.getString("marca"),rs.getString("musculo_alvo")));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return equipamentos;
    }

    public String buscarEquipamento(int idEquipamento) {
        String sql = "SELECT * FROM equipamento WHERE id_equipamento = ?";

        try ( Connection conn = ConexaoDAO.getConnection();
              PreparedStatement stmtBusca = conn.prepareStatement(sql)){
            stmtBusca.setInt(1, idEquipamento);
            ResultSet rs = stmtBusca.executeQuery();

            if(rs.next()){
                Equipamento equipamento = new Equipamento(rs.getInt("id_equipamento"), rs.getString("descricao"),rs.getString("marca"),rs.getString("musculo_alvo"));
                return ("ID: "+equipamento.getIdEquipamento()+"\nDescrição: "+equipamento.getDescricao()+"\nMarca: "+ equipamento.getMarca()+"\nMusculo Alvo: "+equipamento.getMusculoAlvo());
            }
            else{
                System.out.println("Equipamento não encontrado!");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String deletarEquipamento(int idEquipamento) {
        String  sql = "DELETE FROM equipamento WHERE id_equipamento = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmtDeleta = conn.prepareStatement(sql)
                ) {
                    stmtDeleta.setInt(1, idEquipamento);
                    stmtDeleta.executeUpdate();
                    return "Equipamento deletado com sucesso!";
                } catch (SQLException e) {
                    return "Erro ao deletar equipamento "+ e.getMessage();
                }
        }

    public String atualizarEquipamento(int idEquipamento, String descricao, String marca, String musculoAlvo) {
        String sql = "SELECT * FROM equipamento WHERE id_equipamento = ?";

        try (Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmtBusca = conn.prepareStatement(sql)){

            stmtBusca.setInt(1, idEquipamento);
            ResultSet rs = stmtBusca.executeQuery();

            if (rs.next()){
                sql = "UPDATE equipamento SET descricao = ?, marca = ?, musculo_alvo = ? WHERE id_equipamento = ?";
                try( PreparedStatement stmtAtt = conn.prepareStatement(sql)){

                    stmtAtt.setString(1, descricao);
                    stmtAtt.setString(2, marca);
                    stmtAtt.setString(3, musculoAlvo);
                    stmtAtt.setInt(4, rs.getInt("id_equipamento"));

                    stmtAtt.executeUpdate();

                    return "Equipamento atualizado com sucesso!";
                } catch (SQLException e) {
                    return "Erro ao atualizar equipamento "+ e.getMessage();
                }
            }
            return "Equipamento não encontrado";
        } catch (SQLException e) {
            return "Erro ao atualizar equipamento" + e.getMessage();
        }
    }
}
