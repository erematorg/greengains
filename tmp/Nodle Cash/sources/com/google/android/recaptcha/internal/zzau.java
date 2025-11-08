package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class zzau extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzaw zzb;
    final /* synthetic */ RecaptchaAction zzc;
    final /* synthetic */ long zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzau(zzaw zzaw, RecaptchaAction recaptchaAction, long j2, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzaw;
        this.zzc = recaptchaAction;
        this.zzd = j2;
    }

    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzau(this.zzb, this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzau) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i3 != 0) {
            obj2 = ((Result) obj).m8988unboximpl();
        } else {
            zzaw zzaw = this.zzb;
            RecaptchaAction recaptchaAction = this.zzc;
            long j2 = this.zzd;
            this.zza = 1;
            obj2 = zzaw.zzk(recaptchaAction, j2, this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        ResultKt.throwOnFailure(obj2);
        return obj2;
    }
}
