package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesSivKey;
import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesSiv;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesSivKeyManager extends KeyTypeManager<AesSivKey> {
    private static final int KEY_SIZE_IN_BYTES = 64;

    public AesSivKeyManager() {
        super(AesSivKey.class, new PrimitiveFactory<DeterministicAead, AesSivKey>(DeterministicAead.class) {
            public DeterministicAead getPrimitive(AesSivKey aesSivKey) throws GeneralSecurityException {
                return new AesSiv(aesSivKey.getKeyValue().toByteArray());
            }
        });
    }

    public static final KeyTemplate aes256SivTemplate() {
        return createKeyTemplate(64, KeyTemplate.OutputPrefixType.TINK);
    }

    private static KeyTemplate createKeyTemplate(int i3, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new AesSivKeyManager().getKeyType(), ((AesSivKeyFormat) AesSivKeyFormat.newBuilder().setKeySize(i3).build()).toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawAes256SivTemplate() {
        return createKeyTemplate(64, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesSivKeyManager(), z2);
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<AesSivKeyFormat, AesSivKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesSivKeyFormat, AesSivKey>(AesSivKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesSivKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("AES256_SIV", new KeyTypeManager.KeyFactory.KeyFormat((AesSivKeyFormat) AesSivKeyFormat.newBuilder().setKeySize(64).build(), KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_SIV_RAW", new KeyTypeManager.KeyFactory.KeyFormat((AesSivKeyFormat) AesSivKeyFormat.newBuilder().setKeySize(64).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }

            public AesSivKey createKey(AesSivKeyFormat aesSivKeyFormat) throws GeneralSecurityException {
                return (AesSivKey) AesSivKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesSivKeyFormat.getKeySize()))).setVersion(AesSivKeyManager.this.getVersion()).build();
            }

            public AesSivKey deriveKey(AesSivKeyFormat aesSivKeyFormat, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(aesSivKeyFormat.getVersion(), AesSivKeyManager.this.getVersion());
                byte[] bArr = new byte[64];
                try {
                    KeyTypeManager.KeyFactory.readFully(inputStream, bArr);
                    return (AesSivKey) AesSivKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setVersion(AesSivKeyManager.this.getVersion()).build();
                } catch (IOException e3) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e3);
                }
            }

            public AesSivKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesSivKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(AesSivKeyFormat aesSivKeyFormat) throws GeneralSecurityException {
                if (aesSivKeyFormat.getKeySize() != 64) {
                    throw new InvalidAlgorithmParameterException("invalid key size: " + aesSivKeyFormat.getKeySize() + ". Valid keys must have 64 bytes.");
                }
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public AesSivKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesSivKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(AesSivKey aesSivKey) throws GeneralSecurityException {
        Validators.validateVersion(aesSivKey.getVersion(), getVersion());
        if (aesSivKey.getKeyValue().size() != 64) {
            throw new InvalidKeyException("invalid key size: " + aesSivKey.getKeyValue().size() + ". Valid keys must have 64 bytes.");
        }
    }
}
