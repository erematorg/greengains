package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import sun.misc.Unsafe;

final class zzgx extends zzgy {
    public zzgx(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j2) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j2));
    }

    public final float zzb(Object obj, long j2) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j2));
    }

    public final void zzc(Object obj, long j2, boolean z2) {
        if (zzgz.zzb) {
            zzgz.zzD(obj, j2, r3 ? (byte) 1 : 0);
        } else {
            zzgz.zzE(obj, j2, r3 ? (byte) 1 : 0);
        }
    }

    public final void zzd(Object obj, long j2, byte b3) {
        if (zzgz.zzb) {
            zzgz.zzD(obj, j2, b3);
        } else {
            zzgz.zzE(obj, j2, b3);
        }
    }

    public final void zze(Object obj, long j2, double d2) {
        this.zza.putLong(obj, j2, Double.doubleToLongBits(d2));
    }

    public final void zzf(Object obj, long j2, float f2) {
        this.zza.putInt(obj, j2, Float.floatToIntBits(f2));
    }

    public final boolean zzg(Object obj, long j2) {
        return zzgz.zzb ? zzgz.zzt(obj, j2) : zzgz.zzu(obj, j2);
    }
}
