package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

public enum HpkeKem implements Internal.EnumLite {
    KEM_UNKNOWN(0),
    DHKEM_X25519_HKDF_SHA256(1),
    DHKEM_P256_HKDF_SHA256(2),
    DHKEM_P384_HKDF_SHA384(3),
    DHKEM_P521_HKDF_SHA512(4),
    UNRECOGNIZED(-1);
    
    public static final int DHKEM_P256_HKDF_SHA256_VALUE = 2;
    public static final int DHKEM_P384_HKDF_SHA384_VALUE = 3;
    public static final int DHKEM_P521_HKDF_SHA512_VALUE = 4;
    public static final int DHKEM_X25519_HKDF_SHA256_VALUE = 1;
    public static final int KEM_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<HpkeKem> internalValueMap = null;
    private final int value;

    public static final class HpkeKemVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = null;

        static {
            INSTANCE = new HpkeKemVerifier();
        }

        private HpkeKemVerifier() {
        }

        public boolean isInRange(int i3) {
            return HpkeKem.forNumber(i3) != null;
        }
    }

    static {
        internalValueMap = new Internal.EnumLiteMap<HpkeKem>() {
            public HpkeKem findValueByNumber(int i3) {
                return HpkeKem.forNumber(i3);
            }
        };
    }

    private HpkeKem(int i3) {
        this.value = i3;
    }

    public static HpkeKem forNumber(int i3) {
        if (i3 == 0) {
            return KEM_UNKNOWN;
        }
        if (i3 == 1) {
            return DHKEM_X25519_HKDF_SHA256;
        }
        if (i3 == 2) {
            return DHKEM_P256_HKDF_SHA256;
        }
        if (i3 == 3) {
            return DHKEM_P384_HKDF_SHA384;
        }
        if (i3 != 4) {
            return null;
        }
        return DHKEM_P521_HKDF_SHA512;
    }

    public static Internal.EnumLiteMap<HpkeKem> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return HpkeKemVerifier.INSTANCE;
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static HpkeKem valueOf(int i3) {
        return forNumber(i3);
    }
}
