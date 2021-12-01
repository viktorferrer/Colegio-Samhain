package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.bson.types.ObjectId;

import model.bean.Professor;
import utilities.ConnectDB;

public class ProfessorDaoImpl implements ProfessorDAO {

    MongoCollection<Document> prof;

    private void getCollection() {
        prof = ConnectDB.database.getCollection("professores");
    }

    Document novoDocumento(Professor p) {
        Document doc = new Document();
        doc.append("nome", p.getNome());
        doc.append("sala", p.getSala());
        doc.append("turno", p.getTurno());
        doc.append("espec", p.getEspec());
        doc.append("_id", p.getId());
        return doc;
    }

    Professor novoProf(Document doc) {
        Professor p = new Professor(doc.getString("nome"));
        p.setSala(doc.getString("periodo"));
        p.setTurno(doc.getString("turno"));
        p.setEspec(doc.getString("espec"));
        p.setId(doc.getObjectId("_id"));
        return p;
    }

    @Override
    public void adicionar(Professor professor) {
        getCollection();
        Document doc = novoDocumento(professor);
        prof.insertOne(doc);
        System.out.println("O professor " + professor.getNome() + " foi adicionado!");
    }

    @Override
    public void remover(String id) {
        getCollection();
        prof.deleteOne(new BasicDBObject("_id", new ObjectId(id)));
    }

    @Override
    public void atualizar(String id, Professor professor) {
        Document doc = novoDocumento(professor);

        BasicDBObject update = new BasicDBObject("$set", doc);
        getCollection();
        prof.updateOne(new BasicDBObject("_id", new ObjectId(id)), update);
    }

    @Override
    public List<Professor> mostrarProfs() {
        List<Professor> listaProf = new ArrayList<Professor>();
        getCollection();
        MongoCursor<Document> cursor = prof.find().iterator();
        try {
            while (cursor.hasNext()) {
                listaProf.add(novoProf(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return listaProf;
    }

    @Override
    public List<Professor> pesquisarPorCampo(String campo, String valor) {

        Pattern padrao = Pattern.compile(valor, Pattern.CASE_INSENSITIVE);
        Document parametros = new Document(campo, padrao);

        List<Professor> pesquisa = new ArrayList<Professor>();
        getCollection();
        MongoCursor<Document> cursor = prof.find(parametros).iterator();
        try {
            while (cursor.hasNext()) {
                pesquisa.add(novoProf(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return pesquisa;
    }

}