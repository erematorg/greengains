package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class zzmj {
    @Nullable
    private static zzp zza;
    private static final zzr zzb = zzr.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzmc zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzmj(Context context, SharedPrefManager sharedPrefManager, zzmc zzmc, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzmc;
        zzmw.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzmg(this));
        MLTaskExecutor instance = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = instance.scheduleCallable(new zzmh(sharedPrefManager));
        zzr zzr = zzb;
        this.zzj = zzr.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzr.get(str)) : -1;
    }

    @NonNull
    private static synchronized zzp zzd() {
        synchronized (zzmj.class) {
            try {
                zzp zzp = zza;
                if (zzp != null) {
                    return zzp;
                }
                LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
                zzm zzm = new zzm();
                for (int i3 = 0; i3 < locales.size(); i3++) {
                    zzm.zzb(CommonUtils.languageTagFromLocale(locales.get(i3)));
                }
                zzp zzc2 = zzm.zzc();
                zza = zzc2;
                return zzc2;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    public final /* synthetic */ void zzb(zzmb zzmb, zziv zziv, String str) {
        zzmb.zza(zziv);
        String zzc2 = zzmb.zzc();
        zzky zzky = new zzky();
        zzky.zzb(this.zzc);
        zzky.zzc(this.zzd);
        zzky.zzh(zzd());
        zzky.zzg(Boolean.TRUE);
        zzky.zzl(zzc2);
        zzky.zzj(str);
        zzky.zzi(this.zzh.isSuccessful() ? (String) this.zzh.getResult() : this.zzf.getMlSdkInstanceId());
        zzky.zzd(10);
        zzky.zzk(Integer.valueOf(this.zzj));
        zzmb.zzb(zzky);
        this.zze.zza(zzmb);
    }

    @WorkerThread
    public final void zzc(zzmt zzmt, zziv zziv) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzk.get(zziv) == null || elapsedRealtime - ((Long) this.zzk.get(zziv)).longValue() > TimeUnit.SECONDS.toMillis(30)) {
            this.zzk.put(zziv, Long.valueOf(elapsedRealtime));
            int i3 = zzmt.zza;
            int i4 = zzmt.zzb;
            int i5 = zzmt.zzc;
            int i6 = zzmt.zzd;
            int i7 = zzmt.zze;
            long j2 = zzmt.zzf;
            int i8 = zzmt.zzg;
            zzin zzin = new zzin();
            zzin.zzd(i3 != -1 ? i3 != 35 ? i3 != 842094169 ? i3 != 16 ? i3 != 17 ? zzii.UNKNOWN_FORMAT : zzii.NV21 : zzii.NV16 : zzii.YV12 : zzii.YUV_420_888 : zzii.BITMAP);
            zzin.zzf(i4 != 1 ? i4 != 2 ? i4 != 3 ? i4 != 4 ? zzio.ANDROID_MEDIA_IMAGE : zzio.FILEPATH : zzio.BYTEBUFFER : zzio.BYTEARRAY : zzio.BITMAP);
            zzin.zzc(Integer.valueOf(i5));
            zzin.zze(Integer.valueOf(i6));
            zzin.zzg(Integer.valueOf(i7));
            zzin.zzb(Long.valueOf(j2));
            zzin.zzh(Integer.valueOf(i8));
            zziq zzj2 = zzin.zzj();
            zziw zziw = new zziw();
            zziw.zzd(zzj2);
            MLTaskExecutor.workerThreadExecutor().execute(new zzmi(this, zzmk.zze(zziw), zziv, this.zzg.isSuccessful() ? (String) this.zzg.getResult() : LibraryVersion.getInstance().getVersion(this.zzi)));
        }
    }
}
