package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginControl {

    StringProperty email = new SimpleStringProperty("");
    StringProperty senha = new SimpleStringProperty("");

    // LoginDAO lDAO = new LoginDAO();

    public String login() {
        String email = emailProperty().getValue();
        String senha = senhaProperty().getValue();

        // return lDAO.findPerfilUser(email, senha);

        return "diretor";
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty senhaProperty() {
        return senha;
    }

}
