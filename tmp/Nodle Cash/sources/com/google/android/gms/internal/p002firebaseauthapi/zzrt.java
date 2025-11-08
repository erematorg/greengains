package com.google.android.gms.internal.p002firebaseauthapi;

import android.support.v4.media.session.a;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrt  reason: invalid package */
public final class zzrt {
    private final zzbw zza;
    private final int zzb;
    private final String zzc;
    private final String zzd;

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzrt)) {
            return false;
        }
        zzrt zzrt = (zzrt) obj;
        return this.zza == zzrt.zza && this.zzb == zzrt.zzb && this.zzc.equals(zzrt.zzc) && this.zzd.equals(zzrt.zzd);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final String toString() {
        zzbw zzbw = this.zza;
        int i3 = this.zzb;
        String str = this.zzc;
        String str2 = this.zzd;
        StringBuilder sb = new StringBuilder("(status=");
        sb.append(zzbw);
        sb.append(", keyId=");
        sb.append(i3);
        sb.append(", keyType='");
        return a.r(sb, str, "', keyPrefix='", str2, "')");
    }

    public final int zza() {
        return this.zzb;
    }

    private zzrt(zzbw zzbw, int i3, String str, String str2) {
        this.zza = zzbw;
        this.zzb = i3;
        this.zzc = str;
        this.zzd = str2;
    }
}
