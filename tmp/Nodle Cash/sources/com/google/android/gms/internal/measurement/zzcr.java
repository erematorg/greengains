package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.common.base.Preconditions;

public final class zzcr {
    private static final ThreadLocal<Boolean> zza = new zzcu();

    public static SharedPreferences zza(Context context, String str, int i3, zzcm zzcm) {
        zzcq zzcq = zzcf.zza().zza(str, zzcm, zzcj.SHARED_PREFS_TYPE).equals("") ? new zzcq() : null;
        if (zzcq != null) {
            return zzcq;
        }
        ThreadLocal<Boolean> threadLocal = zza;
        Preconditions.checkArgument(threadLocal.get().booleanValue());
        threadLocal.set(Boolean.FALSE);
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            threadLocal.set(Boolean.TRUE);
            return sharedPreferences;
        } catch (Throwable th) {
            zza.set(Boolean.TRUE);
            throw th;
        }
    }
}
