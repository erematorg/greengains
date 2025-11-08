package com.google.android.gms.internal.mlkit_vision_common;

public enum zzio implements zzag {
    SOURCE_UNKNOWN(0),
    BITMAP(1),
    BYTEARRAY(2),
    BYTEBUFFER(3),
    FILEPATH(4),
    ANDROID_MEDIA_IMAGE(5);
    
    private final int zzh;

    private zzio(int i3) {
        this.zzh = i3;
    }

    public final int zza() {
        return this.zzh;
    }
}
