package model.bean;

import org.bson.types.ObjectId;

public class Disciplina {

    String descricao, periodo, modalidade;
    ObjectId id, profId;

    public Disciplina(String descricao) {
        this.descricao = descricao;
    }

    public ObjectId getProfId() {
        return profId;
    }

    public void setProfId(ObjectId profId) {
        this.profId = profId;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    


    
}
