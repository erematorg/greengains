package com.google.android.gms.internal.mlkit_common;

import android.support.v4.media.session.a;
import com.google.mlkit.common.sdkinternal.ModelType;
import org.apache.commons.text.StringSubstitutor;

final class zzrx extends zzsj {
    private final zzmu zza;
    private final String zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final ModelType zze;
    private final zzna zzf;
    private final int zzg;

    public /* synthetic */ zzrx(zzmu zzmu, String str, boolean z2, boolean z3, ModelType modelType, zzna zzna, int i3, zzrw zzrw) {
        this.zza = zzmu;
        this.zzb = str;
        this.zzc = z2;
        this.zzd = z3;
        this.zze = modelType;
        this.zzf = zzna;
        this.zzg = i3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzsj) {
            zzsj zzsj = (zzsj) obj;
            return this.zza.equals(zzsj.zzc()) && this.zzb.equals(zzsj.zze()) && this.zzc == zzsj.zzg() && this.zzd == zzsj.zzf() && this.zze.equals(zzsj.zzb()) && this.zzf.equals(zzsj.zzd()) && this.zzg == zzsj.zza();
        }
    }

    public final int hashCode() {
        int i3 = 1231;
        int hashCode = (((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ (true != this.zzc ? 1237 : 1231)) * 1000003;
        if (true != this.zzd) {
            i3 = 1237;
        }
        return this.zzg ^ ((((((hashCode ^ i3) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003);
    }

    public final String toString() {
        zzna zzna = this.zzf;
        ModelType modelType = this.zze;
        String obj = this.zza.toString();
        String obj2 = modelType.toString();
        String obj3 = zzna.toString();
        StringBuilder w2 = a.w("RemoteModelLoggingOptions{errorCode=", obj, ", tfliteSchemaVersion=");
        w2.append(this.zzb);
        w2.append(", shouldLogRoughDownloadTime=");
        w2.append(this.zzc);
        w2.append(", shouldLogExactDownloadTime=");
        w2.append(this.zzd);
        w2.append(", modelType=");
        w2.append(obj2);
        w2.append(", downloadStatus=");
        w2.append(obj3);
        w2.append(", failureStatusCode=");
        return A.a.m(w2, StringSubstitutor.DEFAULT_VAR_END, this.zzg);
    }

    public final int zza() {
        return this.zzg;
    }

    public final ModelType zzb() {
        return this.zze;
    }

    public final zzmu zzc() {
        return this.zza;
    }

    public final zzna zzd() {
        return this.zzf;
    }

    public final String zze() {
        return this.zzb;
    }

    public final boolean zzf() {
        return this.zzd;
    }

    public final boolean zzg() {
        return this.zzc;
    }
}
