package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCtrHmacStreamingKey;
import com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormat;
import com.google.crypto.tink.proto.AesCtrHmacStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesCtrHmacStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesCtrHmacStreamingKeyManager extends KeyTypeManager<AesCtrHmacStreamingKey> {
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private static final int NONCE_PREFIX_IN_BYTES = 7;

    /* renamed from: com.google.crypto.tink.streamingaead.AesCtrHmacStreamingKeyManager$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.crypto.tink.proto.HashType[] r0 = com.google.crypto.tink.proto.HashType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$HashType = r0
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA1     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA256     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA512     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.streamingaead.AesCtrHmacStreamingKeyManager.AnonymousClass3.<clinit>():void");
        }
    }

    public AesCtrHmacStreamingKeyManager() {
        super(AesCtrHmacStreamingKey.class, new PrimitiveFactory<StreamingAead, AesCtrHmacStreamingKey>(StreamingAead.class) {
            public StreamingAead getPrimitive(AesCtrHmacStreamingKey aesCtrHmacStreamingKey) throws GeneralSecurityException {
                return new AesCtrHmacStreaming(aesCtrHmacStreamingKey.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(aesCtrHmacStreamingKey.getParams().getHkdfHashType()), aesCtrHmacStreamingKey.getParams().getDerivedKeySize(), StreamingAeadUtil.toHmacAlgo(aesCtrHmacStreamingKey.getParams().getHmacParams().getHash()), aesCtrHmacStreamingKey.getParams().getHmacParams().getTagSize(), aesCtrHmacStreamingKey.getParams().getCiphertextSegmentSize(), 0);
            }
        });
    }

    public static final KeyTemplate aes128CtrHmacSha2561MBTemplate() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(16, hashType, 16, hashType, 32, 1048576);
    }

    public static final KeyTemplate aes128CtrHmacSha2564KBTemplate() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(16, hashType, 16, hashType, 32, 4096);
    }

    public static final KeyTemplate aes256CtrHmacSha2561MBTemplate() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(32, hashType, 32, hashType, 32, 1048576);
    }

    public static final KeyTemplate aes256CtrHmacSha2564KBTemplate() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(32, hashType, 32, hashType, 32, 4096);
    }

    /* access modifiers changed from: private */
    public static AesCtrHmacStreamingKeyFormat createKeyFormat(int i3, HashType hashType, int i4, HashType hashType2, int i5, int i6) {
        AesCtrHmacStreamingParams.Builder hkdfHashType = AesCtrHmacStreamingParams.newBuilder().setCiphertextSegmentSize(i6).setDerivedKeySize(i4).setHkdfHashType(hashType);
        return (AesCtrHmacStreamingKeyFormat) AesCtrHmacStreamingKeyFormat.newBuilder().setParams((AesCtrHmacStreamingParams) hkdfHashType.setHmacParams((HmacParams) HmacParams.newBuilder().setHash(hashType2).setTagSize(i5).build()).build()).setKeySize(i3).build();
    }

    private static KeyTemplate createKeyTemplate(int i3, HashType hashType, int i4, HashType hashType2, int i5, int i6) {
        return KeyTemplate.create(new AesCtrHmacStreamingKeyManager().getKeyType(), createKeyFormat(i3, hashType, i4, hashType2, i5, i6).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCtrHmacStreamingKeyManager(), z2);
    }

    private static void validateHmacParams(HmacParams hmacParams) throws GeneralSecurityException {
        if (hmacParams.getTagSize() >= 10) {
            int i3 = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HashType[hmacParams.getHash().ordinal()];
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 != 3) {
                        throw new GeneralSecurityException("unknown hash type");
                    } else if (hmacParams.getTagSize() > 64) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                } else if (hmacParams.getTagSize() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
            } else if (hmacParams.getTagSize() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            throw new GeneralSecurityException("tag size too small");
        }
    }

    /* access modifiers changed from: private */
    public static void validateParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesCtrHmacStreamingParams.getDerivedKeySize());
        if (aesCtrHmacStreamingParams.getHkdfHashType() != HashType.SHA1 && aesCtrHmacStreamingParams.getHkdfHashType() != HashType.SHA256 && aesCtrHmacStreamingParams.getHkdfHashType() != HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HKDF hash type: " + aesCtrHmacStreamingParams.getHkdfHashType().getNumber());
        } else if (aesCtrHmacStreamingParams.getHmacParams().getHash() != HashType.UNKNOWN_HASH) {
            validateHmacParams(aesCtrHmacStreamingParams.getHmacParams());
            if (aesCtrHmacStreamingParams.getCiphertextSegmentSize() < aesCtrHmacStreamingParams.getHmacParams().getTagSize() + aesCtrHmacStreamingParams.getDerivedKeySize() + 9) {
                throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + tag_size + NONCE_PREFIX_IN_BYTES + 2)");
            }
        } else {
            throw new GeneralSecurityException("unknown HMAC hash type");
        }
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<AesCtrHmacStreamingKeyFormat, AesCtrHmacStreamingKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCtrHmacStreamingKeyFormat, AesCtrHmacStreamingKey>(AesCtrHmacStreamingKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesCtrHmacStreamingKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                HashType hashType = HashType.SHA256;
                HashType hashType2 = hashType;
                HashType hashType3 = hashType;
                AesCtrHmacStreamingKeyFormat access$100 = AesCtrHmacStreamingKeyManager.createKeyFormat(16, hashType2, 16, hashType3, 32, 4096);
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("AES128_CTR_HMAC_SHA256_4KB", new KeyTypeManager.KeyFactory.KeyFormat(access$100, outputPrefixType));
                hashMap.put("AES128_CTR_HMAC_SHA256_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(16, hashType2, 16, hashType3, 32, 1048576), outputPrefixType));
                hashMap.put("AES256_CTR_HMAC_SHA256_4KB", new KeyTypeManager.KeyFactory.KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(32, hashType2, 32, hashType3, 32, 4096), outputPrefixType));
                hashMap.put("AES256_CTR_HMAC_SHA256_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(32, hashType2, 32, hashType3, 32, 1048576), outputPrefixType));
                return Collections.unmodifiableMap(hashMap);
            }

            public AesCtrHmacStreamingKey createKey(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat) throws GeneralSecurityException {
                return (AesCtrHmacStreamingKey) AesCtrHmacStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesCtrHmacStreamingKeyFormat.getKeySize()))).setParams(aesCtrHmacStreamingKeyFormat.getParams()).setVersion(AesCtrHmacStreamingKeyManager.this.getVersion()).build();
            }

            public AesCtrHmacStreamingKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesCtrHmacStreamingKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat) throws GeneralSecurityException {
                if (aesCtrHmacStreamingKeyFormat.getKeySize() >= 16) {
                    AesCtrHmacStreamingKeyManager.validateParams(aesCtrHmacStreamingKeyFormat.getParams());
                    return;
                }
                throw new GeneralSecurityException("key_size must be at least 16 bytes");
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public AesCtrHmacStreamingKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesCtrHmacStreamingKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(AesCtrHmacStreamingKey aesCtrHmacStreamingKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCtrHmacStreamingKey.getVersion(), getVersion());
        if (aesCtrHmacStreamingKey.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key_value must have at least 16 bytes");
        } else if (aesCtrHmacStreamingKey.getKeyValue().size() >= aesCtrHmacStreamingKey.getParams().getDerivedKeySize()) {
            validateParams(aesCtrHmacStreamingKey.getParams());
        } else {
            throw new GeneralSecurityException("key_value must have at least as many bits as derived keys");
        }
    }
}
