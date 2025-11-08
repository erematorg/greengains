package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzou  reason: invalid package */
public final class zzou implements zzox {
    private final String zza;
    private final zzxv zzb;
    private final zzaho zzc;
    private final zzuw.zzb zzd;
    private final zzvs zze;
    @Nullable
    private final Integer zzf;

    private zzou(String str, zzxv zzxv, zzaho zzaho, zzuw.zzb zzb2, zzvs zzvs, @Nullable Integer num) {
        this.zza = str;
        this.zzb = zzxv;
        this.zzc = zzaho;
        this.zzd = zzb2;
        this.zze = zzvs;
        this.zzf = num;
    }

    public static zzou zza(String str, zzaho zzaho, zzuw.zzb zzb2, zzvs zzvs, @Nullable Integer num) throws GeneralSecurityException {
        if (zzvs == zzvs.RAW) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new zzou(str, zzph.zza(str), zzaho, zzb2, zzvs, num);
    }

    public final zzxv zzb() {
        return this.zzb;
    }

    public final zzvs zzc() {
        return this.zze;
    }

    public final zzaho zzd() {
        return this.zzc;
    }

    @Nullable
    public final Integer zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zza;
    }

    public final zzuw.zzb zza() {
        return this.zzd;
    }
}
