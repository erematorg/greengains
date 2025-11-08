package com.google.crypto.tink.prf;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;

public final /* synthetic */ class a implements ParametersSerializer.ParametersSerializationFunction, ParametersParser.ParametersParsingFunction, KeySerializer.KeySerializationFunction, KeyParser.KeyParsingFunction {
    public Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
        return HkdfPrfProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
    }

    public Parameters parseParameters(Serialization serialization) {
        return HkdfPrfProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
    }

    public Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
        return HkdfPrfProtoSerialization.serializeKey((HkdfPrfKey) key, secretKeyAccess);
    }

    public Serialization serializeParameters(Parameters parameters) {
        return HkdfPrfProtoSerialization.serializeParameters((HkdfPrfParameters) parameters);
    }
}
