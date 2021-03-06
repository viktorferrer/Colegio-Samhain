package control;

import javax.swing.JOptionPane;

import org.bson.types.ObjectId;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.bean.Aluno;
import model.dao.AlunoDAO;
import model.dao.AlunoDAOImpl;

public class AlunoControl {

    private AlunoDAO aDao = new AlunoDAOImpl();

    private ObservableList<Aluno> listAluno = FXCollections.observableArrayList();

    StringProperty nome = new SimpleStringProperty();
    StringProperty matricula = new SimpleStringProperty();
    StringProperty id = new SimpleStringProperty();
    StringProperty periodo = new SimpleStringProperty();
    StringProperty turma = new SimpleStringProperty();
    StringProperty status = new SimpleStringProperty();

    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty matriculaProperty() {
        return matricula;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty periodoProperty() {
        return periodo;
    }

    public StringProperty turmaProperty() {
        return turma;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public ObservableList<Aluno> getListAlunos() {
        return listAluno;
    }

    public Aluno getAluno() {
        Aluno a = new Aluno(nomeProperty().getValue());
        a.setMatricula(matriculaProperty().getValue());
        a.setTurma(turmaProperty().getValue());
        a.setPeriodo(periodoProperty().getValue());
        a.setStatus(statusProperty().getValue());
        String stringId = idProperty().getValue();
        if (stringId == null || stringId.isBlank()) {
            a.setId(new ObjectId());
        } else {
            a.setId(new ObjectId(stringId));
        }
        return a;
    }

    public void setAluno(Aluno a) {
        nome.set(a.getNome());
        id.set(a.getId().toString());
        matricula.set(a.getMatricula());
        turma.set(a.getTurma());
        periodo.set(a.getPeriodo());
        status.set(a.getStatus());
    }

    public void gravar() {
        Aluno a = getAluno();
        if (aDao.pesquisarPorMatricula(matriculaProperty().getValue()) == null) {
            aDao.adicionar(a);
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Matr??cula indispon??vel.\nPor favor, escolha outro registro", "ERRO", JOptionPane.ERROR_MESSAGE);
            matricula.set("");
        }
        listar();
    }

    public void atualizar() {
        aDao.atualizar(idProperty().getValue(), getAluno());
        listar();
        limparCampos();
    }

    public void buscar() {
        listAluno.clear();
        listAluno.addAll(aDao.pesquisarPorMatricula(matriculaProperty().getValue()));
    }

    public void deletar() {
        aDao.remover(idProperty().getValue());
        listar();
        limparCampos();
    }

    public void listar() {
        listAluno.clear();
        listAluno.addAll(aDao.mostrarAlunos());
    }

    public void limparCampos() {
        nome.set("");
        id.set("");
        status.set("");
        matricula.set("");
        turma.set("");
        periodo.set("");
    }
}
