package com.google.android.recaptcha.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;

public final class zzca implements zzbu {
    @NotNull
    public static final zzbv zza = new zzbv((DefaultConstructorMarker) null);
    @NotNull
    private final CoroutineScope zzb;
    @NotNull
    private final zzcl zzc;
    /* access modifiers changed from: private */
    @NotNull
    public final zzee zzd;
    @NotNull
    private final Map zze;
    @NotNull
    private final Map zzf;

    public zzca(@NotNull CoroutineScope coroutineScope, @NotNull zzcl zzcl, @NotNull zzee zzee, @NotNull Map map) {
        this.zzb = coroutineScope;
        this.zzc = zzcl;
        this.zzd = zzee;
        this.zze = map;
        this.zzf = zzcl.zzb().zzc();
    }

    /* access modifiers changed from: private */
    public final Object zzg(List list, zzcj zzcj, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new zzbx(zzcj, list, this, (Continuation) null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final Object zzh(Exception exc, zzcj zzcj, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new zzby(exc, zzcj, this, (Continuation) null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final void zzi(zzpr zzpr, zzcj zzcj) throws zzae {
        zzfh zzb2 = zzfh.zzb();
        int zza2 = zzcj.zza();
        zzdd zzdd = (zzdd) this.zze.get(Integer.valueOf(zzpr.zzf()));
        if (zzdd != null) {
            int zzg = zzpr.zzg();
            zzpq[] zzpqArr = (zzpq[]) zzpr.zzj().toArray(new zzpq[0]);
            zzdd.zza(zzg, zzcj, (zzpq[]) Arrays.copyOf(zzpqArr, zzpqArr.length));
            if (zza2 == zzcj.zza()) {
                zzcj.zzg(zzcj.zza() + 1);
            }
            zzb2.zzf();
            long zza3 = zzb2.zza(TimeUnit.MICROSECONDS);
            zzv zzv = zzv.zza;
            int zzk = zzpr.zzk();
            if (zzk != 1) {
                zzv.zza(zzk - 2, zza3);
                zzpr.zzk();
                zzpr.zzg();
                String unused = CollectionsKt___CollectionsKt.joinToString$default(zzpr.zzj(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new zzbw(this), 31, (Object) null);
                return;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        throw new zzae(5, 2, (Throwable) null);
    }

    public final void zza(@NotNull String str) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.zzb, (CoroutineContext) null, (CoroutineStart) null, new zzbz(new zzcj(this.zzc), this, str, (Continuation) null), 3, (Object) null);
    }
}
