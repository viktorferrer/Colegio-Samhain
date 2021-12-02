package boundary;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class HomeBoundary extends CommandProducer implements StrategyBoundary {

	private Label lblHome = new Label("Colégio Samhain");
	
	@Override
	public Pane geraTela() {
	    AnchorPane home = new AnchorPane();
	    
	    home.setPrefHeight(400.0);
	    home.setPrefWidth(800.0);
	    home.setLayoutY(50);
	    home.setStyle("-fx-background-color: #E4E4DC;");	 
		
	    lblHome.setLayoutX(124);
	    lblHome.setLayoutY(183);
	    lblHome.setMaxWidth(600);
	    lblHome.setStyle("-fx-text-alignment: center; -fx-text-fill: #6F9580; -fx-font-weight: bold; -fx-font-size: 55px;");
	    
	    home.getChildren().add(lblHome);
	    
		return home;
	}

}