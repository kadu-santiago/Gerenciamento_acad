package control;

import dao.EquipamentoDAO;
import java.util.ArrayList;
import model.Equipamento;

public class EquipamentoController {
    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

    public String cadastrarEquipamento(String descricao, String marca, String musculoAlvo){
        Equipamento equipamento = new Equipamento(descricao, marca, musculoAlvo);
        return equipamentoDAO.cadastroEquipamento(equipamento);
    }

    public ArrayList<Equipamento> listarEquipamentos(){ return equipamentoDAO.listartEquipamentos();}

    public String buscarEquipamento(int idEquipamento){ return equipamentoDAO.buscarEquipamento(idEquipamento);}

    public String deletarEquipamento(int idEquipamento){ return equipamentoDAO.deletarEquipamento(idEquipamento);}

    public String atualizarEquipamento(int idEquipamento, String descricao, String marca, String musculoAlvo){ return equipamentoDAO.atualizarEquipamento(idEquipamento, descricao, marca, musculoAlvo);}
}
