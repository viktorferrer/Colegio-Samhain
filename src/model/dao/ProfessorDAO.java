package model.dao;

import java.util.List;

import model.bean.Professor;

public interface ProfessorDAO {

    void adicionar (Professor professor);
    void remover (String id);
    void atualizar (String id, Professor professor);
    List<Professor>mostrarProfs();
    List<Professor> pesquisarPorCampo(String campo, String valor);    
}
