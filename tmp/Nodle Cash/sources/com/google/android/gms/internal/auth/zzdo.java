package com.google.android.gms.internal.auth;

import java.io.Serializable;

public final class zzdo {
    public static zzdj zza(zzdj zzdj) {
        return ((zzdj instanceof zzdm) || (zzdj instanceof zzdk)) ? zzdj : zzdj instanceof Serializable ? new zzdk(zzdj) : new zzdm(zzdj);
    }

    public static zzdj zzb(Object obj) {
        return new zzdn(obj);
    }
}
