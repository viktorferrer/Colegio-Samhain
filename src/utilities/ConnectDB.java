package utilities;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectDB {
    public static MongoDatabase database;
    
    public void connect() {
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://admin:Projeto123@cluster0.oq0zp.mongodb.net/ColegioSamhain?retryWrites=true&w=majority");

        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("ColegioSamhain");
    }
}
