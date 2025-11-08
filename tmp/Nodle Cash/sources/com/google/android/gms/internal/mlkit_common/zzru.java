package com.google.android.gms.internal.mlkit_common;

import A.a;
import org.apache.commons.text.StringSubstitutor;

final class zzru extends zzsb {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    public /* synthetic */ zzru(String str, boolean z2, int i3, zzrt zzrt) {
        this.zza = str;
        this.zzb = z2;
        this.zzc = i3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzsb) {
            zzsb zzsb = (zzsb) obj;
            return this.zza.equals(zzsb.zzb()) && this.zzb == zzsb.zzc() && this.zzc == zzsb.zza();
        }
    }

    public final int hashCode() {
        int hashCode = this.zza.hashCode() ^ 1000003;
        return this.zzc ^ (((hashCode * 1000003) ^ (true != this.zzb ? 1237 : 1231)) * 1000003);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MLKitLoggingOptions{libraryName=");
        sb.append(this.zza);
        sb.append(", enableFirelog=");
        sb.append(this.zzb);
        sb.append(", firelogEventType=");
        return a.m(sb, StringSubstitutor.DEFAULT_VAR_END, this.zzc);
    }

    public final int zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zza;
    }

    public final boolean zzc() {
        return this.zzb;
    }
}
