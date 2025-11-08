package com.google.android.recaptcha.internal;

import android.webkit.JavascriptInterface;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlinx.coroutines.CompletableDeferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzeq {
    final /* synthetic */ zzez zza;
    @Nullable
    private Long zzb;
    @NotNull
    private final zzfh zzc = zzfh.zzb();

    public zzeq(zzez zzez) {
        this.zza = zzez;
    }

    private final void zzb() {
        if (this.zzb == null) {
            this.zzc.zzf();
            this.zzb = Long.valueOf(this.zzc.zza(TimeUnit.MILLISECONDS));
        }
    }

    @Nullable
    public final Long zza() {
        return this.zzb;
    }

    @JavascriptInterface
    public final void zzlce(@NotNull String str) {
        zznf zzI = zznf.zzI(zzfy.zzh().zzj(str));
        zzez zzez = this.zza;
        if (zzez.zzg().zzb == null) {
            zzez.zzi.zza(zzez.zzp.zza(zzne.LOAD_WEBVIEW));
        }
        zzb();
        zzpc zzi = zzpd.zzi();
        zzi.zzd(zzI);
        this.zza.zzi.zzd((zzpd) zzi.zzj());
    }

    @JavascriptInterface
    public final void zzlsm(@NotNull String str) {
        zzb();
        zzpc zzi = zzpd.zzi();
        zzi.zze(zznu.zzi(zzfy.zzh().zzj(str)));
        this.zza.zzi.zzd((zzpd) zzi.zzj());
    }

    @JavascriptInterface
    public final void zzoid(@NotNull String str) {
        zzb();
        zzox zzg = zzox.zzg(zzfy.zzh().zzj(str));
        zzg.zzi().name();
        if (zzg.zzi() == zzpb.JS_CODE_SUCCESS) {
            this.zza.zzk().hashCode();
            if (!this.zza.zzk().complete(Unit.INSTANCE)) {
                this.zza.zzk().hashCode();
                return;
            }
            return;
        }
        zzg.zzi().name();
        zzo zzo = zzp.zza;
        zzp zza2 = zzo.zza(zzg.zzi());
        this.zza.zzk().hashCode();
        this.zza.zzk().completeExceptionally(zza2);
    }

    @JavascriptInterface
    public final void zzrp(@NotNull String str) {
        zzb();
        zzbu zzbu = this.zza.zzc;
        if (zzbu == null) {
            zzbu = null;
        }
        zzbu.zza(str);
    }

    @JavascriptInterface
    public final void zzscd(@NotNull String str) {
        zzb();
        zzog zzi = zzog.zzi(zzfy.zzh().zzj(str));
        zzi.toString();
        CompletableDeferred completableDeferred = (CompletableDeferred) this.zza.zzl.remove(zzi.zzk());
        if (completableDeferred != null) {
            completableDeferred.complete(zzi);
        }
    }
}
