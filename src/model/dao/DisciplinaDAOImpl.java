package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.bson.types.ObjectId;

import model.bean.Disciplina;
import utilities.ConnectDB;

public class DisciplinaDAOImpl implements DisciplinaDAO {

    MongoCollection<Document> disc;

    private void getCollection() {
        disc = ConnectDB.database.getCollection("disciplinas");
    }

    Document novoDocumento(Disciplina d) {
        Document doc = new Document();
        doc.append("descricao", d.getDescricao());
        doc.append("periodo", d.getPeriodo());
        doc.append("modalidade", d.getModalidade());
        doc.append("profId", d.getProfId());
        doc.append("_id", d.getId());

        return doc;
    }

    Disciplina novaDisciplina(Document doc) {
        Disciplina d = new Disciplina(doc.getString("descricao"));
        d.setPeriodo(doc.getString("periodo"));
        d.setModalidade(doc.getString("modalidade"));
        d.setProfId(doc.getObjectId("profId"));
        d.setId(doc.getObjectId("_id"));
        return d;
    }

    @Override
    public void adicionar(Disciplina disciplina) {
        getCollection();
        Document doc = novoDocumento(disciplina);
        disc.insertOne(doc);
        System.out.println("A disciplina " + disciplina.getDescricao() + " foi adicionada!");
    }

    @Override
    public List<Disciplina> mostrarDisciplinas() {
        List<Disciplina> mostrarDisciplinas = new ArrayList<Disciplina>();
        getCollection();
        MongoCursor<Document> cursor = disc.find().iterator();
        try {
            while (cursor.hasNext()) {
                mostrarDisciplinas.add(novaDisciplina(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return mostrarDisciplinas;
    }

    @Override
    public void atualizar(String id, Disciplina disciplina) {
        Document doc = novoDocumento(disciplina);

        BasicDBObject update = new BasicDBObject("$set", doc);
        getCollection();
        disc.updateOne(new BasicDBObject("_id", new ObjectId(id)), update);
    }

    @Override
    public void remover(String id) {
        getCollection();
        disc.deleteOne(new BasicDBObject("_id", new ObjectId(id)));
    }

    @Override
    public List<Disciplina> pesquisarPorCampo(String campo, String valor) {

        Pattern padrao = Pattern.compile(valor, Pattern.CASE_INSENSITIVE);
        Document parametros = new Document(campo, padrao);

        List<Disciplina> porPeriodo = new ArrayList<Disciplina>();
        getCollection();
        MongoCursor<Document> cursor = disc.find(parametros).iterator();
        try {
            while (cursor.hasNext()) {
                porPeriodo.add(novaDisciplina(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return porPeriodo;
    }
}
