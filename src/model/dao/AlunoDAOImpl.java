package model.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.bson.types.ObjectId;

import model.bean.Aluno;
import utilities.ConnectDB;

public class AlunoDAOImpl implements AlunoDAO {

    // "select"
    MongoCollection<Document> alunos;

    private void getCollection() {
        alunos = ConnectDB.database.getCollection("alunos");
    }

    Document novoDocumento(Aluno aluno) {
        Document doc = new Document();
        doc.append("nome", aluno.getNome());
        doc.append("turma", aluno.getTurma());
        doc.append("periodo", aluno.getPeriodo());
        doc.append("nota", aluno.getNota());
        doc.append("matricula", aluno.getMatricula());
        doc.append("_id", aluno.getId());

        return doc;
    }

    Aluno novoAluno(Document doc) {
        Aluno a = new Aluno(doc.getString("nome"));
        a.setTurma(doc.getString("turma"));
        a.setPeriodo(doc.getString("periodo"));
        a.setNota(doc.getDouble("nota"));
        a.setMatricula(doc.getString("matricula"));
        a.setId(doc.getObjectId("_id"));
        return a;
    }

    @Override
    public void adicionar(Aluno aluno) {
        getCollection();
        Document doc = novoDocumento(aluno);
        alunos.insertOne(doc);
        System.out.println("O aluno " + aluno.getNome() + " foi adicionado!");
    }

    @Override
    public List<Aluno> mostrarAlunos() {
        List<Aluno> mostrarAlunos = new ArrayList<Aluno>();
        getCollection();
        MongoCursor<Document> cursor = alunos.find().iterator();
        try {
            while (cursor.hasNext()) {
                mostrarAlunos.add(novoAluno(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return mostrarAlunos;
    }

    @Override
    public Aluno pesquisarPorMatricula(String matricula) {
        Document parametro = new Document();
        getCollection();
        try {
            parametro = alunos.find(new Document("matricula", matricula)).first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (parametro == null) {
            return null;
        }

        return novoAluno(parametro);
    }

    @Override
    public void atualizar(String id, Aluno aluno) {
        Document doc = novoDocumento(aluno);

        BasicDBObject update = new BasicDBObject("$set", doc);
        getCollection();
        alunos.updateOne(new BasicDBObject("_id", new ObjectId(id)), update);
    }

    @Override
    public void remover(String id) {
        getCollection();
        alunos.deleteOne(new BasicDBObject("_id", new ObjectId(id)));
    }
}
