package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.core.state.b;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.Iterator;
import java.util.List;

public final class zzag implements zzaq {
    private final boolean zza;

    public zzag(Boolean bool) {
        if (bool == null) {
            this.zza = false;
        } else {
            this.zza = bool.booleanValue();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zzag) && this.zza == ((zzag) obj).zza;
    }

    public final int hashCode() {
        return Boolean.valueOf(this.zza).hashCode();
    }

    public final String toString() {
        return String.valueOf(this.zza);
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        if ("toString".equals(str)) {
            return new zzas(Boolean.toString(this.zza));
        }
        throw new IllegalArgumentException(b.m(Boolean.toString(this.zza), JwtUtilsKt.JWT_DELIMITER, str, " is not a function."));
    }

    public final zzaq zzc() {
        return new zzag(Boolean.valueOf(this.zza));
    }

    public final Boolean zzd() {
        return Boolean.valueOf(this.zza);
    }

    public final Double zze() {
        return Double.valueOf(this.zza ? 1.0d : 0.0d);
    }

    public final String zzf() {
        return Boolean.toString(this.zza);
    }

    public final Iterator<zzaq> zzh() {
        return null;
    }
}
