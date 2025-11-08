package com.google.crypto.tink.internal;

import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.internal.Serialization;
import java.security.GeneralSecurityException;

public abstract class ParametersSerializer<ParametersT extends Parameters, SerializationT extends Serialization> {
    private final Class<ParametersT> parametersClass;
    private final Class<SerializationT> serializationClass;

    public interface ParametersSerializationFunction<ParametersT extends Parameters, SerializationT extends Serialization> {
        SerializationT serializeParameters(ParametersT parameterst) throws GeneralSecurityException;
    }

    public static <ParametersT extends Parameters, SerializationT extends Serialization> ParametersSerializer<ParametersT, SerializationT> create(final ParametersSerializationFunction<ParametersT, SerializationT> parametersSerializationFunction, Class<ParametersT> cls, Class<SerializationT> cls2) {
        return new ParametersSerializer<ParametersT, SerializationT>(cls, cls2) {
            public SerializationT serializeParameters(ParametersT parameterst) throws GeneralSecurityException {
                return parametersSerializationFunction.serializeParameters(parameterst);
            }
        };
    }

    public Class<ParametersT> getParametersClass() {
        return this.parametersClass;
    }

    public Class<SerializationT> getSerializationClass() {
        return this.serializationClass;
    }

    public abstract SerializationT serializeParameters(ParametersT parameterst) throws GeneralSecurityException;

    private ParametersSerializer(Class<ParametersT> cls, Class<SerializationT> cls2) {
        this.parametersClass = cls;
        this.serializationClass = cls2;
    }
}
