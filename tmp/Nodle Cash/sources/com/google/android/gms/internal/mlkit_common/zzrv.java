package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

final class zzrv extends zzsi {
    private zzmu zza;
    private String zzb;
    private boolean zzc;
    private boolean zzd;
    private ModelType zze;
    private zzna zzf;
    private int zzg;
    private byte zzh;

    public final zzsi zza(zzna zzna) {
        if (zzna != null) {
            this.zzf = zzna;
            return this;
        }
        throw new NullPointerException("Null downloadStatus");
    }

    public final zzsi zzb(zzmu zzmu) {
        if (zzmu != null) {
            this.zza = zzmu;
            return this;
        }
        throw new NullPointerException("Null errorCode");
    }

    public final zzsi zzc(int i3) {
        this.zzg = i3;
        this.zzh = (byte) (this.zzh | 4);
        return this;
    }

    public final zzsi zzd(ModelType modelType) {
        if (modelType != null) {
            this.zze = modelType;
            return this;
        }
        throw new NullPointerException("Null modelType");
    }

    public final zzsi zze(boolean z2) {
        this.zzd = z2;
        this.zzh = (byte) (this.zzh | 2);
        return this;
    }

    public final zzsi zzf(boolean z2) {
        this.zzc = z2;
        this.zzh = (byte) (this.zzh | 1);
        return this;
    }

    public final zzsi zzg(String str) {
        this.zzb = "NA";
        return this;
    }

    public final zzsj zzh() {
        zzmu zzmu;
        String str;
        ModelType modelType;
        zzna zzna;
        if (this.zzh == 7 && (zzmu = this.zza) != null && (str = this.zzb) != null && (modelType = this.zze) != null && (zzna = this.zzf) != null) {
            return new zzrx(zzmu, str, this.zzc, this.zzd, modelType, zzna, this.zzg, (zzrw) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" errorCode");
        }
        if (this.zzb == null) {
            sb.append(" tfliteSchemaVersion");
        }
        if ((this.zzh & 1) == 0) {
            sb.append(" shouldLogRoughDownloadTime");
        }
        if ((this.zzh & 2) == 0) {
            sb.append(" shouldLogExactDownloadTime");
        }
        if (this.zze == null) {
            sb.append(" modelType");
        }
        if (this.zzf == null) {
            sb.append(" downloadStatus");
        }
        if ((this.zzh & 4) == 0) {
            sb.append(" failureStatusCode");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
