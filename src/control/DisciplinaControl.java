package control;

import org.bson.types.ObjectId;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.bean.Disciplina;
import utilities.Utils;

public class DisciplinaControl {

    Utils ut = new Utils();
    // String descricao, periodo, modalidade;
    // ObjectId id, profId;
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
}
