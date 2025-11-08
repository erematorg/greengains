package com.google.android.gms.internal.p002firebaseauthapi;

import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuj  reason: invalid package */
public enum zzuj implements zzajg {
    KDF_UNKNOWN(0),
    HKDF_SHA256(1),
    HKDF_SHA384(2),
    HKDF_SHA512(3),
    UNRECOGNIZED(-1);
    
    private static final zzajj<zzuj> zzf = null;
    private final int zzh;

    static {
        zzf = new zzum();
    }

    private zzuj(int i3) {
        this.zzh = i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(zzuj.class.getName());
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
            return this.zzh;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzuj zza(int i3) {
        if (i3 == 0) {
            return KDF_UNKNOWN;
        }
        if (i3 == 1) {
            return HKDF_SHA256;
        }
        if (i3 == 2) {
            return HKDF_SHA384;
        }
        if (i3 != 3) {
            return null;
        }
        return HKDF_SHA512;
    }
}
