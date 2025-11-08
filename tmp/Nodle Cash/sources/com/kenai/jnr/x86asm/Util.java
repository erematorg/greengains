package com.kenai.jnr.x86asm;

import androidx.collection.SieveCacheKt;

@Deprecated
public final class Util {
    private Util() {
    }

    public static final boolean isInt16(long j2) {
        return j2 >= -32768 && j2 <= 32767;
    }

    public static final boolean isInt32(long j2) {
        return j2 >= SieveCacheKt.NodeMetaAndPreviousMask && j2 <= SieveCacheKt.NodeLinkMask;
    }

    public static final boolean isInt8(long j2) {
        return j2 >= -128 && j2 <= 127;
    }

    public static final boolean isUInt16(long j2) {
        return j2 >= 0 && j2 <= 65535;
    }

    public static final boolean isUInt32(long j2) {
        return j2 >= 0 && j2 <= 4294967295L;
    }

    public static final boolean isUInt8(long j2) {
        return j2 >= 0 && j2 <= 255;
    }
}
