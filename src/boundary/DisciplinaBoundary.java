package boundary;

import control.DisciplinaControl;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.bean.Disciplina;

public class DisciplinaBoundary extends CommandProducer implements StratregyBoundary {

    TextField tfDescricao = new TextField();
    TextField tfPeriodo = new TextField();
    TextField tfModalidade = new TextField();
    TextField tfId = new TextField();
    TextField tfProfId = new TextField();

    Label lbDescricao = new Label();
    Label lbPeriodo = new Label();
    Label lbModalidade = new Label();
    Label lbId = new Label();
    Label lbProfId = new Label();

    Button btnGravar = new Button();
    Button btnAtualizar = new Button();
    Button btnBuscar = new Button();
    Button btnRemover = new Button();
    Button btnLimpar = new Button();

    TableView<Disciplina> tabela = new TableView<Disciplina>();
    DisciplinaControl control = new DisciplinaControl();

    @Override
    public Pane geraTela() {
        bind();

        
        AnchorPane aPane = new AnchorPane();
        // altura
        aPane.setPrefHeight(400);
        // largura
        aPane.setPrefWidth(800);

        // lbDescricao.setLayoutX();
        // lbDescricao.setLayoutY();
        // lbDescricao.setPrefHeight();
        // lbDescricao.setPrefWidth();

        // tfDescricao.setLayoutX();
        // tfDescricao.setLayoutY();
        // tfDescricao.setPrefHeight();
        // tfDescricao.setPrefWidth();

        // lbPeriodo.setLayoutX();
        // lbPeriodo.setLayoutY();
        // lbPeriodo.setPrefHeight();
        // lbPeriodo.setPrefWidth();

        // tfPeriodo.setLayoutX();
        // tfPeriodo.setLayoutY();
        // tfPeriodo.setPrefHeight();
        // tfPeriodo.setPrefWidth();

        // lbModalidade.setLayoutX();
        // lbModalidade.setLayoutY();
        // lbModalidade.setPrefHeight();
        // lbModalidade.setPrefWidth();

        // tfModalidade.setLayoutX();
        // tfModalidade.setLayoutY();
        // tfModalidade.setPrefHeight();
        // tfModalidade.setPrefWidth();

        // lbProfId.setLayoutX();
        // lbProfId.setLayoutY();
        // lbProfId.setPrefHeight();
        // lbProfId.setPrefWidth();

        // tfProfId.setLayoutX();
        // tfProfId.setLayoutY();
        // tfProfId.setPrefHeight();
        // tfProfId.setPrefWidth();

        // lbId.setLayoutX();
        // lbId.setLayoutY();
        // lbId.setPrefHeight();
        // lbId.setPrefWidth();

        // tfId.setLayoutX();
        // tfId.setLayoutY();
        // tfId.setPrefHeight();
        // tfId.setPrefWidth();

        btnGravar.setLayoutX(82);
        btnGravar.setLayoutY(152);
        btnGravar.setPrefHeight(21);
        btnGravar.setPrefWidth(92);

        btnAtualizar.setLayoutX(218);
        btnAtualizar.setLayoutY(152);
        btnAtualizar.setPrefHeight(21);
        btnAtualizar.setPrefWidth(92);

        btnRemover.setLayoutX(354);
        btnRemover.setLayoutY(152);
        btnRemover.setPrefHeight(21);
        btnRemover.setPrefWidth(92);

        btnBuscar.setLayoutX(490);
        btnBuscar.setLayoutY(152);
        btnBuscar.setPrefHeight(21);
        btnBuscar.setPrefWidth(92);

        btnLimpar.setLayoutX(626);
        btnLimpar.setLayoutY(152);
        btnLimpar.setPrefHeight(21);
        btnLimpar.setPrefWidth(92);

        aPane.getChildren().addAll(tfDescricao,
                tfPeriodo,
                tfModalidade,
                tfId,
                tfProfId,
                lbDescricao,
                lbPeriodo,
                lbModalidade,
                lbId,
                lbProfId,
                btnGravar,
                btnAtualizar,
                btnBuscar,
                btnRemover,
                btnLimpar,
                tabela);
        return aPane;
    }

    public void geraTabela() {
    }

    public TableView<Disciplina> getTable(){
        return tabela;
    }

    private void bind(){
        Bindings.bindBidirectional(tfDescricao.textProperty(), control.descricaoProperty());
        Bindings.bindBidirectional(tfPeriodo.textProperty(), control.periodoProperty());
        Bindings.bindBidirectional(tfModalidade.textProperty(), control.modalidadeProperty());
        Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(tfProfId.textProperty(), control.profIdProperty());
    }


}
