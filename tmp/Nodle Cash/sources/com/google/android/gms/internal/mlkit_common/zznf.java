package com.google.android.gms.internal.mlkit_common;

public enum zznf implements zzba {
    SOURCE_UNKNOWN(0),
    APP_ASSET(1),
    LOCAL(2),
    CLOUD(3),
    SDK_BUILT_IN(4),
    URI(5);
    
    private final int zzh;

    private zznf(int i3) {
        this.zzh = i3;
    }

    public final int zza() {
        return this.zzh;
    }
}
