package com.google.android.gms.internal.measurement;

import android.os.Binder;

public final /* synthetic */ class zzii {
    public static <V> V zza(zzih<V> zzih) {
        long clearCallingIdentity;
        try {
            return zzih.zza();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V zza = zzih.zza();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zza;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }
}
