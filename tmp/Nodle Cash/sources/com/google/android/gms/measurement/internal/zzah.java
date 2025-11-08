package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Size;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzqy;
import com.google.android.gms.internal.measurement.zzqz;
import java.lang.reflect.InvocationTargetException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;

public final class zzah extends zziy {
    private Boolean zza;
    private String zzb;
    private zzaj zzc = new zzag();
    private Boolean zzd;

    public zzah(zzhw zzhw) {
        super(zzhw);
    }

    @VisibleForTesting
    private final Bundle zzac() {
        try {
            if (zza().getPackageManager() == null) {
                zzj().zzg().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(zza()).getApplicationInfo(zza().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzj().zzg().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e3) {
            zzj().zzg().zza("Failed to load metadata: Package name not found", e3);
            return null;
        }
    }

    public static long zzh() {
        return zzbj.zzd.zza(null).longValue();
    }

    public static long zzm() {
        return zzbj.zzaf.zza(null).longValue();
    }

    public static long zzn() {
        return (long) zzbj.zzk.zza(null).intValue();
    }

    public static long zzo() {
        return zzbj.zzae.zza(null).longValue();
    }

    public static long zzp() {
        return zzbj.zzz.zza(null).longValue();
    }

    @WorkerThread
    public final double zza(String str, zzfz<Double> zzfz) {
        if (TextUtils.isEmpty(str)) {
            return zzfz.zza(null).doubleValue();
        }
        String zza2 = this.zzc.zza(str, zzfz.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzfz.zza(null).doubleValue();
        }
        try {
            return zzfz.zza(Double.valueOf(Double.parseDouble(zza2))).doubleValue();
        } catch (NumberFormatException unused) {
            return zzfz.zza(null).doubleValue();
        }
    }

    @WorkerThread
    public final boolean zzaa() {
        if (this.zza == null) {
            Boolean zze = zze("app_measurement_lite");
            this.zza = zze;
            if (zze == null) {
                this.zza = Boolean.FALSE;
            }
        }
        return this.zza.booleanValue() || !this.zzu.zzag();
    }

    @EnsuresNonNull({"this.isMainProcess"})
    public final boolean zzab() {
        if (this.zzd == null) {
            synchronized (this) {
                try {
                    if (this.zzd == null) {
                        ApplicationInfo applicationInfo = zza().getApplicationInfo();
                        String myProcessName = ProcessUtils.getMyProcessName();
                        if (applicationInfo != null) {
                            String str = applicationInfo.processName;
                            this.zzd = Boolean.valueOf(str != null && str.equals(myProcessName));
                        }
                        if (this.zzd == null) {
                            this.zzd = Boolean.TRUE;
                            zzj().zzg().zza("My process not in the list of running processes");
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzd.booleanValue();
    }

    public final int zzb(String str, boolean z2) {
        return Math.max(zza(str, z2), 256);
    }

    public final int zzc() {
        if (!zzqz.zza() || !zze().zzf((String) null, zzbj.zzce) || !zzq().zza(231100000, true)) {
            return 0;
        }
        return 35;
    }

    @WorkerThread
    public final long zzd(String str) {
        return zzc(str, zzbj.zza);
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final int zzg() {
        return zzq().zza(201500000, true) ? 100 : 25;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final String zzu() {
        return zza("debug.firebase.analytics.app", "");
    }

    public final String zzv() {
        return zza("debug.deferred.deeplink", "");
    }

    public final String zzw() {
        return this.zzb;
    }

    public final boolean zzx() {
        Boolean zze = zze("google_analytics_adid_collection_enabled");
        return zze == null || zze.booleanValue();
    }

    public final boolean zzy() {
        Boolean zze = zze("google_analytics_automatic_screen_reporting_enabled");
        return zze == null || zze.booleanValue();
    }

    public final boolean zzz() {
        Boolean zze = zze("firebase_analytics_collection_deactivated");
        return zze != null && zze.booleanValue();
    }

    public final int zzb(@Size(min = 1) String str) {
        return zza(str, zzbj.zzak, 25, 100);
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @VisibleForTesting
    public final Boolean zze(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzac = zzac();
        if (zzac == null) {
            zzj().zzg().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (!zzac.containsKey(str)) {
            return null;
        } else {
            return Boolean.valueOf(zzac.getBoolean(str));
        }
    }

    @WorkerThread
    public final String zzf(String str) {
        return zzd(str, zzbj.zzan);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002b A[SYNTHETIC, Splitter:B:9:0x002b] */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> zzg(@androidx.annotation.Size(min = 1) java.lang.String r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            android.os.Bundle r0 = r3.zzac()
            r1 = 0
            if (r0 != 0) goto L_0x0019
            com.google.android.gms.measurement.internal.zzgi r4 = r3.zzj()
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()
            java.lang.String r0 = "Failed to load metadata: Metadata bundle is null"
            r4.zza(r0)
        L_0x0017:
            r4 = r1
            goto L_0x0028
        L_0x0019:
            boolean r2 = r0.containsKey(r4)
            if (r2 != 0) goto L_0x0020
            goto L_0x0017
        L_0x0020:
            int r4 = r0.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x0028:
            if (r4 != 0) goto L_0x002b
            return r1
        L_0x002b:
            android.content.Context r0 = r3.zza()     // Catch:{ NotFoundException -> 0x0043 }
            android.content.res.Resources r0 = r0.getResources()     // Catch:{ NotFoundException -> 0x0043 }
            int r4 = r4.intValue()     // Catch:{ NotFoundException -> 0x0043 }
            java.lang.String[] r4 = r0.getStringArray(r4)     // Catch:{ NotFoundException -> 0x0043 }
            if (r4 != 0) goto L_0x003e
            return r1
        L_0x003e:
            java.util.List r3 = java.util.Arrays.asList(r4)     // Catch:{ NotFoundException -> 0x0043 }
            return r3
        L_0x0043:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzgi r3 = r3.zzj()
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()
            java.lang.String r0 = "Failed to load string array from metadata: resource not found"
            r3.zza(r0, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzah.zzg(java.lang.String):java.util.List");
    }

    @WorkerThread
    public final boolean zzi(String str) {
        return zzf(str, zzbj.zzam);
    }

    public final boolean zzj(String str) {
        return "1".equals(this.zzc.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzk(String str) {
        return "1".equals(this.zzc.zza(str, "measurement.event_sampling_enabled"));
    }

    @WorkerThread
    public final int zzb(String str, zzfz<Integer> zzfz) {
        if (TextUtils.isEmpty(str)) {
            return zzfz.zza(null).intValue();
        }
        String zza2 = this.zzc.zza(str, zzfz.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzfz.zza(null).intValue();
        }
        try {
            return zzfz.zza(Integer.valueOf(Integer.parseInt(zza2))).intValue();
        } catch (NumberFormatException unused) {
            return zzfz.zza(null).intValue();
        }
    }

    @WorkerThread
    public final String zzd(String str, zzfz<String> zzfz) {
        if (TextUtils.isEmpty(str)) {
            return zzfz.zza(null);
        }
        return zzfz.zza(this.zzc.zza(str, zzfz.zza()));
    }

    @WorkerThread
    public final boolean zzf(String str, zzfz<Boolean> zzfz) {
        if (TextUtils.isEmpty(str)) {
            return zzfz.zza(null).booleanValue();
        }
        String zza2 = this.zzc.zza(str, zzfz.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzfz.zza(null).booleanValue();
        }
        return zzfz.zza(Boolean.valueOf("1".equals(zza2))).booleanValue();
    }

    public final void zzh(String str) {
        this.zzb = str;
    }

    @WorkerThread
    public final int zzc(@Size(min = 1) String str) {
        return zzb(str, zzbj.zzo);
    }

    @WorkerThread
    public final long zzc(String str, zzfz<Long> zzfz) {
        if (TextUtils.isEmpty(str)) {
            return zzfz.zza(null).longValue();
        }
        String zza2 = this.zzc.zza(str, zzfz.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzfz.zza(null).longValue();
        }
        try {
            return zzfz.zza(Long.valueOf(Long.parseLong(zza2))).longValue();
        } catch (NumberFormatException unused) {
            return zzfz.zza(null).longValue();
        }
    }

    public final boolean zze(String str, zzfz<Boolean> zzfz) {
        return zzf(str, zzfz);
    }

    public final int zza(@Size(min = 1) String str) {
        return zza(str, zzbj.zzaj, 500, 2000);
    }

    public final int zza(String str, boolean z2) {
        if (!zzqy.zza() || !zze().zzf((String) null, zzbj.zzcu)) {
            return 100;
        }
        if (z2) {
            return zza(str, zzbj.zzat, 100, 500);
        }
        return 500;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @WorkerThread
    public final int zza(String str, zzfz<Integer> zzfz, int i3, int i4) {
        return Math.max(Math.min(zzb(str, zzfz), i4), i3);
    }

    public final zzjb zzc(String str, boolean z2) {
        Object obj;
        Preconditions.checkNotEmpty(str);
        Bundle zzac = zzac();
        if (zzac == null) {
            zzj().zzg().zza("Failed to load metadata: Metadata bundle is null");
            obj = null;
        } else {
            obj = zzac.get(str);
        }
        if (obj == null) {
            return zzjb.UNINITIALIZED;
        }
        if (Boolean.TRUE.equals(obj)) {
            return zzjb.GRANTED;
        }
        if (Boolean.FALSE.equals(obj)) {
            return zzjb.DENIED;
        }
        if (z2 && "eu_consent_policy".equals(obj)) {
            return zzjb.POLICY;
        }
        zzj().zzu().zza("Invalid manifest metadata for", str);
        return zzjb.UNINITIALIZED;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    private final String zza(String str, String str2) {
        Class<String> cls = String.class;
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{cls, cls}).invoke((Object) null, new Object[]{str, str2});
            Preconditions.checkNotNull(str3);
            return str3;
        } catch (ClassNotFoundException e3) {
            zzj().zzg().zza("Could not find SystemProperties class", e3);
            return str2;
        } catch (NoSuchMethodException e4) {
            zzj().zzg().zza("Could not find SystemProperties.get() method", e4);
            return str2;
        } catch (IllegalAccessException e5) {
            zzj().zzg().zza("Could not access SystemProperties.get()", e5);
            return str2;
        } catch (InvocationTargetException e6) {
            zzj().zzg().zza("SystemProperties.get() threw an exception", e6);
            return str2;
        }
    }

    public final void zza(zzaj zzaj) {
        this.zzc = zzaj;
    }

    public final boolean zza(zzfz<Boolean> zzfz) {
        return zzf((String) null, zzfz);
    }
}
