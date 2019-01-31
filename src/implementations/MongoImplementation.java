/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import interfaces.iMongo;
import java.time.LocalDate;
import java.util.ResourceBundle;
import org.bson.Document;

/**
 *
 * @author Jon Gonzalez
 */
public class MongoImplementation implements iMongo{
    
    private static MongoClient client;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;
    private ResourceBundle properties = ResourceBundle
                    .getBundle("properties/Properties");

    @Override
    public void loginUser(String login) {
        client = MongoClients.create(properties.getString("mongo_url"));
        database = client.getDatabase(properties.getString("mongo_database"));
        collection = database.getCollection(properties.getString("mongo_collection"));
        if(collection.find(Filters.eq("login", login)).first()!=null){
            Document doc = new Document("$set", new Document("login_date", LocalDate.now()));
            collection.updateOne(Filters.eq("login", login), doc);
        }else{
            Document doc = new Document();
            doc.put("login", login);
            doc.put("login_date", LocalDate.now());
            collection.insertOne(doc);
        }
        client.close();
    }

    @Override
    public void deleteUser(String login) {
        client = MongoClients.create(properties.getString("mongo_url"));
        database = client.getDatabase(properties.getString("mongo_database"));
        collection = database.getCollection(properties.getString("mongo_collection"));
        collection.deleteOne(Filters.eq("login", login));
        client.close();
    }
    
}
