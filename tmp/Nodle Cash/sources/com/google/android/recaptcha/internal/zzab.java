package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;

public final class zzab {
    @NotNull
    private final String zza;
    @NotNull
    private final String zzb;
    @NotNull
    private final String zzc;
    @NotNull
    private final String zzd;
    @NotNull
    private final String zze;

    public zzab(@NotNull String str) {
        this.zza = "https://www.recaptcha.net/recaptcha/api3";
        this.zzb = "https://www.recaptcha.net/recaptcha/api3".concat("/mri");
        this.zzc = "https://www.recaptcha.net/recaptcha/api3".concat("/mlg");
        this.zzd = "https://www.recaptcha.net/recaptcha/api3".concat("/mal");
        this.zze = "https://www.recaptcha.net/recaptcha/api3".concat("/mrr");
    }

    @NotNull
    public final String zza() {
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

    @NotNull
    public final String zzd() {
        return this.zze;
    }

    public zzab() {
        this("https://www.recaptcha.net/recaptcha/api3");
    }
}
