package com.google.crypto.tink.signature;

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

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7023a;

    public /* synthetic */ a(int i3) {
        this.f7023a = i3;
    }

    public Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
        ProtoKeySerialization protoKeySerialization = (ProtoKeySerialization) serialization;
        switch (this.f7023a) {
            case 3:
                return EcdsaProtoSerialization.parsePublicKey(protoKeySerialization, secretKeyAccess);
            default:
                return EcdsaProtoSerialization.parsePrivateKey(protoKeySerialization, secretKeyAccess);
        }
    }

    public Parameters parseParameters(Serialization serialization) {
        return EcdsaProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
    }

    public Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
        switch (this.f7023a) {
            case 2:
                return EcdsaProtoSerialization.serializePublicKey((EcdsaPublicKey) key, secretKeyAccess);
            default:
                return EcdsaProtoSerialization.serializePrivateKey((EcdsaPrivateKey) key, secretKeyAccess);
        }
    }

    public Serialization serializeParameters(Parameters parameters) {
        return EcdsaProtoSerialization.serializeParameters((EcdsaParameters) parameters);
    }
}
