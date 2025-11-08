package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzqn implements Supplier<zzqq> {
    private static zzqn zza = new zzqn();
    private final Supplier<zzqq> zzb = Suppliers.ofInstance(new zzqp());

    @SideEffectFree
    public static boolean zza() {
        return ((zzqq) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzqq) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzqq) zza.get()).zzc();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
