package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzpc implements Supplier<zzpb> {
    private static zzpc zza = new zzpc();
    private final Supplier<zzpb> zzb = Suppliers.ofInstance(new zzpe());

    @SideEffectFree
    public static long zza() {
        return ((zzpb) zza.get()).zza();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
