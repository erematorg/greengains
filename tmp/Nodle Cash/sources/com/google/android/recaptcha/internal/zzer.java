package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class zzer extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzez zzb;
    int zzc;
    zzez zzd;
    String zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzer(zzez zzez, Continuation continuation) {
        super(continuation);
        this.zzb = zzez;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object zza2 = this.zzb.zza((String) null, 0, this);
        return zza2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? zza2 : Result.m8978boximpl(zza2);
    }
}
