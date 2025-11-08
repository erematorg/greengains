package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamt  reason: invalid package */
public enum zzamt {
    DOUBLE(zzand.DOUBLE, 1),
    FLOAT(zzand.FLOAT, 5),
    INT64(r5, 0),
    UINT64(r5, 0),
    INT32(r11, 0),
    FIXED64(r5, 1),
    FIXED32(r11, 5),
    BOOL(zzand.BOOLEAN, 0),
    STRING(zzand.STRING, (zzand) null),
    GROUP(r19, (zzand) null),
    MESSAGE(r19, (zzand) null),
    BYTES(zzand.BYTE_STRING, (zzand) null),
    UINT32(r11, 0),
    ENUM(zzand.ENUM, 0),
    SFIXED32(r11, 5),
    SFIXED64(r5, 1),
    SINT32(r11, 0),
    SINT64(r5, 0);
    
    private final zzand zzt;
    private final int zzu;

    public final int zza() {
        return this.zzu;
    }

    public final zzand zzb() {
        return this.zzt;
    }

    private zzamt(zzand zzand, int i3) {
        this.zzt = zzand;
        this.zzu = i3;
    }
}
