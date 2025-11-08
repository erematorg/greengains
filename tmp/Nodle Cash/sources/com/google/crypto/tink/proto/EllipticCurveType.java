package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

public enum EllipticCurveType implements Internal.EnumLite {
    UNKNOWN_CURVE(0),
    NIST_P256(2),
    NIST_P384(3),
    NIST_P521(4),
    CURVE25519(5),
    UNRECOGNIZED(-1);
    
    public static final int CURVE25519_VALUE = 5;
    public static final int NIST_P256_VALUE = 2;
    public static final int NIST_P384_VALUE = 3;
    public static final int NIST_P521_VALUE = 4;
    public static final int UNKNOWN_CURVE_VALUE = 0;
    private static final Internal.EnumLiteMap<EllipticCurveType> internalValueMap = null;
    private final int value;

    public static final class EllipticCurveTypeVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = null;

        static {
            INSTANCE = new EllipticCurveTypeVerifier();
        }

        private EllipticCurveTypeVerifier() {
        }

        public boolean isInRange(int i3) {
            return EllipticCurveType.forNumber(i3) != null;
        }
    }

    static {
        internalValueMap = new Internal.EnumLiteMap<EllipticCurveType>() {
            public EllipticCurveType findValueByNumber(int i3) {
                return EllipticCurveType.forNumber(i3);
            }
        };
    }

    private EllipticCurveType(int i3) {
        this.value = i3;
    }

    public static EllipticCurveType forNumber(int i3) {
        if (i3 == 0) {
            return UNKNOWN_CURVE;
        }
        if (i3 == 2) {
            return NIST_P256;
        }
        if (i3 == 3) {
            return NIST_P384;
        }
        if (i3 == 4) {
            return NIST_P521;
        }
        if (i3 != 5) {
            return null;
        }
        return CURVE25519;
    }

    public static Internal.EnumLiteMap<EllipticCurveType> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return EllipticCurveTypeVerifier.INSTANCE;
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static EllipticCurveType valueOf(int i3) {
        return forNumber(i3);
    }
}
