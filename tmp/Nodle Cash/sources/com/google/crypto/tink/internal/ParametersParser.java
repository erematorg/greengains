package com.google.crypto.tink.internal;

import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;

public abstract class ParametersParser<SerializationT extends Serialization> {
    private final Bytes objectIdentifier;
    private final Class<SerializationT> serializationClass;

    public interface ParametersParsingFunction<SerializationT extends Serialization> {
        Parameters parseParameters(SerializationT serializationt) throws GeneralSecurityException;
    }

    public static <SerializationT extends Serialization> ParametersParser<SerializationT> create(final ParametersParsingFunction<SerializationT> parametersParsingFunction, Bytes bytes, Class<SerializationT> cls) {
        return new ParametersParser<SerializationT>(bytes, cls) {
            public Parameters parseParameters(SerializationT serializationt) throws GeneralSecurityException {
                return parametersParsingFunction.parseParameters(serializationt);
            }
        };
    }

    public final Bytes getObjectIdentifier() {
        return this.objectIdentifier;
    }

    public final Class<SerializationT> getSerializationClass() {
        return this.serializationClass;
    }

    public abstract Parameters parseParameters(SerializationT serializationt) throws GeneralSecurityException;

    private ParametersParser(Bytes bytes, Class<SerializationT> cls) {
        this.objectIdentifier = bytes;
        this.serializationClass = cls;
    }
}
