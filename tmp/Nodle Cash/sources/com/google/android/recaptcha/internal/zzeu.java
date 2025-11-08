package com.google.android.recaptcha.internal;

import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzeu extends WebViewClient {
    final /* synthetic */ zzez zza;

    public zzeu(zzez zzez) {
        this.zza = zzez;
    }

    public final void onLoadResource(@NotNull WebView webView, @NotNull String str) {
        System.currentTimeMillis();
    }

    public final void onPageFinished(@NotNull WebView webView, @NotNull String str) {
        zzez zzez = this.zza;
        zzez.zzi.zza(zzez.zzp.zza(zzne.INIT_NETWORK));
        long zza2 = this.zza.zzn.zza(TimeUnit.MICROSECONDS);
        zzv zzv = zzv.zza;
        zzv.zza(zzx.zzl.zza(), zza2);
    }

    @Deprecated(message = "Use onReceivedError(WebView,request,error) instead")
    public final void onReceivedError(@NotNull WebView webView, int i3, @NotNull String str, @NotNull String str2) {
        super.onReceivedError(webView, i3, str, str2);
        zzn zzn = zzn.zze;
        zzl zzl = (zzl) this.zza.zzk.get(Integer.valueOf(i3));
        if (zzl == null) {
            zzl = zzl.zzY;
        }
        zzp zzp = new zzp(zzn, zzl, (String) null);
        this.zza.zzk().hashCode();
        zzp.getMessage();
        this.zza.zzk().completeExceptionally(zzp);
    }

    @Deprecated(message = "Use shouldInterceptRequest(WebView,WebResourceRequest) instead")
    @Nullable
    public final WebResourceResponse shouldInterceptRequest(@NotNull WebView webView, @NotNull String str) {
        Uri parse = Uri.parse(str);
        zzfb zzfb = zzfb.zza;
        Intrinsics.checkNotNull(parse);
        if (!zzfb.zzb(parse) || zzfb.zza(parse)) {
            return super.shouldInterceptRequest(webView, str);
        }
        zzp zzp = new zzp(zzn.zzc, zzl.zzac, (String) null);
        this.zza.zzk().hashCode();
        parse.toString();
        this.zza.zzk().completeExceptionally(zzp);
        return new WebResourceResponse("text/plain", "UTF-8", new ByteArrayInputStream(new byte[0]));
    }
}
