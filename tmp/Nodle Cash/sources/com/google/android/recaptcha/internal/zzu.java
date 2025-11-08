package com.google.android.recaptcha.internal;

import androidx.camera.camera2.internal.C0118y;
import kotlin.comparisons.ComparisonsKt;
import org.jetbrains.annotations.NotNull;

public final class zzu implements Comparable {
    private int zza;
    private long zzb;
    private long zzc;

    @NotNull
    public final String toString() {
        String G = StringsKt__StringsKt.padEnd$default(String.valueOf(this.zzb / ((long) this.zza)), 10, 0, 2, (Object) null);
        String G2 = StringsKt__StringsKt.padEnd$default(String.valueOf(this.zzc), 10, 0, 2, (Object) null);
        return C0118y.j(C0118y.l("avgExecutionTime: ", G, " us| maxExecutionTime: ", G2, " us| totalTime: "), StringsKt__StringsKt.padEnd$default(String.valueOf(this.zzb), 10, 0, 2, (Object) null), " us| #Usages: ", StringsKt__StringsKt.padEnd$default(String.valueOf(this.zza), 5, 0, 2, (Object) null));
    }

    /* renamed from: zza */
    public final int compareTo(@NotNull zzu zzu) {
        return ComparisonsKt.compareValues(Long.valueOf(this.zzb), Long.valueOf(zzu.zzb));
    }

    public final int zzb() {
        return this.zza;
    }

    public final long zzc() {
        return this.zzc;
    }

    public final long zzd() {
        return this.zzb;
    }

    public final void zze(long j2) {
        this.zzc = j2;
    }

    public final void zzf(long j2) {
        this.zzb = j2;
    }

    public final void zzg(int i3) {
        this.zza = i3;
    }
}
