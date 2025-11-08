package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.checkerframework.dataflow.qual.Pure;

public final class zzms extends zznr {
    public final zzgz zza;
    public final zzgz zzb;
    public final zzgz zzc;
    public final zzgz zzd;
    public final zzgz zze;
    public final zzgz zzf;
    private final Map<String, zzmv> zzh = new HashMap();

    public zzms(zznv zznv) {
        super(zznv);
        zzgu zzk = zzk();
        Objects.requireNonNull(zzk);
        this.zza = new zzgz(zzk, "last_delete_stale", 0);
        zzgu zzk2 = zzk();
        Objects.requireNonNull(zzk2);
        this.zzb = new zzgz(zzk2, "last_delete_stale_batch", 0);
        zzgu zzk3 = zzk();
        Objects.requireNonNull(zzk3);
        this.zzc = new zzgz(zzk3, "backoff", 0);
        zzgu zzk4 = zzk();
        Objects.requireNonNull(zzk4);
        this.zzd = new zzgz(zzk4, "last_upload", 0);
        zzgu zzk5 = zzk();
        Objects.requireNonNull(zzk5);
        this.zze = new zzgz(zzk5, "last_upload_attempt", 0);
        zzgu zzk6 = zzk();
        Objects.requireNonNull(zzk6);
        this.zzf = new zzgz(zzk6, "midnight_offset", 0);
    }

    public final /* bridge */ /* synthetic */ zzol g_() {
        return super.g_();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final boolean zzc() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzv zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzam zzh() {
        return super.zzh();
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

    public final /* bridge */ /* synthetic */ zzhg zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzms zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zznq zzo() {
        return super.zzo();
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

    @WorkerThread
    @Deprecated
    private final Pair<String, Boolean> zza(String str) {
        zzmv zzmv;
        AdvertisingIdClient.Info info;
        zzt();
        long elapsedRealtime = zzb().elapsedRealtime();
        zzmv zzmv2 = this.zzh.get(str);
        if (zzmv2 != null && elapsedRealtime < zzmv2.zzc) {
            return new Pair<>(zzmv2.zza, Boolean.valueOf(zzmv2.zzb));
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        long zzd2 = zze().zzd(str) + elapsedRealtime;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(zza());
        } catch (PackageManager.NameNotFoundException unused) {
            if (zzmv2 != null) {
                try {
                    if (elapsedRealtime < zzmv2.zzc + zze().zzc(str, zzbj.zzb)) {
                        return new Pair<>(zzmv2.zza, Boolean.valueOf(zzmv2.zzb));
                    }
                } catch (Exception e3) {
                    zzj().zzc().zza("Unable to get advertising id", e3);
                    zzmv = new zzmv("", false, zzd2);
                }
            }
            info = null;
        }
        if (info == null) {
            return new Pair<>("00000000-0000-0000-0000-000000000000", Boolean.FALSE);
        }
        String id = info.getId();
        if (id != null) {
            zzmv = new zzmv(id, info.isLimitAdTrackingEnabled(), zzd2);
        } else {
            zzmv = new zzmv("", info.isLimitAdTrackingEnabled(), zzd2);
        }
        this.zzh.put(str, zzmv);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(zzmv.zza, Boolean.valueOf(zzmv.zzb));
    }

    @WorkerThread
    public final Pair<String, Boolean> zza(String str, zzjc zzjc) {
        if (zzjc.zzi()) {
            return zza(str);
        }
        return new Pair<>("", Boolean.FALSE);
    }

    @WorkerThread
    @Deprecated
    public final String zza(String str, boolean z2) {
        String str2;
        zzt();
        if (z2) {
            str2 = (String) zza(str).first;
        } else {
            str2 = "00000000-0000-0000-0000-000000000000";
        }
        MessageDigest zzu = zzop.zzu();
        if (zzu == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzu.digest(str2.getBytes()))});
    }
}
