package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class zzbx extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzcj zzb;
    final /* synthetic */ List zzc;
    final /* synthetic */ zzca zzd;
    private /* synthetic */ Object zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbx(zzcj zzcj, List list, zzca zzca, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzcj;
        this.zzc = list;
        this.zzd = zzca;
    }

    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        zzbx zzbx = new zzbx(this.zzb, this.zzc, this.zzd, continuation);
        zzbx.zze = obj;
        return zzbx;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzbx) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i3 == 0) {
            CoroutineScope coroutineScope = (CoroutineScope) this.zze;
            zzfh zzb2 = zzfh.zzb();
            while (true) {
                zzcj zzcj = this.zzb;
                if (zzcj.zza() < 0) {
                    break;
                }
                if (zzcj.zza() >= this.zzc.size() || !CoroutineScopeKt.isActive(coroutineScope)) {
                    break;
                }
                try {
                    this.zzd.zzi((zzpr) this.zzc.get(this.zzb.zza()), this.zzb);
                } catch (Exception e3) {
                    zzca zzca = this.zzd;
                    zzcj zzcj2 = this.zzb;
                    this.zza = 1;
                    if (zzca.zzh(e3, zzcj2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            zzb2.zzf();
            Boxing.boxLong(zzb2.zza(TimeUnit.MICROSECONDS));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
