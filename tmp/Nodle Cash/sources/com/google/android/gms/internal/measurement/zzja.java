package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzja implements zzif {
    @GuardedBy("SharedPreferencesLoader.class")
    private static final Map<String, zzja> zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final Runnable zzc;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzd;
    private final Object zze = new Object();
    private volatile Map<String, ?> zzf;
    @GuardedBy("this")
    private final List<zzid> zzg = new ArrayList();

    private zzja(SharedPreferences sharedPreferences, Runnable runnable) {
        zzjd zzjd = new zzjd(this);
        this.zzd = zzjd;
        this.zzb = sharedPreferences;
        this.zzc = runnable;
        sharedPreferences.registerOnSharedPreferenceChangeListener(zzjd);
    }

    private static SharedPreferences zza(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzia.zza()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                SharedPreferences zza2 = zzcr.zza(context, str.substring(12), 0, zzcn.zza);
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                return zza2;
            }
            SharedPreferences zza3 = zzcr.zza(context, str, 0, zzcn.zza);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return zza3;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }

    @Nullable
    public static zzja zza(Context context, String str, Runnable runnable) {
        zzja zzja;
        if (!((!zzia.zza() || str.startsWith("direct_boot:")) ? true : zzia.zzb(context))) {
            return null;
        }
        synchronized (zzja.class) {
            try {
                Map<String, zzja> map = zza;
                zzja = map.get(str);
                if (zzja == null) {
                    zzja = new zzja(zza(context, str), runnable);
                    map.put(str, zzja);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzja;
    }

    @Nullable
    public final Object zza(String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads;
        Map<String, ?> map = this.zzf;
        if (map == null) {
            synchronized (this.zze) {
                try {
                    map = this.zzf;
                    if (map == null) {
                        allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        Map<String, ?> all = this.zzb.getAll();
                        this.zzf = all;
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = all;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public static synchronized void zza() {
        synchronized (zzja.class) {
            try {
                for (zzja next : zza.values()) {
                    next.zzb.unregisterOnSharedPreferenceChangeListener(next.zzd);
                }
                zza.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zze) {
            this.zzf = null;
            this.zzc.run();
        }
        synchronized (this) {
            try {
                for (zzid zza2 : this.zzg) {
                    zza2.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
