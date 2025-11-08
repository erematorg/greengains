package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzqg implements Supplier<zzqf> {
    private static zzqg zza = new zzqg();
    private final Supplier<zzqf> zzb = Suppliers.ofInstance(new zzqi());

    @SideEffectFree
    public static boolean zza() {
        return ((zzqf) zza.get()).zza();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
