package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KmsClients;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKey;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

public class KmsEnvelopeAeadKeyManager extends KeyTypeManager<KmsEnvelopeAeadKey> {
    public KmsEnvelopeAeadKeyManager() {
        super(KmsEnvelopeAeadKey.class, new PrimitiveFactory<Aead, KmsEnvelopeAeadKey>(Aead.class) {
            public Aead getPrimitive(KmsEnvelopeAeadKey kmsEnvelopeAeadKey) throws GeneralSecurityException {
                String kekUri = kmsEnvelopeAeadKey.getParams().getKekUri();
                return new KmsEnvelopeAead(kmsEnvelopeAeadKey.getParams().getDekTemplate(), KmsClients.get(kekUri).getAead(kekUri));
            }
        });
    }

    public static KmsEnvelopeAeadKeyFormat createKeyFormat(String str, KeyTemplate keyTemplate) {
        return (KmsEnvelopeAeadKeyFormat) KmsEnvelopeAeadKeyFormat.newBuilder().setDekTemplate((com.google.crypto.tink.proto.KeyTemplate) com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(keyTemplate.getTypeUrl()).setValue(ByteString.copyFrom(keyTemplate.getValue())).build()).setKekUri(str).build();
    }

    public static KeyTemplate createKeyTemplate(String str, KeyTemplate keyTemplate) {
        return KeyTemplate.create(new KmsEnvelopeAeadKeyManager().getKeyType(), createKeyFormat(str, keyTemplate).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new KmsEnvelopeAeadKeyManager(), z2);
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<KmsEnvelopeAeadKeyFormat, KmsEnvelopeAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<KmsEnvelopeAeadKeyFormat, KmsEnvelopeAeadKey>(KmsEnvelopeAeadKeyFormat.class) {
            public KmsEnvelopeAeadKey createKey(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) throws GeneralSecurityException {
                return (KmsEnvelopeAeadKey) KmsEnvelopeAeadKey.newBuilder().setParams(kmsEnvelopeAeadKeyFormat).setVersion(KmsEnvelopeAeadKeyManager.this.getVersion()).build();
            }

            public KmsEnvelopeAeadKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return KmsEnvelopeAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) throws GeneralSecurityException {
                if (kmsEnvelopeAeadKeyFormat.getKekUri().isEmpty() || !kmsEnvelopeAeadKeyFormat.hasDekTemplate()) {
                    throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
                }
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.REMOTE;
    }

    public KmsEnvelopeAeadKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return KmsEnvelopeAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(KmsEnvelopeAeadKey kmsEnvelopeAeadKey) throws GeneralSecurityException {
        Validators.validateVersion(kmsEnvelopeAeadKey.getVersion(), getVersion());
    }
}
