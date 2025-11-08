package com.google.android.gms.internal.auth;

final class zzfo implements zzfv {
    private final zzfv[] zza;

    public zzfo(zzfv... zzfvArr) {
        this.zza = zzfvArr;
    }

    public final zzfu zzb(Class cls) {
        zzfv[] zzfvArr = this.zza;
        for (int i3 = 0; i3 < 2; i3++) {
            zzfv zzfv = zzfvArr[i3];
            if (zzfv.zzc(cls)) {
                return zzfv.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    public final boolean zzc(Class cls) {
        zzfv[] zzfvArr = this.zza;
        for (int i3 = 0; i3 < 2; i3++) {
            if (zzfvArr[i3].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
