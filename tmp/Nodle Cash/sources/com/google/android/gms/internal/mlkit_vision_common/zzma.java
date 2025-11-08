package com.google.android.gms.internal.mlkit_vision_common;

import A.a;
import org.apache.commons.text.StringSubstitutor;

final class zzma extends zzme {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    public /* synthetic */ zzma(String str, boolean z2, int i3, zzlz zzlz) {
        this.zza = str;
        this.zzb = z2;
        this.zzc = i3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzme) {
            zzme zzme = (zzme) obj;
            return this.zza.equals(zzme.zzb()) && this.zzb == zzme.zzc() && this.zzc == zzme.zza();
        }
    }

    public final int hashCode() {
        int hashCode = this.zza.hashCode() ^ 1000003;
        return this.zzc ^ (((hashCode * 1000003) ^ (true != this.zzb ? 1237 : 1231)) * 1000003);
    }

    public final String toString() {
        String str = this.zza;
        boolean z2 = this.zzb;
        int i3 = this.zzc;
        StringBuilder sb = new StringBuilder("MLKitLoggingOptions{libraryName=");
        sb.append(str);
        sb.append(", enableFirelog=");
        sb.append(z2);
        sb.append(", firelogEventType=");
        return a.m(sb, StringSubstitutor.DEFAULT_VAR_END, i3);
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
