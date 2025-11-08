package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzvg;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzon  reason: invalid package */
public final class zzon<P> {
    private final Class<P> zza;
    private Map<zzxv, List<zzop<P>>> zzb;
    private final List<zzop<P>> zzc;
    private zzop<P> zzd;
    private zzrk zze;

    private final zzon<P> zza(P p2, zzbu zzbu, zzvg.zza zza2, boolean z2) throws GeneralSecurityException {
        if (this.zzb == null) {
            throw new IllegalStateException("addEntry cannot be called after build");
        } else if (p2 == null) {
            throw new NullPointerException("`fullPrimitive` must not be null");
        } else if (zza2.zzc() == zzva.ENABLED) {
            zzop zzop = new zzop(p2, zzxv.zza(zzbo.zza(zza2)), zza2.zzc(), zza2.zzf(), zza2.zza(), zza2.zzb().zzf(), zzbu);
            Map<zzxv, List<zzop<P>>> map = this.zzb;
            List<zzop<P>> list = this.zzc;
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzop);
            List put = map.put(zzop.zzb, Collections.unmodifiableList(arrayList));
            if (put != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(put);
                arrayList2.add(zzop);
                map.put(zzop.zzb, Collections.unmodifiableList(arrayList2));
            }
            list.add(zzop);
            if (z2) {
                if (this.zzd == null) {
                    this.zzd = zzop;
                } else {
                    throw new IllegalStateException("you cannot set two primary primitives");
                }
            }
            return this;
        } else {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
    }

    public final zzon<P> zzb(P p2, zzbu zzbu, zzvg.zza zza2) throws GeneralSecurityException {
        return zza(p2, zzbu, zza2, true);
    }

    private zzon(Class<P> cls) {
        this.zzb = new HashMap();
        this.zzc = new ArrayList();
        this.zza = cls;
        this.zze = zzrk.zza;
    }

    public final zzon<P> zza(P p2, zzbu zzbu, zzvg.zza zza2) throws GeneralSecurityException {
        return zza(p2, zzbu, zza2, false);
    }

    public final zzon<P> zza(zzrk zzrk) {
        if (this.zzb != null) {
            this.zze = zzrk;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build");
    }

    public final zzoo<P> zza() throws GeneralSecurityException {
        Map<zzxv, List<zzop<P>>> map = this.zzb;
        if (map != null) {
            zzoo zzoo = new zzoo(map, this.zzc, this.zzd, this.zze, this.zza);
            this.zzb = null;
            return zzoo;
        }
        throw new IllegalStateException("build cannot be called twice");
    }
}
