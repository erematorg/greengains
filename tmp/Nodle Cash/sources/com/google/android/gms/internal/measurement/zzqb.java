package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzqb implements Supplier<zzqe> {
    private static zzqb zza = new zzqb();
    private final Supplier<zzqe> zzb = Suppliers.ofInstance(new zzqd());

    @SideEffectFree
    public static boolean zza() {
        return ((zzqe) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzqe) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzqe) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zzqe) zza.get()).zzd();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
