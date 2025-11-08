package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.internal.zzhw;
import com.google.android.gms.measurement.internal.zzjg;
import com.google.android.gms.measurement.internal.zzjj;
import com.google.android.gms.measurement.internal.zzjk;
import com.google.android.gms.measurement.internal.zzok;
import java.util.List;
import java.util.Map;

final class zza extends AppMeasurement.zza {
    private final zzhw zza;
    private final zzjk zzb;

    public zza(@NonNull zzhw zzhw) {
        super();
        Preconditions.checkNotNull(zzhw);
        this.zza = zzhw;
        this.zzb = zzhw.zzp();
    }

    public final int zza(String str) {
        return zzjk.zza(str);
    }

    public final Boolean zzb() {
        return this.zzb.zzac();
    }

    public final Double zzc() {
        return this.zzb.zzad();
    }

    public final Integer zzd() {
        return this.zzb.zzae();
    }

    public final Long zze() {
        return this.zzb.zzaf();
    }

    public final String zzf() {
        return this.zzb.zzag();
    }

    public final String zzg() {
        return this.zzb.zzah();
    }

    public final String zzh() {
        return this.zzb.zzai();
    }

    public final String zzi() {
        return this.zzb.zzag();
    }

    public final String zzj() {
        return this.zzb.zzak();
    }

    public final long zza() {
        return this.zza.zzt().zzm();
    }

    public final void zzb(String str) {
        this.zza.zze().zza(str, this.zza.zzb().elapsedRealtime());
    }

    public final void zzc(String str) {
        this.zza.zze().zzb(str, this.zza.zzb().elapsedRealtime());
    }

    public final Object zza(int i3) {
        if (i3 == 0) {
            return zzj();
        }
        if (i3 == 1) {
            return zze();
        }
        if (i3 == 2) {
            return zzc();
        }
        if (i3 == 3) {
            return zzd();
        }
        if (i3 != 4) {
            return null;
        }
        return zzb();
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        this.zzb.zzb(str, str2, bundle);
    }

    public final void zzb(zzjj zzjj) {
        this.zzb.zzb(zzjj);
    }

    public final List<Bundle> zza(String str, String str2) {
        return this.zzb.zza(str, str2);
    }

    public final Map<String, Object> zza(boolean z2) {
        List<zzok> zza2 = this.zzb.zza(z2);
        ArrayMap arrayMap = new ArrayMap(zza2.size());
        for (zzok next : zza2) {
            Object zza3 = next.zza();
            if (zza3 != null) {
                arrayMap.put(next.zza, zza3);
            }
        }
        return arrayMap;
    }

    public final Map<String, Object> zza(String str, String str2, boolean z2) {
        return this.zzb.zza(str, str2, z2);
    }

    public final void zza(String str, String str2, Bundle bundle) {
        this.zza.zzp().zza(str, str2, bundle);
    }

    public final void zza(String str, String str2, Bundle bundle, long j2) {
        this.zzb.zza(str, str2, bundle, j2);
    }

    public final void zza(zzjj zzjj) {
        this.zzb.zza(zzjj);
    }

    public final void zza(Bundle bundle) {
        this.zzb.zzc(bundle);
    }

    public final void zza(zzjg zzjg) {
        this.zzb.zza(zzjg);
    }
}
