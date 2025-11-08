package com.google.android.recaptcha.internal;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzbd {
    @NotNull
    public static final zzbc zza = new zzbc((DefaultConstructorMarker) null);
    @NotNull
    private String zzb;
    @NotNull
    private String zzc;
    @Nullable
    private String zzd;

    private zzbd(String str, String str2) {
        this.zzb = str;
        this.zzc = str2;
    }

    @NotNull
    public final zzbb zza(@NotNull zzne zzne) {
        return new zzbb(zzne, this.zzb, this.zzc, this.zzd, (String) null);
    }

    @NotNull
    public final zzbd zzb() {
        return new zzbd(this);
    }

    @NotNull
    public final zzbd zzc(@NotNull String str) {
        this.zzd = str;
        return this;
    }

    @NotNull
    public final String zzd() {
        return this.zzc;
    }

    public /* synthetic */ zzbd(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    private zzbd(zzbd zzbd) {
        this(zzbd.zzb, zzbd.zzc);
        this.zzd = zzbd.zzd;
    }
}
