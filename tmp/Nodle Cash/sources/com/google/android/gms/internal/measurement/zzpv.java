package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzpv implements Supplier<zzpy> {
    private static zzpv zza = new zzpv();
    private final Supplier<zzpy> zzb = Suppliers.ofInstance(new zzpx());

    @SideEffectFree
    public static boolean zza() {
        return ((zzpy) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpy) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzpy) zza.get()).zzc();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
