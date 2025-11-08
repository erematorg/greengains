package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzsc implements Supplier<zzsb> {
    private static zzsc zza = new zzsc();
    private final Supplier<zzsb> zzb = Suppliers.ofInstance(new zzsd());

    @SideEffectFree
    public static boolean zza() {
        return ((zzsb) zza.get()).zza();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
