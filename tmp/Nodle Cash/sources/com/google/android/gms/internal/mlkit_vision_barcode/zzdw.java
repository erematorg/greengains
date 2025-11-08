package com.google.android.gms.internal.mlkit_vision_barcode;

import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

public final class zzdw extends zzdx {
    public static int zza(int i3, int i4, int i5) {
        return Math.min(Math.max(i3, i4), LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }
}
