package com.google.android.gms.measurement.internal;

import A.a;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import org.apache.commons.text.StringSubstitutor;

public final class zzba {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final zzbc zzf;

    public zzba(zzhw zzhw, String str, String str2, String str3, long j2, long j3, Bundle bundle) {
        zzbc zzbc;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = TextUtils.isEmpty(str) ? null : str;
        this.zzd = j2;
        this.zze = j3;
        if (j3 != 0 && j3 > j2) {
            zzhw.zzj().zzu().zza("Event created with reverse previous/current timestamps. appId", zzgi.zza(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzbc = new zzbc(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzhw.zzj().zzg().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object zzb2 = zzhw.zzt().zzb(next, bundle2.get(next));
                    if (zzb2 == null) {
                        zzhw.zzj().zzu().zza("Param value can't be null", zzhw.zzk().zzb(next));
                        it.remove();
                    } else {
                        zzhw.zzt().zza(bundle2, next, zzb2);
                    }
                }
            }
            zzbc = new zzbc(bundle2);
        }
        this.zzf = zzbc;
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        return a.n(C0118y.l("Event{appId='", str, "', name='", str2, "', params="), String.valueOf(this.zzf), StringSubstitutor.DEFAULT_VAR_END);
    }

    public final zzba zza(zzhw zzhw, long j2) {
        return new zzba(zzhw, this.zzc, this.zza, this.zzb, this.zzd, j2, this.zzf);
    }

    private zzba(zzhw zzhw, String str, String str2, String str3, long j2, long j3, zzbc zzbc) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzbc);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = TextUtils.isEmpty(str) ? null : str;
        this.zzd = j2;
        this.zze = j3;
        if (j3 != 0 && j3 > j2) {
            zzhw.zzj().zzu().zza("Event created with reverse previous/current timestamps. appId, name", zzgi.zza(str2), zzgi.zza(str3));
        }
        this.zzf = zzbc;
    }
}
