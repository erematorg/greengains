package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzrl implements Supplier<zzro> {
    private static zzrl zza = new zzrl();
    private final Supplier<zzro> zzb = Suppliers.ofInstance(new zzrn());

    @SideEffectFree
    public static boolean zza() {
        return ((zzro) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzro) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzro) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zzro) zza.get()).zzd();
    }

    @SideEffectFree
    public static boolean zze() {
        return ((zzro) zza.get()).zze();
    }

    @SideEffectFree
    public static boolean zzf() {
        return ((zzro) zza.get()).zzf();
    }

    @SideEffectFree
    public static boolean zzg() {
        return ((zzro) zza.get()).zzg();
    }

    @SideEffectFree
    public static boolean zzh() {
        return ((zzro) zza.get()).zzh();
    }

    @SideEffectFree
    public static boolean zzi() {
        return ((zzro) zza.get()).zzi();
    }

    @SideEffectFree
    public static boolean zzj() {
        return ((zzro) zza.get()).zzj();
    }

    @SideEffectFree
    public static boolean zzk() {
        return ((zzro) zza.get()).zzk();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
