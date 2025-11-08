package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaClient;
import com.google.android.recaptcha.RecaptchaTasksClient;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlinx.coroutines.CoroutineStart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzaw implements RecaptchaClient, RecaptchaTasksClient {
    @NotNull
    public static final zzan zza = new zzan((DefaultConstructorMarker) null);
    @NotNull
    private static final Regex zzb = new Regex("^[a-zA-Z0-9/_]{0,100}$");
    @NotNull
    private final Application zzc;
    @NotNull
    private final zzg zzd;
    @NotNull
    private final String zze;
    /* access modifiers changed from: private */
    @NotNull
    public final zzab zzf;
    /* access modifiers changed from: private */
    @NotNull
    public final zzoe zzg;
    @NotNull
    private final zzbd zzh;
    /* access modifiers changed from: private */
    @Nullable
    public final zzbg zzi;
    @NotNull
    private final zzq zzj;
    @NotNull
    private final zzbs zzk;
    @NotNull
    private final zzt zzl;

    public zzaw(@NotNull Application application, @NotNull zzg zzg2, @NotNull String str, @NotNull zzt zzt, @NotNull zzab zzab, @NotNull zzoe zzoe, @NotNull zzbd zzbd, @Nullable zzbg zzbg, @NotNull zzq zzq, @NotNull zzbs zzbs) {
        this.zzc = application;
        this.zzd = zzg2;
        this.zze = str;
        this.zzl = zzt;
        this.zzf = zzab;
        this.zzg = zzoe;
        this.zzh = zzbd;
        this.zzi = zzbg;
        this.zzj = zzq;
        this.zzk = zzbs;
    }

    public static final /* synthetic */ void zzi(zzaw zzaw, long j2, RecaptchaAction recaptchaAction, zzbd zzbd) {
        zzbb zza2 = zzbd.zza(zzne.EXECUTE_NATIVE);
        zzaw.zzi.zze.put(zza2, new zzbf(zza2, zzaw.zzi.zza, new zzac()));
        zzp zzp = !zzb.matches(recaptchaAction.getAction()) ? new zzp(zzn.zzi, zzl.zzq, (String) null) : null;
        if (j2 < 5000) {
            zzp = new zzp(zzn.zzc, zzl.zzT, (String) null);
        }
        if (zzp == null) {
            zzaw.zzi.zza(zza2);
        } else {
            zzaw.zzi.zzb(zza2, zzp, (String) null);
            throw zzp;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzj(long r6, java.lang.String r8, com.google.android.recaptcha.internal.zzbd r9, kotlin.coroutines.Continuation r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof com.google.android.recaptcha.internal.zzao
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.google.android.recaptcha.internal.zzao r0 = (com.google.android.recaptcha.internal.zzao) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzao r0 = new com.google.android.recaptcha.internal.zzao
            r0.<init>(r5, r10)
        L_0x0018:
            java.lang.Object r10 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            com.google.android.recaptcha.internal.zzbb r5 = r0.zze
            com.google.android.recaptcha.internal.zzaw r6 = r0.zzd
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x0030 }
            r9 = r5
            r5 = r6
            goto L_0x0059
        L_0x0030:
            r7 = move-exception
            r9 = r5
            r5 = r6
            goto L_0x0065
        L_0x0034:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r10)
            com.google.android.recaptcha.internal.zzne r10 = com.google.android.recaptcha.internal.zzne.COLLECT_SIGNALS
            com.google.android.recaptcha.internal.zzbb r9 = r9.zza(r10)
            com.google.android.recaptcha.internal.zzbg r10 = r5.zzi
            r2 = 2
            r10.zze.put(r9, new com.google.android.recaptcha.internal.zzbf(r9, r10.zza, new com.google.android.recaptcha.internal.zzac()))
            com.google.android.recaptcha.internal.zzg r10 = r5.zzd     // Catch:{ Exception -> 0x0061 }
            r0.zzd = r5     // Catch:{ Exception -> 0x0061 }
            r0.zze = r9     // Catch:{ Exception -> 0x0061 }
            r0.zzc = r3     // Catch:{ Exception -> 0x0061 }
            java.lang.Object r10 = r10.zza(r8, r6, r0)     // Catch:{ Exception -> 0x0061 }
            if (r10 == r1) goto L_0x0064
        L_0x0059:
            com.google.android.recaptcha.internal.zzog r10 = (com.google.android.recaptcha.internal.zzog) r10     // Catch:{ Exception -> 0x0061 }
            com.google.android.recaptcha.internal.zzbg r6 = r5.zzi     // Catch:{ Exception -> 0x0061 }
            r6.zza(r9)     // Catch:{ Exception -> 0x0061 }
            return r10
        L_0x0061:
            r6 = move-exception
            r7 = r6
            goto L_0x0065
        L_0x0064:
            return r1
        L_0x0065:
            boolean r6 = r7 instanceof com.google.android.recaptcha.internal.zzp
            if (r6 == 0) goto L_0x006c
            com.google.android.recaptcha.internal.zzp r7 = (com.google.android.recaptcha.internal.zzp) r7
            goto L_0x0075
        L_0x006c:
            com.google.android.recaptcha.internal.zzp r7 = new com.google.android.recaptcha.internal.zzp
            com.google.android.recaptcha.internal.zzn r6 = com.google.android.recaptcha.internal.zzn.zzc
            com.google.android.recaptcha.internal.zzl r8 = com.google.android.recaptcha.internal.zzl.zzan
            r7.<init>(r6, r8, r4)
        L_0x0075:
            com.google.android.recaptcha.internal.zzbg r5 = r5.zzi
            r5.zzb(r9, r7, r4)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzaw.zzj(long, java.lang.String, com.google.android.recaptcha.internal.zzbd, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzk(com.google.android.recaptcha.RecaptchaAction r16, long r17, kotlin.coroutines.Continuation r19) {
        /*
            r15 = this;
            r9 = r15
            r0 = r19
            boolean r1 = r0 instanceof com.google.android.recaptcha.internal.zzas
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.google.android.recaptcha.internal.zzas r1 = (com.google.android.recaptcha.internal.zzas) r1
            int r2 = r1.zzc
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0017
            int r2 = r2 - r3
            r1.zzc = r2
        L_0x0015:
            r0 = r1
            goto L_0x001d
        L_0x0017:
            com.google.android.recaptcha.internal.zzas r1 = new com.google.android.recaptcha.internal.zzas
            r1.<init>(r15, r0)
            goto L_0x0015
        L_0x001d:
            java.lang.Object r1 = r0.zza
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r11 = 1
            r12 = 0
            if (r2 == 0) goto L_0x003d
            if (r2 != r11) goto L_0x0035
            com.google.android.recaptcha.internal.zzbd r2 = r0.zze
            com.google.android.recaptcha.internal.zzaw r3 = r0.zzd
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ Exception -> 0x0033 }
            goto L_0x007a
        L_0x0033:
            r0 = move-exception
            goto L_0x0085
        L_0x0035:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r1)
            java.util.UUID r1 = java.util.UUID.randomUUID()
            java.lang.String r7 = r1.toString()
            com.google.android.recaptcha.internal.zzbd r1 = r9.zzh
            com.google.android.recaptcha.internal.zzbd r13 = r1.zzb()
            r13.zzc(r7)
            com.google.android.recaptcha.internal.zzbg r1 = r9.zzi
            com.google.android.recaptcha.internal.zzne r2 = com.google.android.recaptcha.internal.zzne.EXECUTE_TOTAL
            com.google.android.recaptcha.internal.zzbb r2 = r13.zza(r2)
            r3 = 2
            r1.zze.put(r2, new com.google.android.recaptcha.internal.zzbf(r2, r1.zza, new com.google.android.recaptcha.internal.zzac()))
            com.google.android.recaptcha.internal.zzat r14 = new com.google.android.recaptcha.internal.zzat     // Catch:{ Exception -> 0x0082 }
            r8 = 0
            r1 = r14
            r2 = r15
            r3 = r17
            r5 = r16
            r6 = r13
            r1.<init>(r2, r3, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0082 }
            r0.zzd = r9     // Catch:{ Exception -> 0x0082 }
            r0.zze = r13     // Catch:{ Exception -> 0x0082 }
            r0.zzc = r11     // Catch:{ Exception -> 0x0082 }
            r1 = r17
            java.lang.Object r1 = kotlinx.coroutines.TimeoutKt.withTimeout(r1, r14, r0)     // Catch:{ Exception -> 0x0082 }
            if (r1 == r10) goto L_0x0081
            r3 = r9
            r2 = r13
        L_0x007a:
            kotlin.Result r1 = (kotlin.Result) r1     // Catch:{ Exception -> 0x0033 }
            java.lang.Object r0 = r1.m8988unboximpl()     // Catch:{ Exception -> 0x0033 }
            goto L_0x00b7
        L_0x0081:
            return r10
        L_0x0082:
            r0 = move-exception
            r3 = r9
            r2 = r13
        L_0x0085:
            boolean r1 = r0 instanceof com.google.android.recaptcha.internal.zzp
            if (r1 == 0) goto L_0x008c
            com.google.android.recaptcha.internal.zzp r0 = (com.google.android.recaptcha.internal.zzp) r0
            goto L_0x009e
        L_0x008c:
            java.lang.Class r0 = r0.getClass()
            com.google.android.recaptcha.internal.zzp r1 = new com.google.android.recaptcha.internal.zzp
            com.google.android.recaptcha.internal.zzn r4 = com.google.android.recaptcha.internal.zzn.zzc
            com.google.android.recaptcha.internal.zzl r5 = com.google.android.recaptcha.internal.zzl.zzaj
            java.lang.String r0 = r0.getSimpleName()
            r1.<init>(r4, r5, r0)
            r0 = r1
        L_0x009e:
            com.google.android.recaptcha.internal.zzbg r1 = r3.zzi
            com.google.android.recaptcha.internal.zzne r3 = com.google.android.recaptcha.internal.zzne.EXECUTE_TOTAL
            com.google.android.recaptcha.internal.zzbb r2 = r2.zza(r3)
            r1.zzb(r2, r0, r12)
            com.google.android.recaptcha.RecaptchaException r0 = r0.zzc()
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r0)
        L_0x00b7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzaw.zzk(com.google.android.recaptcha.RecaptchaAction, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void zzl(zzol zzol, zzbd zzbd) {
        zzbb zza2 = zzbd.zza(zzne.POST_EXECUTE);
        this.zzi.zze.put(zza2, new zzbf(zza2, this.zzi.zza, new zzac()));
        try {
            List<zzon> zzj2 = zzol.zzj();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(zzj2, 10)), 16));
            for (zzon zzon : zzj2) {
                Pair pair = TuplesKt.to(zzon.zzg(), zzon.zzi());
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            this.zzj.zzb(linkedHashMap);
            this.zzi.zza(zza2);
        } catch (Exception e3) {
            zzp zzp = e3 instanceof zzp ? (zzp) e3 : new zzp(zzn.zzc, zzl.zzan, (String) null);
            this.zzi.zzb(zza2, zzp, (String) null);
            throw zzp;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: execute-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8230execute0E7RQCE(@org.jetbrains.annotations.NotNull com.google.android.recaptcha.RecaptchaAction r11, long r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.google.android.recaptcha.internal.zzap
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.google.android.recaptcha.internal.zzap r0 = (com.google.android.recaptcha.internal.zzap) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzap r0 = new com.google.android.recaptcha.internal.zzap
            r0.<init>(r10, r14)
        L_0x0018:
            java.lang.Object r14 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0051
        L_0x0029:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r14)
            com.google.android.recaptcha.internal.zzt r14 = r10.zzl
            kotlinx.coroutines.CoroutineScope r14 = r14.zzb()
            kotlin.coroutines.CoroutineContext r14 = r14.getCoroutineContext()
            com.google.android.recaptcha.internal.zzaq r2 = new com.google.android.recaptcha.internal.zzaq
            r9 = 0
            r4 = r2
            r5 = r10
            r6 = r11
            r7 = r12
            r4.<init>(r5, r6, r7, r9)
            r0.zzc = r3
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r14, r2, r0)
            if (r14 != r1) goto L_0x0051
            return r1
        L_0x0051:
            kotlin.Result r14 = (kotlin.Result) r14
            java.lang.Object r10 = r14.m8988unboximpl()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzaw.m8230execute0E7RQCE(com.google.android.recaptcha.RecaptchaAction, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: execute-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8231executegIAlus(@org.jetbrains.annotations.NotNull com.google.android.recaptcha.RecaptchaAction r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.google.android.recaptcha.internal.zzar
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.google.android.recaptcha.internal.zzar r0 = (com.google.android.recaptcha.internal.zzar) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzar r0 = new com.google.android.recaptcha.internal.zzar
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result r6 = (kotlin.Result) r6
            java.lang.Object r4 = r6.m8988unboximpl()
            goto L_0x0045
        L_0x002f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.zzc = r3
            r2 = 10000(0x2710, double:4.9407E-320)
            java.lang.Object r4 = r4.m8230execute0E7RQCE(r5, r2, r0)
            if (r4 != r1) goto L_0x0045
            return r1
        L_0x0045:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzaw.m8231executegIAlus(com.google.android.recaptcha.RecaptchaAction, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final Task<String> executeTask(@NotNull RecaptchaAction recaptchaAction) {
        return zzj.zza(BuildersKt__Builders_commonKt.async$default(this.zzl.zzb(), (CoroutineContext) null, (CoroutineStart) null, new zzau(this, recaptchaAction, 10000, (Continuation) null), 3, (Object) null));
    }

    @NotNull
    public final String zzg() {
        return this.zze;
    }

    @NotNull
    public final Task<String> executeTask(@NotNull RecaptchaAction recaptchaAction, long j2) {
        return zzj.zza(BuildersKt__Builders_commonKt.async$default(this.zzl.zzb(), (CoroutineContext) null, (CoroutineStart) null, new zzau(this, recaptchaAction, j2, (Continuation) null), 3, (Object) null));
    }
}
