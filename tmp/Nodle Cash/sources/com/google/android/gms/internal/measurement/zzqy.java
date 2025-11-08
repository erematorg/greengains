package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzqy implements Supplier<zzqx> {
    private static zzqy zza = new zzqy();
    private final Supplier<zzqx> zzb = Suppliers.ofInstance(new zzra());

    @SideEffectFree
    public static boolean zza() {
        return ((zzqx) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzqx) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
