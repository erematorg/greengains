package com.google.android.gms.internal.auth;

import android.content.Context;
import androidx.camera.camera2.internal.C0118y;
import javax.annotation.Nullable;
import org.apache.commons.text.StringSubstitutor;

final class zzcd extends zzda {
    private final Context zza;
    private final zzdj zzb;

    public zzcd(Context context, @Nullable zzdj zzdj) {
        this.zza = context;
        this.zzb = zzdj;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzda) {
            zzda zzda = (zzda) obj;
            return this.zza.equals(zzda.zza()) && this.zzb.equals(zzda.zzb());
        }
    }

    public final int hashCode() {
        return this.zzb.hashCode() ^ ((this.zza.hashCode() ^ 1000003) * 1000003);
    }

    public final String toString() {
        return C0118y.g("FlagsContext{context=", this.zza.toString(), ", hermeticFileOverrides=", this.zzb.toString(), StringSubstitutor.DEFAULT_VAR_END);
    }

    public final Context zza() {
        return this.zza;
    }

    @Nullable
    public final zzdj zzb() {
        return this.zzb;
    }
}
