package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzqt implements Supplier<zzqw> {
    private static zzqt zza = new zzqt();
    private final Supplier<zzqw> zzb = Suppliers.ofInstance(new zzqv());

    @SideEffectFree
    public static boolean zza() {
        return ((zzqw) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzqw) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
