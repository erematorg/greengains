package com.google.crypto.tink.aead;

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
    public final /* synthetic */ int f7016a;

    public /* synthetic */ a(int i3) {
        this.f7016a = i3;
    }

    public Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
        ProtoKeySerialization protoKeySerialization = (ProtoKeySerialization) serialization;
        switch (this.f7016a) {
            case 3:
                return AesEaxProtoSerialization.parseKey(protoKeySerialization, secretKeyAccess);
            case 7:
                return AesGcmProtoSerialization.parseKey(protoKeySerialization, secretKeyAccess);
            case 11:
                return AesGcmSivProtoSerialization.parseKey(protoKeySerialization, secretKeyAccess);
            case 15:
                return ChaCha20Poly1305ProtoSerialization.parseKey(protoKeySerialization, secretKeyAccess);
            default:
                return XChaCha20Poly1305ProtoSerialization.parseKey(protoKeySerialization, secretKeyAccess);
        }
    }

    public Parameters parseParameters(Serialization serialization) {
        ProtoParametersSerialization protoParametersSerialization = (ProtoParametersSerialization) serialization;
        switch (this.f7016a) {
            case 1:
                return AesEaxProtoSerialization.parseParameters(protoParametersSerialization);
            case 5:
                return AesGcmProtoSerialization.parseParameters(protoParametersSerialization);
            case 9:
                return AesGcmSivProtoSerialization.parseParameters(protoParametersSerialization);
            case 13:
                return ChaCha20Poly1305ProtoSerialization.parseParameters(protoParametersSerialization);
            default:
                return XChaCha20Poly1305ProtoSerialization.parseParameters(protoParametersSerialization);
        }
    }

    public Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
        switch (this.f7016a) {
            case 2:
                return AesEaxProtoSerialization.serializeKey((AesEaxKey) key, secretKeyAccess);
            case 6:
                return AesGcmProtoSerialization.serializeKey((AesGcmKey) key, secretKeyAccess);
            case 10:
                return AesGcmSivProtoSerialization.serializeKey((AesGcmSivKey) key, secretKeyAccess);
            case 14:
                return ChaCha20Poly1305ProtoSerialization.serializeKey((ChaCha20Poly1305Key) key, secretKeyAccess);
            default:
                return XChaCha20Poly1305ProtoSerialization.serializeKey((XChaCha20Poly1305Key) key, secretKeyAccess);
        }
    }

    public Serialization serializeParameters(Parameters parameters) {
        switch (this.f7016a) {
            case 0:
                return AesEaxProtoSerialization.serializeParameters((AesEaxParameters) parameters);
            case 4:
                return AesGcmProtoSerialization.serializeParameters((AesGcmParameters) parameters);
            case 8:
                return AesGcmSivProtoSerialization.serializeParameters((AesGcmSivParameters) parameters);
            case 12:
                return ChaCha20Poly1305ProtoSerialization.serializeParameters((ChaCha20Poly1305Parameters) parameters);
            default:
                return XChaCha20Poly1305ProtoSerialization.serializeParameters((XChaCha20Poly1305Parameters) parameters);
        }
    }
}
