package boundary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.bean.Aluno;
import utilities.ConnectDB;

public class PrincipalBoundary extends Application implements CommandExecution {
    
    AnchorPane principal = new AnchorPane();
    AlunoBoundary aBoundary = new AlunoBoundary();
    DisciplinaBoundary dBoundary = new DisciplinaBoundary();
    ProfessorBoundary pBoundary = new ProfessorBoundary();
    MenuButton mButton = new MenuButton("Opções");
    
    public PrincipalBoundary() {
        aBoundary.addExecution(this);
        dBoundary.addExecution(this);
        pBoundary.addExecution(this);
    }

    @Override
    public void execute(String command) {

        principal.getChildren().clear();
        principal.setTopAnchor(mButton, 0.0);
        principal.getChildren().add(mButton);

        if ("boundary-aluno".equals(command)) {
            principal.setLeftAnchor(aBoundary.geraTela(), 0.0);
            principal.getChildren().add(aBoundary.geraTela());

        } else if ("boundary-professor".equals(command)) {
            principal.setLeftAnchor(pBoundary.geraTela(), 0.0);
            principal.getChildren().add(pBoundary.geraTela());

        } else if ("boundary-disciplina".equals(command)) {
            principal.setLeftAnchor(dBoundary.geraTela(), 0.0);
            principal.getChildren().add(dBoundary.geraTela());
        } else if ("sair".equals(command)) {
            Platform.exit();
            System.exit(0);
        }

    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene sc = new Scene(principal, 800, 400);

        MenuItem itemAluno = new MenuItem("Aluno");
        itemAluno.setOnAction((e) -> {
            System.out.println("Clicou Aluno");
            execute("boundary-aluno");
        });

        MenuItem itemProf = new MenuItem("Professor");
        itemProf.setOnAction((e) -> {
            execute("boundary-professor");
        });

        MenuItem itemDisc = new MenuItem("Disciplina");
        itemDisc.setOnAction((e) -> {
            execute("boundary-disciplina");
        });

        MenuItem itemSair = new MenuItem("Sair");
        itemSair.setOnAction((e) -> {
            execute("sair");
        });

        mButton.getItems().add(itemAluno);
        mButton.getItems().add(itemProf);
        mButton.getItems().add(itemDisc);
        mButton.getItems().add(itemSair);

        principal.setTopAnchor(mButton, 0.0);

        principal.getChildren().add(mButton);

        stage.setScene(sc);

        stage.setTitle("Colégio Samhain");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        ConnectDB cDb = new ConnectDB();
        cDb.connect();
        Application.launch(PrincipalBoundary.class, args);

    }
}
