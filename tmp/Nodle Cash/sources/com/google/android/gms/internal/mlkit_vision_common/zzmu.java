package com.google.android.gms.internal.mlkit_vision_common;

import android.os.SystemClock;
import androidx.annotation.WorkerThread;

public final class zzmu {
    @WorkerThread
    public static void zza(zzmj zzmj, int i3, int i4, long j2, int i5, int i6, int i7, int i8) {
        zzmj.zzc(zzc(i3, i4, j2, i5, i6, i7, i8), zziv.INPUT_IMAGE_CONSTRUCTION);
    }

    @WorkerThread
    public static void zzb(zzmj zzmj, int i3, int i4, long j2, int i5, int i6, int i7, int i8) {
        zzmj.zzc(zzc(i3, i4, j2, i5, i6, i7, i8), zziv.ODML_IMAGE);
    }

    private static zzmt zzc(int i3, int i4, long j2, int i5, int i6, int i7, int i8) {
        return new zzmt(i3, i4, i7, i5, i6, SystemClock.elapsedRealtime() - j2, i8);
    }
}
