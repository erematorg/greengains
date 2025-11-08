package com.google.android.recaptcha.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzba {
    @NotNull
    private final String zza;
    private final long zzb;
    private final int zzc;

    public zzba(@NotNull String str, long j2, int i3) {
        this.zza = str;
        this.zzb = j2;
        this.zzc = i3;
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof zzba)) {
            return false;
        }
        zzba zzba = (zzba) obj;
        return Intrinsics.areEqual((Object) zzba.zza, (Object) this.zza) && zzba.zzb == this.zzb && zzba.zzc == this.zzc;
    }

    public final int zza() {
        return this.zzc;
    }

    public final long zzb() {
        return this.zzb;
    }

    @NotNull
    public final String zzc() {
        return this.zza;
    }
}
