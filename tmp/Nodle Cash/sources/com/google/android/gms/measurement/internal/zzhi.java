package com.google.android.gms.measurement.internal;

import com.amplitude.api.Constants;
import com.amplitude.api.DeviceInfo;
import java.util.HashMap;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzhi implements Callable {
    private /* synthetic */ zzhg zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzhi(zzhg zzhg, String str) {
        this.zza = zzhg;
        this.zzb = str;
    }

    public final Object call() {
        zzhg zzhg = this.zza;
        String str = this.zzb;
        zzh zze = zzhg.zzh().zze(str);
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.AMP_TRACKING_OPTION_PLATFORM, DeviceInfo.OS_NAME);
        hashMap.put("package_name", str);
        hashMap.put("gmp_version", 102001L);
        if (zze != null) {
            String zzaf = zze.zzaf();
            if (zzaf != null) {
                hashMap.put("app_version", zzaf);
            }
            hashMap.put("app_version_int", Long.valueOf(zze.zze()));
            hashMap.put("dynamite_version", Long.valueOf(zze.zzo()));
        }
        return hashMap;
    }
}
