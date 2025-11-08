package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class zzby extends SuspendLambda implements Function2 {
    final /* synthetic */ Exception zza;
    final /* synthetic */ zzcj zzb;
    final /* synthetic */ zzca zzc;
    private /* synthetic */ Object zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzby(Exception exc, zzcj zzcj, zzca zzca, Continuation continuation) {
        super(2, continuation);
        this.zza = exc;
        this.zzb = zzcj;
        this.zzc = zzca;
    }

    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        zzby zzby = new zzby(this.zza, this.zzb, this.zzc, continuation);
        zzby.zzd = obj;
        return zzby;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzby) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        zzpg zzpg;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.zzd;
        Exception exc = this.zza;
        if (exc instanceof zzae) {
            zzpg = ((zzae) exc).zza();
            zzpg.zzd(this.zzb.zza());
        } else {
            zzcj zzcj = this.zzb;
            zzpg zzf = zzph.zzf();
            zzf.zzd(zzcj.zza());
            zzf.zzp(2);
            zzf.zze(2);
            zzpg = zzf;
        }
        zzph zzph = (zzph) zzpg.zzj();
        zzph.zzk();
        zzph.zzj();
        Reflection.getOrCreateKotlinClass(this.zza.getClass()).getSimpleName();
        this.zza.getMessage();
        zzcj zzcj2 = this.zzb;
        zzz zzb2 = zzcj2.zzb();
        zzz zzz = zzcj2.zza;
        if (zzz == null) {
            zzz = null;
        }
        zzno zza2 = zzbp.zza(zzb2, zzz);
        String zzd2 = this.zzb.zzd();
        if (zzd2.length() == 0) {
            zzd2 = "recaptcha.m.Main.rge";
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            zzca zzca = this.zzc;
            zzfy zzh = zzfy.zzh();
            byte[] zzd3 = zzph.zzd();
            String zzi = zzh.zzi(zzd3, 0, zzd3.length);
            zzfy zzh2 = zzfy.zzh();
            byte[] zzd4 = zza2.zzd();
            zzca.zzc.zze().zzb(zzd2, (String[]) Arrays.copyOf(new String[]{zzi, zzh2.zzi(zzd4, 0, zzd4.length)}, 2));
        }
        return Unit.INSTANCE;
    }
}
