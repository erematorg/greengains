package com.google.mlkit.common.internal.model;

import android.support.v4.media.session.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.internal.model.ModelUtils;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_ModelUtils_ModelLoggingInfo extends ModelUtils.ModelLoggingInfo {
    private final long zza;
    private final String zzb;
    private final boolean zzc;

    public AutoValue_ModelUtils_ModelLoggingInfo(long j2, String str, boolean z2) {
        this.zza = j2;
        this.zzb = str;
        this.zzc = z2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModelUtils.ModelLoggingInfo) {
            ModelUtils.ModelLoggingInfo modelLoggingInfo = (ModelUtils.ModelLoggingInfo) obj;
            return this.zza == modelLoggingInfo.getSize() && this.zzb.equals(modelLoggingInfo.getHash()) && this.zzc == modelLoggingInfo.isManifestModel();
        }
    }

    @KeepForSdk
    public String getHash() {
        return this.zzb;
    }

    @KeepForSdk
    public long getSize() {
        return this.zza;
    }

    public final int hashCode() {
        long j2 = this.zza;
        return (true != this.zzc ? 1237 : 1231) ^ ((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003);
    }

    @KeepForSdk
    public boolean isManifestModel() {
        return this.zzc;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ModelLoggingInfo{size=");
        sb.append(this.zza);
        sb.append(", hash=");
        sb.append(this.zzb);
        sb.append(", manifestModel=");
        return a.s(sb, this.zzc, StringSubstitutor.DEFAULT_VAR_END);
    }
}
