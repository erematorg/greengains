package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzpj implements Supplier<zzpm> {
    private static zzpj zza = new zzpj();
    private final Supplier<zzpm> zzb = Suppliers.ofInstance(new zzpl());

    @SideEffectFree
    public static boolean zza() {
        return ((zzpm) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpm) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
