package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

public enum HpkeAead implements Internal.EnumLite {
    AEAD_UNKNOWN(0),
    AES_128_GCM(1),
    AES_256_GCM(2),
    CHACHA20_POLY1305(3),
    UNRECOGNIZED(-1);
    
    public static final int AEAD_UNKNOWN_VALUE = 0;
    public static final int AES_128_GCM_VALUE = 1;
    public static final int AES_256_GCM_VALUE = 2;
    public static final int CHACHA20_POLY1305_VALUE = 3;
    private static final Internal.EnumLiteMap<HpkeAead> internalValueMap = null;
    private final int value;

    public static final class HpkeAeadVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = null;

        static {
            INSTANCE = new HpkeAeadVerifier();
        }

        private HpkeAeadVerifier() {
        }

        public boolean isInRange(int i3) {
            return HpkeAead.forNumber(i3) != null;
        }
    }

    static {
        internalValueMap = new Internal.EnumLiteMap<HpkeAead>() {
            public HpkeAead findValueByNumber(int i3) {
                return HpkeAead.forNumber(i3);
            }
        };
    }

    private HpkeAead(int i3) {
        this.value = i3;
    }

    public static HpkeAead forNumber(int i3) {
        if (i3 == 0) {
            return AEAD_UNKNOWN;
        }
        if (i3 == 1) {
            return AES_128_GCM;
        }
        if (i3 == 2) {
            return AES_256_GCM;
        }
        if (i3 != 3) {
            return null;
        }
        return CHACHA20_POLY1305;
    }

    public static Internal.EnumLiteMap<HpkeAead> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return HpkeAeadVerifier.INSTANCE;
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static HpkeAead valueOf(int i3) {
        return forNumber(i3);
    }
}
