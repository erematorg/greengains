package com.google.android.gms.common;

import java.util.concurrent.Callable;

final class zzv extends zzx {
    private final Callable zze;

    public /* synthetic */ zzv(Callable callable, zzu zzu) {
        super();
        this.zze = callable;
    }

    public final String zza() {
        try {
            return (String) this.zze.call();
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }
}
