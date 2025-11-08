package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzqs implements Supplier<zzqr> {
    private static zzqs zza = new zzqs();
    private final Supplier<zzqr> zzb = Suppliers.ofInstance(new zzqu());

    @SideEffectFree
    public static boolean zza() {
        return ((zzqr) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzqr) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
