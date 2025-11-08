package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzake  reason: invalid package */
final class zzake implements zzakm {
    private zzakm[] zza;

    public zzake(zzakm... zzakmArr) {
        this.zza = zzakmArr;
    }

    public final zzakn zza(Class<?> cls) {
        for (zzakm zzakm : this.zza) {
            if (zzakm.zzb(cls)) {
                return zzakm.zza(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    public final boolean zzb(Class<?> cls) {
        for (zzakm zzb : this.zza) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }
}
