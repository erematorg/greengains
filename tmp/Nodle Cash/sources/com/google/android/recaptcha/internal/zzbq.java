package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.Nullable;

public final class zzbq {
    @Nullable
    private final zzh zza;
    @Nullable
    private final zzbg zzb;

    public zzbq(@Nullable zzh zzh, @Nullable zzbg zzbg) {
        this.zza = zzh;
        this.zzb = zzbg;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0058, code lost:
        throw new com.google.android.recaptcha.internal.zzp(com.google.android.recaptcha.internal.zzn.zzc, com.google.android.recaptcha.internal.zzl.zzR, (java.lang.String) null);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004f */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.recaptcha.internal.zzoe zza(@org.jetbrains.annotations.NotNull java.lang.String r4, @org.jetbrains.annotations.NotNull byte[] r5, @org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzbd r6) throws com.google.android.recaptcha.internal.zzp {
        /*
            r3 = this;
            com.google.android.recaptcha.internal.zzne r0 = com.google.android.recaptcha.internal.zzne.VALIDATE_INPUT
            com.google.android.recaptcha.internal.zzbb r6 = r6.zza(r0)
            com.google.android.recaptcha.internal.zzbg r0 = r3.zzb
            r1 = 2
            r2 = 0
            r0.zze.put(r6, new com.google.android.recaptcha.internal.zzbf(r6, r0.zza, new com.google.android.recaptcha.internal.zzac()))
            java.net.URL r0 = new java.net.URL     // Catch:{ zzp -> 0x004d }
            r0.<init>(r4)     // Catch:{ zzp -> 0x004d }
            java.net.URLConnection r4 = r0.openConnection()     // Catch:{ zzp -> 0x004d }
            java.lang.String r0 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r0)     // Catch:{ zzp -> 0x004d }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ zzp -> 0x004d }
            java.lang.String r0 = "POST"
            r4.setRequestMethod(r0)     // Catch:{ zzp -> 0x004d }
            r0 = 1
            r4.setDoOutput(r0)     // Catch:{ zzp -> 0x004d }
            java.lang.String r0 = "Accept"
            java.lang.String r1 = "application/x-protobuffer"
            r4.setRequestProperty(r0, r1)     // Catch:{ zzp -> 0x004d }
            r4.connect()     // Catch:{ Exception -> 0x0059 }
            java.io.OutputStream r0 = r4.getOutputStream()     // Catch:{ Exception -> 0x0059 }
            r0.write(r5)     // Catch:{ Exception -> 0x0059 }
            int r5 = r4.getResponseCode()     // Catch:{ Exception -> 0x0059 }
            r0 = 200(0xc8, float:2.8E-43)
            if (r5 != r0) goto L_0x005b
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ Exception -> 0x004f }
            com.google.android.recaptcha.internal.zzoe r4 = com.google.android.recaptcha.internal.zzoe.zzi(r4)     // Catch:{ Exception -> 0x004f }
            com.google.android.recaptcha.internal.zzbg r5 = r3.zzb     // Catch:{ zzp -> 0x004d }
            r5.zza(r6)     // Catch:{ zzp -> 0x004d }
            return r4
        L_0x004d:
            r4 = move-exception
            goto L_0x0090
        L_0x004f:
            com.google.android.recaptcha.internal.zzp r4 = new com.google.android.recaptcha.internal.zzp     // Catch:{ Exception -> 0x0059 }
            com.google.android.recaptcha.internal.zzn r5 = com.google.android.recaptcha.internal.zzn.zzc     // Catch:{ Exception -> 0x0059 }
            com.google.android.recaptcha.internal.zzl r0 = com.google.android.recaptcha.internal.zzl.zzR     // Catch:{ Exception -> 0x0059 }
            r4.<init>(r5, r0, r2)     // Catch:{ Exception -> 0x0059 }
            throw r4     // Catch:{ Exception -> 0x0059 }
        L_0x0059:
            r4 = move-exception
            goto L_0x007f
        L_0x005b:
            int r5 = r4.getResponseCode()     // Catch:{ Exception -> 0x0059 }
            r0 = 400(0x190, float:5.6E-43)
            if (r5 != r0) goto L_0x0076
            java.io.InputStream r4 = r4.getErrorStream()     // Catch:{ Exception -> 0x0059 }
            com.google.android.recaptcha.internal.zzoz r4 = com.google.android.recaptcha.internal.zzoz.zzg(r4)     // Catch:{ Exception -> 0x0059 }
            com.google.android.recaptcha.internal.zzo r5 = com.google.android.recaptcha.internal.zzp.zza     // Catch:{ Exception -> 0x0059 }
            com.google.android.recaptcha.internal.zzpb r4 = r4.zzi()     // Catch:{ Exception -> 0x0059 }
            com.google.android.recaptcha.internal.zzp r4 = com.google.android.recaptcha.internal.zzo.zza(r4)     // Catch:{ Exception -> 0x0059 }
            goto L_0x007e
        L_0x0076:
            int r4 = r4.getResponseCode()     // Catch:{ Exception -> 0x0059 }
            com.google.android.recaptcha.internal.zzp r4 = com.google.android.recaptcha.internal.zzbr.zza(r4)     // Catch:{ Exception -> 0x0059 }
        L_0x007e:
            throw r4     // Catch:{ Exception -> 0x0059 }
        L_0x007f:
            boolean r5 = r4 instanceof com.google.android.recaptcha.internal.zzp     // Catch:{ zzp -> 0x004d }
            if (r5 == 0) goto L_0x0086
            com.google.android.recaptcha.internal.zzp r4 = (com.google.android.recaptcha.internal.zzp) r4     // Catch:{ zzp -> 0x004d }
            goto L_0x008f
        L_0x0086:
            com.google.android.recaptcha.internal.zzp r4 = new com.google.android.recaptcha.internal.zzp     // Catch:{ zzp -> 0x004d }
            com.google.android.recaptcha.internal.zzn r5 = com.google.android.recaptcha.internal.zzn.zze     // Catch:{ zzp -> 0x004d }
            com.google.android.recaptcha.internal.zzl r0 = com.google.android.recaptcha.internal.zzl.zzQ     // Catch:{ zzp -> 0x004d }
            r4.<init>(r5, r0, r2)     // Catch:{ zzp -> 0x004d }
        L_0x008f:
            throw r4     // Catch:{ zzp -> 0x004d }
        L_0x0090:
            com.google.android.recaptcha.internal.zzbg r3 = r3.zzb
            r3.zzb(r6, r4, r2)
            com.google.android.recaptcha.RecaptchaException r3 = r4.zzc()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzbq.zza(java.lang.String, byte[], com.google.android.recaptcha.internal.zzbd):com.google.android.recaptcha.internal.zzoe");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:9|10) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:42|43|44) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r11.zzb.zzb(r4, new com.google.android.recaptcha.internal.zzp(com.google.android.recaptcha.internal.zzn.zzn, com.google.android.recaptcha.internal.zzl.zzad, (java.lang.String) null), (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00eb, code lost:
        throw new com.google.android.recaptcha.internal.zzp(com.google.android.recaptcha.internal.zzn.zze, com.google.android.recaptcha.internal.zzl.zzab, (java.lang.String) null);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00d1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x00e2 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002e */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0051 A[Catch:{ Exception -> 0x0107, Exception -> 0x00fd, zzp -> 0x00e0, Exception -> 0x004c }] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzb(@org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzoe r12, @org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzbd r13) throws com.google.android.recaptcha.internal.zzp {
        /*
            r11 = this;
            java.lang.String r0 = "gzip"
            r1 = 0
            java.lang.String r2 = r12.zzk()     // Catch:{ Exception -> 0x004c }
            java.lang.String r3 = r12.zzH()     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzh r4 = r11.zza     // Catch:{ Exception -> 0x004c }
            boolean r4 = r4.zzd(r3)     // Catch:{ Exception -> 0x004c }
            r5 = 1
            r6 = 2
            if (r4 != r5) goto L_0x004a
            com.google.android.recaptcha.internal.zzne r4 = com.google.android.recaptcha.internal.zzne.LOAD_CACHE_JS     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzbb r4 = r13.zza(r4)     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzbg r7 = r11.zzb     // Catch:{ Exception -> 0x004c }
            r7.zze.put(r4, new com.google.android.recaptcha.internal.zzbf(r4, r7.zza, new com.google.android.recaptcha.internal.zzac()))     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzh r7 = r11.zza     // Catch:{ Exception -> 0x002e }
            java.lang.String r7 = r7.zza(r3)     // Catch:{ Exception -> 0x002e }
            if (r7 == 0) goto L_0x003c
            com.google.android.recaptcha.internal.zzbg r8 = r11.zzb     // Catch:{ Exception -> 0x002e }
            r8.zza(r4)     // Catch:{ Exception -> 0x002e }
            goto L_0x004f
        L_0x002e:
            com.google.android.recaptcha.internal.zzbg r7 = r11.zzb     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzp r8 = new com.google.android.recaptcha.internal.zzp     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzn r9 = com.google.android.recaptcha.internal.zzn.zzn     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzl r10 = com.google.android.recaptcha.internal.zzl.zzad     // Catch:{ Exception -> 0x004c }
            r8.<init>(r9, r10, r1)     // Catch:{ Exception -> 0x004c }
            r7.zzb(r4, r8, r1)     // Catch:{ Exception -> 0x004c }
        L_0x003c:
            com.google.android.recaptcha.internal.zzbg r7 = r11.zzb     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzp r8 = new com.google.android.recaptcha.internal.zzp     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzn r9 = com.google.android.recaptcha.internal.zzn.zzn     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzl r10 = com.google.android.recaptcha.internal.zzl.zzae     // Catch:{ Exception -> 0x004c }
            r8.<init>(r9, r10, r1)     // Catch:{ Exception -> 0x004c }
            r7.zzb(r4, r8, r1)     // Catch:{ Exception -> 0x004c }
        L_0x004a:
            r7 = r1
            goto L_0x004f
        L_0x004c:
            r11 = move-exception
            goto L_0x0122
        L_0x004f:
            if (r7 != 0) goto L_0x0117
            com.google.android.recaptcha.internal.zzh r4 = r11.zza     // Catch:{ Exception -> 0x004c }
            r4.zzb()     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzne r4 = com.google.android.recaptcha.internal.zzne.DOWNLOAD_JS     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzbb r4 = r13.zza(r4)     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzbg r7 = r11.zzb     // Catch:{ zzp -> 0x00e0 }
            r7.zze.put(r4, new com.google.android.recaptcha.internal.zzbf(r4, r7.zza, new com.google.android.recaptcha.internal.zzac()))     // Catch:{ zzp -> 0x00e0 }
            java.net.URL r7 = new java.net.URL     // Catch:{ Exception -> 0x0107 }
            r7.<init>(r2)     // Catch:{ Exception -> 0x0107 }
            java.net.URLConnection r2 = r7.openConnection()     // Catch:{ Exception -> 0x00fd }
            java.lang.String r7 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r7)     // Catch:{ Exception -> 0x00fd }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x00fd }
            java.lang.String r7 = "GET"
            r2.setRequestMethod(r7)     // Catch:{ Exception -> 0x00fd }
            r2.setDoInput(r5)     // Catch:{ Exception -> 0x00fd }
            java.lang.String r5 = "Accept"
            java.lang.String r7 = "application/x-protobuffer"
            r2.setRequestProperty(r5, r7)     // Catch:{ Exception -> 0x00fd }
            java.lang.String r5 = "Accept-Encoding"
            r2.setRequestProperty(r5, r0)     // Catch:{ Exception -> 0x00fd }
            r2.connect()     // Catch:{ Exception -> 0x00fd }
            int r5 = r2.getResponseCode()     // Catch:{ zzp -> 0x00e0 }
            r7 = 200(0xc8, float:2.8E-43)
            if (r5 != r7) goto L_0x00ec
            java.lang.String r5 = r2.getContentEncoding()     // Catch:{ Exception -> 0x00e2 }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r5)     // Catch:{ Exception -> 0x00e2 }
            if (r0 == 0) goto L_0x00a9
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00e2 }
            java.util.zip.GZIPInputStream r5 = new java.util.zip.GZIPInputStream     // Catch:{ Exception -> 0x00e2 }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x00e2 }
            r5.<init>(r2)     // Catch:{ Exception -> 0x00e2 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x00e2 }
            goto L_0x00b2
        L_0x00a9:
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00e2 }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x00e2 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x00e2 }
        L_0x00b2:
            java.lang.String r7 = kotlin.io.TextStreamsKt.readText(r0)     // Catch:{ Exception -> 0x00e2 }
            com.google.android.recaptcha.internal.zzbg r0 = r11.zzb     // Catch:{ zzp -> 0x00e0 }
            r0.zza(r4)     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzne r0 = com.google.android.recaptcha.internal.zzne.SAVE_CACHE_JS     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzbb r13 = r13.zza(r0)     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzbg r0 = r11.zzb     // Catch:{ Exception -> 0x00d1 }
            r0.zze.put(r13, new com.google.android.recaptcha.internal.zzbf(r13, r0.zza, new com.google.android.recaptcha.internal.zzac()))     // Catch:{ Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzh r0 = r11.zza     // Catch:{ Exception -> 0x00d1 }
            r0.zzc(r3, r7)     // Catch:{ Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzbg r0 = r11.zzb     // Catch:{ Exception -> 0x00d1 }
            r0.zza(r13)     // Catch:{ Exception -> 0x00d1 }
            goto L_0x0117
        L_0x00d1:
            com.google.android.recaptcha.internal.zzbg r11 = r11.zzb     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzp r0 = new com.google.android.recaptcha.internal.zzp     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzn r2 = com.google.android.recaptcha.internal.zzn.zzn     // Catch:{ Exception -> 0x004c }
            com.google.android.recaptcha.internal.zzl r3 = com.google.android.recaptcha.internal.zzl.zzaf     // Catch:{ Exception -> 0x004c }
            r0.<init>(r2, r3, r1)     // Catch:{ Exception -> 0x004c }
            r11.zzb(r13, r0, r1)     // Catch:{ Exception -> 0x004c }
            goto L_0x0117
        L_0x00e0:
            r12 = move-exception
            goto L_0x0111
        L_0x00e2:
            com.google.android.recaptcha.internal.zzp r12 = new com.google.android.recaptcha.internal.zzp     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzn r13 = com.google.android.recaptcha.internal.zzn.zze     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzl r0 = com.google.android.recaptcha.internal.zzl.zzab     // Catch:{ zzp -> 0x00e0 }
            r12.<init>(r13, r0, r1)     // Catch:{ zzp -> 0x00e0 }
            throw r12     // Catch:{ zzp -> 0x00e0 }
        L_0x00ec:
            com.google.android.recaptcha.internal.zzp r12 = new com.google.android.recaptcha.internal.zzp     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzn r13 = com.google.android.recaptcha.internal.zzn.zze     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzl r0 = new com.google.android.recaptcha.internal.zzl     // Catch:{ zzp -> 0x00e0 }
            int r2 = r2.getResponseCode()     // Catch:{ zzp -> 0x00e0 }
            r0.<init>(r2)     // Catch:{ zzp -> 0x00e0 }
            r12.<init>(r13, r0, r1)     // Catch:{ zzp -> 0x00e0 }
            throw r12     // Catch:{ zzp -> 0x00e0 }
        L_0x00fd:
            com.google.android.recaptcha.internal.zzp r12 = new com.google.android.recaptcha.internal.zzp     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzn r13 = com.google.android.recaptcha.internal.zzn.zze     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzl r0 = com.google.android.recaptcha.internal.zzl.zzaa     // Catch:{ zzp -> 0x00e0 }
            r12.<init>(r13, r0, r1)     // Catch:{ zzp -> 0x00e0 }
            throw r12     // Catch:{ zzp -> 0x00e0 }
        L_0x0107:
            com.google.android.recaptcha.internal.zzp r12 = new com.google.android.recaptcha.internal.zzp     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzn r13 = com.google.android.recaptcha.internal.zzn.zzc     // Catch:{ zzp -> 0x00e0 }
            com.google.android.recaptcha.internal.zzl r0 = com.google.android.recaptcha.internal.zzl.zzZ     // Catch:{ zzp -> 0x00e0 }
            r12.<init>(r13, r0, r1)     // Catch:{ zzp -> 0x00e0 }
            throw r12     // Catch:{ zzp -> 0x00e0 }
        L_0x0111:
            com.google.android.recaptcha.internal.zzbg r11 = r11.zzb     // Catch:{ Exception -> 0x004c }
            r11.zzb(r4, r12, r1)     // Catch:{ Exception -> 0x004c }
            throw r12     // Catch:{ Exception -> 0x004c }
        L_0x0117:
            java.lang.String r11 = r12.zzj()     // Catch:{ Exception -> 0x004c }
            java.lang.String r12 = "JAVASCRIPT_TAG"
            java.lang.String r11 = kotlin.text.StringsKt__StringsJVMKt.replace$default((java.lang.String) r11, (java.lang.String) r12, (java.lang.String) r7, false, 4, (java.lang.Object) null)     // Catch:{ Exception -> 0x004c }
            return r11
        L_0x0122:
            boolean r12 = r11 instanceof com.google.android.recaptcha.internal.zzp
            if (r12 == 0) goto L_0x0127
            throw r11
        L_0x0127:
            com.google.android.recaptcha.internal.zzp r11 = new com.google.android.recaptcha.internal.zzp
            com.google.android.recaptcha.internal.zzn r12 = com.google.android.recaptcha.internal.zzn.zzc
            com.google.android.recaptcha.internal.zzl r13 = com.google.android.recaptcha.internal.zzl.zzX
            r11.<init>(r12, r13, r1)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzbq.zzb(com.google.android.recaptcha.internal.zzoe, com.google.android.recaptcha.internal.zzbd):java.lang.String");
    }
}
