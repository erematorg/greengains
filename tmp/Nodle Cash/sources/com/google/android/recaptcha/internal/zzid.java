package com.google.android.recaptcha.internal;

final class zzid {
    private final Object zza;
    private final int zzb;

    public zzid(Object obj, int i3) {
        this.zza = obj;
        this.zzb = i3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzid)) {
            return false;
        }
        zzid zzid = (zzid) obj;
        return this.zza == zzid.zza && this.zzb == zzid.zzb;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
