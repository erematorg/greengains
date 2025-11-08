package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCmacKey;
import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfAesCmac;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesCmacKeyManager extends KeyTypeManager<AesCmacKey> {
    private static final PrimitiveConstructor<AesCmacKey, ChunkedMac> CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(new a(8), AesCmacKey.class, ChunkedMac.class);
    private static final int KEY_SIZE_IN_BYTES = 32;
    private static final int MAX_TAG_SIZE_IN_BYTES = 16;
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private static final int VERSION = 0;

    public AesCmacKeyManager() {
        super(AesCmacKey.class, new PrimitiveFactory<Mac, AesCmacKey>(Mac.class) {
            public Mac getPrimitive(AesCmacKey aesCmacKey) throws GeneralSecurityException {
                return new PrfMac(new PrfAesCmac(aesCmacKey.getKeyValue().toByteArray()), aesCmacKey.getParams().getTagSize());
            }
        });
    }

    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacKeyManager().getKeyType(), ((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build()).toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacKeyManager().getKeyType(), ((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build()).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCmacKeyManager(), z2);
        AesCmacProtoSerialization.register();
        MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR);
    }

    /* access modifiers changed from: private */
    public static void validateParams(AesCmacParams aesCmacParams) throws GeneralSecurityException {
        if (aesCmacParams.getTagSize() < 10) {
            throw new GeneralSecurityException("tag size too short");
        } else if (aesCmacParams.getTagSize() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* access modifiers changed from: private */
    public static void validateSize(int i3) throws GeneralSecurityException {
        if (i3 != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<AesCmacKeyFormat, AesCmacKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCmacKeyFormat, AesCmacKey>(AesCmacKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesCmacKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("AES_CMAC", new KeyTypeManager.KeyFactory.KeyFormat((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build(), outputPrefixType));
                hashMap.put("AES256_CMAC", new KeyTypeManager.KeyFactory.KeyFormat((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build(), outputPrefixType));
                hashMap.put("AES256_CMAC_RAW", new KeyTypeManager.KeyFactory.KeyFormat((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }

            public AesCmacKey createKey(AesCmacKeyFormat aesCmacKeyFormat) throws GeneralSecurityException {
                return (AesCmacKey) AesCmacKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCmacKeyFormat.getKeySize()))).setParams(aesCmacKeyFormat.getParams()).build();
            }

            public AesCmacKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesCmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(AesCmacKeyFormat aesCmacKeyFormat) throws GeneralSecurityException {
                AesCmacKeyManager.validateParams(aesCmacKeyFormat.getParams());
                AesCmacKeyManager.validateSize(aesCmacKeyFormat.getKeySize());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public AesCmacKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesCmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(AesCmacKey aesCmacKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCmacKey.getVersion(), getVersion());
        validateSize(aesCmacKey.getKeyValue().size());
        validateParams(aesCmacKey.getParams());
    }
}
