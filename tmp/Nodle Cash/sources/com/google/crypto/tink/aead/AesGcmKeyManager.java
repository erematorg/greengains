package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmKey;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesGcmKeyManager extends KeyTypeManager<AesGcmKey> {
    public AesGcmKeyManager() {
        super(AesGcmKey.class, new PrimitiveFactory<Aead, AesGcmKey>(Aead.class) {
            public Aead getPrimitive(AesGcmKey aesGcmKey) throws GeneralSecurityException {
                return new AesGcmJce(aesGcmKey.getKeyValue().toByteArray());
            }
        });
    }

    public static final KeyTemplate aes128GcmTemplate() {
        return createKeyTemplate(16, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate aes256GcmTemplate() {
        return createKeyTemplate(32, KeyTemplate.OutputPrefixType.TINK);
    }

    /* access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<AesGcmKeyFormat> createKeyFormat(int i3, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((AesGcmKeyFormat) AesGcmKeyFormat.newBuilder().setKeySize(i3).build(), outputPrefixType);
    }

    private static KeyTemplate createKeyTemplate(int i3, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new AesGcmKeyManager().getKeyType(), ((AesGcmKeyFormat) AesGcmKeyFormat.newBuilder().setKeySize(i3).build()).toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawAes128GcmTemplate() {
        return createKeyTemplate(16, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawAes256GcmTemplate() {
        return createKeyTemplate(32, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmKeyManager(), z2);
        AesGcmProtoSerialization.register();
    }

    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<AesGcmKeyFormat, AesGcmKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesGcmKeyFormat, AesGcmKey>(AesGcmKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesGcmKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("AES128_GCM", AesGcmKeyManager.createKeyFormat(16, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("AES128_GCM_RAW", AesGcmKeyManager.createKeyFormat(16, outputPrefixType2));
                hashMap.put("AES256_GCM", AesGcmKeyManager.createKeyFormat(32, outputPrefixType));
                hashMap.put("AES256_GCM_RAW", AesGcmKeyManager.createKeyFormat(32, outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public AesGcmKey createKey(AesGcmKeyFormat aesGcmKeyFormat) throws GeneralSecurityException {
                return (AesGcmKey) AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmKeyFormat.getKeySize()))).setVersion(AesGcmKeyManager.this.getVersion()).build();
            }

            public AesGcmKey deriveKey(AesGcmKeyFormat aesGcmKeyFormat, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(aesGcmKeyFormat.getVersion(), AesGcmKeyManager.this.getVersion());
                byte[] bArr = new byte[aesGcmKeyFormat.getKeySize()];
                try {
                    KeyTypeManager.KeyFactory.readFully(inputStream, bArr);
                    return (AesGcmKey) AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setVersion(AesGcmKeyManager.this.getVersion()).build();
                } catch (IOException e3) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e3);
                }
            }

            public AesGcmKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesGcmKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(AesGcmKeyFormat aesGcmKeyFormat) throws GeneralSecurityException {
                Validators.validateAesKeySize(aesGcmKeyFormat.getKeySize());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public AesGcmKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesGcmKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(AesGcmKey aesGcmKey) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmKey.getVersion(), getVersion());
        Validators.validateAesKeySize(aesGcmKey.getKeyValue().size());
    }
}
