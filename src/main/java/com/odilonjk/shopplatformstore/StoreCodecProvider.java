package com.odilonjk.shopplatformstore;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class StoreCodecProvider implements CodecProvider {

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry codecRegistry) {
        if (clazz == Store.class) {
            return (Codec<T>) new StoreCodec();
        }
        return null;
    }

}
