package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.dao.UsuarioDAO;
import model.dao.UsuarioDAOImpl;

public class LoginControl {

    StringProperty user = new SimpleStringProperty("");
    StringProperty senha = new SimpleStringProperty("");

    UsuarioDAO uDao = new UsuarioDAOImpl();

    public int login() {
        String user = userProperty().getValue();
        String senha = senhaProperty().getValue();
        return uDao.login(user, senha);
    }

    public StringProperty userProperty() {
        return user;
    }

    public StringProperty senhaProperty() {
        return senha;
    }

}
