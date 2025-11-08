package com.google.android.recaptcha.internal;

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

final class zze extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zza zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzoe zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zze(zza zza2, long j2, zzoe zzoe, Continuation continuation) {
        super(2, continuation);
        this.zzb = zza2;
        this.zzc = j2;
        this.zzd = zzoe;
    }

    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zze(this.zzb, this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zze) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
            zza zza2 = this.zzb;
            long j2 = this.zzc;
            zzoe zzoe = this.zzd;
            this.zza = 1;
            obj2 = zza2.zzb(j2, zzoe, this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Result.m8978boximpl(obj2);
    }
}
