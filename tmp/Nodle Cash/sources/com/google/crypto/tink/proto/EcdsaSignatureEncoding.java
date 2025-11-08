package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

public enum EcdsaSignatureEncoding implements Internal.EnumLite {
    UNKNOWN_ENCODING(0),
    IEEE_P1363(1),
    DER(2),
    UNRECOGNIZED(-1);
    
    public static final int DER_VALUE = 2;
    public static final int IEEE_P1363_VALUE = 1;
    public static final int UNKNOWN_ENCODING_VALUE = 0;
    private static final Internal.EnumLiteMap<EcdsaSignatureEncoding> internalValueMap = null;
    private final int value;

    public static final class EcdsaSignatureEncodingVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = null;

        static {
            INSTANCE = new EcdsaSignatureEncodingVerifier();
        }

        private EcdsaSignatureEncodingVerifier() {
        }

        public boolean isInRange(int i3) {
            return EcdsaSignatureEncoding.forNumber(i3) != null;
        }
    }

    static {
        internalValueMap = new Internal.EnumLiteMap<EcdsaSignatureEncoding>() {
            public EcdsaSignatureEncoding findValueByNumber(int i3) {
                return EcdsaSignatureEncoding.forNumber(i3);
            }
        };
    }

    private EcdsaSignatureEncoding(int i3) {
        this.value = i3;
    }

    public static EcdsaSignatureEncoding forNumber(int i3) {
        if (i3 == 0) {
            return UNKNOWN_ENCODING;
        }
        if (i3 == 1) {
            return IEEE_P1363;
        }
        if (i3 != 2) {
            return null;
        }
        return DER;
    }

    public static Internal.EnumLiteMap<EcdsaSignatureEncoding> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return EcdsaSignatureEncodingVerifier.INSTANCE;
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static EcdsaSignatureEncoding valueOf(int i3) {
        return forNumber(i3);
    }
}
