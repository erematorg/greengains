package com.google.android.recaptcha.internal;

import android.net.TrafficStats;
import android.webkit.URLUtil;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class zzbo implements zzbn {
    @NotNull
    private final String zza;

    public zzbo(@NotNull String str) {
        this.zza = str;
    }

    private static final void zzb(byte[] bArr) {
        for (zznf zznf : zzni.zzk(bArr).zzH()) {
            if (!CollectionsKt.listOf("INIT_TOTAL", "EXECUTE_TOTAL").contains(zznf.zzj().name()) || !zznf.zzT()) {
                zznf.zzJ();
                zznf.zzK();
                zznf.zzj().name();
                zznf.zzU();
            } else {
                zznf.zzJ();
                zznf.zzK();
                zznf.zzj().name();
                zznf.zzg().zzk();
                zznf.zzg().zzf();
                zznf.zzU();
            }
        }
    }

    public final boolean zza(@NotNull byte[] bArr) {
        HttpURLConnection httpURLConnection;
        try {
            TrafficStats.setThreadStatsTag((int) Thread.currentThread().getId());
            zzb(bArr);
            if (URLUtil.isHttpUrl(this.zza)) {
                URLConnection openConnection = new URL(this.zza).openConnection();
                Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                httpURLConnection = (HttpURLConnection) openConnection;
            } else if (URLUtil.isHttpsUrl(this.zza)) {
                URLConnection openConnection2 = new URL(this.zza).openConnection();
                Intrinsics.checkNotNull(openConnection2, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
                httpURLConnection = (HttpsURLConnection) openConnection2;
            } else {
                throw new MalformedURLException("Recaptcha server url only allows using Http or Https.");
            }
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-protobuffer");
            httpURLConnection.connect();
            httpURLConnection.getOutputStream().write(bArr);
            return httpURLConnection.getResponseCode() == 200;
        } catch (Exception e3) {
            e3.getMessage();
            return false;
        }
    }
}
