package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class zzor implements Supplier<zzou> {
    private static zzor zza = new zzor();
    private final Supplier<zzou> zzb = Suppliers.ofInstance(new zzot());

    @SideEffectFree
    public static boolean zza() {
        return ((zzou) zza.get()).zza();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
