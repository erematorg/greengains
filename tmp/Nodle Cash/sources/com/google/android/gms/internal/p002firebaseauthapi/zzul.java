package com.google.android.gms.internal.p002firebaseauthapi;

import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzul  reason: invalid package */
public enum zzul implements zzajg {
    KEM_UNKNOWN(0),
    DHKEM_X25519_HKDF_SHA256(1),
    DHKEM_P256_HKDF_SHA256(2),
    DHKEM_P384_HKDF_SHA384(3),
    DHKEM_P521_HKDF_SHA512(4),
    UNRECOGNIZED(-1);
    
    private static final zzajj<zzul> zzg = null;
    private final int zzi;

    static {
        zzg = new zzuo();
    }

    private zzul(int i3) {
        this.zzi = i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(zzul.class.getName());
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this != UNRECOGNIZED) {
            sb.append(" number=");
            sb.append(zza());
        }
        sb.append(" name=");
        sb.append(name());
        sb.append(Typography.greater);
        return sb.toString();
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzi;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzul zza(int i3) {
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
}
