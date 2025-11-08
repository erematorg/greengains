package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCmacPrfKey;
import com.google.crypto.tink.proto.AesCmacPrfKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfAesCmac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesCmacPrfKeyManager extends KeyTypeManager<AesCmacPrfKey> {
    private static final int KEY_SIZE_IN_BYTES = 32;
    private static final int VERSION = 0;

    public AesCmacPrfKeyManager() {
        super(AesCmacPrfKey.class, new PrimitiveFactory<Prf, AesCmacPrfKey>(Prf.class) {
            public Prf getPrimitive(AesCmacPrfKey aesCmacPrfKey) throws GeneralSecurityException {
                return new PrfAesCmac(aesCmacPrfKey.getKeyValue().toByteArray());
            }
        });
    }

    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacPrfKeyManager().getKeyType(), ((AesCmacPrfKeyFormat) AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build()).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCmacPrfKeyManager(), z2);
    }

    /* access modifiers changed from: private */
    public static void validateSize(int i3) throws GeneralSecurityException {
        if (i3 != 32) {
            throw new GeneralSecurityException("AesCmacPrfKey size wrong, must be 32 bytes");
        }
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacPrfKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<AesCmacPrfKeyFormat, AesCmacPrfKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCmacPrfKeyFormat, AesCmacPrfKey>(AesCmacPrfKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesCmacPrfKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("AES256_CMAC_PRF", new KeyTypeManager.KeyFactory.KeyFormat((AesCmacPrfKeyFormat) AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build(), outputPrefixType));
                hashMap.put("AES_CMAC_PRF", new KeyTypeManager.KeyFactory.KeyFormat((AesCmacPrfKeyFormat) AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build(), outputPrefixType));
                return Collections.unmodifiableMap(hashMap);
            }

            public AesCmacPrfKey createKey(AesCmacPrfKeyFormat aesCmacPrfKeyFormat) {
                return (AesCmacPrfKey) AesCmacPrfKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCmacPrfKeyFormat.getKeySize()))).build();
            }

            public AesCmacPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesCmacPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(AesCmacPrfKeyFormat aesCmacPrfKeyFormat) throws GeneralSecurityException {
                AesCmacPrfKeyManager.validateSize(aesCmacPrfKeyFormat.getKeySize());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public AesCmacPrfKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesCmacPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(AesCmacPrfKey aesCmacPrfKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCmacPrfKey.getVersion(), getVersion());
        validateSize(aesCmacPrfKey.getKeyValue().size());
    }
}
