package model.dao;

import java.util.List;

import model.bean.Aluno;

public interface AlunoDAO {
    
    void adicionar (Aluno aluno);
    List <Aluno> mostrarAlunos();
    Aluno pesquisarPorMatricula (String matricula);
    void atualizar(String id, Aluno aluno);
    void remover(String id);
}
