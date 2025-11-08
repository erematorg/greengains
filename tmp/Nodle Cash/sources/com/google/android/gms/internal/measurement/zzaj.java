package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

public final class zzaj implements zzaq {
    private final zzaq zza;
    private final String zzb;

    public zzaj() {
        this.zza = zzaq.zzc;
        this.zzb = "return";
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaj)) {
            return false;
        }
        zzaj zzaj = (zzaj) obj;
        return this.zzb.equals(zzaj.zzb) && this.zza.equals(zzaj.zza);
    }

    public final int hashCode() {
        return this.zza.hashCode() + (this.zzb.hashCode() * 31);
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        throw new IllegalStateException("Control does not have functions");
    }

    public final String zzb() {
        return this.zzb;
    }

    public final zzaq zzc() {
        return new zzaj(this.zzb, this.zza.zzc());
    }

    public final Boolean zzd() {
        throw new IllegalStateException("Control is not a boolean");
    }

    public final Double zze() {
        throw new IllegalStateException("Control is not a double");
    }

    public final String zzf() {
        throw new IllegalStateException("Control is not a String");
    }

    public final Iterator<zzaq> zzh() {
        return null;
    }

    public final zzaq zza() {
        return this.zza;
    }

    public zzaj(String str) {
        this.zza = zzaq.zzc;
        this.zzb = str;
    }

    public zzaj(String str, zzaq zzaq) {
        this.zza = zzaq;
        this.zzb = str;
    }
}
