package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzrk implements Supplier<zzrj> {
    private static zzrk zza = new zzrk();
    private final Supplier<zzrj> zzb = Suppliers.ofInstance(new zzrm());

    @SideEffectFree
    public static boolean zza() {
        return ((zzrj) zza.get()).zza();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
