package boundary;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TopMenuBoundary {
	
	  private RadioButton btnHome = new RadioButton("Home");
	  private RadioButton btnAlunos = new RadioButton("Alunos");
	  private RadioButton btnDisciplinas = new RadioButton("Disciplinas");
	  private RadioButton btnProfessores = new RadioButton("Professores");	
	  private RadioButton btnSair = new RadioButton("Sair");
	  
	  private static AnchorPane topMenu = new AnchorPane();  
	  
	  static PrincipalBoundary principal = new PrincipalBoundary();
	  static StrategyBoundary hBoundary = new HomeBoundary();
	  static StrategyBoundary aBoundary = new AlunoBoundary();
	  static StrategyBoundary dBoundary = new DisciplinaBoundary();
	  static StrategyBoundary pBoundary = new ProfessorBoundary();
	  
	  public Pane gerarTopMenuStrategy(String perfilUser) {
		  topMenu.setPrefHeight(50.0);
		  topMenu.setPrefWidth(800.0);
		  topMenu.setStyle("-fx-background-color: #BFCAB4;");

		  ToggleGroup group = new ToggleGroup();		  

		  switch (perfilUser) {
		  case "diretor":
			  btnHome.setLayoutX(19.0);
			  btnHome.setLayoutY(11.0);
			  btnHome.setMinSize(112, 29);
			  btnHome.setOnAction((e) -> {
				  principal.execute(topMenu, hBoundary.geraTela());
				  if (!btnHome.isSelected()) {
					  btnHome.setSelected(true);
				  }
			  });
			  btnHome.setToggleGroup(group);
			  btnHome.setSelected(true);
			  btnHome.setAlignment(Pos.CENTER);

			  btnAlunos.setLayoutX(148.0);
			  btnAlunos.setLayoutY(11.0);	
			  btnAlunos.setMinSize(112, 29);
			  btnAlunos.setOnAction((e) -> {
				  principal.execute(topMenu, aBoundary.geraTela());
				  if (!btnAlunos.isSelected()) {
					  btnAlunos.setSelected(true);
				  }
			  });
			  btnAlunos.setToggleGroup(group);
			  btnAlunos.setAlignment(Pos.CENTER);

			  btnDisciplinas.setLayoutX(277.0);
			  btnDisciplinas.setLayoutY(11.0);	
			  btnDisciplinas.setMinSize(112, 29);
			  btnDisciplinas.setOnAction((e) -> {
				  principal.execute(topMenu, dBoundary.geraTela());
				  if (!btnDisciplinas.isSelected()) {
					  btnDisciplinas.setSelected(true);
				  }
			  });
			  btnDisciplinas.setToggleGroup(group);
			  btnDisciplinas.setAlignment(Pos.CENTER);

			  btnProfessores.setLayoutX(406.0);
			  btnProfessores.setLayoutY(11.0);	
			  btnProfessores.setMinSize(112, 29);
			  btnProfessores.setOnAction((e) -> {
				  principal.execute(topMenu, pBoundary.geraTela());
				  if (!btnProfessores.isSelected()) {
					  btnProfessores.setSelected(true);
				  }
			  });
			  btnProfessores.setToggleGroup(group);
			  btnProfessores.setAlignment(Pos.CENTER);

			  btnSair.setLayoutX(668.0);
			  btnSair.setLayoutY(11.0);	
			  btnSair.setMinSize(112, 29);
			  btnSair.setOnAction((e) -> {
				  Platform.exit();
				  System.exit(0);
			  });
			  btnSair.setToggleGroup(group);
			  btnSair.setAlignment(Pos.CENTER);

			  topMenu
			  .getChildren()
			  .addAll(
					  btnHome,
					  btnAlunos,
					  btnDisciplinas,
					  btnProfessores,
					  btnSair
					  );
			  
			  topMenu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

			  return topMenu;
			  
		  case "professor":
			  btnHome.setLayoutX(19.0);
			  btnHome.setLayoutY(11.0);	
			  btnHome.setMinSize(112, 29);
			  btnHome.setOnAction((e) -> {
				  principal.execute(topMenu, hBoundary.geraTela());
				  if (!btnHome.isSelected()) {
					  btnHome.setSelected(true);
				  }
			  });
			  btnHome.setToggleGroup(group);
			  btnHome.setSelected(true);
			  btnHome.setAlignment(Pos.CENTER);

			  btnAlunos.setLayoutX(148.0);
			  btnAlunos.setLayoutY(11.0);	
			  btnAlunos.setMinSize(112, 29);
			  btnAlunos.setOnAction((e) -> {
				  principal.execute(topMenu, aBoundary.geraTela());
				  if (!btnAlunos.isSelected()) {
					  btnAlunos.setSelected(true);
				  }
			  });
			  btnAlunos.setToggleGroup(group);
			  btnAlunos.setAlignment(Pos.CENTER);

			  btnDisciplinas.setLayoutX(260.0);
			  btnDisciplinas.setLayoutY(11.0);	
			  btnDisciplinas.setMinSize(112, 29);
			  btnDisciplinas.setOnAction((e) -> {
				  principal.execute(topMenu, dBoundary.geraTela());
				  if (!btnDisciplinas.isSelected()) {
					  btnDisciplinas.setSelected(true);
				  }
			  });
			  btnDisciplinas.setToggleGroup(group);
			  btnDisciplinas.setAlignment(Pos.CENTER);

			  btnSair.setLayoutX(668.0);
			  btnSair.setLayoutY(11.0);	
			  btnSair.setMinSize(112, 29);
			  btnSair.setOnAction((e) -> {
				  Platform.exit();
				  System.exit(0);
			  });
			  btnSair.setToggleGroup(group);
			  btnSair.setAlignment(Pos.CENTER);

			  topMenu
			  .getChildren()
			  .addAll(
					  btnHome,
					  btnAlunos,
					  btnDisciplinas,
					  btnSair
					  );
			  
			  topMenu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

			  return topMenu;

		  case "":
			  btnHome.setLayoutX(19.0);
			  btnHome.setLayoutY(11.0);
			  btnHome.setMinSize(112, 29);
			  btnHome.setOnAction((e) -> {
				  principal.execute(topMenu, hBoundary.geraTela());
				  if (!btnHome.isSelected()) {
					  btnHome.setSelected(true);
				  }
			  });
			  btnHome.setToggleGroup(group);
			  btnHome.setSelected(true);
			  btnHome.setAlignment(Pos.CENTER);

			  btnSair.setLayoutX(668.0);
			  btnSair.setLayoutY(11.0);
			  btnSair.setMinSize(112, 29);
			  btnSair.setOnAction((e) -> {
				  Platform.exit();
				  System.exit(0);
			  });
			  btnSair.setToggleGroup(group);
			  btnSair.setAlignment(Pos.CENTER);

			  topMenu
			  .getChildren()
			  .addAll(
					  btnHome,
					  btnSair
					  );
			  
			  topMenu.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

			  return topMenu;
		  }
		  
		  return null;
		  
	  }

}