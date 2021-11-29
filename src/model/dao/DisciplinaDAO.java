package model.dao;

import java.util.List;

import model.bean.Disciplina;

public interface DisciplinaDAO {
    
    void adicionar (Disciplina disciplina);
    List<Disciplina> mostrarDisciplinas();
    void atualizar(String id, Disciplina disciplina);
    void remover (String id);
    List<Disciplina> pesquisarPorCampo(String campo, String valor);
}
