package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Matricula;

public class MatriculaDAO {
    public String matricularCliente(String cpf, Matricula matricula){
        String sqlConta = "SELECT id_conta FROM conta WHERE cpf = ?";
        try(
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt1 = conn.prepareStatement(sqlConta)
        ){
            stmt1.setString(1, cpf);
            ResultSet rs1 = stmt1.executeQuery();

            if(rs1.next()){
                String sqlCliente = "SELECT id_cliente FROM cliente WHERE id_conta = ?";
                try(PreparedStatement stmt2 = conn.prepareStatement(sqlCliente)){
                    stmt2.setString(1, rs1.getString("id_conta"));
                    ResultSet rs2 = stmt2.executeQuery();
                    if(rs2.next()){
                        String sqlMatricula = "INSERT INTO matricula (id_conta, id_plano, data_inicio, data_fim, data_vencimento, esta_ativo ) values (?,?,CURDATE(),DATE_ADD(CURDATE(), INTERVAL ? MONTH),DATE_ADD(CURDATE(), INTERVAL 30 DAY), 1)";
                        PreparedStatement stmt3 = conn.prepareStatement(sqlMatricula);
                        matricula.setIdConta(rs2.getInt("id_conta"));
                        stmt3.setInt(1, matricula.getIdConta());
                        stmt3.setInt(2, matricula.getIdPlano());
                        stmt3.setInt(3, matricula.getDuracaoMeses());
                        stmt3.executeUpdate();
                        return "Matrícula realizada com sucesso!";
                    }
                    return "Cliente não encontrado!";
                }catch(SQLException e){
                    return "Erro ao realizar matrícula: " + e.getMessage();
                }
                
            }
        }catch(SQLException e){
            return "Erro ao realizar matricula "+e.getMessage();
        }
        return "Erro ao realizar matrícula: CPF não encontrado!";
    }

    public String desativarMatricula (String cpf) {
        String sql = "UPDATE matricula SET esta_ativo = 0 WHERE id_cliente = (SELECT id_cliente FROM conta WHERE id_conta = (SELECT id_conta FROM conta WHERE cpf = ?))";
        try (
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, cpf);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Matrícula desativada com sucesso!";
            } else {
                return "Nenhuma matrícula encontrada para o CPF informado.";
            }
        } catch (SQLException e) {
            return "Erro ao desativar matrícula: " + e.getMessage();
        }
    }

    public String pagamentoMatricula(String cpf){
        String sql = "UPDATE matricula SET data_pagamento = CURDATE(), data_vencimento = DATE_ADD(CURDATE(), INTERVAL 30 DAY) WHERE id_cliente = (SELECT id_cliente FROM conta WHERE id_conta = (SELECT id_conta FROM conta WHERE cpf = ?))";
        try (
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            return "Pagamento realizado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao realizar pagamento: " + e.getMessage();
        }
    }

    public ArrayList<Matricula> listarMatriculasCliente(String cpf) {
        String sql = "SELECT * FROM matricula WHERE id_cliente = (SELECT id_cliente FROM conta WHERE id_conta = (SELECT id_conta FROM conta WHERE cpf = ?))";
        ArrayList<Matricula> matriculas = new ArrayList<>();
        try (
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdConta(rs.getInt("id_conta"));
                matricula.setIdPlano(rs.getInt("id_plano"));
                matricula.setDataInicio(rs.getString("data_inicio"));
                matricula.setDataFim(rs.getString("data_fim"));
                matricula.setDataPagamento(rs.getString("data_pagamento"));
                matricula.setDataVencimento(rs.getString("data_vencimento"));
                matricula.setEstaAtivo(rs.getBoolean("esta_ativo"));
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar matrículas do cliente: " + e.getMessage());
        }
        return matriculas;
    }

    public ArrayList<Matricula> listarMatriculasAtivas() {
        String sql = "SELECT * FROM matricula WHERE esta_ativo = 1";
        ArrayList<Matricula> matriculasAtivas = new ArrayList<>();
        try (
            Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdConta(rs.getInt("id_conta"));
                matricula.setIdPlano(rs.getInt("id_plano"));
                matricula.setDataInicio(rs.getString("data_inicio"));
                matricula.setDataFim(rs.getString("data_fim"));
                matricula.setDataVencimento(rs.getString("data_vencimento"));
                matricula.setEstaAtivo(rs.getBoolean("esta_ativo"));
                matriculasAtivas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar matrículas ativas: " + e.getMessage());
        }
        return matriculasAtivas;
    }


}
