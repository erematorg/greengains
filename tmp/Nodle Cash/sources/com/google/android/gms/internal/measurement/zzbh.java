package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

final class zzbh implements Comparator<zzaq> {
    private final /* synthetic */ zzal zza;
    private final /* synthetic */ zzh zzb;

    public zzbh(zzal zzal, zzh zzh) {
        this.zza = zzal;
        this.zzb = zzh;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzaq zzaq = (zzaq) obj;
        zzaq zzaq2 = (zzaq) obj2;
        zzal zzal = this.zza;
        zzh zzh = this.zzb;
        if (zzaq instanceof zzax) {
            return !(zzaq2 instanceof zzax) ? 1 : 0;
        }
        if (zzaq2 instanceof zzax) {
            return -1;
        }
        if (zzal == null) {
            return zzaq.zzf().compareTo(zzaq2.zzf());
        }
        return (int) zzg.zza(zzal.zza(zzh, (List<zzaq>) Arrays.asList(new zzaq[]{zzaq, zzaq2})).zze().doubleValue());
    }
}
