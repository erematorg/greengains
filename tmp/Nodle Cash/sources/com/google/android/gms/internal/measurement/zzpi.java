package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzpi implements Supplier<zzph> {
    private static zzpi zza = new zzpi();
    private final Supplier<zzph> zzb = Suppliers.ofInstance(new zzpk());

    @SideEffectFree
    public static boolean zza() {
        return ((zzph) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzph) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzph) zza.get()).zzc();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
