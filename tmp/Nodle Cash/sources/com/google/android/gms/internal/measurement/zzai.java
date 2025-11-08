package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.core.state.b;
import androidx.exifinterface.media.ExifInterface;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public final class zzai implements zzaq {
    private final Double zza;

    public zzai(Double d2) {
        if (d2 == null) {
            this.zza = Double.valueOf(Double.NaN);
        } else {
            this.zza = d2;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzai)) {
            return false;
        }
        return this.zza.equals(((zzai) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return zzf();
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        if ("toString".equals(str)) {
            return new zzas(zzf());
        }
        throw new IllegalArgumentException(b.m(zzf(), JwtUtilsKt.JWT_DELIMITER, str, " is not a function."));
    }

    public final zzaq zzc() {
        return new zzai(this.zza);
    }

    public final Boolean zzd() {
        return Boolean.valueOf(!Double.isNaN(this.zza.doubleValue()) && this.zza.doubleValue() != 0.0d);
    }

    public final Double zze() {
        return this.zza;
    }

    public final String zzf() {
        if (Double.isNaN(this.zza.doubleValue())) {
            return "NaN";
        }
        if (Double.isInfinite(this.zza.doubleValue())) {
            return this.zza.doubleValue() > 0.0d ? "Infinity" : "-Infinity";
        }
        BigDecimal valueOf = BigDecimal.valueOf(this.zza.doubleValue());
        BigDecimal bigDecimal = valueOf.signum() == 0 ? new BigDecimal(BigInteger.ZERO, 0) : valueOf.stripTrailingZeros();
        DecimalFormat decimalFormat = new DecimalFormat("0E0");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        decimalFormat.setMinimumFractionDigits((bigDecimal.scale() > 0 ? bigDecimal.precision() : bigDecimal.scale()) - 1);
        String format = decimalFormat.format(bigDecimal);
        int indexOf = format.indexOf(ExifInterface.LONGITUDE_EAST);
        if (indexOf <= 0) {
            return format;
        }
        int parseInt = Integer.parseInt(format.substring(indexOf + 1));
        return ((parseInt >= 0 || parseInt <= -7) && (parseInt < 0 || parseInt >= 21)) ? format.replace("E-", "e-").replace(ExifInterface.LONGITUDE_EAST, "e+") : bigDecimal.toPlainString();
    }

    public final Iterator<zzaq> zzh() {
        return null;
    }
}
