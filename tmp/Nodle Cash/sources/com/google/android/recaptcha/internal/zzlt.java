package com.google.android.recaptcha.internal;

import sun.misc.Unsafe;

final class zzlt extends zzlu {
    public zzlt(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j2) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j2));
    }

    public final float zzb(Object obj, long j2) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j2));
    }

    public final void zzc(Object obj, long j2, boolean z2) {
        if (zzlv.zzb) {
            zzlv.zzD(obj, j2, r3 ? (byte) 1 : 0);
        } else {
            zzlv.zzE(obj, j2, r3 ? (byte) 1 : 0);
        }
    }

    public final void zzd(Object obj, long j2, byte b3) {
        if (zzlv.zzb) {
            zzlv.zzD(obj, j2, b3);
        } else {
            zzlv.zzE(obj, j2, b3);
        }
    }

    public final void zze(Object obj, long j2, double d2) {
        this.zza.putLong(obj, j2, Double.doubleToLongBits(d2));
    }

    public final void zzf(Object obj, long j2, float f2) {
        this.zza.putInt(obj, j2, Float.floatToIntBits(f2));
    }

    public final boolean zzg(Object obj, long j2) {
        return zzlv.zzb ? zzlv.zzt(obj, j2) : zzlv.zzu(obj, j2);
    }
}
