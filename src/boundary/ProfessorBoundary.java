package boundary;

import model.bean.Professor;
import control.ProfessorControl;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ProfessorBoundary extends CommandProducer implements StratregyBoundary {

    TextField tfId = new TextField();
    TextField tfNome = new TextField();
    TextField tfSala = new TextField();
    TextField tfTurno = new TextField();
    TextField tfEspec = new TextField();

    Label lbId = new Label();
    Label lbNome = new Label();
    Label lbSala = new Label();
    Label lbTurno = new Label();
    Label lbEspec = new Label();

    Button btnGravar = new Button();
    Button btnAtualizar = new Button();
    Button btnBuscar = new Button();
    Button btnRemover = new Button();
    Button btnLimpar = new Button();

       

    TableView<Professor>tabela=new TableView<Professor>();
    ProfessorControl control = new ProfessorControl();

    @Override
    public Pane geraTela() {
        bind();
        AnchorPane aPane = new AnchorPane();
        aPane.setPrefHeight(400);
        aPane.setPrefWidth(800);
        
        
        // lbId.setLayoutX();
        // lbId.setLayoutY();
        // lbId.setPrefHeight();
        // lbId.setPrefWidth();
        
        // tfId.setLayoutX();
        // tfId.setLayoutY();
        // tfId.setPrefHeight();
        // tfId.setPrefWidth();       
        
        // lbNome.setLayoutX();
        // lbNome.setLayoutY();
        // lbNome.setPrefHeight();
        // lbNome.setPrefWidth();

        // tfNome.setLayoutX();
        // tfNome.setLayoutY();
        // tfNome.setPrefHeight();
        // tfNome.setPrefWidth();

        // lbEspec.setLayoutX();
        // lbEspec.setLayoutY();
        // lbEspec.setPrefHeight();
        // lbEspec.setPrefWidth();

        // tfEspec.setLayoutX();
        // tfEspec.setLayoutY();
        // tfEspec.setPrefHeight();
        // tfEspec.setPrefWidth();

        // lbSala.setLayoutX();
        // lbSala.setLayoutY();
        // lbSala.setPrefHeight();
        // lbSala.setPrefWidth();

        // tfSala.setLayoutX();
        // tfSala.setLayoutY();
        // tfSala.setPrefHeight();
        // tfSala.setPrefWidth();

        // lbTurno.setLayoutX();
        // lbTurno.setLayoutY();
        // lbTurno.setPrefHeight();
        // lbTurno.setPrefWidth();

        // tfTurno.setLayoutX();
        // tfTurno.setLayoutY();
        // tfTurno.setPrefHeight();
        // tfTurno.setPrefWidth();
                
        // btnGravar.setLayoutX(82);
        // btnGravar.setLayoutY(152);
        // btnGravar.setPrefHeight(21);
        // btnGravar.setPrefWidth(92);

        // btnAtualizar.setLayoutX(218);
        // btnAtualizar.setLayoutY(152);
        // btnAtualizar.setPrefHeight(21);
        // btnAtualizar.setPrefWidth(92);

        // btnRemover.setLayoutX(354);
        // btnRemover.setLayoutY(152);
        // btnRemover.setPrefHeight(21);
        // btnRemover.setPrefWidth(92);

        // btnBuscar.setLayoutX(490);
        // btnBuscar.setLayoutY(152);
        // btnBuscar.setPrefHeight(21);
        // btnBuscar.setPrefWidth(92);

        // btnLimpar.setLayoutX(626);
        // btnLimpar.setLayoutY(152);
        // btnLimpar.setPrefHeight(21);
        // btnLimpar.setPrefWidth(92);

        
        aPane.getChildren().addAll(tfId,
                tfNome,
                tfEspec,
                tfSala,
                tfTurno,
                lbId,
                lbNome,
                lbEspec,
                lbSala,
                lbTurno,
                btnGravar,
                btnAtualizar,
                btnRemover,
                btnBuscar);

        return aPane;
    }

    public void geraTabela() {
    }

    public TableView<Professor> getTable() {
        return tabela;
    }

    private void bind() {
        Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(tfNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(tfEspec.textProperty(), control.especProperty());
        Bindings.bindBidirectional(tfSala.textProperty(), control.salaProperty());
        Bindings.bindBidirectional(tfTurno.textProperty(), control.turnoProperty());
    }

}
