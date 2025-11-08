package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznc  reason: invalid package */
public final class zznc extends zzci {
    private final zzot zza;

    public zznc(zzot zzot) {
        this.zza = zzot;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zznc)) {
            return false;
        }
        zzot zzot = ((zznc) obj).zza;
        return this.zza.zza().zzd().equals(zzot.zza().zzd()) && this.zza.zza().zzf().equals(zzot.zza().zzf()) && this.zza.zza().zze().equals(zzot.zza().zze());
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza.zza(), this.zza.zzb()});
    }

    public final String toString() {
        String zzf = this.zza.zza().zzf();
        int i3 = zznb.zza[this.zza.zza().zzd().ordinal()];
        return C0118y.g("(typeUrl=", zzf, ", outputPrefixType=", i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK", ")");
    }

    public final boolean zza() {
        return this.zza.zza().zzd() != zzvs.RAW;
    }

    public final zzot zzb() {
        return this.zza;
    }
}
