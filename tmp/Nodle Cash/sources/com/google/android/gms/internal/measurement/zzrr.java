package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzrr implements Supplier<zzru> {
    private static zzrr zza = new zzrr();
    private final Supplier<zzru> zzb = Suppliers.ofInstance(new zzrt());

    @SideEffectFree
    public static boolean zza() {
        return ((zzru) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzru) zza.get()).zzb();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
