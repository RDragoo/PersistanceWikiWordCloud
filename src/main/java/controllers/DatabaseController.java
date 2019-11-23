package controllers;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import models.Page;

public class DatabaseController {

	private MongoClient mongoClient;
	private DB db;
	private DBCollection col;

	public DatabaseController() throws Exception {
		mongoClient = new MongoClient();
		db = mongoClient.getDB("wikipediaData");
		col = db.getCollection("pages");
	}

	public void savePage(Page page) {
		DBObject doc = createDBObject(page);

		col.insert(doc);

	}

	private static DBObject createDBObject(Page page) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("title", page.getTitle());
		docBuilder.append("text", page.getText());

		return docBuilder.get();
	}

}
