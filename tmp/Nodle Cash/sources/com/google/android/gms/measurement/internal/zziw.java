package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzrl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

final class zziw implements Callable<List<zznk>> {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ Bundle zzb;
    private final /* synthetic */ zzia zzc;

    public zziw(zzia zzia, zzp zzp, Bundle bundle) {
        this.zza = zzp;
        this.zzb = bundle;
        this.zzc = zzia;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzc.zza.zzr();
        zznv zza2 = this.zzc.zza;
        zzp zzp = this.zza;
        Bundle bundle = this.zzb;
        zza2.zzl().zzt();
        if (!zzrl.zza() || !zza2.zze().zze(zzp.zza, zzbj.zzcg) || zzp.zza == null) {
            return new ArrayList();
        }
        if (bundle != null) {
            int[] intArray = bundle.getIntArray("uriSources");
            long[] longArray = bundle.getLongArray("uriTimestamps");
            if (intArray != null) {
                if (longArray == null || longArray.length != intArray.length) {
                    zza2.zzj().zzg().zza("Uri sources and timestamps do not match");
                } else {
                    for (int i3 = 0; i3 < intArray.length; i3++) {
                        zzam zzf = zza2.zzf();
                        String str = zzp.zza;
                        int i4 = intArray[i3];
                        long j2 = longArray[i3];
                        Preconditions.checkNotEmpty(str);
                        zzf.zzt();
                        zzf.zzal();
                        try {
                            int delete = zzf.e_().delete("trigger_uris", "app_id=? and source=? and timestamp_millis<=?", new String[]{str, String.valueOf(i4), String.valueOf(j2)});
                            zzgk zzp2 = zzf.zzj().zzp();
                            zzp2.zza("Pruned " + delete + " trigger URIs. appId, source, timestamp", str, Integer.valueOf(i4), Long.valueOf(j2));
                        } catch (SQLiteException e3) {
                            zzf.zzj().zzg().zza("Error pruning trigger URIs. appId", zzgi.zza(str), e3);
                        }
                    }
                }
            }
        }
        return zza2.zzf().zzk(zzp.zza);
    }
}
