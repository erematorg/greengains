package com.google.android.gms.internal.measurement;

public enum zzof {
    DOUBLE(zzop.DOUBLE, 1),
    FLOAT(zzop.FLOAT, 5),
    INT64(r5, 0),
    UINT64(r5, 0),
    INT32(r11, 0),
    FIXED64(r5, 1),
    FIXED32(r11, 5),
    BOOL(zzop.BOOLEAN, 0),
    STRING(zzop.STRING, (zzop) null),
    GROUP(r19, (zzop) null),
    MESSAGE(r19, (zzop) null),
    BYTES(zzop.BYTE_STRING, (zzop) null),
    UINT32(r11, 0),
    ENUM(zzop.ENUM, 0),
    SFIXED32(r11, 5),
    SFIXED64(r5, 1),
    SINT32(r11, 0),
    SINT64(r5, 0);
    
    private final zzop zzt;
    private final int zzu;

    public final int zza() {
        return this.zzu;
    }

    public final zzop zzb() {
        return this.zzt;
    }

    private zzof(zzop zzop, int i3) {
        this.zzt = zzop;
        this.zzu = i3;
    }
}
