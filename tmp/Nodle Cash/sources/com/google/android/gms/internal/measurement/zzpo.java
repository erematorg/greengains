package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzpo implements Supplier<zzpn> {
    private static zzpo zza = new zzpo();
    private final Supplier<zzpn> zzb = Suppliers.ofInstance(new zzpq());

    @SideEffectFree
    public static boolean zza() {
        return ((zzpn) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpn) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
