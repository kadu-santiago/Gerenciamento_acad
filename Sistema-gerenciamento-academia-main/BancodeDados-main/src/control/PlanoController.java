package control;

import dao.PlanoDAO;
import java.util.ArrayList;
import model.Plano;

public class PlanoController {
    private PlanoDAO planoDAO = new PlanoDAO();

    public String inserirPlano(String descricao, double preco){
        Plano plano = new Plano(descricao,preco);
        return planoDAO.inserirPlano(plano);
    }
    public Plano buscarPlano(int id_plano){
        return planoDAO.buscarPlano(id_plano);
    }

    public ArrayList<Plano> listarPlanos(){
        return planoDAO.listarPlanos();
    }

    public String atualizarPlano(int id_plano, double preco){
        return planoDAO.atualizarPlano(id_plano, preco);
    }

    public String deletarPlano(int id_plano){
        return planoDAO.deletarPlano(id_plano);
    }
}
