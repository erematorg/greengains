package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

public abstract class KeyParser<SerializationT extends Serialization> {
    private final Bytes objectIdentifier;
    private final Class<SerializationT> serializationClass;

    public interface KeyParsingFunction<SerializationT extends Serialization> {
        Key parseKey(SerializationT serializationt, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException;
    }

    public static <SerializationT extends Serialization> KeyParser<SerializationT> create(final KeyParsingFunction<SerializationT> keyParsingFunction, Bytes bytes, Class<SerializationT> cls) {
        return new KeyParser<SerializationT>(bytes, cls) {
            public Key parseKey(SerializationT serializationt, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
                return keyParsingFunction.parseKey(serializationt, secretKeyAccess);
            }
        };
    }

    public final Bytes getObjectIdentifier() {
        return this.objectIdentifier;
    }

    public final Class<SerializationT> getSerializationClass() {
        return this.serializationClass;
    }

    public abstract Key parseKey(SerializationT serializationt, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException;

    private KeyParser(Bytes bytes, Class<SerializationT> cls) {
        this.objectIdentifier = bytes;
        this.serializationClass = cls;
    }
}
