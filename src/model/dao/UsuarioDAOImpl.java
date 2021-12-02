package model.dao;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import utilities.ConnectDB;

public class UsuarioDAOImpl implements UsuarioDAO {

    MongoCollection<Document> usuarios;

    private void getCollection() {
        usuarios = ConnectDB.database.getCollection("usuarios");
    }

    @Override
    public int login(String user, String senha) {
        getCollection();
        Document parametros = new Document("user", user);
        Document doc = usuarios.find(parametros).first();
        if (doc != null) {
            if (senha.equals(doc.getString("senha"))) {
                return doc.getInteger("permissao");
            }
        }
        return 0;
    }

}
