package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.Serialization;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

public abstract class KeySerializer<KeyT extends Key, SerializationT extends Serialization> {
    private final Class<KeyT> keyClass;
    private final Class<SerializationT> serializationClass;

    public interface KeySerializationFunction<KeyT extends Key, SerializationT extends Serialization> {
        SerializationT serializeKey(KeyT keyt, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException;
    }

    public static <KeyT extends Key, SerializationT extends Serialization> KeySerializer<KeyT, SerializationT> create(final KeySerializationFunction<KeyT, SerializationT> keySerializationFunction, Class<KeyT> cls, Class<SerializationT> cls2) {
        return new KeySerializer<KeyT, SerializationT>(cls, cls2) {
            public SerializationT serializeKey(KeyT keyt, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
                return keySerializationFunction.serializeKey(keyt, secretKeyAccess);
            }
        };
    }

    public Class<KeyT> getKeyClass() {
        return this.keyClass;
    }

    public Class<SerializationT> getSerializationClass() {
        return this.serializationClass;
    }

    public abstract SerializationT serializeKey(KeyT keyt, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException;

    private KeySerializer(Class<KeyT> cls, Class<SerializationT> cls2) {
        this.keyClass = cls;
        this.serializationClass = cls2;
    }
}
