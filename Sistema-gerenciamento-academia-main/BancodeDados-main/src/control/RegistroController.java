package control;

import dao.RegistroDAO;
import model.Registro;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RegistroController {
    private RegistroDAO registroDAO = new RegistroDAO();

    public String inserirRegistro(int idCliente, LocalDateTime dataEntrada, LocalDateTime dataSaida) {
        if (idCliente <= 0) {
            return "Erro: ID do cliente inválido.";
        }
        if (dataEntrada == null || dataSaida == null) {
            return "Erro: Data de entrada e saída são obrigatórias.";
        }
        if (dataSaida.isBefore(dataEntrada)) {
            return "Erro: Data de saída não pode ser anterior à data de entrada.";
        }

        return registroDAO.inserirRegistro(idCliente, dataEntrada, dataSaida);
    }

    public String excluirRegistro(int idCliente, LocalDateTime dataEntrada) {
        if (idCliente <= 0) {
            return "ID do cliente inválido.";
        }
        return registroDAO.excluirRegistro(idCliente, dataEntrada);
    }
    public ArrayList<Registro> buscarPorCliente(int idCliente) {
        return registroDAO.buscarPorCliente(idCliente);
    }
    public ArrayList<Registro> listarRegistros() {
        return registroDAO.listarRegistros();
    }
}