package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

final class zzik implements zzif {
    @GuardedBy("GservicesLoader.class")
    private static zzik zza;
    @Nullable
    private final Context zzb;
    @Nullable
    private final ContentObserver zzc;

    private zzik() {
        this.zzb = null;
        this.zzc = null;
    }

    public static zzik zza(Context context) {
        zzik zzik;
        synchronized (zzik.class) {
            try {
                if (zza == null) {
                    zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzik(context) : new zzik();
                }
                zzik = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzik;
    }

    /* access modifiers changed from: private */
    @Nullable
    /* renamed from: zzc */
    public final String zza(String str) {
        Context context = this.zzb;
        if (context != null && !zzia.zza(context)) {
            try {
                return (String) zzii.zza(new zzij(this, str));
            } catch (IllegalStateException | NullPointerException | SecurityException e3) {
                Log.e("GservicesLoader", "Unable to read GServices for: " + str, e3);
            }
        }
        return null;
    }

    public final /* synthetic */ String zzb(String str) {
        return zzhn.zza(this.zzb.getContentResolver(), str, (String) null);
    }

    private zzik(Context context) {
        this.zzb = context;
        zzim zzim = new zzim(this, (Handler) null);
        this.zzc = zzim;
        context.getContentResolver().registerContentObserver(zzhq.zza, true, zzim);
    }

    public static synchronized void zza() {
        Context context;
        synchronized (zzik.class) {
            try {
                zzik zzik = zza;
                if (!(zzik == null || (context = zzik.zzb) == null || zzik.zzc == null)) {
                    context.getContentResolver().unregisterContentObserver(zza.zzc);
                }
                zza = null;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
