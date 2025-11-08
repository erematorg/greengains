package com.google.android.gms.internal.p002firebaseauthapi;

import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzui  reason: invalid package */
public enum zzui implements zzajg {
    AEAD_UNKNOWN(0),
    AES_128_GCM(1),
    AES_256_GCM(2),
    CHACHA20_POLY1305(3),
    UNRECOGNIZED(-1);
    
    private static final zzajj<zzui> zzf = null;
    private final int zzh;

    static {
        zzf = new zzuk();
    }

    private zzui(int i3) {
        this.zzh = i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(zzui.class.getName());
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

    public static zzui zza(int i3) {
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
}
