package control;

import org.bson.types.ObjectId;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.bean.Professor;

public class ProfessorControl {
    
    StringProperty nome = new SimpleStringProperty();
    StringProperty id = new SimpleStringProperty();
    StringProperty turno = new SimpleStringProperty();
    StringProperty sala = new SimpleStringProperty();
    StringProperty espec = new SimpleStringProperty();

public StringProperty nomeProperty(){
    return nome;
}

public StringProperty idProperty(){
    return id;
}

public StringProperty turnoProperty(){
    return turno;
}

public StringProperty salaProperty(){
    return sala;
}

public StringProperty especProperty(){
    return espec;
}

public Professor getProf(){
    Professor p = new Professor(nomeProperty().getValue());
    p.setTurno(turnoProperty().getValue());
    p.setSala(salaProperty().getValue());
    p.setEspec(especProperty().getValue());
    String stringId = idProperty().getValue();
    if (stringId == null || stringId.isBlank()){
        p.setId(new ObjectId());
    } else {
        p.setId(new ObjectId(stringId));
    }
    return p;
}


public void setProfessor(Professor p){
    nome.set(p.getNome());
    id.set(p.getId().toString());
    turno.set(p.getTurno());
    sala.set(p.getSala());
    espec.set(p.getEspec());

}




}
