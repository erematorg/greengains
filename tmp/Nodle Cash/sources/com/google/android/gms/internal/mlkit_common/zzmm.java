package com.google.android.gms.internal.mlkit_common;

public enum zzmm implements zzba {
    UNKNOWN(0),
    TRANSLATE(1);
    
    private final int zzd;

    private zzmm(int i3) {
        this.zzd = i3;
    }

    public static zzmm zzb(int i3) {
        for (zzmm zzmm : values()) {
            if (zzmm.zzd == i3) {
                return zzmm;
            }
        }
        return UNKNOWN;
    }

    public final int zza() {
        return this.zzd;
    }
}
