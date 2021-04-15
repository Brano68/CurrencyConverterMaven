package sk.kosickaakademia.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.Date;


public class DatabaseMongo {

    public boolean insertIntoMongo(String key, double value, double result){
        //vytvorenie mongo klinta
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //vytvorenie connection
        MongoDatabase database = mongo.getDatabase("Currency");
        //vytvorenie kolekcie
        //database.createCollection("allRecords");
        Date date = new Date();
        System.out.println(date);
        Document document = new Document();
        document.append("Date", date.toString());
        document.append("Currency", key);
        document.append("HowMuchEuro", value);
        document.append("Result", result);
        //vkladanie dokumentu do kolekcie
        database.getCollection("allRecords").insertOne(document);

        System.out.println("Document inserted successfully");


        return true;
    }



}
