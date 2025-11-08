package com.google.android.gms.internal.fido;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

final class zzax extends zzaz {
    private final transient zzaz zza;

    public zzax(zzaz zzaz) {
        this.zza = zzaz;
    }

    private final int zzl(int i3) {
        return (this.zza.size() - 1) - i3;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.contains(obj);
    }

    public final Object get(int i3) {
        zzap.zza(i3, this.zza.size(), FirebaseAnalytics.Param.INDEX);
        return this.zza.get(zzl(i3));
    }

    public final int indexOf(@CheckForNull Object obj) {
        int lastIndexOf = this.zza.lastIndexOf(obj);
        if (lastIndexOf >= 0) {
            return zzl(lastIndexOf);
        }
        return -1;
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        int indexOf = this.zza.indexOf(obj);
        if (indexOf >= 0) {
            return zzl(indexOf);
        }
        return -1;
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzaz zzf() {
        return this.zza;
    }

    /* renamed from: zzg */
    public final zzaz subList(int i3, int i4) {
        zzap.zze(i3, i4, this.zza.size());
        zzaz zzaz = this.zza;
        return zzaz.subList(zzaz.size() - i4, this.zza.size() - i3).zzf();
    }
}
