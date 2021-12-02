package control;

import org.bson.types.ObjectId;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.bean.Professor;
import model.dao.ProfessorDAO;
import model.dao.ProfessorDAOImpl;

public class ProfessorControl {

    ProfessorDAO pDao = new ProfessorDAOImpl();
    ObservableList<Professor> listProf = FXCollections.observableArrayList();

    StringProperty nome = new SimpleStringProperty();
    StringProperty id = new SimpleStringProperty();
    StringProperty turno = new SimpleStringProperty();
    StringProperty sala = new SimpleStringProperty();
    StringProperty espec = new SimpleStringProperty();

    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty turnoProperty() {
        return turno;
    }

    public StringProperty salaProperty() {
        return sala;
    }

    public StringProperty especProperty() {
        return espec;
    }

    public ObservableList<Professor> getListProf() {
        return listProf;
    }

    public Professor getProf() {
        Professor p = new Professor(nomeProperty().getValue());
        p.setTurno(turnoProperty().getValue());
        p.setSala(salaProperty().getValue());
        p.setEspec(especProperty().getValue());
        String stringId = idProperty().getValue();
        if (stringId == null || stringId.isBlank()) {
            p.setId(new ObjectId());
        } else {
            p.setId(new ObjectId(stringId));
        }
        return p;
    }

    public void setProfessor(Professor p) {
        nome.set(p.getNome());
        id.set(p.getId().toString());
        turno.set(p.getTurno());
        sala.set(p.getSala());
        espec.set(p.getEspec());

    }

    public void gravar() {
        Professor p = getProf();
        pDao.adicionar(p);
        listar();
    }

    public void atualizar() {
        pDao.atualizar(idProperty().getValue(), getProf());
        limparCampos();
        listar();
    }

    public void deletar() {
        pDao.remover(idProperty().getValue());
        limparCampos();
        listar();
    }

    public void buscar() {
        listProf.clear();
        listProf.addAll(pDao.pesquisarPorCampo("nome", nomeProperty().getValue()));
    }

    public void nomeProf() {
        pDao.nomeProfessor(idProperty().getValue());
    }

    public void listar() {
        listProf.clear();
        listProf.addAll(pDao.mostrarProfs());
    }

    public void limparCampos() {
        nome.set("");
        id.set("");
        turno.set("");
        sala.set("");
        espec.set("");
    }
}
