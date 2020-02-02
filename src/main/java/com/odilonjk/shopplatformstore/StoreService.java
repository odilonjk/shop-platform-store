package com.odilonjk.shopplatformstore;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StoreService {

    private static final String STORE_COLLECTION = "store";
    private static final String SHOP_PLATFORM_STORE_DB = "shop-platform-store";

    @Inject
    MongoClient mongoClient;

    void createStore(Store store) {
        Document document = new Document().append("name", store.getName());
        MongoDatabase db = mongoClient.getDatabase(SHOP_PLATFORM_STORE_DB);
        MongoCollection<Document> stores = db.getCollection(STORE_COLLECTION);
        stores.insertOne(document);
    }

    List<Store> findByName(String name) {
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);
        MongoDatabase db = mongoClient.getDatabase(SHOP_PLATFORM_STORE_DB);
        MongoCollection<Store> stores = db.getCollection(STORE_COLLECTION, Store.class);
        FindIterable<Store> iterable = stores.find(query);
        MongoCursor<Store> cursor = iterable.iterator();
        List<Store> storesFound = new ArrayList<>();
        while (cursor.hasNext()) {
            storesFound.add(cursor.next());
        }
        return  storesFound;
    }

}
