package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzpu implements Supplier<zzpt> {
    private static zzpu zza = new zzpu();
    private final Supplier<zzpt> zzb = Suppliers.ofInstance(new zzpw());

    @SideEffectFree
    public static boolean zza() {
        return ((zzpt) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpt) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
