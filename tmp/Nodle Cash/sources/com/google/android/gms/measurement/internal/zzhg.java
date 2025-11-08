package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.amplitude.api.AmplitudeClient;
import com.amplitude.api.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzhg;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzlk;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.measurement.internal.zzjc;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.checkerframework.dataflow.qual.Pure;

public final class zzhg extends zznr implements zzaj {
    @VisibleForTesting
    final LruCache<String, zzb> zza = new zzhm(this, 20);
    final zzv zzb = new zzhl(this);
    /* access modifiers changed from: private */
    public final Map<String, Map<String, String>> zzc = new ArrayMap();
    @VisibleForTesting
    private final Map<String, Set<String>> zzd = new ArrayMap();
    @VisibleForTesting
    private final Map<String, Map<String, Boolean>> zze = new ArrayMap();
    @VisibleForTesting
    private final Map<String, Map<String, Boolean>> zzf = new ArrayMap();
    private final Map<String, zzfx.zzd> zzh = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzi = new ArrayMap();
    private final Map<String, String> zzj = new ArrayMap();
    private final Map<String, String> zzk = new ArrayMap();
    private final Map<String, String> zzl = new ArrayMap();

    public zzhg(zznv zznv) {
        super(zznv);
    }

    @WorkerThread
    private final void zzv(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        if (this.zzh.get(str) == null) {
            zzao zzf2 = zzh().zzf(str);
            if (zzf2 == null) {
                this.zzc.put(str, (Object) null);
                this.zze.put(str, (Object) null);
                this.zzd.put(str, (Object) null);
                this.zzf.put(str, (Object) null);
                this.zzh.put(str, (Object) null);
                this.zzj.put(str, (Object) null);
                this.zzk.put(str, (Object) null);
                this.zzl.put(str, (Object) null);
                this.zzi.put(str, (Object) null);
                return;
            }
            zzfx.zzd.zza zza2 = (zzfx.zzd.zza) zza(str, zzf2.zza).zzcd();
            zza(str, zza2);
            this.zzc.put(str, zza((zzfx.zzd) ((zzlc) zza2.zzai())));
            this.zzh.put(str, (zzfx.zzd) ((zzlc) zza2.zzai()));
            zza(str, (zzfx.zzd) ((zzlc) zza2.zzai()));
            this.zzj.put(str, zza2.zzc());
            this.zzk.put(str, zzf2.zzb);
            this.zzl.put(str, zzf2.zzc);
        }
    }

    public final /* bridge */ /* synthetic */ zzol g_() {
        return super.g_();
    }

