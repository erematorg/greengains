package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaErrorCode;
import com.google.android.recaptcha.RecaptchaException;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzp extends Exception {
    @NotNull
    public static final zzo zza = new zzo((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final Map zzb;
    @NotNull
    private final zzn zzc;
    @NotNull
    private final zzl zzd;
    @Nullable
    private final String zze;
    @NotNull
    private final Map zzf;

    static {
        Pair pair = TuplesKt.to(zzpb.JS_NETWORK_ERROR, new zzp(zzn.zze, zzl.zzm, (String) null));
        zzpb zzpb = zzpb.JS_INTERNAL_ERROR;
        zzn zzn = zzn.zzc;
        zzb = MapsKt.mapOf(pair, TuplesKt.to(zzpb, new zzp(zzn, zzl.zzk, (String) null)), TuplesKt.to(zzpb.JS_INVALID_SITE_KEY, new zzp(zzn.zzf, zzl.zzn, (String) null)), TuplesKt.to(zzpb.JS_INVALID_SITE_KEY_TYPE, new zzp(zzn.zzg, zzl.zzo, (String) null)), TuplesKt.to(zzpb.JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED, new zzp(zzn.zzh, zzl.zzp, (String) null)), TuplesKt.to(zzpb.JS_INVALID_ACTION, new zzp(zzn.zzi, zzl.zzq, (String) null)), TuplesKt.to(zzpb.JS_PROGRAM_ERROR, new zzp(zzn, zzl.zzu, (String) null)));
    }

    public zzp(@NotNull zzn zzn, @NotNull zzl zzl, @Nullable String str) {
        this.zzc = zzn;
        this.zzd = zzl;
        this.zze = str;
        zzn zzn2 = zzn.zze;
        RecaptchaErrorCode recaptchaErrorCode = RecaptchaErrorCode.NETWORK_ERROR;
        this.zzf = MapsKt.mapOf(TuplesKt.to(zzn2, new RecaptchaException(recaptchaErrorCode, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzn.zzk, new RecaptchaException(recaptchaErrorCode, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzn.zzf, new RecaptchaException(RecaptchaErrorCode.INVALID_SITEKEY, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzn.zzg, new RecaptchaException(RecaptchaErrorCode.INVALID_KEYTYPE, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzn.zzh, new RecaptchaException(RecaptchaErrorCode.INVALID_PACKAGE_NAME, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzn.zzi, new RecaptchaException(RecaptchaErrorCode.INVALID_ACTION, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzn.zzc, new RecaptchaException(RecaptchaErrorCode.INTERNAL_ERROR, (String) null, 2, (DefaultConstructorMarker) null)));
    }

    @NotNull
    public final zzl zza() {
        return this.zzd;
    }

    @NotNull
    public final zzn zzb() {
        return this.zzc;
    }

    @NotNull
    public final RecaptchaException zzc() {
        if (Intrinsics.areEqual((Object) this.zzd, (Object) zzl.zzT)) {
            return new RecaptchaException(RecaptchaErrorCode.INVALID_TIMEOUT, (String) null, 2, (DefaultConstructorMarker) null);
        }
        RecaptchaException recaptchaException = (RecaptchaException) this.zzf.get(this.zzc);
        return recaptchaException == null ? new RecaptchaException(RecaptchaErrorCode.INTERNAL_ERROR, (String) null, 2, (DefaultConstructorMarker) null) : recaptchaException;
    }

    @Nullable
    public final String zzd() {
        return this.zze;
    }
}
