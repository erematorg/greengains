package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

public enum JwtHmacAlgorithm implements Internal.EnumLite {
    HS_UNKNOWN(0),
    HS256(1),
    HS384(2),
    HS512(3),
    UNRECOGNIZED(-1);
    
    public static final int HS256_VALUE = 1;
    public static final int HS384_VALUE = 2;
    public static final int HS512_VALUE = 3;
    public static final int HS_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<JwtHmacAlgorithm> internalValueMap = null;
    private final int value;

    public static final class JwtHmacAlgorithmVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = null;

        static {
            INSTANCE = new JwtHmacAlgorithmVerifier();
        }

        private JwtHmacAlgorithmVerifier() {
        }

        public boolean isInRange(int i3) {
            return JwtHmacAlgorithm.forNumber(i3) != null;
        }
    }

    static {
        internalValueMap = new Internal.EnumLiteMap<JwtHmacAlgorithm>() {
            public JwtHmacAlgorithm findValueByNumber(int i3) {
                return JwtHmacAlgorithm.forNumber(i3);
            }
        };
    }

    private JwtHmacAlgorithm(int i3) {
        this.value = i3;
    }

    public static JwtHmacAlgorithm forNumber(int i3) {
        if (i3 == 0) {
            return HS_UNKNOWN;
        }
        if (i3 == 1) {
            return HS256;
        }
        if (i3 == 2) {
            return HS384;
        }
        if (i3 != 3) {
            return null;
        }
        return HS512;
    }

    public static Internal.EnumLiteMap<JwtHmacAlgorithm> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return JwtHmacAlgorithmVerifier.INSTANCE;
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static JwtHmacAlgorithm valueOf(int i3) {
        return forNumber(i3);
    }
}
