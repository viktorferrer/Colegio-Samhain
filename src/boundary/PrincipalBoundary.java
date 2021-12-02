package boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.ConnectDB;

public class PrincipalBoundary extends Application implements CommandExecution {
    
	static AnchorPane principal = new AnchorPane();
    static StrategyBoundary login = new LoginBoundary();

    @Override
    public void start(Stage stage) throws Exception {

        Scene sc = new Scene(principal, 800, 450);
        principal.setStyle("-fx-background-color: #E4E4DC;");	
        
        principal.setLeftAnchor(login.geraTela(), 0.0);
        principal.getChildren().addAll(login.geraTela());
		
        principal.setPrefHeight(768.0);
        principal.setPrefWidth(1366.0);
 
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

	@Override
	public void execute(Pane top, Pane left) {
		principal.getChildren().clear();
		principal.setTopAnchor(top, 0.0);
		principal.setLeftAnchor(left, 0.0);
		principal.getChildren().addAll(top, left);
	}
}