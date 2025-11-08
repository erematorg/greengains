package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzqz implements Supplier<zzrc> {
    private static zzqz zza = new zzqz();
    private final Supplier<zzrc> zzb = Suppliers.ofInstance(new zzrb());

    @SideEffectFree
    public static boolean zza() {
        return ((zzrc) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzrc) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzrc) zza.get()).zzc();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
