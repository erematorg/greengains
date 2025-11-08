package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.camera.camera2.internal.C0118y;

final class zzxg extends zzxn {
    private final float zza;
    private final float zzb;
    private final float zzc;
    private final float zzd;

    public zzxg(float f2, float f3, float f4, float f5, float f6) {
        this.zza = f2;
        this.zzb = f3;
        this.zzc = f4;
        this.zzd = f5;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzxn) {
            zzxn zzxn = (zzxn) obj;
            if (Float.floatToIntBits(this.zza) == Float.floatToIntBits(zzxn.zzc()) && Float.floatToIntBits(this.zzb) == Float.floatToIntBits(zzxn.zze()) && Float.floatToIntBits(this.zzc) == Float.floatToIntBits(zzxn.zzb()) && Float.floatToIntBits(this.zzd) == Float.floatToIntBits(zzxn.zzd())) {
                int floatToIntBits = Float.floatToIntBits(0.0f);
                zzxn.zza();
                if (floatToIntBits == Float.floatToIntBits(0.0f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int floatToIntBits = ((Float.floatToIntBits(this.zza) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.zzb);
        return ((Float.floatToIntBits(this.zzd) ^ (((floatToIntBits * 1000003) ^ Float.floatToIntBits(this.zzc)) * 1000003)) * 1000003) ^ Float.floatToIntBits(0.0f);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PredictedArea{xMin=");
        sb.append(this.zza);
        sb.append(", yMin=");
        sb.append(this.zzb);
        sb.append(", xMax=");
        sb.append(this.zzc);
        sb.append(", yMax=");
        return C0118y.i(sb, ", confidenceScore=0.0}", this.zzd);
    }

    public final float zza() {
        return 0.0f;
    }

    public final float zzb() {
        return this.zzc;
    }

    public final float zzc() {
        return this.zza;
    }

    public final float zzd() {
        return this.zzd;
    }

    public final float zze() {
        return this.zzb;
    }
}
