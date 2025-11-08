package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import androidx.annotation.NonNull;
import java.util.Iterator;

public abstract class zzxn {
    public static zzxn zzg(@NonNull Iterable iterable, int i3, int i4, float f2) {
        Iterator it = iterable.iterator();
        int i5 = 0;
        int i6 = i3;
        int i7 = i4;
        int i8 = 0;
        while (it.hasNext()) {
            Point point = (Point) it.next();
            i6 = Math.min(i6, point.x);
            i7 = Math.min(i7, point.y);
            i5 = Math.max(i5, point.x);
            i8 = Math.max(i8, point.y);
        }
        float f3 = (float) i3;
        float f4 = (float) i4;
        return new zzxg((((float) i6) + 0.0f) / f3, (((float) i7) + 0.0f) / f4, (((float) i5) + 0.0f) / f3, (((float) i8) + 0.0f) / f4, 0.0f);
    }

    public abstract float zza();

    public abstract float zzb();

    public abstract float zzc();

    public abstract float zzd();

    public abstract float zze();

    public final float zzf() {
        if (!zzh()) {
            return 0.0f;
        }
        return (zzd() - zze()) * (zzb() - zzc());
    }

    public final boolean zzh() {
        return zzc() >= 0.0f && zzc() < zzb() && zzb() <= 1.0f && zze() >= 0.0f && zze() < zzd() && zzd() <= 1.0f;
    }
}
