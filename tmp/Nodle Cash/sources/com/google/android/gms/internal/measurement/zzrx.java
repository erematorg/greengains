package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzrx implements Supplier<zzsa> {
    private static zzrx zza = new zzrx();
    private final Supplier<zzsa> zzb = Suppliers.ofInstance(new zzrz());

    @SideEffectFree
    public static boolean zza() {
        return ((zzsa) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzsa) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
