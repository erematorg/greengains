package com.google.android.gms.internal.p002firebaseauthapi;

import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztw  reason: invalid package */
public enum zztw implements zzajg {
    UNKNOWN_CURVE(0),
    NIST_P256(2),
    NIST_P384(3),
    NIST_P521(4),
    CURVE25519(5),
    UNRECOGNIZED(-1);
    
    private static final zzajj<zztw> zzg = null;
    private final int zzi;

    static {
        zzg = new zzty();
    }

    private zztw(int i3) {
        this.zzi = i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(zztw.class.getName());
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

    public static zztw zza(int i3) {
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
}