    @WorkerThread
    public final int zzb(String str, String str2) {
        Integer num;
        zzt();
        zzv(str);
        Map map = this.zzi.get(str);
        if (map == null || (num = (Integer) map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
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

    @WorkerThread
    public final boolean zzp(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("app_instance_id");
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
    public final boolean zzu(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains(AmplitudeClient.USER_ID_KEY);
    }

    @WorkerThread
    public final long zza(String str) {
        String zza2 = zza(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(zza2)) {
            return 0;
        }
        try {
            return Long.parseLong(zza2);
        } catch (NumberFormatException e3) {
            zzj().zzu().zza("Unable to parse timezone offset. appId", zzgi.zza(str), e3);
            return 0;
        }
    }

    @WorkerThread
    public final zzfx.zzd zzc(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        zzv(str);
        return this.zzh.get(str);
    }

    @WorkerThread
    public final String zzd(String str) {
        zzt();
        return this.zzl.get(str);
    }

    @WorkerThread
    public final String zze(String str) {
        zzt();
        return this.zzk.get(str);
    }

    @WorkerThread
    public final String zzf(String str) {
        zzt();
        zzv(str);
        return this.zzj.get(str);
    }

    @WorkerThread
    public final Set<String> zzg(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str);
    }

    @WorkerThread
    public final SortedSet<String> zzh(String str) {
        zzt();
        zzv(str);
        TreeSet treeSet = new TreeSet();
        zzfx.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return treeSet;
        }
        for (zzfx.zza.zzf zzb3 : zzb2.zzc()) {
            treeSet.add(zzb3.zzb());
        }
        return treeSet;
    }

    @WorkerThread
    public final void zzi(String str) {
        zzt();
        this.zzk.put(str, (Object) null);
    }

    @WorkerThread
    public final void zzj(String str) {
        zzt();
        this.zzh.remove(str);
    }

    @WorkerThread
    public final boolean zzk(String str) {
        zzt();
        zzfx.zzd zzc2 = zzc(str);
        if (zzc2 == null) {
            return false;
        }
        return zzc2.zzo();
    }

    public final boolean zzl(String str) {
        zzfx.zzd zzd2;
        if (TextUtils.isEmpty(str) || (zzd2 = this.zzh.get(str)) == null || zzd2.zza() == 0) {
            return false;
        }
        return true;
    }

    public final boolean zzm(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    @WorkerThread
    public final boolean zzn(String str) {
        zzt();
        zzv(str);
        zzfx.zza zzb2 = zzb(str);
        if (zzb2 != null && zzb2.zzh() && !zzb2.zzg()) {
            return false;
        }
        return true;
    }

    public final boolean zzo(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    @WorkerThread
    public final boolean zzq(String str) {
        zzt();
        zzv(str);
        if (this.zzd.get(str) != null) {
            return this.zzd.get(str).contains(Constants.AMP_TRACKING_OPTION_DEVICE_MODEL) || this.zzd.get(str).contains("device_info");
        }
        return false;
    }

    @WorkerThread
    public final boolean zzr(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("enhanced_user_id");
    }

    @WorkerThread
    public final boolean zzs(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("google_signals");
    }

    @WorkerThread
    public final boolean zzt(String str) {
        zzt();
        zzv(str);
        if (this.zzd.get(str) != null) {
            return this.zzd.get(str).contains(Constants.AMP_TRACKING_OPTION_OS_VERSION) || this.zzd.get(str).contains("device_info");
        }
        return false;
    }

    @WorkerThread
    public final boolean zzd(String str, String str2) {
        Boolean bool;
        zzt();
        zzv(str);
        if (zzm(str) && zzop.zzg(str2)) {
            return true;
        }
        if (zzo(str) && zzop.zzh(str2)) {
            return true;
        }
        Map map = this.zze.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @WorkerThread
    public final zzjc.zza zzb(String str, zzjc.zza zza2) {
        zzt();
        zzv(str);
        zzfx.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return null;
        }
        for (zzfx.zza.zzc next : zzb2.zze()) {
            if (zza2 == zza(next.zzc())) {
                return zza(next.zzb());
            }
        }
        return null;
    }

    @WorkerThread
    public final boolean zzc(String str, zzjc.zza zza2) {
        zzt();
        zzv(str);
        zzfx.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return false;
        }
        Iterator<zzfx.zza.zzb> it = zzb2.zzd().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            zzfx.zza.zzb next = it.next();
            if (zza2 == zza(next.zzc())) {
                if (next.zzb() == zzfx.zza.zzd.GRANTED) {
                    return true;
                }
            }
        }
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    public static /* synthetic */ zzb zza(zzhg zzhg, String str) {
        zzhg.zzal();
        Preconditions.checkNotEmpty(str);
        if (!zzhg.zzl(str)) {
            return null;
        }
        if (!zzhg.zzh.containsKey(str) || zzhg.zzh.get(str) == null) {
            zzhg.zzv(str);
        } else {
            zzhg.zza(str, zzhg.zzh.get(str));
        }
        return zzhg.zza.snapshot().get(str);
    }

    @WorkerThread
    public final zzfx.zza zzb(String str) {
        zzt();
        zzv(str);
        zzfx.zzd zzc2 = zzc(str);
        if (zzc2 == null || !zzc2.zzp()) {
            return null;
        }
        return zzc2.zzd();
    }

    @WorkerThread
    public final boolean zzc(String str, String str2) {
        Boolean bool;
        zzt();
        zzv(str);
        if ("ecommerce_purchase".equals(str2) || FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2)) {
            return true;
        }
        Map map = this.zzf.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @WorkerThread
    public final zzjb zza(String str, zzjc.zza zza2) {
        zzt();
        zzv(str);
        zzfx.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return zzjb.UNINITIALIZED;
        }
        for (zzfx.zza.zzb next : zzb2.zzf()) {
            if (zza(next.zzc()) == zza2) {
                int i3 = zzhn.zzc[next.zzb().ordinal()];
                if (i3 == 1) {
                    return zzjb.DENIED;
                }
                if (i3 != 2) {
                    return zzjb.UNINITIALIZED;
                }
                return zzjb.GRANTED;
            }
        }
        return zzjb.UNINITIALIZED;
    }

    private static zzjc.zza zza(zzfx.zza.zze zze2) {
        int i3 = zzhn.zzb[zze2.ordinal()];
        if (i3 == 1) {
            return zzjc.zza.AD_STORAGE;
        }
        if (i3 == 2) {
            return zzjc.zza.ANALYTICS_STORAGE;
        }
        if (i3 == 3) {
            return zzjc.zza.AD_USER_DATA;
        }
        if (i3 != 4) {
            return null;
        }
        return zzjc.zza.AD_PERSONALIZATION;
    }

    @WorkerThread
    private final zzfx.zzd zza(String str, byte[] bArr) {
        if (bArr == null) {
            return zzfx.zzd.zzg();
        }
        try {
            zzfx.zzd zzd2 = (zzfx.zzd) ((zzlc) ((zzfx.zzd.zza) zzol.zza(zzfx.zzd.zze(), bArr)).zzai());
            zzgk zzp = zzj().zzp();
            String str2 = null;
            Long valueOf = zzd2.zzs() ? Long.valueOf(zzd2.zzc()) : null;
            if (zzd2.zzq()) {
                str2 = zzd2.zzi();
            }
            zzp.zza("Parsed config. version, gmp_app_id", valueOf, str2);
            return zzd2;
        } catch (zzlk e3) {
            zzj().zzu().zza("Unable to merge remote config. appId", zzgi.zza(str), e3);
            return zzfx.zzd.zzg();
        } catch (RuntimeException e4) {
            zzj().zzu().zza("Unable to merge remote config. appId", zzgi.zza(str), e4);
            return zzfx.zzd.zzg();
        }
    }

    @WorkerThread
    public final String zza(String str, String str2) {
        zzt();
        zzv(str);
        Map map = this.zzc.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    private static Map<String, String> zza(zzfx.zzd zzd2) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzd2 != null) {
            for (zzfx.zzh next : zzd2.zzn()) {
                arrayMap.put(next.zzb(), next.zzc());
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzfx.zzd.zza zza2) {
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zza2 != null) {
            for (zzfx.zzb zzb2 : zza2.zze()) {
                hashSet.add(zzb2.zzb());
            }
            for (int i3 = 0; i3 < zza2.zza(); i3++) {
                zzfx.zzc.zza zza3 = (zzfx.zzc.zza) zza2.zza(i3).zzcd();
                if (zza3.zzb().isEmpty()) {
                    zzj().zzu().zza("EventConfig contained null event name");
                } else {
                    String zzb3 = zza3.zzb();
                    String zzb4 = zzjf.zzb(zza3.zzb());
                    if (!TextUtils.isEmpty(zzb4)) {
                        zza3 = zza3.zza(zzb4);
                        zza2.zza(i3, zza3);
                    }
                    if (zza3.zze() && zza3.zzc()) {
                        arrayMap.put(zzb3, Boolean.TRUE);
                    }
                    if (zza3.zzf() && zza3.zzd()) {
                        arrayMap2.put(zza3.zzb(), Boolean.TRUE);
                    }
                    if (zza3.zzg()) {
                        if (zza3.zza() < 2 || zza3.zza() > 65535) {
                            zzj().zzu().zza("Invalid sampling rate. Event name, sample rate", zza3.zzb(), Integer.valueOf(zza3.zza()));
                        } else {
                            arrayMap3.put(zza3.zzb(), Integer.valueOf(zza3.zza()));
                        }
                    }
                }
            }
        }
        this.zzd.put(str, hashSet);
        this.zze.put(str, arrayMap);
        this.zzf.put(str, arrayMap2);
        this.zzi.put(str, arrayMap3);
    }

    @WorkerThread
    private final void zza(String str, zzfx.zzd zzd2) {
        if (zzd2.zza() == 0) {
            this.zza.remove(str);
            return;
        }
        zzj().zzp().zza("EES programs found", Integer.valueOf(zzd2.zza()));
        zzhg.zzc zzc2 = zzd2.zzm().get(0);
        try {
            zzb zzb2 = new zzb();
            zzb2.zza("internal.remoteConfig", new zzhh(this, str));
            zzb2.zza("internal.appMetadata", new zzhk(this, str));
            zzb2.zza("internal.logger", new zzhj(this));
            zzb2.zza(zzc2);
            this.zza.put(str, zzb2);
            zzj().zzp().zza("EES program loaded for appId, activities", str, Integer.valueOf(zzc2.zza().zza()));
            for (zzhg.zzb zzb3 : zzc2.zza().zzd()) {
                zzj().zzp().zza("EES program activity", zzb3.zzb());
            }
        } catch (zzc unused) {
            zzj().zzg().zza("Failed to load EES program. appId", str);
        }
    }

    @WorkerThread
    public final boolean zza(String str, byte[] bArr, String str2, String str3) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        zzfx.zzd.zza zza2 = (zzfx.zzd.zza) zza(str, bArr).zzcd();
        if (zza2 == null) {
            return false;
        }
        zza(str, zza2);
        zza(str, (zzfx.zzd) ((zzlc) zza2.zzai()));
        this.zzh.put(str, (zzfx.zzd) ((zzlc) zza2.zzai()));
        this.zzj.put(str, zza2.zzc());
        this.zzk.put(str, str2);
        this.zzl.put(str, str3);
        this.zzc.put(str, zza((zzfx.zzd) ((zzlc) zza2.zzai())));
        zzh().zza(str, (List<zzfn.zza>) new ArrayList(zza2.zzd()));
        try {
            zza2.zzb();
            bArr = ((zzfx.zzd) ((zzlc) zza2.zzai())).zzca();
        } catch (RuntimeException e3) {
            zzj().zzu().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzgi.zza(str), e3);
        }
        zzam zzh2 = zzh();
        Preconditions.checkNotEmpty(str);
        zzh2.zzt();
        zzh2.zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        contentValues.put("e_tag", str3);
        try {
            if (((long) zzh2.e_().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzh2.zzj().zzg().zza("Failed to update remote config (got 0). appId", zzgi.zza(str));
            }
        } catch (SQLiteException e4) {
            zzh2.zzj().zzg().zza("Error storing remote config. appId", zzgi.zza(str), e4);
        }
        this.zzh.put(str, (zzfx.zzd) ((zzlc) zza2.zzai()));
        return true;
    }
}
