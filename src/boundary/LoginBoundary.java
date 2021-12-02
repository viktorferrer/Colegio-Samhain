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
	Label lblUser = new Label("User:");
	Label lblSenha = new Label("Senha:");
	TextField tfUser = new TextField();
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
		lblTituto.setStyle(
				"-fx-text-alignment: center; -fx-text-fill: #6F9580; -fx-font-weight: bold; -fx-font-size: 55px;");

		lblUser.setLayoutX(250.0);
		lblUser.setLayoutY(135.5);
		lblUser.setPrefWidth(150);
		lblUser.setStyle(
				"-fx-text-alignment: center; -fx-text-fill: #6F9580; -fx-font-weight: bold; -fx-font-size: 18px;");

		tfUser.setLayoutX(250.0);
		tfUser.setLayoutY(170.0);
		tfUser.setMinSize(300.0, 30.0);
		tfUser.setStyle("-fx-border-color: #000000;");

		lblSenha.setLayoutX(250);
		lblSenha.setLayoutY(230);
		lblSenha.setPrefWidth(150);
		lblSenha.setStyle(
				"-fx-text-alignment: center; -fx-text-fill: #6F9580; -fx-font-weight: bold; -fx-font-size: 18px;");

		tfSenha.setLayoutX(250.0);
		tfSenha.setLayoutY(265.5);
		tfSenha.setMinSize(300.0, 30.0);
		tfSenha.setStyle("-fx-border-color: #000000;");

		btnLogin.setOnAction((e) -> {
			identificarPerfilUser(control.login());
		});
		btnLogin.setLayoutX(250.0);
		btnLogin.setLayoutY(320.0);
		btnLogin.setMinSize(300.0, 30.0);
		btnLogin.setStyle("-fx-background-color: #6F9580; -fx-text-fill: white; -fx-cursor: hand;");

		login
				.getChildren()
				.addAll(
						lblTituto,
						lblUser,
						lblSenha,
						tfUser,
						tfSenha,
						btnLogin);

		return login;
	}

	private void identificarPerfilUser(int permissao) {
		tfUser.setText("");
		tfSenha.setText("");

		switch (permissao) {
			case 1:
				JOptionPane.showMessageDialog(null, "Seja bem vindo administrador!", "Login",
						JOptionPane.INFORMATION_MESSAGE);
				principal.execute(topMenu.gerarTopMenuStrategy(permissao), home.geraTela());
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Seja bem vindo funcionário!", "Login",
						JOptionPane.INFORMATION_MESSAGE);
				principal.execute(topMenu.gerarTopMenuStrategy(permissao), home.geraTela());
				break;
			case 0:
				JOptionPane.showMessageDialog(null,
						"Seus dados não foram encontrados, entre com contato com o administrador!", "Login",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Erro ao entrar", "Erro no Login", JOptionPane.INFORMATION_MESSAGE);
				break;
		}
	}

	private void vincular() {
		Bindings.bindBidirectional(tfUser.textProperty(), control.userProperty());
		Bindings.bindBidirectional(tfSenha.textProperty(), control.senhaProperty());
	}
}