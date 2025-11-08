package com.google.android.gms.internal.measurement;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import com.google.common.base.Preconditions;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzic implements zzif {
    @GuardedBy("ConfigurationContentLoader.class")
    private static final Map<Uri, zzic> zza = new ArrayMap();
    private static final String[] zzb = {JwtUtilsKt.DID_METHOD_KEY, "value"};
    private final ContentResolver zzc;
    private final Uri zzd;
    private final Runnable zze;
    private final ContentObserver zzf;
    private final Object zzg = new Object();
    private volatile Map<String, String> zzh;
    @GuardedBy("this")
    private final List<zzid> zzi = new ArrayList();

    private zzic(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        zzie zzie = new zzie(this, (Handler) null);
        this.zzf = zzie;
        Preconditions.checkNotNull(contentResolver);
        Preconditions.checkNotNull(uri);
        this.zzc = contentResolver;
        this.zzd = uri;
        this.zze = runnable;
        contentResolver.registerContentObserver(uri, false, zzie);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|9)|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzic zza(android.content.ContentResolver r4, android.net.Uri r5, java.lang.Runnable r6) {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzic> r0 = com.google.android.gms.internal.measurement.zzic.class
            monitor-enter(r0)
            java.util.Map<android.net.Uri, com.google.android.gms.internal.measurement.zzic> r1 = zza     // Catch:{ all -> 0x0017 }
            java.lang.Object r2 = r1.get(r5)     // Catch:{ all -> 0x0017 }
            com.google.android.gms.internal.measurement.zzic r2 = (com.google.android.gms.internal.measurement.zzic) r2     // Catch:{ all -> 0x0017 }
            if (r2 != 0) goto L_0x0019
            com.google.android.gms.internal.measurement.zzic r3 = new com.google.android.gms.internal.measurement.zzic     // Catch:{ SecurityException -> 0x0019 }
            r3.<init>(r4, r5, r6)     // Catch:{ SecurityException -> 0x0019 }
            r1.put(r5, r3)     // Catch:{ SecurityException -> 0x0015 }
        L_0x0015:
            r2 = r3
            goto L_0x0019
        L_0x0017:
            r4 = move-exception
            goto L_0x001b
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            return r2
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzic.zza(android.content.ContentResolver, android.net.Uri, java.lang.Runnable):com.google.android.gms.internal.measurement.zzic");
    }

    public static synchronized void zzc() {
        synchronized (zzic.class) {
            try {
                for (zzic next : zza.values()) {
                    next.zzc.unregisterContentObserver(next.zzf);
                }
                zza.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private final Map<String, String> zze() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return (Map) zzii.zza(new zzib(this));
        } catch (SQLiteException | IllegalStateException | SecurityException e3) {
            Log.w("ConfigurationContentLdr", "Unable to query ContentProvider, using default values", e3);
            return Collections.emptyMap();
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public final /* synthetic */ Map zzb() {
        Cursor query;
        ContentProviderClient acquireUnstableContentProviderClient = this.zzc.acquireUnstableContentProviderClient(this.zzd);
        if (acquireUnstableContentProviderClient == null) {
            Log.w("ConfigurationContentLdr", "Unable to acquire ContentProviderClient, using default values");
            return Collections.emptyMap();
        }
        try {
            query = acquireUnstableContentProviderClient.query(this.zzd, zzb, (String) null, (String[]) null, (String) null);
            if (query == null) {
                Log.w("ConfigurationContentLdr", "ContentProvider query returned null cursor, using default values");
                Map emptyMap = Collections.emptyMap();
                if (query != null) {
                    query.close();
                }
                acquireUnstableContentProviderClient.release();
                return emptyMap;
            }
            int count = query.getCount();
            if (count == 0) {
                Map emptyMap2 = Collections.emptyMap();
                query.close();
                acquireUnstableContentProviderClient.release();
                return emptyMap2;
            }
            Map arrayMap = count <= 256 ? new ArrayMap(count) : new HashMap(count, 1.0f);
            while (query.moveToNext()) {
                arrayMap.put(query.getString(0), query.getString(1));
            }
            if (!query.isAfterLast()) {
                Log.w("ConfigurationContentLdr", "Cursor read incomplete (ContentProvider dead?), using default values");
                Map emptyMap3 = Collections.emptyMap();
                query.close();
                acquireUnstableContentProviderClient.release();
                return emptyMap3;
            }
            query.close();
            acquireUnstableContentProviderClient.release();
            return arrayMap;
        } catch (RemoteException e3) {
            Log.w("ConfigurationContentLdr", "ContentProvider query failed, using default values", e3);
            Map emptyMap4 = Collections.emptyMap();
            acquireUnstableContentProviderClient.release();
            return emptyMap4;
        } catch (Throwable th) {
            acquireUnstableContentProviderClient.release();
            throw th;
        }
        throw th;
    }

    public final void zzd() {
        synchronized (this.zzg) {
            this.zzh = null;
            this.zze.run();
        }
        synchronized (this) {
            try {
                for (zzid zza2 : this.zzi) {
                    zza2.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ Object zza(String str) {
        return zza().get(str);
    }

    public final Map<String, String> zza() {
        Map<String, String> map = this.zzh;
        if (map == null) {
            synchronized (this.zzg) {
                try {
                    map = this.zzh;
                    if (map == null) {
                        map = zze();
                        this.zzh = map;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }
}
