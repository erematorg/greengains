package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzqb;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzx {
    private String zza;
    private boolean zzb;
    private zzgn.zzm zzc;
    /* access modifiers changed from: private */
    public BitSet zzd;
    private BitSet zze;
    private Map<Integer, Long> zzf;
    private Map<Integer, List<Long>> zzg;
    private final /* synthetic */ zzv zzh;

    @NonNull
    public final zzgn.zzd zza(int i3) {
        ArrayList arrayList;
        List list;
        zzgn.zzd.zza zzb2 = zzgn.zzd.zzb();
        zzb2.zza(i3);
        zzb2.zza(this.zzb);
        zzgn.zzm zzm = this.zzc;
        if (zzm != null) {
            zzb2.zza(zzm);
        }
        zzgn.zzm.zza zzd2 = zzgn.zzm.zze().zzb(zzol.zza(this.zzd)).zzd(zzol.zza(this.zze));
        if (this.zzf == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.zzf.size());
            for (Integer next : this.zzf.keySet()) {
                int intValue = next.intValue();
                Long l2 = this.zzf.get(next);
                if (l2 != null) {
                    arrayList.add((zzgn.zze) ((zzlc) zzgn.zze.zzc().zza(intValue).zza(l2.longValue()).zzai()));
                }
            }
        }
        if (arrayList != null) {
            zzd2.zza(arrayList);
        }
        if (this.zzg == null) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList2 = new ArrayList(this.zzg.size());
            for (Integer next2 : this.zzg.keySet()) {
                zzgn.zzn.zza zza2 = zzgn.zzn.zzc().zza(next2.intValue());
                List list2 = this.zzg.get(next2);
                if (list2 != null) {
                    Collections.sort(list2);
                    zza2.zza((Iterable<? extends Long>) list2);
                }
                arrayList2.add((zzgn.zzn) ((zzlc) zza2.zzai()));
            }
            list = arrayList2;
        }
        zzd2.zzc(list);
        zzb2.zza(zzd2);
        return (zzgn.zzd) ((zzlc) zzb2.zzai());
    }

    private zzx(zzv zzv, String str) {
        this.zzh = zzv;
        this.zza = str;
        this.zzb = true;
        this.zzd = new BitSet();
        this.zze = new BitSet();
        this.zzf = new ArrayMap();
        this.zzg = new ArrayMap();
    }

    private zzx(zzv zzv, String str, zzgn.zzm zzm, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.zzh = zzv;
        this.zza = str;
        this.zzd = bitSet;
        this.zze = bitSet2;
        this.zzf = map;
        this.zzg = new ArrayMap();
        if (map2 != null) {
            for (Integer next : map2.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(map2.get(next));
                this.zzg.put(next, arrayList);
            }
        }
        this.zzb = false;
        this.zzc = zzm;
    }

    public final void zza(@NonNull zzaa zzaa) {
        int zza2 = zzaa.zza();
        Boolean bool = zzaa.zzc;
        if (bool != null) {
            this.zze.set(zza2, bool.booleanValue());
        }
        Boolean bool2 = zzaa.zzd;
        if (bool2 != null) {
            this.zzd.set(zza2, bool2.booleanValue());
        }
        if (zzaa.zze != null) {
            Long l2 = this.zzf.get(Integer.valueOf(zza2));
            long longValue = zzaa.zze.longValue() / 1000;
            if (l2 == null || longValue > l2.longValue()) {
                this.zzf.put(Integer.valueOf(zza2), Long.valueOf(longValue));
            }
        }
        if (zzaa.zzf != null) {
            List list = this.zzg.get(Integer.valueOf(zza2));
            if (list == null) {
                list = new ArrayList();
                this.zzg.put(Integer.valueOf(zza2), list);
            }
            if (zzaa.zzc()) {
                list.clear();
            }
            if (zzqb.zza() && this.zzh.zze().zzf(this.zza, zzbj.zzbo) && zzaa.zzb()) {
                list.clear();
            }
            if (!zzqb.zza() || !this.zzh.zze().zzf(this.zza, zzbj.zzbo)) {
                list.add(Long.valueOf(zzaa.zzf.longValue() / 1000));
                return;
            }
            long longValue2 = zzaa.zzf.longValue() / 1000;
            if (!list.contains(Long.valueOf(longValue2))) {
                list.add(Long.valueOf(longValue2));
            }
        }
    }
}
