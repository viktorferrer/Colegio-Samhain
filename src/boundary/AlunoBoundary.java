package boundary;

import java.lang.StackWalker.Option;
import java.util.Optional;

import control.AlunoControl;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
import model.bean.Aluno;

public class AlunoBoundary extends CommandProducer implements StrategyBoundary {

    TextField tfId = new TextField();
    TextField tfNome = new TextField();
    TextField tfTurma = new TextField();
    TextField tfPeriodo = new TextField();
    TextField tfMatricula = new TextField();
    TextField tfNota = new TextField();

    Label lbId = new Label("ID: ");
    Label lbNome = new Label("Nome: ");
    Label lbTurma = new Label("Turma: ");
    Label lbPeriodo = new Label("Periodo: ");
    Label lbMatricula = new Label("Matrícula: ");
    Label lbNota = new Label("Nota: ");

    Button btnGravar = new Button("Gravar");
    Button btnAtualizar = new Button("Atualizar");
    Button btnBuscar = new Button("Pesquisar");
    Button btnRemover = new Button("Remover");
    Button btnLimpar = new Button("Limpar");

    TableView<Aluno> tabela = new TableView<Aluno>();
    AlunoControl control = new AlunoControl();

    @Override
    public Pane geraTela() {

        bind();

        AnchorPane aPane = new AnchorPane();
        aPane.setPrefHeight(400);
        aPane.setPrefWidth(800);
        aPane.setLayoutY(50);
        aPane.setStyle("-fx-background-color: #E4E4DC");

        lbNome.setLayoutX(20);
        lbNome.setLayoutY(23);
        lbNome.setPrefHeight(23);
        lbNome.setPrefWidth(65);

        tfNome.setLayoutX(20);
        tfNome.setLayoutY(43);
        tfNome.setPrefHeight(25);
        tfNome.setPrefWidth(224);

        lbMatricula.setLayoutX(288);
        lbMatricula.setLayoutY(23);
        lbMatricula.setPrefHeight(23);
        lbMatricula.setPrefWidth(110);

        tfMatricula.setLayoutX(288);
        tfMatricula.setLayoutY(43);
        tfMatricula.setPrefHeight(25);
        tfMatricula.setPrefWidth(224);

        lbId.setLayoutX(556);
        lbId.setLayoutY(23);
        lbId.setPrefHeight(25);
        lbId.setPrefWidth(45);

        tfId.setLayoutX(556);
        tfId.setLayoutY(43);
        tfId.setPrefHeight(25);
        tfId.setPrefWidth(224);

        lbPeriodo.setLayoutX(20);
        lbPeriodo.setLayoutY(75);
        lbPeriodo.setPrefHeight(25);
        lbPeriodo.setPrefWidth(100);

        tfPeriodo.setLayoutX(20);
        tfPeriodo.setLayoutY(96);
        tfPeriodo.setPrefHeight(25);
        tfPeriodo.setPrefWidth(224);

        lbTurma.setLayoutX(288);
        lbTurma.setLayoutY(75);
        lbTurma.setPrefHeight(25);
        lbTurma.setPrefWidth(70);

        tfTurma.setLayoutX(288);
        tfTurma.setLayoutY(96);
        tfTurma.setPrefHeight(25);
        tfTurma.setPrefWidth(224);

        lbNota.setLayoutX(556);
        lbNota.setLayoutY(75);
        lbNota.setPrefHeight(25);
        lbNota.setPrefWidth(70);

        tfNota.setLayoutX(556);
        tfNota.setLayoutY(96);
        tfNota.setPrefHeight(25);
        tfNota.setPrefWidth(224);

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

        aPane.getChildren().addAll(tfId,
                tfNome,
                tfTurma,
                tfPeriodo,
                tfMatricula,
                tfNota,
                lbId,
                lbNome,
                lbTurma,
                lbPeriodo,
                lbMatricula,
                lbNota, btnGravar,
                btnAtualizar,
                btnBuscar,
                btnRemover,
                btnLimpar,
                tabela);

        return aPane;
    }

    public void geraTabela() {
        control.listar();
        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));

        TableColumn<Aluno, String> colMatricula = new TableColumn<>("Matricula");
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno, String>("matricula"));

        TableColumn<Aluno, Double> colNota = new TableColumn<>("Nota");
        colNota.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota"));

        TableColumn<Aluno, String> colPeriodo = new TableColumn<>("Período");
        colPeriodo.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nota"));

        TableColumn<Aluno, String> colTurma = new TableColumn<>("Turma");
        colTurma.setCellValueFactory(new PropertyValueFactory<Aluno, String>("turma"));

        tabela.getColumns().addAll(colNome, colMatricula, colNota, colPeriodo, colTurma);
        
        tabela.setItems(control.getListAlunos());

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, older, newer) -> {
            if (newer != null) {
                control.setAluno(newer);
            } 
        });

        tabela.setLayoutY(180);
        tabela.setPrefWidth(800);
        tabela.setPrefHeight(220);
    }

    public TableView<Aluno> getTable() {
        return tabela;
    }

    private void bind() {
        Bindings.bindBidirectional(tfNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(tfMatricula.textProperty(), control.matriculaProperty());
        Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(tfPeriodo.textProperty(), control.periodoProperty());
        Bindings.bindBidirectional(tfTurma.textProperty(), control.turmaProperty());
        Bindings.bindBidirectional(tfNota.textProperty(), control.notaProperty(), new NumberStringConverter());
    };

}
