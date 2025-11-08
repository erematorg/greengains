package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.vision.barcode.internal.zzk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class zzwp {
    @Nullable
    private static zzcs zza;
    private static final zzcu zzb = zzcu.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzwf zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzwp(Context context, SharedPrefManager sharedPrefManager, zzwf zzwf, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzwf;
        zzxb.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzwl(this));
        MLTaskExecutor instance = MLTaskExecutor.getInstance();
        Objects.requireNonNull(sharedPrefManager);
        this.zzh = instance.scheduleCallable(new zzwm(sharedPrefManager));
        zzcu zzcu = zzb;
        this.zzj = zzcu.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzcu.get(str)) : -1;
    }

    @VisibleForTesting
    public static long zza(List list, double d2) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d2 / 100.0d) * ((double) list.size()))) - 1, 0))).longValue();
    }

    @NonNull
    private static synchronized zzcs zzi() {
        synchronized (zzwp.class) {
            try {
                zzcs zzcs = zza;
                if (zzcs != null) {
                    return zzcs;
                }
                LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
                zzcp zzcp = new zzcp();
                for (int i3 = 0; i3 < locales.size(); i3++) {
                    zzcp.zzd(CommonUtils.languageTagFromLocale(locales.get(i3)));
                }
                zzcs zzf2 = zzcp.zzf();
                zza = zzf2;
                return zzf2;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    @WorkerThread
    private final String zzj() {
        if (this.zzg.isSuccessful()) {
            return (String) this.zzg.getResult();
        }
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    @WorkerThread
    private final boolean zzk(zzrc zzrc, long j2, long j3) {
        return this.zzk.get(zzrc) == null || j2 - ((Long) this.zzk.get(zzrc)).longValue() > TimeUnit.SECONDS.toMillis(30);
    }

    public final /* synthetic */ String zzb() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    public final /* synthetic */ void zzc(zzwe zzwe, zzrc zzrc, String str) {
        zzwe.zzb(zzrc);
        String zzd2 = zzwe.zzd();
        zzvb zzvb = new zzvb();
        zzvb.zzb(this.zzc);
        zzvb.zzc(this.zzd);
        zzvb.zzh(zzi());
        zzvb.zzg(Boolean.TRUE);
        zzvb.zzl(zzd2);
        zzvb.zzj(str);
        zzvb.zzi(this.zzh.isSuccessful() ? (String) this.zzh.getResult() : this.zzf.getMlSdkInstanceId());
        zzvb.zzd(10);
        zzvb.zzk(Integer.valueOf(this.zzj));
        zzwe.zzc(zzvb);
        this.zze.zza(zzwe);
    }

    public final void zzd(zzwe zzwe, zzrc zzrc) {
        zze(zzwe, zzrc, zzj());
    }

    public final void zze(zzwe zzwe, zzrc zzrc, String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new zzwj(this, zzwe, zzrc, str));
    }

    @WorkerThread
    public final void zzf(zzwo zzwo, zzrc zzrc) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzk(zzrc, elapsedRealtime, 30)) {
            this.zzk.put(zzrc, Long.valueOf(elapsedRealtime));
            zze(zzwo.zza(), zzrc, zzj());
        }
    }

    public final /* synthetic */ void zzg(zzrc zzrc, zzk zzk2) {
        zzcy zzcy = (zzcy) this.zzl.get(zzrc);
        if (zzcy != null) {
            for (Object next : zzcy.zzw()) {
                ArrayList arrayList = new ArrayList(zzcy.zze(next));
                Collections.sort(arrayList);
                zzqb zzqb = new zzqb();
                Iterator it = arrayList.iterator();
                long j2 = 0;
                while (it.hasNext()) {
                    j2 += ((Long) it.next()).longValue();
                }
                zzqb.zza(Long.valueOf(j2 / ((long) arrayList.size())));
                zzqb.zzc(Long.valueOf(zza(arrayList, 100.0d)));
                zzqb.zzf(Long.valueOf(zza(arrayList, 75.0d)));
                zzqb.zzd(Long.valueOf(zza(arrayList, 50.0d)));
                zzqb.zzb(Long.valueOf(zza(arrayList, 25.0d)));
                zzqb.zze(Long.valueOf(zza(arrayList, 0.0d)));
                zze(zzk2.zza(next, arrayList.size(), zzqb.zzg()), zzrc, zzj());
            }
            this.zzl.remove(zzrc);
        }
    }

    public final /* synthetic */ void zzh(zzrc zzrc, Object obj, long j2, zzk zzk2) {
        if (!this.zzl.containsKey(zzrc)) {
            this.zzl.put(zzrc, zzbw.zzz());
        }
        ((zzcy) this.zzl.get(zzrc)).zzt(obj, Long.valueOf(j2));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzk(zzrc, elapsedRealtime, 30)) {
            this.zzk.put(zzrc, Long.valueOf(elapsedRealtime));
            MLTaskExecutor.workerThreadExecutor().execute(new zzwk(this, zzrc, zzk2));
        }
    }
}
