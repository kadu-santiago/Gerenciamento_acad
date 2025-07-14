package dao;

import model.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RegistroDAO {

    public String inserirRegistro(int idCliente, LocalDateTime dataEntrada, LocalDateTime dataSaida) {
        String sql = "INSERT INTO registro_acesso (id_cliente, data_entrada, data_saida) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setTimestamp(2, Timestamp.valueOf(dataEntrada));
            stmt.setTimestamp(3, Timestamp.valueOf(dataSaida));

            stmt.executeUpdate();
            return "Registro de acesso inserido com sucesso!";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao inserir registro de acesso: " + e.getMessage();
        }
    }

    public ArrayList<Registro> listarRegistros() {
        ArrayList<Registro> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro_acesso";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                LocalDateTime dataEntrada = rs.getTimestamp("data_entrada").toLocalDateTime();
                LocalDateTime dataSaida = rs.getTimestamp("data_saida").toLocalDateTime();

                registros.add(new Registro( idCliente, dataEntrada, dataSaida));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar registros: " + e.getMessage());
        }
        return registros;
    }

    public String excluirRegistro(int idCliente, LocalDateTime dataEntrada) {
        String sql = "DELETE FROM registro_acesso WHERE id_cliente = ? and data_entrada = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setTimestamp(2, Timestamp.valueOf(dataEntrada));

            int linhas  = stmt.executeUpdate();

            if (linhas > 0) {
                return "Registro excluído com sucesso!";
            } else {
                return "Nenhum registro encontrado com os dados informados.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao excluir registro: " + e.getMessage();
        }
    }

    public ArrayList<Registro> buscarPorCliente(int idCliente) {
        ArrayList<Registro> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro_acesso WHERE id_cliente = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalDateTime dataEntrada = rs.getTimestamp("data_entrada").toLocalDateTime();
                    LocalDateTime dataSaida = rs.getTimestamp("data_saida").toLocalDateTime();

                    registros.add(new Registro(idCliente, dataEntrada, dataSaida));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar registros por cliente: " + e.getMessage());
        }
        return registros;
    }
}