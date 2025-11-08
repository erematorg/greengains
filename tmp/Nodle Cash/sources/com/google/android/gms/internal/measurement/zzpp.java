package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzpp implements Supplier<zzps> {
    private static zzpp zza = new zzpp();
    private final Supplier<zzps> zzb = Suppliers.ofInstance(new zzpr());

    @SideEffectFree
    public static boolean zza() {
        return ((zzps) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzps) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
