package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import androidx.annotation.WorkerThread;
import com.amplitude.api.Constants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzjc;
import com.google.common.annotations.VisibleForTesting;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

final class zzgu extends zzix {
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    private long zzaa;
    public zzgy zzb;
    public final zzgz zzc = new zzgz(this, "first_open_time", 0);
    public final zzgz zzd = new zzgz(this, "app_install_time", 0);
    public final zzha zze = new zzha(this, "app_instance_id", (String) null);
    public final zzgz zzf = new zzgz(this, "session_timeout", Constants.SESSION_TIMEOUT_MILLIS);
    public final zzgx zzg = new zzgx(this, "start_new_session", true);
    public final zzha zzh = new zzha(this, "non_personalized_ads", (String) null);
    public final zzgw zzi = new zzgw(this, "last_received_uri_timestamps_by_source", (Bundle) null);
    public final zzgx zzj = new zzgx(this, "allow_remote_dynamite", false);
    public final zzgz zzk = new zzgz(this, "last_pause_time", 0);
    public final zzgz zzl = new zzgz(this, "session_id", 0);
    public boolean zzm;
    public zzgx zzn = new zzgx(this, "app_backgrounded", false);
    public zzgx zzo = new zzgx(this, "deep_link_retrieval_complete", false);
    public zzgz zzp = new zzgz(this, "deep_link_retrieval_attempts", 0);
    public final zzha zzq = new zzha(this, "firebase_feature_rollouts", (String) null);
    public final zzha zzr = new zzha(this, "deferred_attribution_cache", (String) null);
    public final zzgz zzs = new zzgz(this, "deferred_attribution_cache_timestamp", 0);
    public final zzgw zzt = new zzgw(this, "default_event_parameters", (Bundle) null);
    private SharedPreferences zzv;
    private Object zzw = new Object();
    private SharedPreferences zzx;
    private String zzy;
    private boolean zzz;

    public zzgu(zzhw zzhw) {
        super(zzhw);
    }

