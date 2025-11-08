package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaException;
import java.util.List;
import java.util.UUID;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzam {
    @NotNull
    public static final zzam zza = new zzam();
    @Nullable
    private static zzaw zzb;
    @NotNull
    private static final String zzc = UUID.randomUUID().toString();
    @NotNull
    private static final Mutex zzd = MutexKt.Mutex$default(false, 1, (Object) null);
    @NotNull
    private static final zzt zze = new zzt();
    @NotNull
    private static zzg zzf = new zzg((List) null, 1, (DefaultConstructorMarker) null);

    private zzam() {
    }

    @Nullable
    public static final Object zzc(@NotNull Application application, @NotNull String str, long j2, @Nullable zzbq zzbq, @NotNull Continuation continuation) throws TimeoutCancellationException, ApiException, RecaptchaException {
        return BuildersKt.withContext(zze.zzb().getCoroutineContext(), new zzah(application, str, j2, (zzbq) null, (Continuation) null), continuation);
    }

    @NotNull
    public static final Task zzd(@NotNull Application application, @NotNull String str, long j2) throws TimeoutCancellationException, ApiException, RecaptchaException {
        return zzj.zza(BuildersKt__Builders_commonKt.async$default(zze.zzb(), (CoroutineContext) null, (CoroutineStart) null, new zzak(application, str, j2, (Continuation) null), 3, (Object) null));
    }

    @NotNull
    public static final zzg zze() {
        return zzf;
    }

    public static final void zzf(@NotNull zzg zzg) {
        zzf = zzg;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:62:0x01ae */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00d5 A[Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x018d A[Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zza(@org.jetbrains.annotations.NotNull android.app.Application r23, @org.jetbrains.annotations.NotNull java.lang.String r24, long r25, @org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzab r27, @org.jetbrains.annotations.Nullable android.webkit.WebView r28, @org.jetbrains.annotations.Nullable com.google.android.recaptcha.internal.zzbq r29, @org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzt r30, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r31) throws kotlinx.coroutines.TimeoutCancellationException, com.google.android.gms.common.api.ApiException, com.google.android.recaptcha.RecaptchaException {
        /*
            r22 = this;
            r0 = r31
            java.lang.String r1 = "Only one site key can be used per runtime. The site key you provided "
            boolean r2 = r0 instanceof com.google.android.recaptcha.internal.zzai
            if (r2 == 0) goto L_0x0017
            r2 = r0
            com.google.android.recaptcha.internal.zzai r2 = (com.google.android.recaptcha.internal.zzai) r2
            int r3 = r2.zzg
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.zzg = r3
            goto L_0x001e
        L_0x0017:
            com.google.android.recaptcha.internal.zzai r2 = new com.google.android.recaptcha.internal.zzai
            r3 = r22
            r2.<init>(r3, r0)
        L_0x001e:
            java.lang.Object r0 = r2.zze
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.zzg
            r5 = 1
            r6 = 2
            r7 = 0
            if (r4 == 0) goto L_0x0067
            if (r4 == r5) goto L_0x004e
            if (r4 != r6) goto L_0x0046
            java.lang.Object r1 = r2.zzc
            com.google.android.recaptcha.internal.zzbg r1 = (com.google.android.recaptcha.internal.zzbg) r1
            java.lang.Object r3 = r2.zzb
            com.google.android.recaptcha.internal.zzbd r3 = (com.google.android.recaptcha.internal.zzbd) r3
            java.lang.Object r2 = r2.zza
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ RecaptchaException -> 0x0043, Exception -> 0x01ae }
            goto L_0x015a
        L_0x0040:
            r0 = move-exception
            goto L_0x01ba
        L_0x0043:
            r0 = move-exception
            goto L_0x01b9
        L_0x0046:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004e:
            long r4 = r2.zzd
            kotlinx.coroutines.sync.Mutex r8 = r2.zzh
            com.google.android.recaptcha.internal.zzt r9 = r2.zzi
            java.lang.Object r10 = r2.zzc
            com.google.android.recaptcha.internal.zzab r10 = (com.google.android.recaptcha.internal.zzab) r10
            java.lang.Object r11 = r2.zzb
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r2.zza
            android.app.Application r12 = (android.app.Application) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r15 = r8
            r14 = r9
            r9 = r12
            goto L_0x0090
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.sync.Mutex r0 = zzd
            r4 = r23
            r2.zza = r4
            r8 = r24
            r2.zzb = r8
            r9 = r27
            r2.zzc = r9
            r10 = r30
            r2.zzi = r10
            r2.zzh = r0
            r11 = r25
            r2.zzd = r11
            r2.zzg = r5
            java.lang.Object r5 = r0.lock(r7, r2)
            if (r5 == r3) goto L_0x01be
            r15 = r0
            r14 = r10
            r10 = r9
            r9 = r4
            r4 = r11
            r11 = r8
        L_0x0090:
            java.util.UUID r0 = java.util.UUID.randomUUID()     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            java.lang.String r0 = r0.toString()     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            java.lang.String r8 = zzc     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzbd r13 = new com.google.android.recaptcha.internal.zzbd     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r13.<init>(r8, r0, r7)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r13.zzc(r0)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzbg r0 = new com.google.android.recaptcha.internal.zzbg     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzbm r8 = new com.google.android.recaptcha.internal.zzbm     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzbo r12 = new com.google.android.recaptcha.internal.zzbo     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            java.lang.String r6 = r10.zzc()     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r12.<init>(r6)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            kotlinx.coroutines.CoroutineScope r6 = r14.zza()     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r8.<init>(r9, r12, r6)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r22 = r0
            r23 = r11
            r24 = r9
            r25 = r10
            r26 = r14
            r27 = r8
            r22.<init>(r23, r24, r25, r26, r27)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzne r6 = com.google.android.recaptcha.internal.zzne.INIT_TOTAL     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzbb r8 = r13.zza(r6)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r12 = 2
            r0.zze.put(r8, new com.google.android.recaptcha.internal.zzbf(r8, r0.zza, new com.google.android.recaptcha.internal.zzac()))     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r16 = 5000(0x1388, double:2.4703E-320)
            int r8 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r8 < 0) goto L_0x018d
            java.lang.String r8 = "android.permission.INTERNET"
            int r8 = androidx.core.content.ContextCompat.checkSelfPermission(r9, r8)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            if (r8 != 0) goto L_0x0171
            com.google.android.recaptcha.internal.zzbq r12 = new com.google.android.recaptcha.internal.zzbq     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzy r8 = new com.google.android.recaptcha.internal.zzy     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r8.<init>(r9)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r12.<init>(r8, r0)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzaw r8 = zzb     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            if (r8 == 0) goto L_0x012c
            java.lang.String r2 = r8.zzg()     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r11)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            if (r2 == 0) goto L_0x010c
            com.google.android.recaptcha.internal.zzbb r1 = r13.zza(r6)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r0.zza(r1)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            goto L_0x0169
        L_0x00fe:
            r0 = move-exception
            r20 = r15
            goto L_0x01a9
        L_0x0103:
            r20 = r15
            goto L_0x01ac
        L_0x0107:
            r0 = move-exception
            r20 = r15
            goto L_0x01b7
        L_0x010c:
            com.google.android.recaptcha.RecaptchaException r0 = new com.google.android.recaptcha.RecaptchaException     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.RecaptchaErrorCode r2 = com.google.android.recaptcha.RecaptchaErrorCode.INVALID_SITEKEY     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            java.lang.String r3 = r8.zzg()     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r4.<init>(r1)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r4.append(r11)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            java.lang.String r1 = " is different than "
            r4.append(r1)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r4.append(r3)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            java.lang.String r1 = r4.toString()     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r0.<init>(r2, r1)     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            throw r0     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
        L_0x012c:
            r2.zza = r15     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r2.zzb = r13     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r2.zzc = r0     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r2.zzi = r7     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r2.zzh = r7     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r1 = 2
            r2.zzg = r1     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            com.google.android.recaptcha.internal.zzaj r1 = new com.google.android.recaptcha.internal.zzaj     // Catch:{ RecaptchaException -> 0x0107, Exception -> 0x0103, all -> 0x00fe }
            r6 = 0
            r19 = 0
            r8 = r1
            r22 = r13
            r20 = r15
            r15 = r6
            r16 = r0
            r17 = r4
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r19)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            java.lang.Object r1 = kotlinx.coroutines.TimeoutKt.withTimeout(r4, r1, r2)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            if (r1 == r3) goto L_0x01be
            r3 = r22
            r2 = r20
            r21 = r1
            r1 = r0
            r0 = r21
        L_0x015a:
            r8 = r0
            com.google.android.recaptcha.internal.zzaw r8 = (com.google.android.recaptcha.internal.zzaw) r8     // Catch:{ RecaptchaException -> 0x0043, Exception -> 0x01ae }
            zzb = r8     // Catch:{ RecaptchaException -> 0x0043, Exception -> 0x01ae }
            com.google.android.recaptcha.internal.zzne r0 = com.google.android.recaptcha.internal.zzne.INIT_TOTAL     // Catch:{ RecaptchaException -> 0x0043, Exception -> 0x01ae }
            com.google.android.recaptcha.internal.zzbb r0 = r3.zza(r0)     // Catch:{ RecaptchaException -> 0x0043, Exception -> 0x01ae }
            r1.zza(r0)     // Catch:{ RecaptchaException -> 0x0043, Exception -> 0x01ae }
            r15 = r2
        L_0x0169:
            r15.unlock(r7)
            return r8
        L_0x016d:
            r0 = move-exception
            goto L_0x01a9
        L_0x016f:
            r0 = move-exception
            goto L_0x01b7
        L_0x0171:
            r1 = r13
            r20 = r15
            com.google.android.recaptcha.internal.zzbb r1 = r1.zza(r6)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.internal.zzp r2 = new com.google.android.recaptcha.internal.zzp     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.internal.zzn r3 = com.google.android.recaptcha.internal.zzn.zze     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.internal.zzl r4 = com.google.android.recaptcha.internal.zzl.zzv     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            r2.<init>(r3, r4, r7)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            r0.zzb(r1, r2, r7)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.RecaptchaException r0 = new com.google.android.recaptcha.RecaptchaException     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.RecaptchaErrorCode r1 = com.google.android.recaptcha.RecaptchaErrorCode.NETWORK_ERROR     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            r2 = 2
            r0.<init>(r1, r7, r2, r7)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            throw r0     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
        L_0x018d:
            r1 = r13
            r20 = r15
            com.google.android.recaptcha.internal.zzp r2 = new com.google.android.recaptcha.internal.zzp     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.internal.zzn r3 = com.google.android.recaptcha.internal.zzn.zzm     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.internal.zzl r4 = com.google.android.recaptcha.internal.zzl.zzT     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            r2.<init>(r3, r4, r7)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.internal.zzbb r1 = r1.zza(r6)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            r0.zzb(r1, r2, r7)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.RecaptchaException r0 = new com.google.android.recaptcha.RecaptchaException     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            com.google.android.recaptcha.RecaptchaErrorCode r1 = com.google.android.recaptcha.RecaptchaErrorCode.INVALID_TIMEOUT     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            r2 = 2
            r0.<init>(r1, r7, r2, r7)     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
            throw r0     // Catch:{ RecaptchaException -> 0x016f, Exception -> 0x01ac, all -> 0x016d }
        L_0x01a9:
            r2 = r20
            goto L_0x01ba
        L_0x01ac:
            r2 = r20
        L_0x01ae:
            com.google.android.recaptcha.RecaptchaException r0 = new com.google.android.recaptcha.RecaptchaException     // Catch:{ all -> 0x0040 }
            com.google.android.recaptcha.RecaptchaErrorCode r1 = com.google.android.recaptcha.RecaptchaErrorCode.INTERNAL_ERROR     // Catch:{ all -> 0x0040 }
            r3 = 2
            r0.<init>(r1, r7, r3, r7)     // Catch:{ all -> 0x0040 }
            throw r0     // Catch:{ all -> 0x0040 }
        L_0x01b7:
            r2 = r20
        L_0x01b9:
            throw r0     // Catch:{ all -> 0x0040 }
        L_0x01ba:
            r2.unlock(r7)
            throw r0
        L_0x01be:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzam.zza(android.app.Application, java.lang.String, long, com.google.android.recaptcha.internal.zzab, android.webkit.WebView, com.google.android.recaptcha.internal.zzbq, com.google.android.recaptcha.internal.zzt, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
