package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzbb {
    @NotNull
    private final zzne zza;
    @NotNull
    private final String zzb;
    @NotNull
    private final String zzc;
    @Nullable
    private final String zzd;
    @Nullable
    private final String zze = null;

    public zzbb(@NotNull zzne zzne, @NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable String str4) {
        this.zza = zzne;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof zzbb)) {
            return false;
        }
        zzbb zzbb = (zzbb) obj;
        return zzbb.zza == this.zza && Intrinsics.areEqual((Object) zzbb.zzb, (Object) this.zzb) && Intrinsics.areEqual((Object) zzbb.zzc, (Object) this.zzc) && Intrinsics.areEqual((Object) zzbb.zzd, (Object) this.zzd) && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, null});
    }

    @NotNull
    public final zzne zza() {
        return this.zza;
    }

    @NotNull
    public final String zzb() {
        return this.zzb;
    }

    @NotNull
    public final String zzc() {
        return this.zzc;
    }

    @Nullable
    public final String zzd() {
        return this.zzd;
    }
}
