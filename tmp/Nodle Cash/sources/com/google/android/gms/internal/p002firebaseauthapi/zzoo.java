package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoo  reason: invalid package */
public final class zzoo<P> {
    private final Map<zzxv, List<zzop<P>>> zza;
    private final List<zzop<P>> zzb;
    private final zzop<P> zzc;
    private final Class<P> zzd;
    private final zzrk zze;

    public static <P> zzon<P> zza(Class<P> cls) {
        return new zzon<>(cls);
    }

    public final zzrk zzb() {
        return this.zze;
    }

    public final Class<P> zzc() {
        return this.zzd;
    }

    public final Collection<List<zzop<P>>> zzd() {
        return this.zza.values();
    }

    public final List<zzop<P>> zze() {
        return zza(zzbo.zza);
    }

    public final boolean zzf() {
        return !this.zze.zza().isEmpty();
    }

    private zzoo(Map<zzxv, List<zzop<P>>> map, List<zzop<P>> list, zzop<P> zzop, zzrk zzrk, Class<P> cls) {
        this.zza = map;
        this.zzb = list;
        this.zzc = zzop;
        this.zzd = cls;
        this.zze = zzrk;
    }

    @Nullable
    public final zzop<P> zza() {
        return this.zzc;
    }

    public final List<zzop<P>> zza(byte[] bArr) {
        List<zzop<P>> list = this.zza.get(zzxv.zza(bArr));
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }
}
