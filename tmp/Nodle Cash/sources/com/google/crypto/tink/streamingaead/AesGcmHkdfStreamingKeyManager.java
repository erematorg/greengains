package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKey;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesGcmHkdfStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesGcmHkdfStreamingKeyManager extends KeyTypeManager<AesGcmHkdfStreamingKey> {
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int TAG_SIZE_IN_BYTES = 16;

    public AesGcmHkdfStreamingKeyManager() {
        super(AesGcmHkdfStreamingKey.class, new PrimitiveFactory<StreamingAead, AesGcmHkdfStreamingKey>(StreamingAead.class) {
            public StreamingAead getPrimitive(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) throws GeneralSecurityException {
                return new AesGcmHkdfStreaming(aesGcmHkdfStreamingKey.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(aesGcmHkdfStreamingKey.getParams().getHkdfHashType()), aesGcmHkdfStreamingKey.getParams().getDerivedKeySize(), aesGcmHkdfStreamingKey.getParams().getCiphertextSegmentSize(), 0);
            }
        });
    }

    public static final KeyTemplate aes128GcmHkdf1MBTemplate() {
        return createKeyTemplate(16, HashType.SHA256, 16, 1048576);
    }

    public static final KeyTemplate aes128GcmHkdf4KBTemplate() {
        return createKeyTemplate(16, HashType.SHA256, 16, 4096);
    }

    public static final KeyTemplate aes256GcmHkdf1MBTemplate() {
        return createKeyTemplate(32, HashType.SHA256, 32, 1048576);
    }

    public static final KeyTemplate aes256GcmHkdf4KBTemplate() {
        return createKeyTemplate(32, HashType.SHA256, 32, 4096);
    }

    /* access modifiers changed from: private */
    public static AesGcmHkdfStreamingKeyFormat createKeyFormat(int i3, HashType hashType, int i4, int i5) {
        return (AesGcmHkdfStreamingKeyFormat) AesGcmHkdfStreamingKeyFormat.newBuilder().setKeySize(i3).setParams((AesGcmHkdfStreamingParams) AesGcmHkdfStreamingParams.newBuilder().setCiphertextSegmentSize(i5).setDerivedKeySize(i4).setHkdfHashType(hashType).build()).build();
    }

    private static KeyTemplate createKeyTemplate(int i3, HashType hashType, int i4, int i5) {
        return KeyTemplate.create(new AesGcmHkdfStreamingKeyManager().getKeyType(), createKeyFormat(i3, hashType, i4, i5).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmHkdfStreamingKeyManager(), z2);
    }

    /* access modifiers changed from: private */
    public static void validateParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesGcmHkdfStreamingParams.getDerivedKeySize());
        if (aesGcmHkdfStreamingParams.getHkdfHashType() == HashType.UNKNOWN_HASH) {
            throw new GeneralSecurityException("unknown HKDF hash type");
        } else if (aesGcmHkdfStreamingParams.getCiphertextSegmentSize() < aesGcmHkdfStreamingParams.getDerivedKeySize() + 25) {
            throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + NONCE_PREFIX_IN_BYTES + TAG_SIZE_IN_BYTES + 2)");
        }
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<AesGcmHkdfStreamingKeyFormat, AesGcmHkdfStreamingKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesGcmHkdfStreamingKeyFormat, AesGcmHkdfStreamingKey>(AesGcmHkdfStreamingKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesGcmHkdfStreamingKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                HashType hashType = HashType.SHA256;
                AesGcmHkdfStreamingKeyFormat access$100 = AesGcmHkdfStreamingKeyManager.createKeyFormat(16, hashType, 16, 4096);
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("AES128_GCM_HKDF_4KB", new KeyTypeManager.KeyFactory.KeyFormat(access$100, outputPrefixType));
                hashMap.put("AES128_GCM_HKDF_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(16, hashType, 16, 1048576), outputPrefixType));
                hashMap.put("AES256_GCM_HKDF_4KB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(32, hashType, 32, 4096), outputPrefixType));
                hashMap.put("AES256_GCM_HKDF_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(32, hashType, 32, 1048576), outputPrefixType));
                return Collections.unmodifiableMap(hashMap);
            }

            public AesGcmHkdfStreamingKey createKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) throws GeneralSecurityException {
                return (AesGcmHkdfStreamingKey) AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmHkdfStreamingKeyFormat.getKeySize()))).setParams(aesGcmHkdfStreamingKeyFormat.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
            }

            public AesGcmHkdfStreamingKey deriveKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(aesGcmHkdfStreamingKeyFormat.getVersion(), AesGcmHkdfStreamingKeyManager.this.getVersion());
                byte[] bArr = new byte[aesGcmHkdfStreamingKeyFormat.getKeySize()];
                try {
                    KeyTypeManager.KeyFactory.readFully(inputStream, bArr);
                    return (AesGcmHkdfStreamingKey) AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setParams(aesGcmHkdfStreamingKeyFormat.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
                } catch (IOException e3) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e3);
                }
            }

            public AesGcmHkdfStreamingKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesGcmHkdfStreamingKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) throws GeneralSecurityException {
                if (aesGcmHkdfStreamingKeyFormat.getKeySize() >= 16) {
                    AesGcmHkdfStreamingKeyManager.validateParams(aesGcmHkdfStreamingKeyFormat.getParams());
                    return;
                }
                throw new GeneralSecurityException("key_size must be at least 16 bytes");
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public AesGcmHkdfStreamingKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesGcmHkdfStreamingKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmHkdfStreamingKey.getVersion(), getVersion());
        validateParams(aesGcmHkdfStreamingKey.getParams());
    }
}
