package com.google.mlkit.vision.common;

import androidx.camera.camera2.internal.C0118y;
import org.apache.commons.text.StringSubstitutor;

final class zza extends PointF3D {
    private final float zza;
    private final float zzb;
    private final float zzc;

    public zza(float f2, float f3, float f4) {
        this.zza = f2;
        this.zzb = f3;
        this.zzc = f4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PointF3D) {
            PointF3D pointF3D = (PointF3D) obj;
            return Float.floatToIntBits(this.zza) == Float.floatToIntBits(pointF3D.getX()) && Float.floatToIntBits(this.zzb) == Float.floatToIntBits(pointF3D.getY()) && Float.floatToIntBits(this.zzc) == Float.floatToIntBits(pointF3D.getZ());
        }
    }

    public final float getX() {
        return this.zza;
    }

    public final float getY() {
        return this.zzb;
    }

    public final float getZ() {
        return this.zzc;
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.zzc) ^ ((((Float.floatToIntBits(this.zza) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.zzb)) * 1000003);
    }

    public final String toString() {
        float f2 = this.zza;
        float f3 = this.zzb;
        float f4 = this.zzc;
        StringBuilder sb = new StringBuilder("PointF3D{x=");
        sb.append(f2);
        sb.append(", y=");
        sb.append(f3);
        sb.append(", z=");
        return C0118y.i(sb, StringSubstitutor.DEFAULT_VAR_END, f4);
    }
}
