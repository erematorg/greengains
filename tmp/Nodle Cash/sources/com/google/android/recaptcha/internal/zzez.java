package com.google.android.recaptcha.internal;

import android.content.Context;
import android.webkit.WebView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutCancellationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzez implements zza {
    @NotNull
    public static final zzep zza = new zzep((DefaultConstructorMarker) null);
    public CompletableDeferred zzb;
    public zzbu zzc;
    @NotNull
    private final WebView zzd;
    @NotNull
    private final String zze;
    @NotNull
    private final Context zzf;
    @NotNull
    private final zzab zzg;
    @NotNull
    private final zzbd zzh;
    /* access modifiers changed from: private */
    @Nullable
    public final zzbg zzi;
    @NotNull
    private final zzbq zzj;
    /* access modifiers changed from: private */
    @NotNull
    public final Map zzk = zzfa.zza();
    /* access modifiers changed from: private */
    @NotNull
    public final Map zzl;
    @NotNull
    private final Map zzm;
    /* access modifiers changed from: private */
    @NotNull
    public final zzfh zzn;
    @NotNull
    private final zzeq zzo;
    /* access modifiers changed from: private */
    @NotNull
    public final zzbd zzp;
    /* access modifiers changed from: private */
    @NotNull
    public final zzt zzq;

    public zzez(@NotNull WebView webView, @NotNull String str, @NotNull Context context, @NotNull zzab zzab, @NotNull zzbd zzbd, @NotNull zzt zzt, @Nullable zzbg zzbg, @NotNull zzbq zzbq) {
        this.zzd = webView;
        this.zze = str;
        this.zzf = context;
        this.zzg = zzab;
        this.zzh = zzbd;
        this.zzq = zzt;
        this.zzi = zzbg;
        this.zzj = zzbq;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zzl = linkedHashMap;
        this.zzm = linkedHashMap;
        this.zzn = zzfh.zzc();
        zzeq zzeq = new zzeq(this);
        this.zzo = zzeq;
        zzbd zzb2 = zzbd.zzb();
        zzb2.zzc(zzbd.zzd());
        this.zzp = zzb2;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(zzeq, "RN");
        webView.setWebViewClient(new zzeu(this));
    }

    public static final /* synthetic */ void zzl(zzez zzez, zzoe zzoe) {
        zzez.zzd.clearCache(true);
        zzbb zza2 = zzez.zzp.zza(zzne.INIT_NETWORK);
        zzez.zzi.zze.put(zza2, new zzbf(zza2, zzez.zzi.zza, new zzac()));
        Job unused = BuildersKt__Builders_commonKt.launch$default(zzez.zzq.zza(), (CoroutineContext) null, (CoroutineStart) null, new zzey(zzez, zzoe, zza2, (Continuation) null), 3, (Object) null);
    }

    public static final /* synthetic */ void zzm(zzez zzez, String str) {
        zzbb zza2 = zzez.zzp.zza(zzne.LOAD_WEBVIEW);
        try {
            zzez.zzi.zze.put(zza2, new zzbf(zza2, zzez.zzi.zza, new zzac()));
            zzez.zzd.loadDataWithBaseURL(zzez.zzg.zza(), str, "text/html", "utf-8", (String) null);
        } catch (Exception unused) {
            zzp zzp2 = new zzp(zzn.zzc, zzl.zzag, (String) null);
            zzez.zzi.zzb(zza2, zzp2, (String) null);
            zzez.zzk().completeExceptionally(zzp2);
        }
    }

    private final zzp zzp(Exception exc, zzp zzp2) {
        return exc instanceof TimeoutCancellationException ? new zzp(zzn.zzc, zzl.zzj, (String) null) : exc instanceof zzp ? (zzp) exc : zzp2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zza(@org.jetbrains.annotations.NotNull java.lang.String r5, long r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.google.android.recaptcha.internal.zzer
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.google.android.recaptcha.internal.zzer r0 = (com.google.android.recaptcha.internal.zzer) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzer r0 = new com.google.android.recaptcha.internal.zzer
            r0.<init>(r4, r8)
        L_0x0018:
            java.lang.Object r8 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.String r5 = r0.zze
            com.google.android.recaptcha.internal.zzez r4 = r0.zzd
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x002d }
            goto L_0x004d
        L_0x002d:
            r6 = move-exception
            goto L_0x0054
        L_0x002f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r8)
            com.google.android.recaptcha.internal.zzet r8 = new com.google.android.recaptcha.internal.zzet     // Catch:{ Exception -> 0x002d }
            r2 = 0
            r8.<init>(r5, r4, r2)     // Catch:{ Exception -> 0x002d }
            r0.zzd = r4     // Catch:{ Exception -> 0x002d }
            r0.zze = r5     // Catch:{ Exception -> 0x002d }
            r0.zzc = r3     // Catch:{ Exception -> 0x002d }
            java.lang.Object r8 = kotlinx.coroutines.TimeoutKt.withTimeout(r6, r8, r0)     // Catch:{ Exception -> 0x002d }
            if (r8 != r1) goto L_0x004d
            return r1
        L_0x004d:
            com.google.android.recaptcha.internal.zzog r8 = (com.google.android.recaptcha.internal.zzog) r8     // Catch:{ Exception -> 0x002d }
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r8)     // Catch:{ Exception -> 0x002d }
            goto L_0x0084
        L_0x0054:
            java.lang.Class r7 = r6.getClass()
            com.google.android.recaptcha.internal.zzp r8 = new com.google.android.recaptcha.internal.zzp
            com.google.android.recaptcha.internal.zzn r0 = com.google.android.recaptcha.internal.zzn.zzc
            com.google.android.recaptcha.internal.zzl r1 = com.google.android.recaptcha.internal.zzl.zzai
            java.lang.String r7 = r7.getSimpleName()
            r8.<init>(r0, r1, r7)
            com.google.android.recaptcha.internal.zzp r6 = r4.zzp(r6, r8)
            java.util.Map r4 = r4.zzl
            java.lang.Object r4 = r4.remove(r5)
            kotlinx.coroutines.CompletableDeferred r4 = (kotlinx.coroutines.CompletableDeferred) r4
            if (r4 == 0) goto L_0x007a
            boolean r4 = r4.completeExceptionally(r6)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
        L_0x007a:
            kotlin.Result$Companion r4 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)
        L_0x0084:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzez.zza(java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b2, code lost:
        if (r1.longValue() > (r7 - 2000)) goto L_0x00b4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00dd A[LOOP:0: B:33:0x00d7->B:35:0x00dd, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzb(long r7, @org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzoe r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.google.android.recaptcha.internal.zzev
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.google.android.recaptcha.internal.zzev r0 = (com.google.android.recaptcha.internal.zzev) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzev r0 = new com.google.android.recaptcha.internal.zzev
            r0.<init>(r6, r10)
        L_0x0018:
            java.lang.Object r10 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            long r7 = r0.zza
            com.google.android.recaptcha.internal.zzez r6 = r0.zze
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x002e }
            goto L_0x007a
        L_0x002e:
            r9 = move-exception
            goto L_0x0083
        L_0x0030:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r10)
            com.google.android.recaptcha.internal.zzbg r10 = r6.zzi     // Catch:{ Exception -> 0x002e }
            com.google.android.recaptcha.internal.zzbd r2 = r6.zzp     // Catch:{ Exception -> 0x002e }
            com.google.android.recaptcha.internal.zzne r5 = com.google.android.recaptcha.internal.zzne.INIT_NATIVE     // Catch:{ Exception -> 0x002e }
            com.google.android.recaptcha.internal.zzbb r2 = r2.zza(r5)     // Catch:{ Exception -> 0x002e }
            r5 = 2
            r10.zze.put(r2, new com.google.android.recaptcha.internal.zzbf(r2, r10.zza, new com.google.android.recaptcha.internal.zzac()))     // Catch:{ Exception -> 0x002e }
            com.google.android.recaptcha.internal.zzag r10 = new com.google.android.recaptcha.internal.zzag     // Catch:{ Exception -> 0x002e }
            com.google.android.recaptcha.internal.zzgw r2 = r9.zzf()     // Catch:{ Exception -> 0x002e }
            r10.<init>(r2)     // Catch:{ Exception -> 0x002e }
            com.google.android.recaptcha.internal.zzca r10 = r6.zzo(r9, r10)     // Catch:{ Exception -> 0x002e }
            r6.zzc = r10     // Catch:{ Exception -> 0x002e }
            kotlinx.coroutines.CompletableDeferred r10 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r4, r3, r4)     // Catch:{ Exception -> 0x002e }
            r6.zzb = r10     // Catch:{ Exception -> 0x002e }
            kotlinx.coroutines.CompletableDeferred r10 = r6.zzk()     // Catch:{ Exception -> 0x002e }
            int r10 = r10.hashCode()     // Catch:{ Exception -> 0x002e }
            kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)     // Catch:{ Exception -> 0x002e }
            com.google.android.recaptcha.internal.zzew r10 = new com.google.android.recaptcha.internal.zzew     // Catch:{ Exception -> 0x002e }
            r10.<init>(r6, r9, r4)     // Catch:{ Exception -> 0x002e }
            r0.zze = r6     // Catch:{ Exception -> 0x002e }
            r0.zza = r7     // Catch:{ Exception -> 0x002e }
            r0.zzd = r3     // Catch:{ Exception -> 0x002e }
            java.lang.Object r10 = kotlinx.coroutines.TimeoutKt.withTimeout(r7, r10, r0)     // Catch:{ Exception -> 0x002e }
            if (r10 == r1) goto L_0x0082
        L_0x007a:
            kotlin.Result r10 = (kotlin.Result) r10     // Catch:{ Exception -> 0x002e }
            java.lang.Object r6 = r10.m8988unboximpl()     // Catch:{ Exception -> 0x002e }
            goto L_0x00fd
        L_0x0082:
            return r1
        L_0x0083:
            r9.getMessage()
            boolean r10 = r9 instanceof kotlinx.coroutines.TimeoutCancellationException
            if (r10 == 0) goto L_0x0097
            com.google.android.recaptcha.internal.zzne r0 = com.google.android.recaptcha.internal.zzne.INIT_TOTAL
            com.google.android.recaptcha.internal.zzne r1 = com.google.android.recaptcha.internal.zzne.LOAD_WEBVIEW
            com.google.android.recaptcha.internal.zzne[] r0 = new com.google.android.recaptcha.internal.zzne[]{r0, r1}
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
            goto L_0x009d
        L_0x0097:
            com.google.android.recaptcha.internal.zzne r0 = com.google.android.recaptcha.internal.zzne.INIT_TOTAL
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
        L_0x009d:
            com.google.android.recaptcha.internal.zzeq r1 = r6.zzo
            java.lang.Long r1 = r1.zza()
            if (r10 != 0) goto L_0x00a6
            goto L_0x00be
        L_0x00a6:
            if (r1 != 0) goto L_0x00a9
            goto L_0x00b4
        L_0x00a9:
            r2 = -2000(0xfffffffffffff830, double:NaN)
            long r7 = r7 + r2
            long r1 = r1.longValue()
            int r7 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x00be
        L_0x00b4:
            com.google.android.recaptcha.internal.zzp r7 = new com.google.android.recaptcha.internal.zzp
            com.google.android.recaptcha.internal.zzn r8 = com.google.android.recaptcha.internal.zzn.zze
            com.google.android.recaptcha.internal.zzl r9 = com.google.android.recaptcha.internal.zzl.zzS
            r7.<init>(r8, r9, r4)
            goto L_0x00d3
        L_0x00be:
            java.lang.Class r7 = r9.getClass()
            com.google.android.recaptcha.internal.zzp r8 = new com.google.android.recaptcha.internal.zzp
            com.google.android.recaptcha.internal.zzn r10 = com.google.android.recaptcha.internal.zzn.zzc
            com.google.android.recaptcha.internal.zzl r1 = com.google.android.recaptcha.internal.zzl.zzah
            java.lang.String r7 = r7.getSimpleName()
            r8.<init>(r10, r1, r7)
            com.google.android.recaptcha.internal.zzp r7 = r6.zzp(r9, r8)
        L_0x00d3:
            java.util.Iterator r8 = r0.iterator()
        L_0x00d7:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00ef
            java.lang.Object r9 = r8.next()
            com.google.android.recaptcha.internal.zzne r9 = (com.google.android.recaptcha.internal.zzne) r9
            com.google.android.recaptcha.internal.zzbg r10 = r6.zzi
            com.google.android.recaptcha.internal.zzbd r0 = r6.zzp
            com.google.android.recaptcha.internal.zzbb r9 = r0.zza(r9)
            r10.zzb(r9, r7, r4)
            goto L_0x00d7
        L_0x00ef:
            kotlin.Result$Companion r6 = kotlin.Result.Companion
            com.google.android.recaptcha.RecaptchaException r6 = r7.zzc()
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m8979constructorimpl(r6)
        L_0x00fd:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzez.zzb(long, com.google.android.recaptcha.internal.zzoe, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final WebView zzc() {
        return this.zzd;
    }

    @NotNull
    public final zzbq zzf() {
        return this.zzj;
    }

    @NotNull
    public final zzeq zzg() {
        return this.zzo;
    }

    @NotNull
    public final CompletableDeferred zzk() {
        CompletableDeferred completableDeferred = this.zzb;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        return null;
    }

    @NotNull
    public final zzca zzo(@NotNull zzoe zzoe, @NotNull zzag zzag) {
        zzcd zzcd = new zzcd(this.zzd, this.zzq.zzb());
        zzef zzef = new zzef();
        zzef.zzb(CollectionsKt___CollectionsKt.toLongArray(zzoe.zzK()));
        zzcl zzcl = new zzcl(zzcd, zzag, new zzaa());
        zzeg zzeg = new zzeg(zzef, new zzed());
        zzcl.zzf(3, this.zzf);
        zzcl.zzf(5, zzen.class.getMethod("cs", new Class[]{new Object[0].getClass()}));
        zzcl.zzf(6, new zzeh(this.zzf));
        zzcl.zzf(7, new zzej());
        zzcl.zzf(8, new zzeo(this.zzf));
        zzcl.zzf(9, new zzek(this.zzf));
        zzcl.zzf(10, new zzei(this.zzf));
        return new zzca(this.zzq.zzc(), zzcl, zzeg, zzbt.zza());
    }
}
