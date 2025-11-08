package com.google.android.gms.internal.auth;

import sun.misc.Unsafe;

final class zzhg extends zzhi {
    public zzhg(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j2) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j2));
    }

    public final float zzb(Object obj, long j2) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j2));
    }

    public final void zzc(Object obj, long j2, boolean z2) {
        if (zzhj.zza) {
            zzhj.zzi(obj, j2, z2);
        } else {
            zzhj.zzj(obj, j2, z2);
        }
    }

    public final void zzd(Object obj, long j2, double d2) {
        this.zza.putLong(obj, j2, Double.doubleToLongBits(d2));
    }

    public final void zze(Object obj, long j2, float f2) {
        this.zza.putInt(obj, j2, Float.floatToIntBits(f2));
    }

    public final boolean zzf(Object obj, long j2) {
        return zzhj.zza ? zzhj.zzq(obj, j2) : zzhj.zzr(obj, j2);
    }
}
