package com.google.android.recaptcha.internal;

import java.util.Timer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class zzbk extends SuspendLambda implements Function2 {
    final /* synthetic */ zzbm zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbk(zzbm zzbm, Continuation continuation) {
        super(2, continuation);
        this.zza = zzbm;
    }

    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzbk(this.zza, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzbk) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzbm zzbm = this.zza;
        synchronized (zzbh.class) {
            try {
                zzaz zzb = zzbm.zze;
                if (zzb != null && zzb.zzb() == 0) {
                    Timer zzc = zzbm.zzb;
                    if (zzc != null) {
                        zzc.cancel();
                    }
                    zzbm.zzb = null;
                }
                zzbm.zzg();
            } catch (Throwable th) {
                throw th;
            }
        }
        return Unit.INSTANCE;
    }
}
