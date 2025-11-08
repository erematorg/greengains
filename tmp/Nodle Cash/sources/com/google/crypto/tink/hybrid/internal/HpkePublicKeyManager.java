package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

public final class HpkePublicKeyManager extends KeyTypeManager<HpkePublicKey> {
    public HpkePublicKeyManager() {
        super(HpkePublicKey.class, new PrimitiveFactory<HybridEncrypt, HpkePublicKey>(HybridEncrypt.class) {
            public HybridEncrypt getPrimitive(HpkePublicKey hpkePublicKey) throws GeneralSecurityException {
                return HpkeEncrypt.createHpkeEncrypt(hpkePublicKey);
            }
        });
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HpkePublicKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public HpkePublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HpkePublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(HpkePublicKey hpkePublicKey) throws GeneralSecurityException {
        Validators.validateVersion(hpkePublicKey.getVersion(), getVersion());
        if (hpkePublicKey.hasParams()) {
            HpkeUtil.validateParams(hpkePublicKey.getParams());
            return;
        }
        throw new GeneralSecurityException("Missing HPKE key params.");
    }
}
