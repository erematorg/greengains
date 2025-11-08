package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.a;
import com.google.common.annotations.VisibleForTesting;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzh {
    @VisibleForTesting
    private final zzh zza;
    @VisibleForTesting
    private zzbb zzb;
    @VisibleForTesting
    private Map<String, zzaq> zzc = new HashMap();
    @VisibleForTesting
    private Map<String, Boolean> zzd = new HashMap();

    public zzh(zzh zzh, zzbb zzbb) {
        this.zza = zzh;
        this.zzb = zzbb;
    }

    public final zzh zza() {
        return new zzh(this, this.zzb);
    }

    public final void zzb(String str, zzaq zzaq) {
        zza(str, zzaq);
        this.zzd.put(str, Boolean.TRUE);
    }

    public final void zzc(String str, zzaq zzaq) {
        while (!this.zzc.containsKey(str) && (r0 = this.zza) != null && r0.zzb(str)) {
            this = this.zza;
        }
        if (this.zzd.containsKey(str)) {
            return;
        }
        if (zzaq == null) {
            this.zzc.remove(str);
        } else {
            this.zzc.put(str, zzaq);
        }
    }

    public final zzaq zza(zzaq zzaq) {
        return this.zzb.zza(this, zzaq);
    }

    public final zzaq zza(zzaf zzaf) {
        zzaq zzaq = zzaq.zzc;
        Iterator<Integer> zzg = zzaf.zzg();
        while (zzg.hasNext()) {
            zzaq = this.zzb.zza(this, zzaf.zza(zzg.next().intValue()));
            if (zzaq instanceof zzaj) {
                break;
            }
        }
        return zzaq;
    }

    public final boolean zzb(String str) {
        while (!this.zzc.containsKey(str)) {
            this = this.zza;
            if (this == null) {
                return false;
            }
        }
        return true;
    }

    public final zzaq zza(String str) {
        while (!this.zzc.containsKey(str)) {
            this = this.zza;
            if (this == null) {
                throw new IllegalArgumentException(a.m(str, " is not defined"));
            }
        }
        return this.zzc.get(str);
    }

    public final void zza(String str, zzaq zzaq) {
        if (this.zzd.containsKey(str)) {
            return;
        }
        if (zzaq == null) {
            this.zzc.remove(str);
        } else {
            this.zzc.put(str, zzaq);
        }
    }
}
