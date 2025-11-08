package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

public final class zzec {
    @NotNull
    public static final zzeb zza = new zzeb((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final zzea zzb = new zzea(11, ((long) Math.pow(2.0d, 32.0d)) ^ 20919936621L, (long) Math.pow(2.0d, 48.0d));
    @NotNull
    private final zzea zzc;
    private long zzd;

    public zzec(long j2, long j3, @NotNull zzea zzea) {
        this.zzc = zzea;
        this.zzd = Math.abs(j2);
    }

    public final long zza() {
        zzea zzea = this.zzc;
        long longValue = (BigInteger.valueOf(zzea.zzb()).multiply(BigInteger.valueOf(this.zzd)).mod(BigInteger.valueOf(zzea.zza())).longValue() + 11) % this.zzc.zza();
        this.zzd = longValue;
        return longValue % 255;
    }
}
