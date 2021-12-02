package boundary;

import javax.swing.JOptionPane;
import control.LoginControl;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LoginBoundary extends CommandProducer implements StrategyBoundary {
	
	  Label lblTituto = new Label("Colégio Samhain");
	  Label lblEmail = new Label("Email:");
	  Label lblSenha = new Label("Senha:");
	  TextField tfEmail = new TextField();
	  TextField tfSenha = new TextField();
      Button btnLogin = new Button("Login");

	  static LoginControl control = new LoginControl();
	  static PrincipalBoundary principal = new PrincipalBoundary();
	  static TopMenuBoundary topMenu = new TopMenuBoundary();
	  static StrategyBoundary home = new HomeBoundary();

	  @Override
	  public Pane geraTela() {
		  AnchorPane login = new AnchorPane();

		  vincular();

		  login.setLayoutY(50);
		  login.setPrefHeight(400.0);
		  login.setPrefWidth(800.0);
		  login.setStyle("-fx-background-color: #E4E4DC;");

		  lblTituto.setLayoutX(124);
		  lblTituto.setLayoutY(0);
		  lblTituto.setPrefWidth(600);
		  lblTituto.setStyle("-fx-text-alignment: center; -fx-text-fill: #6F9580; -fx-font-weight: bold; -fx-font-size: 55px;");
		  
		  lblEmail.setLayoutX(250);
		  lblEmail.setLayoutY(171);
		  lblEmail.setPrefWidth(150);
		  lblEmail.setStyle("-fx-text-alignment: center; -fx-text-fill: #6F9580; -fx-font-weight: bold; -fx-font-size: 18px;");
		  
		  tfEmail.setLayoutX(250.0);
		  tfEmail.setLayoutY(201.0);
		  tfEmail.setMinSize(300.0, 30.0);
		  tfEmail.setStyle("-fx-border-color: #000000;");
		  
		  lblSenha.setLayoutX(250);
		  lblSenha.setLayoutY(260);
		  lblSenha.setPrefWidth(150);
		  lblSenha.setStyle("-fx-text-alignment: center; -fx-text-fill: #6F9580; -fx-font-weight: bold; -fx-font-size: 18px;");

		  tfSenha.setLayoutX(250.0);
		  tfSenha.setLayoutY(289.0);
		  tfSenha.setMinSize(300.0, 30.0);
		  tfSenha.setStyle("-fx-border-color: #000000;");

		  btnLogin.setOnAction((e) -> {
			  identificarPerfilUser(control.login());
		  });
		  btnLogin.setLayoutX(250.0);
		  btnLogin.setLayoutY(356.0);
		  btnLogin.setMinSize(300.0, 30.0);
		  btnLogin.setStyle("-fx-background-color: #6F9580; -fx-text-fill: white; -fx-cursor: hand;");

		  login
		  .getChildren()
		  .addAll(
				  lblTituto,
				  lblEmail,
				  lblSenha,
				  tfEmail,
				  tfSenha,
				  btnLogin
				  );

		  return login;
	  }

	  private void identificarPerfilUser(String perfilUser) {
		  tfEmail.setText("");
		  tfSenha.setText("");

		  switch (perfilUser) {
		  case "diretor":
			  JOptionPane.showMessageDialog(null,"Seja bem vindo Diretor!", "Login" , JOptionPane.INFORMATION_MESSAGE);
			  principal.execute(topMenu.gerarTopMenuStrategy(perfilUser), home.geraTela());
			  break;
		  case "professor":
			  JOptionPane.showMessageDialog(null,"Seja bem vindo professor!", "Login", JOptionPane.INFORMATION_MESSAGE);
			  principal.execute(topMenu.gerarTopMenuStrategy(perfilUser), home.geraTela());
			  break;
		  case "":
			  JOptionPane.showMessageDialog(null,"Entre em contato com o Diretor!","Login", JOptionPane.INFORMATION_MESSAGE);
			  principal.execute(topMenu.gerarTopMenuStrategy(perfilUser), home.geraTela());
			  break;
		  case "400 - Bad Request":
		  case "401 - Unauthorized":
			  JOptionPane.showMessageDialog( null, "Seus dados não foram encontrados, entre com contato com o diretor!", perfilUser,JOptionPane.INFORMATION_MESSAGE);
			  break;
		  default:
			  JOptionPane.showMessageDialog(null,"Erro ao entrar","Erro no Login",JOptionPane.INFORMATION_MESSAGE);
			  break;
		  }
	  }

	  private void vincular() {
		  Bindings.bindBidirectional(tfEmail.textProperty(), control.emailProperty());
		  Bindings.bindBidirectional(tfSenha.textProperty(), control.senhaProperty());
	  }
}