    @WorkerThread
    public final Pair<String, Boolean> zza(String str) {
        zzt();
        if (!zzn().zza(zzjc.zza.AD_STORAGE)) {
            return new Pair<>("", Boolean.FALSE);
        }
        long elapsedRealtime = zzb().elapsedRealtime();
        if (this.zzy != null && elapsedRealtime < this.zzaa) {
            return new Pair<>(this.zzy, Boolean.valueOf(this.zzz));
        }
        this.zzaa = zze().zzd(str) + elapsedRealtime;
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zza());
            this.zzy = "";
            String id = advertisingIdInfo.getId();
            if (id != null) {
                this.zzy = id;
            }
            this.zzz = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Exception e3) {
            zzj().zzc().zza("Unable to get advertising id", e3);
            this.zzy = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzy, Boolean.valueOf(this.zzz));
    }

    @WorkerThread
    @EnsuresNonNull.List({@EnsuresNonNull({"this.preferences"}), @EnsuresNonNull({"this.monitoringSample"})})
    public final void zzaa() {
        SharedPreferences sharedPreferences = zza().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzv = sharedPreferences;
        boolean z2 = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzm = z2;
        if (!z2) {
            SharedPreferences.Editor edit = this.zzv.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzb = new zzgy(this, "health_monitor", Math.max(0, zzbj.zzc.zza(null).longValue()));
    }

    @WorkerThread
    public final boolean zzab() {
        SharedPreferences sharedPreferences = this.zzv;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    @WorkerThread
    public final void zzb(String str) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    @WorkerThread
    public final SharedPreferences zzc() {
        zzt();
        zzac();
        if (this.zzx == null) {
            synchronized (this.zzw) {
                try {
                    if (this.zzx == null) {
                        String str = zza().getPackageName() + "_preferences";
                        zzj().zzp().zza("Default prefs file", str);
                        this.zzx = zza().getSharedPreferences(str, 0);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzx;
    }

    @WorkerThread
    @VisibleForTesting
    public final SharedPreferences zzg() {
        zzt();
        zzac();
        Preconditions.checkNotNull(this.zzv);
        return this.zzv;
    }

    public final SparseArray<Long> zzh() {
        Bundle zza2 = this.zzi.zza();
        if (zza2 == null) {
            return new SparseArray<>();
        }
        int[] intArray = zza2.getIntArray("uriSources");
        long[] longArray = zza2.getLongArray("uriTimestamps");
        if (intArray == null || longArray == null) {
            return new SparseArray<>();
        }
        if (intArray.length != longArray.length) {
            zzj().zzg().zza("Trigger URI source and timestamp array lengths do not match");
            return new SparseArray<>();
        }
        SparseArray<Long> sparseArray = new SparseArray<>();
        for (int i3 = 0; i3 < intArray.length; i3++) {
            sparseArray.put(intArray[i3], Long.valueOf(longArray[i3]));
        }
        return sparseArray;
    }

    @WorkerThread
    public final zzaz zzm() {
        zzt();
        return zzaz.zza(zzg().getString("dma_consent_settings", (String) null));
    }

    @WorkerThread
    public final zzjc zzn() {
        zzt();
        return zzjc.zza(zzg().getString("consent_settings", "G1"), zzg().getInt("consent_source", 100));
    }

    public final boolean zzo() {
        return true;
    }

    @WorkerThread
    public final Boolean zzp() {
        zzt();
        if (!zzg().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzg().getBoolean("use_service", false));
    }

    @WorkerThread
    public final Boolean zzu() {
        zzt();
        if (zzg().contains("measurement_enabled_from_api")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled_from_api", true));
        }
        return null;
    }

    @WorkerThread
    public final Boolean zzv() {
        zzt();
        if (zzg().contains("measurement_enabled")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    @WorkerThread
    public final String zzw() {
        zzt();
        String string = zzg().getString("previous_os_version", (String) null);
        zzf().zzac();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzg().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    @WorkerThread
    public final String zzx() {
        zzt();
        return zzg().getString("admob_app_id", (String) null);
    }

    @WorkerThread
    public final String zzy() {
        zzt();
        return zzg().getString("gmp_app_id", (String) null);
    }

    @WorkerThread
    public final void zzz() {
        zzt();
        Boolean zzv2 = zzv();
        SharedPreferences.Editor edit = zzg().edit();
        edit.clear();
        edit.apply();
        if (zzv2 != null) {
            zza(zzv2);
        }
    }

    @WorkerThread
    public final void zzb(Boolean bool) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled_from_api");
        }
        edit.apply();
    }

    @WorkerThread
    public final void zzb(boolean z2) {
        zzt();
        zzj().zzp().zza("App measurement setting deferred collection", Boolean.valueOf(z2));
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("deferred_analytics_collection", z2);
        edit.apply();
    }

    @WorkerThread
    public final void zzc(String str) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    @WorkerThread
    public final void zza(Boolean bool) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    @WorkerThread
    public final void zza(boolean z2) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("use_service", z2);
        edit.apply();
    }

    public final void zza(SparseArray<Long> sparseArray) {
        if (sparseArray == null) {
            this.zzi.zza((Bundle) null);
            return;
        }
        int[] iArr = new int[sparseArray.size()];
        long[] jArr = new long[sparseArray.size()];
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            iArr[i3] = sparseArray.keyAt(i3);
            jArr[i3] = sparseArray.valueAt(i3).longValue();
        }
        Bundle bundle = new Bundle();
        bundle.putIntArray("uriSources", iArr);
        bundle.putLongArray("uriTimestamps", jArr);
        this.zzi.zza(bundle);
    }

    public final boolean zza(long j2) {
        return j2 - this.zzf.zza() > this.zzk.zza();
    }

    @WorkerThread
    public final boolean zza(zzaz zzaz) {
        zzt();
        if (!zzjc.zza(zzaz.zza(), zzm().zza())) {
            return false;
        }
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("dma_consent_settings", zzaz.zzf());
        edit.apply();
        return true;
    }

    @WorkerThread
    public final boolean zza(zzjc zzjc) {
        zzt();
        int zza2 = zzjc.zza();
        if (!zza(zza2)) {
            return false;
        }
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("consent_settings", zzjc.zzh());
        edit.putInt("consent_source", zza2);
        edit.apply();
        return true;
    }

    @WorkerThread
    public final boolean zza(int i3) {
        return zzjc.zza(i3, zzg().getInt("consent_source", 100));
    }

    @WorkerThread
    public final boolean zza(zzni zzni) {
        zzt();
        String string = zzg().getString("stored_tcf_param", "");
        String zzc2 = zzni.zzc();
        if (zzc2.equals(string)) {
            return false;
        }
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("stored_tcf_param", zzc2);
        edit.apply();
        return true;
    }
}
