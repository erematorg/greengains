package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzrw implements Supplier<zzrv> {
    private static zzrw zza = new zzrw();
    private final Supplier<zzrv> zzb = Suppliers.ofInstance(new zzry());

    @SideEffectFree
    public static boolean zza() {
        return ((zzrv) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzrv) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzrv) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zzrv) zza.get()).zzd();
    }

    @SideEffectFree
    public static boolean zze() {
        return ((zzrv) zza.get()).zze();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
