package boundary;

import java.util.Optional;

import control.ProfessorControl;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.bean.Professor;

public class ProfessorBoundary extends CommandProducer implements StrategyBoundary {

    TextField tfId = new TextField();
    TextField tfNome = new TextField();
    TextField tfSala = new TextField();
    TextField tfTurno = new TextField();
    ComboBox<String> cbEspec = new ComboBox<>();

    Label lbId = new Label("ID: ");
    Label lbNome = new Label("Nome: ");
    Label lbSala = new Label("Sala: ");
    Label lbTurno = new Label("Turno: ");
    Label lbEspec = new Label("Especialidade: ");

    Button btnGravar = new Button("Gravar");
    Button btnAtualizar = new Button("Atualizar");
    Button btnBuscar = new Button("Buscar");
    Button btnRemover = new Button("Remover");
    Button btnLimpar = new Button("Limpar");

    TableView<Professor> tabela = new TableView<Professor>();
    ProfessorControl control = new ProfessorControl();

    @Override
    public Pane geraTela() {

        vincular();

        AnchorPane professor = new AnchorPane();
        professor.setPrefHeight(400.0);
        professor.setPrefWidth(800.0);
        professor.setLayoutY(50);
        professor.setStyle("-fx-background-color: #E4E4DC;");

        lbNome.setLayoutX(20);
        lbNome.setLayoutY(23);
        lbNome.setPrefHeight(23);
        lbNome.setPrefWidth(65);

        tfNome.setLayoutX(20);
        tfNome.setLayoutY(43);
        tfNome.setPrefHeight(25);
        tfNome.setPrefWidth(224);

        lbEspec.setLayoutX(288);
        lbEspec.setLayoutY(23);
        lbEspec.setPrefHeight(23);
        lbEspec.setPrefWidth(110);

        cbEspec.setLayoutX(288);
        cbEspec.setLayoutY(43);
        cbEspec.setPrefHeight(25);
        cbEspec.setPrefWidth(224);
        ObservableList<String> espec = FXCollections.observableArrayList("1", "2");
        cbEspec.setItems(espec);

        lbId.setLayoutX(556);
        lbId.setLayoutY(23);
        lbId.setPrefHeight(25);
        lbId.setPrefWidth(45);

        tfId.setLayoutX(556);
        tfId.setLayoutY(43);
        tfId.setPrefHeight(25);
        tfId.setPrefWidth(224);
        tfId.setEditable(false);
        tfId.setDisable(true);

        lbSala.setLayoutX(20);
        lbSala.setLayoutY(75);
        lbSala.setPrefHeight(25);
        lbSala.setPrefWidth(50);

        tfSala.setLayoutX(20);
        tfSala.setLayoutY(96);
        tfSala.setPrefHeight(25);
        tfSala.setPrefWidth(224);

        lbTurno.setLayoutX(288);
        lbTurno.setLayoutY(75);
        lbTurno.setPrefHeight(25);
        lbTurno.setPrefWidth(60);

        tfTurno.setLayoutX(288);
        tfTurno.setLayoutY(96);
        tfTurno.setPrefHeight(25);
        tfTurno.setPrefWidth(224);

        btnGravar.setLayoutX(82);
        btnGravar.setLayoutY(152);
        btnGravar.setPrefHeight(21);
        btnGravar.setPrefWidth(92);

        btnGravar.setOnAction((e) -> {
            control.gravar();
        });

        btnAtualizar.setLayoutX(218);
        btnAtualizar.setLayoutY(152);
        btnAtualizar.setPrefHeight(21);
        btnAtualizar.setPrefWidth(92);

        btnAtualizar.setOnAction((e) -> {
            control.atualizar();
        });

        btnRemover.setLayoutX(354);
        btnRemover.setLayoutY(152);
        btnRemover.setPrefHeight(21);
        btnRemover.setPrefWidth(92);

        btnRemover.setOnAction((e) -> {
            if (tfId.getText() == null || tfId.getText() == "") {
                System.out.println("Selecione o aluno que deseja remover.");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Confirmar a remoção do aluno?", ButtonType.YES,
                        ButtonType.CANCEL);
                Optional<ButtonType> click = alert.showAndWait();
                if (click.isPresent() && click.get().equals(ButtonType.YES)) {
                    control.deletar();
                }
            }
        });

        btnBuscar.setLayoutX(490);
        btnBuscar.setLayoutY(152);
        btnBuscar.setPrefHeight(21);
        btnBuscar.setPrefWidth(92);

        btnBuscar.setOnAction((e) -> {
            control.buscar();
        });

        btnLimpar.setLayoutX(626);
        btnLimpar.setLayoutY(152);
        btnLimpar.setPrefHeight(21);
        btnLimpar.setPrefWidth(92);

        btnLimpar.setOnAction((e) -> {
            control.limparCampos();
        });

        if (getTable().getColumns().size() == 0) {
            geraTabela();
        }

        professor.getChildren().addAll(tfId,
                tfNome,
                cbEspec,
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
                btnBuscar,
                tabela);

        return professor;
    }

    public void geraTabela() {

        control.listar();

        TableColumn<Professor, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome"));

        TableColumn<Professor, String> colTurno = new TableColumn<>("Turno");
        colTurno.setCellValueFactory(new PropertyValueFactory<Professor, String>("turno"));

        TableColumn<Professor, String> colSala = new TableColumn<>("Sala");
        colSala.setCellValueFactory(new PropertyValueFactory<Professor, String>("sala"));

        TableColumn<Professor, String> colEspec = new TableColumn<>("Especialidade");
        colEspec.setCellValueFactory(new PropertyValueFactory<Professor, String>("espec"));

        tabela.getColumns().addAll(colNome, colTurno, colSala, colEspec);

        tabela.setItems(control.getListProf());

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, older, newer) -> {
            if (newer != null) {
                control.setProfessor(newer);
            }
        });

        tabela.setLayoutY(180);
        tabela.setPrefWidth(800);
        tabela.setPrefHeight(220);
    }

    public TableView<Professor> getTable() {
        return tabela;
    }

    private void vincular() {
        Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(tfNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(cbEspec.valueProperty(), control.especProperty());
        Bindings.bindBidirectional(tfSala.textProperty(), control.salaProperty());
        Bindings.bindBidirectional(tfTurno.textProperty(), control.turnoProperty());
    }

}