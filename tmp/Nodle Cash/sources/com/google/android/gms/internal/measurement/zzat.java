package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzat implements zzaq {
    private final String zza;
    private final ArrayList<zzaq> zzb;

    public zzat(String str, List<zzaq> list) {
        this.zza = str;
        ArrayList<zzaq> arrayList = new ArrayList<>();
        this.zzb = arrayList;
        arrayList.addAll(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzat)) {
            return false;
        }
        zzat zzat = (zzat) obj;
        String str = this.zza;
        if (str == null ? zzat.zza != null : !str.equals(zzat.zza)) {
            return false;
        }
        ArrayList<zzaq> arrayList = this.zzb;
        return arrayList != null ? arrayList.equals(zzat.zzb) : zzat.zzb == null;
    }

    public final int hashCode() {
        String str = this.zza;
        int i3 = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ArrayList<zzaq> arrayList = this.zzb;
        if (arrayList != null) {
            i3 = arrayList.hashCode();
        }
        return hashCode + i3;
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        throw new IllegalStateException("Statement is not an evaluated entity");
    }

    public final ArrayList<zzaq> zzb() {
        return this.zzb;
    }

    public final zzaq zzc() {
        return this;
    }

    public final Boolean zzd() {
        throw new IllegalStateException("Statement cannot be cast as Boolean");
    }

    public final Double zze() {
        throw new IllegalStateException("Statement cannot be cast as Double");
    }

    public final String zzf() {
        throw new IllegalStateException("Statement cannot be cast as String");
    }

    public final Iterator<zzaq> zzh() {
        return null;
    }

    public final String zza() {
        return this.zza;
    }
}
