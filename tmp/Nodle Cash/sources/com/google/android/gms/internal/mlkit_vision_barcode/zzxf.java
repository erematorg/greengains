package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.camera.camera2.internal.C0118y;
import org.apache.commons.text.StringSubstitutor;

final class zzxf extends zzxm {
    private final int zzb;
    private final int zzc;
    private final float zzd;
    private final float zze;
    private final boolean zzf;
    private final float zzg;
    private final float zzh;
    private final long zzi;
    private final long zzj;
    private final boolean zzk;
    private final float zzl;
    private final float zzm;

    public /* synthetic */ zzxf(int i3, int i4, float f2, float f3, boolean z2, float f4, float f5, long j2, long j3, boolean z3, float f6, float f7, zzxe zzxe) {
        this.zzb = i3;
        this.zzc = i4;
        this.zzd = f2;
        this.zze = f3;
        this.zzf = z2;
        this.zzg = f4;
        this.zzh = f5;
        this.zzi = j2;
        this.zzj = j3;
        this.zzk = z3;
        this.zzl = f6;
        this.zzm = f7;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzxm) {
            zzxm zzxm = (zzxm) obj;
            return this.zzb == zzxm.zzh() && this.zzc == zzxm.zzg() && Float.floatToIntBits(this.zzd) == Float.floatToIntBits(zzxm.zzd()) && Float.floatToIntBits(this.zze) == Float.floatToIntBits(zzxm.zzc()) && this.zzf == zzxm.zzl() && Float.floatToIntBits(this.zzg) == Float.floatToIntBits(zzxm.zzb()) && Float.floatToIntBits(this.zzh) == Float.floatToIntBits(zzxm.zza()) && this.zzi == zzxm.zzj() && this.zzj == zzxm.zzi() && this.zzk == zzxm.zzk() && Float.floatToIntBits(this.zzl) == Float.floatToIntBits(zzxm.zze()) && Float.floatToIntBits(this.zzm) == Float.floatToIntBits(zzxm.zzf());
        }
    }

    public final int hashCode() {
        int i3 = 1231;
        int floatToIntBits = (((((((((((((((((this.zzb ^ 1000003) * 1000003) ^ this.zzc) * 1000003) ^ Float.floatToIntBits(this.zzd)) * 1000003) ^ Float.floatToIntBits(this.zze)) * 1000003) ^ (true != this.zzf ? 1237 : 1231)) * 1000003) ^ Float.floatToIntBits(this.zzg)) * 1000003) ^ Float.floatToIntBits(this.zzh)) * 1000003) ^ ((int) this.zzi)) * 1000003) ^ ((int) this.zzj)) * 1000003;
        if (true != this.zzk) {
            i3 = 1237;
        }
        return Float.floatToIntBits(this.zzm) ^ ((((floatToIntBits ^ i3) * 1000003) ^ Float.floatToIntBits(this.zzl)) * 1000003);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AutoZoomOptions{recentFramesToCheck=");
        sb.append(this.zzb);
        sb.append(", recentFramesContainingPredictedArea=");
        sb.append(this.zzc);
        sb.append(", recentFramesIou=");
        sb.append(this.zzd);
        sb.append(", maxCoverage=");
        sb.append(this.zze);
        sb.append(", useConfidenceScore=");
        sb.append(this.zzf);
        sb.append(", lowerConfidenceScore=");
        sb.append(this.zzg);
        sb.append(", higherConfidenceScore=");
        sb.append(this.zzh);
        sb.append(", zoomIntervalInMillis=");
        sb.append(this.zzi);
        sb.append(", resetIntervalInMillis=");
        sb.append(this.zzj);
        sb.append(", enableZoomThreshold=");
        sb.append(this.zzk);
        sb.append(", zoomInThreshold=");
        sb.append(this.zzl);
        sb.append(", zoomOutThreshold=");
        return C0118y.i(sb, StringSubstitutor.DEFAULT_VAR_END, this.zzm);
    }

    public final float zza() {
        return this.zzh;
    }

    public final float zzb() {
        return this.zzg;
    }

    public final float zzc() {
        return this.zze;
    }

    public final float zzd() {
        return this.zzd;
    }

    public final float zze() {
        return this.zzl;
    }

    public final float zzf() {
        return this.zzm;
    }

    public final int zzg() {
        return this.zzc;
    }

    public final int zzh() {
        return this.zzb;
    }

    public final long zzi() {
        return this.zzj;
    }

    public final long zzj() {
        return this.zzi;
    }

    public final boolean zzk() {
        return this.zzk;
    }

    public final boolean zzl() {
        return this.zzf;
    }
}
