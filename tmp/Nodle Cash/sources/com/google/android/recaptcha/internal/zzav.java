package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class zzav extends SuspendLambda implements Function2 {
    final /* synthetic */ zzbd zza;
    final /* synthetic */ zzaw zzb;
    final /* synthetic */ RecaptchaAction zzc;
    final /* synthetic */ zzog zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzav(zzbd zzbd, zzaw zzaw, RecaptchaAction recaptchaAction, zzog zzog, Continuation continuation) {
        super(2, continuation);
        this.zza = zzbd;
        this.zzb = zzaw;
        this.zzc = recaptchaAction;
        this.zzd = zzog;
    }

    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzav(this.zza, this.zzb, this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzav) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00f8, code lost:
        throw new com.google.android.recaptcha.internal.zzp(com.google.android.recaptcha.internal.zzn.zzc, com.google.android.recaptcha.internal.zzl.zzR, (java.lang.String) null);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x00ef */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            kotlin.ResultKt.throwOnFailure(r6)
            com.google.android.recaptcha.internal.zzbd r6 = r5.zza
            com.google.android.recaptcha.internal.zzne r0 = com.google.android.recaptcha.internal.zzne.FETCH_TOKEN
            com.google.android.recaptcha.internal.zzbb r6 = r6.zza(r0)
            com.google.android.recaptcha.internal.zzaw r0 = r5.zzb
            com.google.android.recaptcha.internal.zzbg r0 = r0.zzi
            r1 = 2
            r2 = 0
            r0.zze.put(r6, new com.google.android.recaptcha.internal.zzbf(r6, r0.zza, new com.google.android.recaptcha.internal.zzac()))
            com.google.android.recaptcha.internal.zzob r0 = com.google.android.recaptcha.internal.zzoc.zzf()
            com.google.android.recaptcha.internal.zzaw r1 = r5.zzb
            java.lang.String r3 = r1.zzg()
            r0.zzr(r3)
            com.google.android.recaptcha.RecaptchaAction r3 = r5.zzc
            java.lang.String r3 = r3.getAction()
            r0.zzd(r3)
            com.google.android.recaptcha.internal.zzoe r3 = r1.zzg
            java.lang.String r3 = r3.zzI()
            r0.zzq(r3)
            com.google.android.recaptcha.internal.zzoe r1 = r1.zzg
            java.lang.String r1 = r1.zzH()
            r0.zzp(r1)
            com.google.android.recaptcha.internal.zzog r1 = r5.zzd
            java.lang.String r3 = r1.zzH()
            r0.zzt(r3)
            java.lang.String r3 = r1.zzj()
            r0.zze(r3)
            java.lang.String r1 = r1.zzI()
            r0.zzs(r1)
            com.google.android.recaptcha.internal.zzit r0 = r0.zzj()
            com.google.android.recaptcha.internal.zzoc r0 = (com.google.android.recaptcha.internal.zzoc) r0
            com.google.android.recaptcha.internal.zzaw r1 = r5.zzb     // Catch:{ Exception -> 0x010b }
            com.google.android.recaptcha.internal.zzab r1 = r1.zzf     // Catch:{ Exception -> 0x010b }
            java.net.URL r3 = new java.net.URL     // Catch:{ Exception -> 0x010b }
            java.lang.String r1 = r1.zzd()     // Catch:{ Exception -> 0x010b }
            r3.<init>(r1)     // Catch:{ Exception -> 0x010b }
            java.net.URLConnection r1 = r3.openConnection()     // Catch:{ Exception -> 0x010b }
            java.lang.String r3 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r3)     // Catch:{ Exception -> 0x010b }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x010b }
            java.lang.String r3 = "POST"
            r1.setRequestMethod(r3)     // Catch:{ Exception -> 0x010b }
            r3 = 1
            r1.setDoOutput(r3)     // Catch:{ Exception -> 0x010b }
            java.lang.String r3 = "application/x-protobuffer"
            java.lang.String r4 = "Content-Type"
            r1.setRequestProperty(r4, r3)     // Catch:{ Exception -> 0x010b }
            r1.connect()     // Catch:{ Exception -> 0x00f9 }
            com.google.android.recaptcha.internal.zzoi r3 = com.google.android.recaptcha.internal.zzoj.zzf()     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = r0.zzi()     // Catch:{ Exception -> 0x00f9 }
            r3.zzd(r4)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = r0.zzk()     // Catch:{ Exception -> 0x00f9 }
            r3.zzq(r4)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = r0.zzI()     // Catch:{ Exception -> 0x00f9 }
            r3.zzt(r4)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = r0.zzH()     // Catch:{ Exception -> 0x00f9 }
            r3.zzp(r4)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = r0.zzJ()     // Catch:{ Exception -> 0x00f9 }
            r3.zzr(r4)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = r0.zzK()     // Catch:{ Exception -> 0x00f9 }
            r3.zzs(r4)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r0 = r0.zzj()     // Catch:{ Exception -> 0x00f9 }
            r3.zze(r0)     // Catch:{ Exception -> 0x00f9 }
            com.google.android.recaptcha.internal.zzit r0 = r3.zzj()     // Catch:{ Exception -> 0x00f9 }
            com.google.android.recaptcha.internal.zzoj r0 = (com.google.android.recaptcha.internal.zzoj) r0     // Catch:{ Exception -> 0x00f9 }
            byte[] r0 = r0.zzd()     // Catch:{ Exception -> 0x00f9 }
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch:{ Exception -> 0x00f9 }
            r3.write(r0)     // Catch:{ Exception -> 0x00f9 }
            int r0 = r1.getResponseCode()     // Catch:{ Exception -> 0x00f9 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r0 != r3) goto L_0x00fb
            java.io.InputStream r0 = r1.getInputStream()     // Catch:{ Exception -> 0x00f9 }
            com.google.android.recaptcha.internal.zzol r0 = com.google.android.recaptcha.internal.zzol.zzg(r0)     // Catch:{ Exception -> 0x00ef }
            com.google.android.recaptcha.internal.zzaw r5 = r5.zzb
            com.google.android.recaptcha.internal.zzbg r5 = r5.zzi
            r5.zza(r6)
            return r0
        L_0x00ef:
            com.google.android.recaptcha.internal.zzp r0 = new com.google.android.recaptcha.internal.zzp     // Catch:{ Exception -> 0x00f9 }
            com.google.android.recaptcha.internal.zzn r1 = com.google.android.recaptcha.internal.zzn.zzc     // Catch:{ Exception -> 0x00f9 }
            com.google.android.recaptcha.internal.zzl r3 = com.google.android.recaptcha.internal.zzl.zzR     // Catch:{ Exception -> 0x00f9 }
            r0.<init>(r1, r3, r2)     // Catch:{ Exception -> 0x00f9 }
            throw r0     // Catch:{ Exception -> 0x00f9 }
        L_0x00f9:
            r0 = move-exception
            goto L_0x0104
        L_0x00fb:
            int r0 = r1.getResponseCode()     // Catch:{ Exception -> 0x00f9 }
            com.google.android.recaptcha.internal.zzp r0 = com.google.android.recaptcha.internal.zzbr.zza(r0)     // Catch:{ Exception -> 0x00f9 }
            throw r0     // Catch:{ Exception -> 0x00f9 }
        L_0x0104:
            boolean r1 = r0 instanceof com.google.android.recaptcha.internal.zzp     // Catch:{ Exception -> 0x010b }
            if (r1 == 0) goto L_0x010d
            com.google.android.recaptcha.internal.zzp r0 = (com.google.android.recaptcha.internal.zzp) r0     // Catch:{ Exception -> 0x010b }
            goto L_0x0116
        L_0x010b:
            r0 = move-exception
            goto L_0x0117
        L_0x010d:
            com.google.android.recaptcha.internal.zzp r0 = new com.google.android.recaptcha.internal.zzp     // Catch:{ Exception -> 0x010b }
            com.google.android.recaptcha.internal.zzn r1 = com.google.android.recaptcha.internal.zzn.zze     // Catch:{ Exception -> 0x010b }
            com.google.android.recaptcha.internal.zzl r3 = com.google.android.recaptcha.internal.zzl.zzQ     // Catch:{ Exception -> 0x010b }
            r0.<init>(r1, r3, r2)     // Catch:{ Exception -> 0x010b }
        L_0x0116:
            throw r0     // Catch:{ Exception -> 0x010b }
        L_0x0117:
            boolean r1 = r0 instanceof com.google.android.recaptcha.internal.zzp
            if (r1 == 0) goto L_0x011e
            com.google.android.recaptcha.internal.zzp r0 = (com.google.android.recaptcha.internal.zzp) r0
            goto L_0x0127
        L_0x011e:
            com.google.android.recaptcha.internal.zzp r0 = new com.google.android.recaptcha.internal.zzp
            com.google.android.recaptcha.internal.zzn r1 = com.google.android.recaptcha.internal.zzn.zzc
            com.google.android.recaptcha.internal.zzl r3 = com.google.android.recaptcha.internal.zzl.zzam
            r0.<init>(r1, r3, r2)
        L_0x0127:
            com.google.android.recaptcha.internal.zzaw r5 = r5.zzb
            com.google.android.recaptcha.internal.zzbg r5 = r5.zzi
            r5.zzb(r6, r0, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzav.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
