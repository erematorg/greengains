package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public enum zzhf {
    DOUBLE(zzhg.DOUBLE, 1),
    FLOAT(zzhg.FLOAT, 5),
    INT64(r12, 0),
    UINT64(r12, 0),
    INT32(r9, 0),
    FIXED64(r12, 1),
    FIXED32(r9, 5),
    BOOL(zzhg.BOOLEAN, 0),
    STRING(zzhg.STRING, 2),
    GROUP(r13, 3),
    MESSAGE(r13, 2),
    BYTES(zzhg.BYTE_STRING, 2),
    UINT32(r15, 0),
    ENUM(zzhg.ENUM, 0),
    SFIXED32(r15, 5),
    SFIXED64(r1, 1),
    SINT32(r3, 0),
    SINT64(r1, 0);
    
    private final zzhg zzt;

    private zzhf(zzhg zzhg, int i3) {
        this.zzt = zzhg;
    }

    public final zzhg zza() {
        return this.zzt;
    }
}
