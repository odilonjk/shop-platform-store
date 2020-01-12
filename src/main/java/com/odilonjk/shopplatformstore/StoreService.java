package com.odilonjk.shopplatformstore;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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

    public void createStore(Store store) {
        Document document = new Document().append("name", store.getName());
        getCollection().insertOne(document);
    }

    public List<Store> findByName(String name) {
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);
        MongoCursor<Store> cursor = getCollection().find(query).iterator();
        List<Store> stores = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                stores.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return stores;
    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase(SHOP_PLATFORM_STORE_DB).getCollection(STORE_COLLECTION);
    }

}
