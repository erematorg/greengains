package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzqa implements Supplier<zzpz> {
    private static zzqa zza = new zzqa();
    private final Supplier<zzpz> zzb = Suppliers.ofInstance(new zzqc());

    @SideEffectFree
    public static boolean zza() {
        return ((zzpz) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpz) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzpz) zza.get()).zzc();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
