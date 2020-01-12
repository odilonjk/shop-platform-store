package com.odilonjk.shopplatformstore;

import com.mongodb.MongoClient;
import org.bson.*;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.util.UUID;

public class StoreCodec implements CollectibleCodec<Store> {
    private final Codec<Document> documentCodec;

    public StoreCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public void encode(BsonWriter writer, Store store, EncoderContext encoderContext) {
        Document doc = new Document();
        if (store.getId() != null) {
            doc.put("_id", store.getId());
        }
        doc.put("name", store.getName());
        documentCodec.encode(writer, doc, encoderContext);
    }

    @Override
    public Class<Store> getEncoderClass() {
        return Store.class;
    }

    @Override
    public Store generateIdIfAbsentFromDocument(Store document) {
        if (!documentHasId(document)) {
            document.setId(UUID.randomUUID().toString());
        }
        return document;
    }

    @Override
    public boolean documentHasId(Store document) {
        return document.getId() != null;
    }

    @Override
    public BsonValue getDocumentId(Store document) {
        return new BsonString(document.getId());
    }

    @Override
    public Store decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        Store Store = new Store();
        if (document.getString("_id") != null) {
            Store.setId(document.getString("_id"));
        }
        Store.setName(document.getString("name"));
        return Store;
    }
}
