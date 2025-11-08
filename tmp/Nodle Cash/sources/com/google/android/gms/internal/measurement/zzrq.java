package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzrq implements Supplier<zzrp> {
    private static zzrq zza = new zzrq();
    private final Supplier<zzrp> zzb = Suppliers.ofInstance(new zzrs());

    @SideEffectFree
    public static boolean zza() {
        return ((zzrp) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzrp) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
