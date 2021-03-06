package control;

import java.util.List;

import org.bson.types.ObjectId;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.bean.Disciplina;
import model.bean.Professor;
import model.dao.DisciplinaDAO;
import model.dao.DisciplinaDAOImpl;
import model.dao.ProfessorDAO;
import model.dao.ProfessorDAOImpl;
import utilities.Utils;

public class DisciplinaControl {

    Utils ut = new Utils();
    DisciplinaDAO dao = new DisciplinaDAOImpl();
    ProfessorDAO pDao = new ProfessorDAOImpl();

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

    public ObservableList<Disciplina> getListDisc() {
        return listDisc;
    }

    public Disciplina getDisc() {
        Disciplina d = new Disciplina(descricaoProperty().getValue());
        d.setPeriodo(periodoProperty().getValue());
        d.setModalidade(modalidadeProperty().getValue());
        d.setId(ut.getId(idProperty().getValue()));
        d.setProfId(new ObjectId(nomeParaId(profIdProperty().getValue())));
        return d;
    }

    public void setDisc(Disciplina d) {
        descricao.set(d.getDescricao());
        periodo.set(d.getPeriodo());
        modalidade.set(d.getModalidade());
        id.set(d.getId().toString());
        profId.set(idParaNome(d.getProfId().toString()));
    }

    public void gravar() {
        Disciplina d = getDisc();
        dao.adicionar(d);

        listar();
        limparCampos();
    }

    public void atualizar() {
        dao.atualizar(idProperty().getValue(), getDisc());
        listar();
        limparCampos();
    }

    public void buscar() {
        listDisc.clear();
        listDisc.addAll(dao.pesquisarPorCampo("descricao", descricaoProperty().getValue()));
    }

    public void deletar() {
        dao.remover(idProperty().getValue());
        listar();
        limparCampos();
    }

    public void listar() {
        listDisc.clear();
        listDisc.addAll(dao.mostrarDisciplinas());
    }

    public void limparCampos() {
        descricao.set("");
        periodo.set("");
        modalidade.set("");
        id.set("");
        profId.set("");

    }

    public ObservableList<String> getProfNomes() {
        ObservableList<String> nomesProf = FXCollections.observableArrayList();
        List<Professor> listaProfessor = pDao.mostrarProfs();
        for (Professor p : listaProfessor) {
            nomesProf.add(p.getNome());
        }
        return nomesProf;
    }

    public String nomeParaId(String nome) {
        List<Professor> listaProfessor = pDao.mostrarProfs();
        for (Professor p : listaProfessor) {
            if (p.getNome().equals(nome)) {
                return p.getId().toString();
            }
        }
        return null;
    }

    public String idParaNome(String id) {
        List<Professor> listaProfessor = pDao.mostrarProfs();
        for (Professor p : listaProfessor) {
            if (p.getId().toString().equals(id)) {
                return p.getNome();
            }
        }
        return null;
    }

    // metodo para retornar todos os nomes de prof
    // transformar o nome em id
    // transformar ID no nome

}
