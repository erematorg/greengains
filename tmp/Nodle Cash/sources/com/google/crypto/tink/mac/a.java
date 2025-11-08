package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.mac.internal.ChunkedAesCmacImpl;
import com.google.crypto.tink.mac.internal.ChunkedHmacImpl;

public final /* synthetic */ class a implements ParametersSerializer.ParametersSerializationFunction, ParametersParser.ParametersParsingFunction, KeySerializer.KeySerializationFunction, KeyParser.KeyParsingFunction, PrimitiveConstructor.PrimitiveConstructionFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7020a;

    public /* synthetic */ a(int i3) {
        this.f7020a = i3;
    }

    public Object constructPrimitive(Key key) {
        switch (this.f7020a) {
            case 8:
                return new ChunkedAesCmacImpl((AesCmacKey) key);
            default:
                return new ChunkedHmacImpl((HmacKey) key);
        }
    }

    public Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
        ProtoKeySerialization protoKeySerialization = (ProtoKeySerialization) serialization;
        switch (this.f7020a) {
            case 3:
                return AesCmacProtoSerialization.parseKey(protoKeySerialization, secretKeyAccess);
            default:
                return HmacProtoSerialization.parseKey(protoKeySerialization, secretKeyAccess);
        }
    }

    public Parameters parseParameters(Serialization serialization) {
        ProtoParametersSerialization protoParametersSerialization = (ProtoParametersSerialization) serialization;
        switch (this.f7020a) {
            case 1:
                return AesCmacProtoSerialization.parseParameters(protoParametersSerialization);
            default:
                return HmacProtoSerialization.parseParameters(protoParametersSerialization);
        }
    }

    public Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
        switch (this.f7020a) {
            case 2:
                return AesCmacProtoSerialization.serializeKey((AesCmacKey) key, secretKeyAccess);
            default:
                return HmacProtoSerialization.serializeKey((HmacKey) key, secretKeyAccess);
        }
    }

    public Serialization serializeParameters(Parameters parameters) {
        switch (this.f7020a) {
            case 0:
                return AesCmacProtoSerialization.serializeParameters((AesCmacParameters) parameters);
            default:
                return HmacProtoSerialization.serializeParameters((HmacParameters) parameters);
        }
    }
}
