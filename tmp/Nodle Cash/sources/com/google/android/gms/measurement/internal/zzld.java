package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.List;
import java.util.Map;

@WorkerThread
final class zzld implements Runnable {
    private final URL zza;
    private final zzla zzb;
    private final String zzc;
    private final Map<String, String> zzd = null;
    private final /* synthetic */ zzlb zze;

    public zzld(zzlb zzlb, String str, URL url, byte[] bArr, Map<String, String> map, zzla zzla) {
        this.zze = zzlb;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzla);
        this.zza = url;
        this.zzb = zzla;
        this.zzc = str;
    }

    private final void zzb(int i3, Exception exc, byte[] bArr, Map<String, List<String>> map) {
        this.zze.zzl().zzb((Runnable) new zzlc(this, i3, exc, bArr, map));
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r5 = this;
            com.google.android.gms.measurement.internal.zzlb r0 = r5.zze
            r0.zzr()
            r0 = 0
            r1 = 0
            java.net.URL r2 = r5.zza     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            com.google.android.gms.internal.measurement.zzcv r3 = com.google.android.gms.internal.measurement.zzcv.zza()     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            java.lang.String r4 = "client-measurement"
            java.net.URLConnection r2 = r3.zza(r2, r4)     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            boolean r3 = r2 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            if (r3 == 0) goto L_0x0056
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            r2.setDefaultUseCaches(r1)     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            r3 = 60000(0xea60, float:8.4078E-41)
            r2.setConnectTimeout(r3)     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            r3 = 61000(0xee48, float:8.5479E-41)
            r2.setReadTimeout(r3)     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            r2.setInstanceFollowRedirects(r1)     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            r3 = 1
            r2.setDoInput(r3)     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            int r1 = r2.getResponseCode()     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            java.util.Map r3 = r2.getHeaderFields()     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            com.google.android.gms.measurement.internal.zzlb r4 = r5.zze     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
            byte[] r4 = com.google.android.gms.measurement.internal.zzlb.zza(r2)     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
            r2.disconnect()
            r5.zzb(r1, r0, r4, r3)
            return
        L_0x0044:
            r4 = move-exception
            goto L_0x005e
        L_0x0046:
            r4 = move-exception
            goto L_0x0067
        L_0x0048:
            r4 = move-exception
            r3 = r0
            goto L_0x005e
        L_0x004b:
            r4 = move-exception
            r3 = r0
            goto L_0x0067
        L_0x004e:
            r4 = move-exception
            r2 = r0
            r3 = r2
            goto L_0x005e
        L_0x0052:
            r4 = move-exception
            r2 = r0
            r3 = r2
            goto L_0x0067
        L_0x0056:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            java.lang.String r3 = "Failed to obtain HTTP connection"
            r2.<init>(r3)     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            throw r2     // Catch:{ IOException -> 0x0052, all -> 0x004e }
        L_0x005e:
            if (r2 == 0) goto L_0x0063
            r2.disconnect()
        L_0x0063:
            r5.zzb(r1, r0, r0, r3)
            throw r4
        L_0x0067:
            if (r2 == 0) goto L_0x006c
            r2.disconnect()
        L_0x006c:
            r5.zzb(r1, r4, r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzld.run():void");
    }

    public final /* synthetic */ void zza(int i3, Exception exc, byte[] bArr, Map map) {
        this.zzb.zza(this.zzc, i3, exc, bArr, map);
    }
}
