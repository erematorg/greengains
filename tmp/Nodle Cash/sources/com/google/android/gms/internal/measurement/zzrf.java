package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzrf implements Supplier<zzri> {
    private static zzrf zza = new zzrf();
    private final Supplier<zzri> zzb = Suppliers.ofInstance(new zzrh());

    @SideEffectFree
    public static double zza() {
        return ((zzri) zza.get()).zza();
    }

    @SideEffectFree
    public static long zzb() {
        return ((zzri) zza.get()).zzb();
    }

    @SideEffectFree
    public static long zzc() {
        return ((zzri) zza.get()).zzc();
    }

    @SideEffectFree
    public static long zzd() {
        return ((zzri) zza.get()).zzd();
    }

    @SideEffectFree
    public static String zze() {
        return ((zzri) zza.get()).zze();
    }

    @SideEffectFree
    public static boolean zzf() {
        return ((zzri) zza.get()).zzf();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
