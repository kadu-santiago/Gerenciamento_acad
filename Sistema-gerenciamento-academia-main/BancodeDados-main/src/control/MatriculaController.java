package control;

import dao.MatriculaDAO;
import java.util.ArrayList;
import model.Matricula;

public class MatriculaController {
    private MatriculaDAO matriculaDAO = new MatriculaDAO();


    public String matricularCliente(String cpf, int idPlano, int duracaoMeses) {
        Matricula matricula = new Matricula(idPlano, duracaoMeses);
        return matriculaDAO.matricularCliente(cpf, matricula);
    }

    public String desativarMatricula(String cpf) {
        return matriculaDAO.desativarMatricula(cpf);
    }

    public String pagamentoMatricula(String cpf) {
        return matriculaDAO.pagamentoMatricula(cpf);
    }
    public ArrayList<Matricula> listarMatriculasAtivas() {
        return matriculaDAO.listarMatriculasAtivas();
    }
    public ArrayList<Matricula> listarMatriculasCliente(String cpf) {
        return matriculaDAO.listarMatriculasCliente(cpf);
    }
}
