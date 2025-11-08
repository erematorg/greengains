package com.google.android.gms.internal.measurement;

final class zzma implements zzmi {
    private zzmi[] zza;

    public zzma(zzmi... zzmiArr) {
        this.zza = zzmiArr;
    }

    public final zzmj zza(Class<?> cls) {
        for (zzmi zzmi : this.zza) {
            if (zzmi.zzb(cls)) {
                return zzmi.zza(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    public final boolean zzb(Class<?> cls) {
        for (zzmi zzb : this.zza) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }
}
