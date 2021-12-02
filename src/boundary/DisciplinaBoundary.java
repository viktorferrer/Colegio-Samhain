package boundary;

import java.util.Optional;

import javax.swing.JOptionPane;

import control.DisciplinaControl;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import model.bean.Disciplina;

public class DisciplinaBoundary extends CommandProducer implements StrategyBoundary {

    TextField tfDescricao = new TextField();
    TextField tfPeriodo = new TextField();
    TextField tfModalidade = new TextField();
    TextField tfId = new TextField();
    ComboBox <String> cbProfId = new ComboBox<>();

    Label lbDescricao = new Label("Descrição:");
    Label lbPeriodo = new Label("Periodo:");
    Label lbModalidade = new Label("Modalidade:");
    Label lbId = new Label("ID:");
    Label lbProfId = new Label("Professor:");

    Button btnGravar = new Button("Gravar");
    Button btnAtualizar = new Button("Atualizar");
    Button btnBuscar = new Button("Pesquisar");
    Button btnRemover = new Button("Remover");
    Button btnLimpar = new Button("Limpar");

    TableView<Disciplina> tabela = new TableView<Disciplina>();
    DisciplinaControl control = new DisciplinaControl();

    @Override
    public Pane geraTela() {

        vincular();

        AnchorPane disciplina = new AnchorPane();

        disciplina.setPrefHeight(400.0);
        disciplina.setPrefWidth(800.0);
        disciplina.setLayoutY(50);
        disciplina.setStyle("-fx-background-color: #E4E4DC;");

        lbDescricao.setLayoutX(20);
        lbDescricao.setLayoutY(23);
        lbDescricao.setPrefHeight(23);
        lbDescricao.setPrefWidth(100);

        tfDescricao.setLayoutX(20);
        tfDescricao.setLayoutY(43);
        tfDescricao.setPrefHeight(25);
        tfDescricao.setPrefWidth(224);

        lbPeriodo.setLayoutX(288);
        lbPeriodo.setLayoutY(23);
        lbPeriodo.setPrefHeight(23);
        lbPeriodo.setPrefWidth(60);

        tfPeriodo.setLayoutX(288);
        tfPeriodo.setLayoutY(43);
        tfPeriodo.setPrefHeight(25);
        tfPeriodo.setPrefWidth(224);

        lbId.setLayoutX(556);
        lbId.setLayoutY(23);
        lbId.setPrefHeight(25);
        lbId.setPrefWidth(120);

        tfId.setLayoutX(556);
        tfId.setLayoutY(43);
        tfId.setPrefHeight(25);
        tfId.setPrefWidth(224);
        tfId.setEditable(false);
        tfId.setDisable(true);

        lbProfId.setLayoutX(20);
        lbProfId.setLayoutY(75);
        lbProfId.setPrefHeight(25);
        lbProfId.setPrefWidth(120);

        cbProfId.setLayoutX(20);
        cbProfId.setLayoutY(96);
        cbProfId.setPrefHeight(25);
        cbProfId.setPrefWidth(224);
        cbProfId.setItems(control.getProfNomes());

        lbModalidade.setLayoutX(288);
        lbModalidade.setLayoutY(75);
        lbModalidade.setPrefHeight(25);
        lbModalidade.setPrefWidth(100);

        tfModalidade.setLayoutX(288);
        tfModalidade.setLayoutY(96);
        tfModalidade.setPrefHeight(25);
        tfModalidade.setPrefWidth(224);

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
                JOptionPane.showMessageDialog(null, "Selecione a disciplina que deseja remover.", "ERRO", JOptionPane.ERROR_MESSAGE);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Confirmar a remoção da disciplina?", ButtonType.YES,
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

        disciplina.getChildren().addAll(tfDescricao,
                tfPeriodo,
                tfModalidade,
                tfId,
                cbProfId,
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

        return disciplina;
    }

    public void geraTabela() {
        control.listar();

        TableColumn<Disciplina, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("descricao"));

        TableColumn<Disciplina, String> colPeriodo = new TableColumn<>("Periodo");
        colPeriodo.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("periodo"));

        TableColumn<Disciplina, String> colModalidade = new TableColumn<>("Modalidade");
        colModalidade.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("modalidade"));

        TableColumn<Disciplina, String> colProfessor = new TableColumn<>("Professor");
        colProfessor.setCellValueFactory((p) -> {
            String profId = p.getValue().getProfId().toString();
            return new ReadOnlyStringWrapper(control.idParaNome(profId));
        });

        tabela.getColumns().addAll(colDescricao, colPeriodo, colModalidade, colProfessor);

        tabela.setItems(control.getListDisc());

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, older, newer) -> {
            if (newer != null) {
                control.setDisc(newer);
            }
        });

        tabela.setLayoutY(180);
        tabela.setPrefWidth(800);
        tabela.setPrefHeight(220);
    }

    public TableView<Disciplina> getTable() {
        return tabela;
    }

    private void vincular() {
        Bindings.bindBidirectional(tfDescricao.textProperty(), control.descricaoProperty());
        Bindings.bindBidirectional(tfPeriodo.textProperty(), control.periodoProperty());
        Bindings.bindBidirectional(tfModalidade.textProperty(), control.modalidadeProperty());
        Bindings.bindBidirectional(tfId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(cbProfId.valueProperty(), control.profIdProperty());
    }

}