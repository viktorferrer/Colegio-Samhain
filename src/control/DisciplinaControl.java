package control;

import org.bson.types.ObjectId;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.bean.Disciplina;
import model.dao.DisciplinaDAO;
import model.dao.DisciplinaDAOImpl;
import utilities.Utils;

public class DisciplinaControl {

    Utils ut = new Utils();
    DisciplinaDAO dao = new DisciplinaDAOImpl();
    ObservableList<Disciplina> listDisc = FXCollections.observableArrayList();

    StringProperty descricao = new SimpleStringProperty();
    StringProperty periodo = new SimpleStringProperty();
    StringProperty modalidade = new SimpleStringProperty();
    StringProperty id = new SimpleStringProperty();
    StringProperty profId = new SimpleStringProperty();

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public StringProperty periodoProperty() {
        return periodo;
    }

    public StringProperty modalidadeProperty() {
        return modalidade;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty profIdProperty() {
        return profId;
    }

    public ObservableList<Disciplina> getListDisc(){
        return listDisc;
    }

    public Disciplina getDisc(){
        Disciplina d = new Disciplina(descricaoProperty().getValue());
        d.setPeriodo(periodoProperty().getValue());
        d.setModalidade(modalidadeProperty().getValue());
        d.setId(ut.getId(idProperty().getValue()));
        d.setProfId(ut.getId(profIdProperty().getValue()));

        return d;
    }
    
    public void setDisc(Disciplina d){
        descricao.set(d.getDescricao());
        periodo.set(d.getPeriodo());
        modalidade.set(d.getModalidade());
        id.set(d.getId().toString());
        profId.set(d.getProfId().toString());
    }

    public void gravar(){
        Disciplina d = getDisc();
        dao.adicionar(d);
        System.out.println("Disciplina adicionada!");
        limparCampos();   
    }

    public void atualizar(){
        dao.atualizar(idProperty().getValue(), getDisc());
        listar();
        limparCampos();
    }

    public void buscar(){
        listDisc.clear();
        listDisc.addAll(dao.pesquisarPorCampo("descricao", descricaoProperty().getValue()));
    }

    public void deletar(){
        dao.remover(idProperty().getValue());
        listar();
        limparCampos();
    }

    public void listar(){
        listDisc.clear();
        listDisc.addAll(dao.mostrarDisciplinas());
    }

    public void limparCampos(){
        descricao.set("");
        periodo.set("");
        modalidade.set("");
        id.set("");
        profId.set("");
    }

}
