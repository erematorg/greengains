package com.google.android.gms.internal.p002firebaseauthapi;

import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzti  reason: invalid package */
public enum zzti implements zzajg {
    UNKNOWN_FORMAT(0),
    UNCOMPRESSED(1),
    COMPRESSED(2),
    DO_NOT_USE_CRUNCHY_UNCOMPRESSED(3),
    UNRECOGNIZED(-1);
    
    private static final zzajj<zzti> zzf = null;
    private final int zzh;

    static {
        zzf = new zztk();
    }

    private zzti(int i3) {
        this.zzh = i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(zzti.class.getName());
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

    public static zzti zza(int i3) {
        if (i3 == 0) {
            return UNKNOWN_FORMAT;
        }
        if (i3 == 1) {
            return UNCOMPRESSED;
        }
        if (i3 == 2) {
            return COMPRESSED;
        }
        if (i3 != 3) {
            return null;
        }
        return DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
    }
}
